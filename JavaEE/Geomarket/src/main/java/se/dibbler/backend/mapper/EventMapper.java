/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.EventDto;
import se.dibbler.backend.dto.summary.EventTextSummaryDto;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.error.MapperError;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim
 */
public class EventMapper extends BaseMapper<EventDto, Event> {

    private static final EventMapper INSTANCE = new EventMapper();

    public static EventMapper getInstance() {
        return INSTANCE;
    }

    public EventMapper() {
        super(EventDto.class, Event.class);
    }

}
