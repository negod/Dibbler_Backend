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

    public static EventTextMapper getInstance() {
        return INSTANCE;
    }

    public EventTextMapper() {
        super(EventTextDto.class, EventText.class);
    }

}
