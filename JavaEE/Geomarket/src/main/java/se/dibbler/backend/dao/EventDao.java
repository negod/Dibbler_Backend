/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.dto.EventDto;
import se.dibbler.backend.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.dto.summary.EventSummaryDto;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.generics.BaseDao;
import se.dibbler.backend.generics.BaseDto;
import se.dibbler.backend.generics.BaseEntity;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim
 * @param <E>
 * @param <D>
 */

@Local
public interface EventDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addEventText(LanguageTextDto eventText, String eventId);

    public Response<List<EventSummaryDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, String languageId);
    
    public Response<List<EventSummaryDto>> getPublishedEventsByLocation(Double longitude, Double latitude, Double radius, String languageId);

    public Response<String> publishEvent(Event event, String languageId);
    
    public Response<List<EventDto>> getEventByCompany(String companyId);
    
    

}
