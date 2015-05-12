/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.dto.create.PublishEventCreateDto;
import se.dibbler.backend.dto.full.EventDtoFull;
import se.dibbler.backend.dto.languagesupport.LanguageTextDto;
import se.dibbler.backend.dto.summary.EventSummaryDto;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDao;
import se.dibbler.generic.dto.BaseDto;
import se.dibbler.generic.entity.BaseEntity;

/**
 *
 * @author Joakim
 * @param <E>
 * @param <D>
 */
@Local
public interface EventDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addEventText(LanguageTextDto eventText, String eventId);

    //public Response<List<EventSummaryDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, String languageId);
    public Response<List<EventSummaryDto>> getPublishedEventsByLocation(Double longitude, Double latitude, Double radius, String languageId);

    public Response<String> publishEvent(PublishEventCreateDto data);

    public Response<List<EventDtoFull>> getEventByCompany(String companyId);

    public Response<EventDtoFull> getEventById(String eventId);

}
