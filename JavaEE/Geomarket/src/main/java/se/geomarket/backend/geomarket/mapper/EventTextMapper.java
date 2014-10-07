/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.EventTextDto;
import se.geomarket.backend.geomarket.entity.EventText;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventTextMapper extends BaseMapper<EventTextDto, EventText> {

    private static final EventTextMapper INSTANCE = new EventTextMapper();

    private EventTextMapper() {
    }

    public static EventTextMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public EventText mapFromDtoToEntity(EventTextDto dto) {
        EventText entity = new EventText();
        entity.setBody(dto.getBody());
        entity.setHeading(dto.getHeading());
        return entity;
    }

    @Override
    public EventTextDto mapFromEntityToDto(EventText entity) {
        EventTextDto dto = new EventTextDto();
        dto.setBody(entity.getBody());
        dto.setId(entity.getExtId());
        dto.setHeading(entity.getHeading());
        dto.setLanguageId(entity.getLanguage().getExtId());
        return dto;
    }

    @Override
    public void updateEntityFromDto(EventText entity, EventTextDto dto) {
        entity.setBody(dto.getBody());
        entity.setHeading(dto.getHeading());
    }

}
