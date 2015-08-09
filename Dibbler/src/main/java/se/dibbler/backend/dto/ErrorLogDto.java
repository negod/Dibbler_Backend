/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import se.dibbler.backend.generics.BaseDto;
import se.dibbler.backend.generics.ErrorCode;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class ErrorLogDto extends BaseDto {

    private String errorCode;
    private String errorMessage;
    private String exceptionMessage;
    private String errorType;

    ErrorCode error;

    public ErrorLogDto(ErrorCode code, Exception ex) {
        errorCode = code.getErrorCode().toString();
        errorMessage = code.getErrorText();
        errorType = code.getErrorType();
        exceptionMessage = ex.getCause().getMessage();
        this.error = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
