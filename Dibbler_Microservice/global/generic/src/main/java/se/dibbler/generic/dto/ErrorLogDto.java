/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.dto;

import javax.persistence.Column;
import javax.persistence.Transient;
import se.dibbler.generic.error.ErrorCode;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class ErrorLogDto extends BaseDto {

    @Column
    private final String errorCode;

    @Column
    private final String errorMessage;

    @Column
    private final String exceptionMessage;

    @Transient
    ErrorCode error;

    public ErrorLogDto(ErrorCode code, Exception ex) {
        errorCode = code.getErrorCode().toString();
        errorMessage = code.getErrorText();
        exceptionMessage = ex.getMessage();
        this.error = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public ErrorCode getCode() {
        return error;
    }

    public void setCode(ErrorCode code) {
        this.error = code;
    }

}
