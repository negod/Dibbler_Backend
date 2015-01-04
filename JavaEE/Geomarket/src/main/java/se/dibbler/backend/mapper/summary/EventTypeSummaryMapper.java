/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper.summary;

import se.dibbler.backend.dto.summary.NameSummaryDto;
import se.dibbler.backend.entity.EventTypeText;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class EventTypeSummaryMapper extends BaseMapper<NameSummaryDto, EventTypeText> {

    private static final EventTypeSummaryMapper INSTANCE = new EventTypeSummaryMapper();

    public static EventTypeSummaryMapper getInstance() {
        return INSTANCE;
    }

    public EventTypeSummaryMapper() {
        super(NameSummaryDto.class, EventTypeText.class);
    }

}
