/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.EventTextDao;
import se.geomarket.backend.geomarket.dto.EventTextDto;
import se.geomarket.backend.geomarket.entity.EventText;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.DaoResponse;
import se.geomarket.backend.geomarket.mapper.EventTextMapper;

/**
 * 
 * @author Joakim
 */
@Stateless
public class EventTextDaoBean extends BaseDaoBean<EventText, EventTextDto> implements EventTextDao<EventText, EventTextDto>  {

    public EventTextDaoBean() {
        super(EventText.class);
    }

    @Override
    public DaoResponse create(EventTextDto dto) {
        return super.create(EventTextMapper.getInstance().mapFromDtoToEntity(dto));
    }

   

}
