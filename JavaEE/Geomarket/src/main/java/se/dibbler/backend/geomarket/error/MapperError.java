/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.error;

import se.dibbler.backend.geomarket.generics.ErrorCode;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum MapperError implements ErrorCode {

    /**
     * EventSummary Mapper ERRORS
     */
    EVENT_SUMMARY_GET_EVENTTEXT(3000, "Error when mapping default EventText"),
    EVENT_SUMMARY_GET_EVENT(3001, "Error when mapping EventSummary event"),
    EVENT_EXTRACT_EVENTS(3002, "Error when mapping EventSummaryLIST for company");

    private final Integer errorCode;
    private final String errorText;

    MapperError(Integer code, String errorText) {
        this.errorCode = code;
        this.errorText = errorText;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorText() {
        return "[ MAPPER ERROR ] " + errorText;
    }

}
