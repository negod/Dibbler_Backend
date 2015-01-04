/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.PublishedEventDto;
import se.dibbler.backend.entity.PublishedEvent;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class PublishedEventMapper extends BaseMapper<PublishedEventDto, PublishedEvent> {

    private static final PublishedEventMapper INSTANCE = new PublishedEventMapper();

    public static PublishedEventMapper getInstance() {
        return INSTANCE;
    }

    public PublishedEventMapper() {
        super(PublishedEventDto.class, PublishedEvent.class);
    }
}
