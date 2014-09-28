/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.superclass.Name;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class EventTypeMapper extends BaseMapper<EventTypeDto, EventType> {

    private static final EventTypeMapper INSTANCE = new EventTypeMapper();

    private EventTypeMapper() {
    }

    public static EventTypeMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public EventType mapFromDtoToEntity(EventTypeDto dto) {
        return (EventType) BaseNameMapper.getInstance().mapFromDtoToEntity(dto);
    }

    @Override
    public EventTypeDto mapFromEntityToDto(EventType entity) {
        EventTypeDto dto = new EventTypeDto();
        dto.setId(entity.getExtId());
        dto.setDefaultName(entity.getDefaultName());
        dto.setDescription(entity.getDescription());
        for (Name name : entity.getNames()) {
            dto.getNames().add(NameMapper.getInstance().mapFromEntityToDto(name));
        }
        return dto;
    }

    @Override
    public void updateEntityFromDto(EventType entity, EventTypeDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
