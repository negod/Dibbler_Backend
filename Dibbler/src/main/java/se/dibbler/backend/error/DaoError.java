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
     * ******* Category DAO ERRORS *********
     */
    /**
     * Error when extracting category names by language
     */
    CATEGORY_NAME_BY_LANGUAGE(2000, "Error when extracting category names by language"),
    /**
     * Error when adding language to category
     */
    CATEGORY_ADD_LANGUAGE(2002, "Error when adding language to category"),
    /**
     * Error when getting categories by language
     */
    CATEGORY_GET_BY_LANGUAGE(2003, "Error when getting categories by language"),
    /**
     * Error when creating category
     */
    CATEGORY_CREATE(2004, "Error when creating category"),
    /**
     * ******** EventSummary DAO ERRORS *********
     */
    EVENT_CHECK_DATE_OK(2010, "Error when checking date for event"),
    // * ******** Event DAO Errors ***********

    /**
     * Error when adding EventText
     */
    EVENT_ADD_EVENT_TEXT(2020, "Error when adding EventText"),
    /**
     * Error when getting events by location
     */
    EVENT_GET_EVENT_BY_LOCATION(2021, "Error when getting events by location"),
    /**
     * Error when creating event
     */
    EVENT_CREATE(2022, "Error when creating event"),
    /**
     * More than 2 EventTexts for the specified language
     */
    EVENT_MORE_EVENTTEXTS_THAN_ALOWED(2023, "More than 2 EventTexts for the specified language"),
    /**
     * Error when retreiving event by company
     */
    EVENT_GET_BY_COMPANY(2024, "Error when retreiving event by company"),
    /**
     * Error when getting events by location
     */
    EVENT_BY_LOCATION(2025, "Error when getting events by location"),
    /**
     * Error when publishing event
     */
    EVENT_PUBLISH(2026, "Error when publishing event"),
    /**
     * Error when getting published events by expired date
     */
    EVENT_PUBLISH_BY_EXPIRED_DATE(2027, "Error when getting published events by expired date"),
    /**
     * There are no events connected to the selected company
     */
    EVENT_NO_EVENTS_IN_COMPANY(2028, "There are no events connected to the selected company"),
    /**
     * Only locations attached to the Company are allowed!
     */
    EVENT_PUBLISH_COMPANY_HAS_NOT_LOCATION(2029, "Only locations attached to the Company are allowed!"),
    /**
     * Error when updating published event
     */
    EVENT_PUBLISH_UPDATE(2030, "Error when updating published event"),
    /**
     * Error when updating event
     */
    EVENT_UPDATE(2031, "Error when updating event"),
    /**
     * Could not find one of the selected locations
     */
    EVENT_UPDATE_GET_LOCATION(2032, "Could not find one of the selected locations"),
    /* ******* EventType DAO Errors ********/
    EVENTTYPE_BY_LANGUAGE(2100, "Error when getting eventtypes by language"),
    /**
     * Error when adding language to EventType
     */
    EVENTTYPE_ADD_LANGUAGE(2101, "Error when adding language to EventType"),
    /**
     * Error when extracting eventtype by language
     */
    EVENTTYPE_NAME_BY_LANGUAGE(2102, "Error when extracting eventtype by language"),
    /**
     * Error when creating EventType
     */
    EVENTTYPE_CREATE(2032, "Error when creating EventType"),
    // * Company DAO Errors 
    /**
     * Error when getting company by location
     */
    COMPANY_BY_LOCATION(2201, "Error when getting company by location"),
    /**
     * Company with orgNr already exists, orgNr must be unique!
     */
    COMPANY_CREATE_UNIQUE_ORGNO(2202, "Company with orgNr already exists, orgNr must be unique!"),
    /**
     * Parent company does not exist
     */
    COMPANY_PARENT_NON_EXISTENT(2203, "Parent company does not exist"),
    //  Location DAO Errors

    /**
     * Error when adding location to locationlist in company
     */
    LOCATION_ADD_TO_COMPANY(2301, "Error when adding location to locationlist in company"),
    /**
     * Error when adding location to LocationGroup in company
     */
    LOCATION_ADD_TO_LOCATION_GROUP(2302, "Error when adding location to LocationGroup in company"),
    /**
     * Error when removing Location from LocationGroup
     */
    LOCATION_REMOVE_LOCATION_FROM_GROUP(2303, "Error when removing Location from LocationGroup"),
    /**
     * Error when adding Location to LocationGroup
     */
    LOCATION_ADD_LOCATION_GROUP(2304, "Error when adding Location to LocationGroup"),
    /**
     * Error when updating Location in Company LocationList
     */
    LOCATION_UPDATE_IN_COMPANY(2305, "Error when updating Location in Company LocationList"),
    /**
     * Error when removing Location in Company LocationList
     */
    LOCATION_REMOVE_IN_COMPANY(2306, "Error when removing Location in Company LocationList"),
    /**
     * Generic error, see log for more details
     */
    LOCATION_GENERIC_ERROR(2307, "Generic error, see log for more details");

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
