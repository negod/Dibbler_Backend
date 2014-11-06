/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper.summary;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import se.geomarket.backend.geomarket.dto.summary.EventSummaryDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Event;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.mapper.EventMapper;
import se.geomarket.backend.geomarket.mapper.PointMapper;

/**
 *
 * @author Joakim
 */
public class EventSummaryMapper extends BaseMapper<EventSummaryDto, Company> {

    private static final EventSummaryMapper INSTANCE = new EventSummaryMapper();

    public static EventSummaryMapper getInstance() {
        return INSTANCE;
    }

    public EventSummaryMapper() {
        super(EventSummaryDto.class, Company.class);
    }

    public List<EventSummaryDto> extractEvents(Company entity, String languageId) {
        List<EventSummaryDto> dtoList = new ArrayList<>();
        for (Event event : entity.getEvents()) {
            if (checkDateIsOk(event)) {
                EventSummaryDto summary = new EventSummaryDto();
                summary.setCategoryId(event.getCategory().getExtId());
                summary.setEventTypeId(event.getEventType().getExtId());
                summary.setExpires(event.getEndDate());
                summary.setId(event.getExtId());
                summary.setCompany(CompanySummaryMapper.getInstance().mapFromEntityToDto(entity));
                summary.setLocation(PointMapper.getInstance().mapFromEntityToDto(entity.getLocation()));
                summary.setEventText(EventMapper.getInstance().getEventText(event, languageId));
                dtoList.add(summary);
            }
        }
        return dtoList;
    }

    private boolean checkDateIsOk(Event event) {
        if (event.getEndDate().getTime() > DateTime.now().getMillis()) {
            if (event.getStartDate().getTime() < DateTime.now().getMillis()) {
                return true;
            }
        }
        return false;
    }

}
