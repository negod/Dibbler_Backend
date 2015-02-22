/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.constants.TextType;
import se.dibbler.backend.dao.EventTypeDao;
import se.dibbler.backend.dao.EventTypeTextDao;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dto.EventTypeDto;
import se.dibbler.backend.dto.summary.NameSummaryDto;
import se.dibbler.backend.entity.EventType;
import se.dibbler.backend.entity.EventTypeText;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.summary.EventTypeSummaryMapper;

/**
 *
 * @author Joakim
 */
@Stateless
public class EventTypeDaoBean extends BaseDaoBean<EventType, EventTypeDto> implements EventTypeDao<EventType, EventTypeDto> {

    @EJB
    LanguageDao languageDao;
    @EJB
    EventTypeTextDao languageText;

    public EventTypeDaoBean() {
        super(EventType.class);
    }

    @Override
    public Response<String> addLanguage(String eventTypeId, String name, String language) {

        Response<Language> languageEntity = languageDao.getByExtId(language);
        if (languageEntity.hasErrors) {
            return Response.error(languageEntity.getError());
        }

        Response<EventType> eventType = super.getByExtId(eventTypeId);
        if (eventType.hasErrors) {
            return Response.error(eventType.getError());
        }

        try {
            //TODO check that language does not exist in category
            EventTypeText languageTextEntity = new EventTypeText();
            languageTextEntity.setLanguage(languageEntity.getData());
            languageTextEntity.setValue(name);
            languageTextEntity.setTextType(TextType.NAME);
            languageTextEntity.setEventType(eventType.getData());
            return languageText.create(languageTextEntity);

        } catch (Exception e) {
            getLogger().error("[ Error when adding language to EventType ]", e);
            return Response.error(DaoError.EVENTTYPE_ADD_LANGUAGE);
        }
    }

    @Override
    public Response<List<NameSummaryDto>> getEventTypesByLanguage(String languageId) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("languageExtId", languageId);
            Response<List<EventTypeText>> eventTypeTexts = languageText.getListByNamedQuery(DibblerNamedQueries.EVENTTYPE_FINDBY_LANGUAGE_EXTID, params);

            if (eventTypeTexts.hasErrors) {
                return Response.error(eventTypeTexts.getError());
            }

            return EventTypeSummaryMapper.getInstance().mapToDtoList(eventTypeTexts.getData());

        } catch (Exception e) {
            getLogger().error(DaoError.CATEGORY_GET_BY_LANGUAGE.getErrorText(), e.getMessage());
            return Response.error(DaoError.CATEGORY_GET_BY_LANGUAGE);
        }
    }

    @Override
    public Response create(EventTypeDto dto) {

        Response<Language> languageEntity = languageDao.getByExtId(dto.getDefaultLanguage());
        if (languageEntity.hasErrors) {
            return Response.error(languageEntity.getError());
        }

        try {
            EventType eventType = new EventType();
            eventType.setDescription(dto.getDescription());
            eventType.setDefaultLanguage(languageEntity.getData());

            List<EventTypeText> categoryNames = new ArrayList<>();
            EventTypeText categoryName = new EventTypeText();
            categoryName.setLanguage(languageEntity.getData());
            categoryName.setValue(dto.getDefaultName());
            categoryName.setEventType(eventType);
            categoryName.setTextType(TextType.NAME);
            categoryNames.add(categoryName);
            eventType.setEventTexts(categoryNames);

            return super.create(eventType);
        } catch (Exception e) {
            super.getLogger().error("[ Error when creating EventType ]", e);
            return Response.error(DaoError.EVENTTYPE_CREATE);
        }

    }

    @Override
    public Response<String> updateEventTypeDescription(String description, String eventTypeNameId) {
        Response<EventType> eventType = super.getByExtId(eventTypeNameId);
        if (eventType.hasErrors) {
            return Response.error(eventType.getError());
        }

        eventType.getData().setDescription(description);

        return Response.success(eventType.getData().getExtId());
    }

    @Override
    public Response<String> update(EventTypeDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
