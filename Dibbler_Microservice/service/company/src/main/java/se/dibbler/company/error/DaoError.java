/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.company.error;

import se.dibbler.generic.error.ErrorCode;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum DaoError implements ErrorCode {

    COMPANY_BY_LOCATION(2040, "Error when getting company by location"),
    COMPANY_CREATE_UNIQUE_ORGNO(2041, "Company with orgNr already exists, orgNr must be unique!"),
    COMPANY_PARENT_NON_EXISTENT(2042, "Parent company does not exist");

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
