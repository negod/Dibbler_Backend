/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.mapper;

import se.geomarket.backend.geomarket.dto.languagesupport.EventTypeTextDto;
import se.geomarket.backend.geomarket.entity.EventTypeText;
import se.geomarket.backend.geomarket.generics.BaseMapper;

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
