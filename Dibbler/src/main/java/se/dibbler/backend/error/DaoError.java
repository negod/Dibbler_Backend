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
    EVENT_PUBLISH_BY_EXPIRED_DATE(2027, "Error when getting published events by expired date"),
    EVENT_NO_EVENTS_IN_COMPANY(2028, "There are no events connected to the selected company"),
    EVENT_PUBLISH_COMPANY_HAS_NOT_LOCATION(2029, "Only locations attached to the Company are allowed!"),
    EVENT_PUBLISH_UPDATE(2030, "Error when updating published event"),
    EVENT_UPDATE(2030, "Error when updating event"),
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
    COMPANY_BY_LOCATION(2040, "Error when getting company by location"),
    COMPANY_CREATE_UNIQUE_ORGNO(2041, "Company with orgNr already exists, orgNr must be unique!"),
    COMPANY_PARENT_NON_EXISTENT(2042, "Parent company does not exist"),
    /**
     * Location DAO Errors
     */
    LOCATION_ADD_TO_COMPANY(2050, "Error when adding location to locationlist in company"),
    LOCATION_ADD_TO_LOCATION_GROUP(2051, "Error when adding location to LocationGroup in company"),
    LOCATION_REMOVE_LOCATION_FROM_GROUP(2052, "Error when removing Location from LocationGroup"),
    LOCATION_ADD_LOCATION_GROUP(2053, "Error when adding Location to LocationGroup"),
    LOCATION_UPDATE_IN_COMPANY(2054, "Error when updating Location in Company LocationList"),
    LOCATION_REMOVE_IN_COMPANY(2055, "Error when removing Location in Company LocationList"),
    LOCATION_GENERIC_ERROR(2056, "Generic error, see log for more details");

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
        return "[ ERROR ] " + errorText;
    }

    @Override
    public String getErrorType() {
        return this.getClass().getName();
    }

}
