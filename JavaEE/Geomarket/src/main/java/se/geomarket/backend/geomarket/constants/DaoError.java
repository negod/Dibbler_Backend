/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.constants;

import se.geomarket.backend.geomarket.generics.ErrorCode;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum DaoError implements ErrorCode {

    /**
     * Category DAO ERRORS
     */
    CATEGORY_NAME_BY_LANGUAGE(2000),
    CATEGORY_UPDATE_CATEGORYNAME(2001),
    CATEGORY_ADD_LANGUAGE(2002),
    CATEGORY_GET_BY_LANGUAGE(2003),
    CATEGORY_CREATE(2004),
    /**
     * EventSummary DAO ERRORS
     */
    EVENT_CHECK_DATE_OK(2010),
    EVENT_EXTRACT_EVENTS(2011),
    /**
     * Event DAO Errors
     */
    EVENT_ADD_EVENT_TEXT(2020),
    EVENT_GET_EVENT_BY_LOCATION(2021),
    EVENT_CREATE(2022),
    /**
     * EventType DAO Errors
     */
    EVENTTYPE_BY_LANGUAGE(2030),
    EVENTTYPE_ADD_LANGUAGE(2031),
    EVENTTYPE_NAME_BY_LANGUAGE(2032),
    EVENTTYPE_CREATE(2032),
    /**
     * Company DAO Errors
     */
    COMPANY_BY_LOCATION(2040);

    private final Integer errorCode;

    DaoError(Integer code) {
        this.errorCode = code;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

}
