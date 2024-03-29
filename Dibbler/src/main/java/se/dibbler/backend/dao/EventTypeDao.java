/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.dto.summary.NameSummaryDto;
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
public interface EventTypeDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addLanguage(String eventTypeId, String name, String language);

    public Response<List<NameSummaryDto>> getEventTypesByLanguage(String languageId);

    public Response<String> updateEventTypeDescription(String description, String eventTypeNameId);

}
