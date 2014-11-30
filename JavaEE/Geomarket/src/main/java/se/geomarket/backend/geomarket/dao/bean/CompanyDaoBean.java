/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Location;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;
import se.geomarket.backend.geomarket.mapper.CompanyMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @inheritDoc
 */
@Stateless
public class CompanyDaoBean extends BaseDaoBean<Company, CompanyDto> implements CompanyDao<Company, CompanyDto> {

    public CompanyDaoBean() {
        super(Company.class);
    }

    @Override
    public MethodResponse<List<Company>> getCompanyByLocation(Double longitude, Double latitude, Double radius, Unit unit) {
        try {
            Session session = (Session) super.getEntityManager().getDelegate();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Location.class).get();
            Query query = builder.spatial().onDefaultCoordinates()
                    .within(radius, unit)
                    .ofLatitude(latitude)
                    .andLongitude(longitude)
                    .createQuery();
            org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, Location.class);
            List<Location> results = hibQuery.list();
            return extractCompanies(results);
        } catch (Exception e) {
            super.getLogger().error("[ Error when getting company by location] [LONG: {}] [LAT: {}] [RADIUS: {}]", longitude, latitude, radius);
            return MethodResponse.error(DaoError.COMPANY_BY_LOCATION);
        }
    }

    private MethodResponse<List<Company>> extractCompanies(List<Location> locations) {
        List<Company> companies = new ArrayList<>();
        for (Location loc : locations) {
            if (loc.getCompany() != null) {
                companies.add(loc.getCompany());
            } else {
                super.getLogger().debug("[ (Extract companies) Failed to get Company from location with ID: {} }", loc.getExtId());
            }
        }
        return MethodResponse.success(companies);
    }

    @Override
    public MethodResponse create(CompanyDto dto) {
        MethodResponse<Company> entity = CompanyMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return MethodResponse.error(entity.getErrorCode());
        }
        return super.create(entity.getData());
    }

}
