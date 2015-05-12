/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import org.hibernate.search.query.dsl.Unit;
import se.dibbler.backend.dto.PublishedEventDto;
import se.dibbler.backend.dto.create.PublishEventCreateDto;
import se.dibbler.backend.dto.summary.PublishedEventSummaryDto;
import se.dibbler.backend.entity.Event;
import se.dibbler.backend.entity.PublishedEvent;
import se.dibbler.backend.generics.BaseDao;
import se.dibbler.backend.generics.BaseDto;
import se.dibbler.backend.generics.BaseEntity;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 */
@Local
public interface PublishedEventDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<List<PublishedEventDto>> getEventsByLocation(Double longitude, Double latitude, Double radius, Unit unit);

    public Response<String> publishEvent(PublishEventCreateDto dto);

    public Response<List<PublishedEvent>> getPublishedEventsByExpiredDate(Date expired);

    public Response<PublishedEventDto> updatePublishedEvent(PublishedEventSummaryDto event);

    public Response<PublishedEventDto> getPublishedEventByCompany(String companyId);

}
