/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.constants.DibblerNamedQueries;
import se.geomarket.backend.geomarket.constants.TextType;
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.EventTypeTextDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.dto.languagesupport.EventTypeTextDto;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.EventTypeText;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;
import se.geomarket.backend.geomarket.mapper.EventTypeTextMapper;

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
            EventTypeText languageText = new EventTypeText();
            languageText.setLanguage(languageEntity.getData());
            languageText.setValue(name);
            languageText.setTextType(TextType.NAME);
            languageText.setEventType(eventType.getData());
            eventType.getData().getEventTexts().add(languageText);

            return Response.success(eventType.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ Error when adding language to EventType ]", e);
            return Response.error(DaoError.EVENTTYPE_ADD_LANGUAGE);
        }
    }

    @Override
    public Response<List<EventTypeTextDto>> getEventTypesByLanguage(String languageId) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("languageExtId", languageId);
            Response<List<EventTypeText>> eventTypeTexts = languageText.getListByNamedQuery(DibblerNamedQueries.EVENTTYPE_FINDBY_LANGUAGE_EXTID, params);

            if (eventTypeTexts.hasErrors) {
                return Response.error(eventTypeTexts.getError());
            }

            return EventTypeTextMapper.getInstance().mapToDtoList(eventTypeTexts.getData());

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

}
