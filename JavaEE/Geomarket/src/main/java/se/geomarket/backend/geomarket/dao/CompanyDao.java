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
import se.geomarket.backend.geomarket.generics.BaseDto;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 * 
 */
@Local
public interface CompanyDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public List<Company> getCompanyByLocation(Double longitude, Double latitude, Double radius, Unit unit);

}
