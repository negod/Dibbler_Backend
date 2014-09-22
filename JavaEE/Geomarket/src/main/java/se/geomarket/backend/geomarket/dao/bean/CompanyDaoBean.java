/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.List;
import javax.ejb.Stateless;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Location;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class CompanyDaoBean extends BaseDaoBean implements CompanyDao {

    public CompanyDaoBean() {
        super(Company.class);
    }

    @Override
    public List<Location> getCompanyByLocation(Double longitude, Double latitude, Double radius, Unit unit) {
        Session session = (Session) super.getEntityManager().getDelegate();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Location.class).get();
        Query query = builder.spatial().onDefaultCoordinates()
                .within(radius, unit)
                .ofLatitude(latitude)
                .andLongitude(longitude)
                .createQuery();
        org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query,
                Location.class);
        List<Location> results = hibQuery.list();
        return results;


        /*Coordinates coordinates = Point.fromDegrees(24d, 31.5d);
         Query query = builder
         .spatial()
         .onCoordinates( "location" )
         .within( 51, Unit.KM )
         .ofCoordinates( coordinates )
         .createQuery();

         List results = fullTextSession.createFullTextQuery( query, POI.class ).list();*/
    }

}
