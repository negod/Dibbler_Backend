/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.EventTypeNameDao;
import se.geomarket.backend.geomarket.dto.languagesupport.BaseNameDto;
import se.geomarket.backend.geomarket.dto.languagesupport.NameDto;
import se.geomarket.backend.geomarket.entity.EventTypeName;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.DaoResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */

@Stateless
public class EventTypeNameDaoBean extends BaseDaoBean<EventTypeName, BaseNameDto> implements EventTypeNameDao<EventTypeName, BaseNameDto> {

    public EventTypeNameDaoBean() {
        super(EventTypeName.class);
    }

    @Override
    public DaoResponse create(BaseNameDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateEventTypeName(NameDto name, String eventTypeNameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
