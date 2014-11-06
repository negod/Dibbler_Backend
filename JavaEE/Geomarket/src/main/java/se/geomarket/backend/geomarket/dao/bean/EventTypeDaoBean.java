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
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.EventTypeDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.CategoryName;
import se.geomarket.backend.geomarket.entity.EventType;
import se.geomarket.backend.geomarket.entity.EventTypeName;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.DaoResponse;

/**
 *
 * @author Joakim
 */
@Stateless
public class EventTypeDaoBean extends BaseDaoBean<EventType, EventTypeDto> implements EventTypeDao<EventType, EventTypeDto> {

    @EJB
    LanguageDao languageDao;

    public EventTypeDaoBean() {
        super(EventType.class);
    }

    @Override
    public String addLanguage(String categoryId, String name, String language) {
        Language languageEntity = (Language) languageDao.getByExtId(language);
        EventType eventType = (EventType) super.getByExtId(categoryId);

        //TODO check that language does not exist in category
        EventTypeName eventTypeName = new EventTypeName();
        eventTypeName.setLanguage(languageEntity);
        eventTypeName.setName(name);
        eventTypeName.setEventType(eventType);
        eventType.getNames().add(eventTypeName);

        return eventType.getExtId();
    }

    @Override
    public List getEventTypesByLanguage(String languageId) {
        List<EventType> allCategories = super.getAll();
        List<NameSummaryDto> categoryNames = new ArrayList<>();

        for (EventType category : allCategories) {
            String name = getNameByLanguage(category.getNames(), languageId);
            NameSummaryDto dto = new NameSummaryDto();
            if (name.isEmpty()) {
                dto.setName(category.getDefaultName());

            } else {
                dto.setName(name);
            }

            dto.setId(category.getExtId());
            categoryNames.add(dto);
        }

        return categoryNames;
    }

    private String getNameByLanguage(List<EventTypeName> categories, String languageId) {
        for (EventTypeName cat : categories) {
            if (cat.getLanguage().getExtId().equalsIgnoreCase(languageId)) {
                return cat.getName();
            }
        }
        return "";
    }

    @Override
    public DaoResponse create(EventTypeDto dto) {
        Language languageEntity = (Language) languageDao.getByExtId(dto.getDefaultLanguage());

        EventType eventType = new EventType();
        eventType.setDescription(dto.getDescription());
        eventType.setDefaultName(dto.getDefaultName());

        List<EventTypeName> eventTypeNames = new ArrayList<>();
        EventTypeName categoryname = new EventTypeName();
        categoryname.setLanguage(languageEntity);
        categoryname.setName(dto.getDefaultName());
        categoryname.setEventType(eventType);
        eventTypeNames.add(categoryname);

        eventType.setNames(eventTypeNames);

        return super.create(eventType);
    }

}
