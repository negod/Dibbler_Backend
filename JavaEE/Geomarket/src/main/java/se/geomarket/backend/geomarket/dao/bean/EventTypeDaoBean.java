/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.constants.DaoError;
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.EventTypeNameDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.dto.languagesupport.NameDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.EventTypeName;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;

/**
 *
 * @author Joakim
 */
@Stateless
public class EventTypeDaoBean extends BaseDaoBean<EventType, EventTypeDto> implements EventTypeDao<EventType, EventTypeDto> {

    @EJB
    LanguageDao languageDao;
    @EJB
    EventTypeNameDao eventTypeNameDao;

    public EventTypeDaoBean() {
        super(EventType.class);
    }

    @Override
    public MethodResponse<String> addLanguage(String eventTypeId, String name, String language) {
        MethodResponse<Language> languageEntity = languageDao.getByExtId(language);
        if (languageEntity.hasErrors) {
            return MethodResponse.error(languageEntity.getErrorCode());
        }

        MethodResponse<EventType> eventType = super.getByExtId(eventTypeId);
        if (eventType.hasErrors) {
            return MethodResponse.error(eventType.getErrorCode());
        }

        try {
            //TODO check that language does not exist in category
            EventTypeName eventTypeName = new EventTypeName();
            eventTypeName.setLanguage(languageEntity.getData());
            eventTypeName.setName(name);
            eventTypeName.setEventType(eventType.getData());
            eventType.getData().getNames().add(eventTypeName);

            return MethodResponse.success(eventType.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ Error when adding language to EventType ]", e);
            return MethodResponse.error(DaoError.EVENTTYPE_ADD_LANGUAGE);
        }
    }

    @Override
    public MethodResponse<List<NameSummaryDto>> getEventTypesByLanguage(String languageId) {
        MethodResponse<List<EventType>> allCategories = super.getAll();
        if (allCategories.hasErrors) {
            return MethodResponse.error(allCategories.getErrorCode());
        }

        try {
            List<NameSummaryDto> categoryNames = new ArrayList<>();
            for (EventType category : allCategories.getData()) {
                MethodResponse<String> name = getNameByLanguage(category.getNames(), languageId);
                NameSummaryDto dto = new NameSummaryDto();
                if (name.hasErrors) {
                    dto.setName(category.getDefaultName());
                } else {
                    dto.setName(name.getData());
                }
                dto.setId(category.getExtId());
                categoryNames.add(dto);
            }
            return MethodResponse.success(categoryNames);
        } catch (Exception e) {
            super.getLogger().error("[ Error when getting eventtypes by language ] [ LanguageId: {} ]", languageId, e);
            return MethodResponse.error(DaoError.EVENTTYPE_BY_LANGUAGE);
        }
    }

    private MethodResponse<String> getNameByLanguage(List<EventTypeName> categories, String languageId) {
        for (EventTypeName cat : categories) {
            if (cat.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                MethodResponse.success(cat.getName());
            }
        }
        return MethodResponse.error(DaoError.EVENTTYPE_NAME_BY_LANGUAGE);
    }

    @Override
    public MethodResponse create(EventTypeDto dto) {
        MethodResponse<Language> languageEntity = languageDao.getByExtId(dto.getDefaultLanguage());
        if (languageEntity.hasErrors) {
            return MethodResponse.error(languageEntity.getErrorCode());
        }

        try {
            EventType eventType = new EventType();
            eventType.setDescription(dto.getDescription());
            eventType.setDefaultName(dto.getDefaultName());

            List<EventTypeName> eventTypeNames = new ArrayList<>();
            EventTypeName categoryname = new EventTypeName();
            categoryname.setLanguage(languageEntity.getData());
            categoryname.setName(dto.getDefaultName());
            categoryname.setEventType(eventType);
            eventTypeNames.add(categoryname);
            eventType.setNames(eventTypeNames);

            return super.create(eventType);
        } catch (Exception e) {
            super.getLogger().error("[ Error when creating EventType ]", e);
            return MethodResponse.error(DaoError.EVENTTYPE_CREATE);
        }

    }

    @Override
    public MethodResponse<String> updateEventTypeName(NameDto name, String categoryNameId) {
        MethodResponse<EventTypeName> eventTypeName = eventTypeNameDao.getByExtId(categoryNameId);
        if (eventTypeName.hasErrors) {
            return MethodResponse.error(eventTypeName.getErrorCode());
        }

        //TODO FIX THIS WITH LANGUAGE SUPPORT!
        /*MethodResponse<Name> updatedName = NameMapper.getInstance().updateEntityFromDto(eventTypeName, name);
         if (updatedName.hasErrors) {
         return MethodResponse.error(updatedName.getErrorCode());
         }*/
        return eventTypeNameDao.update(eventTypeName.getData());
    }

}
