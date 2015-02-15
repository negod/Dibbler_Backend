/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.full.EventDtoFull;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventFullMapper extends BaseMapper<EventDtoFull, Event> {

    private static final EventFullMapper INSTANCE = new EventFullMapper();

    public static EventFullMapper getInstance() {
        return INSTANCE;
    }

    public EventFullMapper() {
        super(EventDtoFull.class, Event.class);
    }

}
