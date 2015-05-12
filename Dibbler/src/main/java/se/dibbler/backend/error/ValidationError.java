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
public enum ValidationError implements ErrorCode {

    ID_NOT_CORRECT(4000, "One of the id:s provided does not conform to UUID standard"),
    GENERIC_VALIDATION_ERROR(4001, "Error when validating");

    private final Integer errorCode;
    private final String errorText;

    ValidationError(Integer code, String errorText) {
        this.errorCode = code;
        this.errorText = errorText;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorText() {
        return "[ VALIDATION ERROR ] " + errorText;
    }

    @Override
    public String getErrorType() {
        return this.getClass().getSimpleName();
    }

}
