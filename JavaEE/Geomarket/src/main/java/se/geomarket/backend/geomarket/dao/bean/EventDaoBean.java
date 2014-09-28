/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.EventDao;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakim
 */

@Stateless
public class EventDaoBean extends BaseDaoBean implements EventDao {

    public EventDaoBean() {
        super(Event.class);
    }

}
