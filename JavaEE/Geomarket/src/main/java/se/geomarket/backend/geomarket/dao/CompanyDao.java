/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao;

import java.util.List;
import javax.ejb.Local;
import org.hibernate.search.query.dsl.Unit;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.generics.BaseDao;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E>
 */
@Local
public interface CompanyDao<E extends BaseEntity> extends BaseDao<E> {

    public List<Company> getCompanyByLocation(Double longitude, Double latitude, Double radius, Unit unit);

}
