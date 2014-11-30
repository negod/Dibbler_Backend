/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum GenericError implements ErrorCode {

    //GENERIC
    METHOD_NOT_IMPLEMENTED(700),
    FAILURE(701),
    //CRUD
    CREATE(1000),
    CONSTRAINT_VIOLATION(1001),
    READ(1002),
    UPDATE(1003),
    DELETE(1004),
    //MAPPING
    DTO_TO_ENTITY(1005),
    ENTITY_TO_DTO(1006),
    UPDATE_ENTITY(1007),
    //PARAMETERS
    WRONG_PARAMETER(1008);

    private final Integer errorCode;

    GenericError(Integer code) {
        this.errorCode = code;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

}
