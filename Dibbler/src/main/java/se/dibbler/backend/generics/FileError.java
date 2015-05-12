/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum FileError implements ErrorCode {

    NOT_FOLDER(2000, "Target must be a folder"),
    UNHANDELED_EXCEPTION(2001, "Unhandeled exception");

    private final Integer errorCode;
    private final String errorText;

    FileError(Integer code, String errorText) {
        this.errorCode = code;
        this.errorText = errorText;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorText() {
        return errorText;
    }

    @Override
    public String getErrorType() {
        return this.getClass().getSimpleName();
    }

}
