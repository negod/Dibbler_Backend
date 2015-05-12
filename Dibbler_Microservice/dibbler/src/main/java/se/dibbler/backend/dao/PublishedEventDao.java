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
import se.dibbler.backend.entity.PublishedEvent;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDao;
import se.dibbler.generic.dto.BaseDto;
import se.dibbler.generic.entity.BaseEntity;

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

}
