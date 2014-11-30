/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao;

import java.util.List;
import se.geomarket.backend.geomarket.dto.EventTextDto;
import se.geomarket.backend.geomarket.dto.summary.EventSummaryDto;
import se.geomarket.backend.geomarket.generics.BaseDao;
import se.geomarket.backend.geomarket.generics.BaseDto;
import se.geomarket.backend.geomarket.generics.BaseEntity;
import se.geomarket.backend.geomarket.generics.MethodResponse;

/**
 *
 * @author Joakim
 * @param <E>
 * @param <D>
 */
public interface EventDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public MethodResponse<String> addEventText(EventTextDto eventText, String eventId);

    public MethodResponse<List<EventSummaryDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, String languageId);

}
