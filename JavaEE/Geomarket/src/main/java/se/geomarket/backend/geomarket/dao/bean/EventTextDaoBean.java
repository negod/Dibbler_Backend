/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.EventTextDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.entity.EventText;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;

/**
 *
 * @author Joakim
 */
@Stateless
public class EventTextDaoBean extends BaseDaoBean implements EventTextDao {

    public EventTextDaoBean() {
        super(EventText.class);
    }

}
