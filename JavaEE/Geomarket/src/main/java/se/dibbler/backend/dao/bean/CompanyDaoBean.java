/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dto.CompanyDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.CompanyMapper;

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
    public Response<List<Company>> getCompanyByLocation(Double longitude, Double latitude, Double radius, Unit unit) {
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
            super.getLogger().error("[ Error when getting company by location ] [LONG: {}] [LAT: {}] [RADIUS: {}]", longitude, latitude, radius);
            return Response.error(DaoError.COMPANY_BY_LOCATION);
        }
    }

    private Response<List<Company>> extractCompanies(List<Location> locations) {
        List<Company> companies = new ArrayList<>();
        for (Location loc : locations) {
            if (loc.getCompany() != null) {
                companies.add(loc.getCompany());
            } else {
                super.getLogger().debug("[ (Extract companies) Failed to get Company from location with ID: {} }", loc.getExtId());
            }
        }
        return Response.success(companies);
    }

    @Override
    public Response create(CompanyDto dto) {
        Response<Company> entity = CompanyMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return super.create(entity.getData());
    }

}
