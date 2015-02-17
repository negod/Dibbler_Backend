/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.EventTypeDto;
import se.dibbler.backend.entity.EventType;
import se.dibbler.backend.generics.BaseMapper;

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