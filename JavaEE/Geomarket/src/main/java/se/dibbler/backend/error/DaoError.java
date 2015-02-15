/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.error;

import se.dibbler.backend.generics.ErrorCode;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum DaoError implements ErrorCode {

    /**
     * Category DAO ERRORS
     */
    CATEGORY_NAME_BY_LANGUAGE(2000, "Error when extracting category names by language"),
    CATEGORY_ADD_LANGUAGE(2002, "Error when adding language to category"),
    CATEGORY_GET_BY_LANGUAGE(2003, "Error when getting categories by language"),
    CATEGORY_CREATE(2004, "Error when creating category"),
    /**
     * EventSummary DAO ERRORS
     */
    EVENT_CHECK_DATE_OK(2010, "Error when checking date for event"),
    /**
     * Event DAO Errors
     */
    EVENT_ADD_EVENT_TEXT(2020, "Error when adding EventText"),
    EVENT_GET_EVENT_BY_LOCATION(2021, "Error when getting events by location"),
    EVENT_CREATE(2022, "Error when creating event"),
    EVENT_MORE_EVENTTEXTS_THAN_ALOWED(2023, "More than 2 EventTexts for the specified language"),
    EVENT_GET_BY_COMPANY(2024, "Error when retreiving event by company"),
    EVENT_BY_LOCATION(2025, "Error when getting events by location"),
    EVENT_PUBLISH(2026, "Error when publishing event"),
    EVENT_PUBLISH_BY_EXPIRED_DATE(2026, "Error when getting published events by expired date"),
    /**
     * EventType DAO Errors
     */
    EVENTTYPE_BY_LANGUAGE(2030, "Error when getting eventtypes by language"),
    EVENTTYPE_ADD_LANGUAGE(2031, "Error when adding language to EventType"),
    EVENTTYPE_NAME_BY_LANGUAGE(2032, "Error when extracting eventtype by language"),
    EVENTTYPE_CREATE(2032, "Error when creating EventType"),
    /**
     * Company DAO Errors
     */
    COMPANY_BY_LOCATION(2040, "Error when getting company by location");

    private final Integer errorCode;
    private final String errorText;

    DaoError(Integer code, String errorText) {
        this.errorCode = code;
        this.errorText = errorText;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorText() {
        return "[ DAO ERROR ( Not generic DAO ) ] " + errorText;
    }

}
