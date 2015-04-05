/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper.summary;

import se.dibbler.backend.dto.summary.PublishedEventSummaryDto;
import se.dibbler.backend.entity.PublishedEvent;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class PublishedEventSummaryMapper extends BaseMapper<PublishedEventSummaryDto, PublishedEvent> {

    private static final PublishedEventSummaryMapper INSTANCE = new PublishedEventSummaryMapper();

    public static PublishedEventSummaryMapper getInstance() {
        return INSTANCE;
    }

    public PublishedEventSummaryMapper() {
        super(PublishedEventSummaryDto.class, PublishedEvent.class);
    }

}
