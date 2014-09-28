/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.LocationDao;
import se.geomarket.backend.geomarket.entity.Location;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakim
 */
@Stateless
public class LocationDaoBean extends BaseDaoBean implements LocationDao {

    public LocationDaoBean() {
        super(Location.class);
    }

}
