/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.languagesupport.EventTypeTextDto;
import se.dibbler.backend.entity.EventTypeText;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventTypeTextMapper extends BaseMapper<EventTypeTextDto, EventTypeText> {

    private static final EventTypeTextMapper INSTANCE = new EventTypeTextMapper();

    public static EventTypeTextMapper getInstance() {
        return INSTANCE;
    }

    public EventTypeTextMapper() {
        super(EventTypeTextDto.class, EventTypeText.class);
    }

}
