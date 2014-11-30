/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.superclass.Name;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakim
 */
public class EventTypeMapper extends BaseMapper<EventTypeDto, EventType> {

    private static final EventTypeMapper INSTANCE = new EventTypeMapper();

    public static EventTypeMapper getInstance() {
        return INSTANCE;
    }

    public EventTypeMapper() {
        super(EventTypeDto.class, EventType.class);
    }
}
