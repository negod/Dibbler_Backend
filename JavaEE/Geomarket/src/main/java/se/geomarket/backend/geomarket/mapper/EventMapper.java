/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.EventDto;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class EventMapper extends BaseMapper<EventDto, Event> {
    
    private static final EventMapper INSTANCE = new EventMapper();
    
    private EventMapper() {
    }
    
    public static EventMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Event mapFromDtoToEntity(EventDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Event destObject = mapper.map(dto, Event.class);
        return destObject;
    }
    
    @Override
    public EventDto mapFromEntityToDto(Event entity) {
        EventDto event = new EventDto();
        event.setId(entity.getExtId());
        event.setCategoryId(entity.getCategory().getExtId());
        event.setCompanyId(entity.getCompany().getExtId());
        event.setEndDate(entity.getEndDate());
        event.setEventHeader(entity.getEventText().getHeading());
        event.setEventTextBody(entity.getEventText().getBody());
        event.setEventTypeId(entity.getEventType().getExtId());
        event.setMaxredeem(entity.getMaxRedeem());
        event.setStartDate(entity.getStartDate());
        return event;
    }
    
    @Override
    public void updateEntityFromDto(Event entity, EventDto dto) {
    }
    
}
