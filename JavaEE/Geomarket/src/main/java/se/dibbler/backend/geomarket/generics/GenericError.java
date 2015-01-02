/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public enum GenericError implements ErrorCode {

    UNHANDELED_EXCEPTION(500, "Unhandled exception"),
    //GENERIC
    METHOD_NOT_IMPLEMENTED(700, "Method not yet implemented"),
    FAILURE(701, "Generic error"),
    //CRUD
    CREATE(1000, "Error when inserting to database"),
    CONSTRAINT_VIOLATION(1001, "Contraint violation when inserting to database"),
    READ(1002, "Error when reading from database"),
    UPDATE(1003, "Error when updating in database"),
    DELETE(1004, "Error when deleting from database"),
    NO_RESULT(1009, "Could not find any data for the requested resource"),
    //MAPPING
    DTO_TO_ENTITY(1005, "Error when mapping from Dto to Entity"),
    ENTITY_TO_DTO(1006, "Error when mapping from Entity to Dto"),
    UPDATE_ENTITY(1007, "Error when updating entity in mapper"),
    //PARAMETERS
    WRONG_PARAMETER(1008, "Wrong parameters or null in request");

    private final Integer errorCode;
    private final String errorText;

    GenericError(Integer code, String errorText) {
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

}
