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
public enum MapperError implements ErrorCode {

    /**
     * EventSummary Mapper ERRORS
     */
    EVENT_SUMMARY_GET_EVENTTEXT(2000),
    EVENT_SUMMARY_GET_EVENT(2001);

    private final Integer errorCode;

    MapperError(Integer code) {
        this.errorCode = code;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

}
