/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.Arrays;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.entity.ErrorLog;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class ErrorLogBean extends BaseDaoBean<ErrorLog, ErrorLogDto> implements ErrorLogDao<ErrorLog, ErrorLogDto> {

    @EJB
    EmailBean email;

    public ErrorLogBean() {
        super(ErrorLog.class, ErrorLogDto.class);
    }

    @Override
    public Response<String> create(ErrorLogDto dto) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<String> update(ErrorLogDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public Response createLog(ErrorLogDto dto) {
        try {
            Response<ErrorLog> log = super.getMapper().mapFromDtoToEntity(dto);
            if (log.hasNoErrors) {
                Response createdLog = super.create(log.getData());
            }

            email.sendErrorMessage(Arrays.asList("joakimjohansson@outlook.com"), dto);

            return Response.error(dto.getError(), dto.getExceptionMessage());
        } catch (Exception e) {
            return Response.error(GenericError.FAILURE, e.getMessage());
        }
    }

    @Override
    public Response createLog(Response dto) {

        if (dto.hasErrors) {

            ErrorLogDto errorLog = new ErrorLogDto(dto.getError(), dto.getException());

            if (dto.getData() != null) {
                errorLog.setErrorMessage((String) dto.getData());
            } else {
                errorLog.setErrorMessage(dto.getError().getErrorText());
            }

            errorLog.setErrorCode(dto.getError().getErrorCode().toString());
            errorLog.setErrorType(dto.getError().getErrorType());

            if (dto.getException() != null) {

                if (dto.getException().getMessage() != null) {
                    errorLog.setExceptionMessage(dto.getException().getMessage());
                } else {
                    errorLog.setExceptionMessage("No message available");
                }

                if (dto.getException().getCause() != null) {
                    errorLog.setExceptionMessage(dto.getException().getCause().getMessage());
                } else {
                    errorLog.setExceptionMessage("No message available");
                }

            }

            return createLog(errorLog);
        }

        return dto;

    }

}
