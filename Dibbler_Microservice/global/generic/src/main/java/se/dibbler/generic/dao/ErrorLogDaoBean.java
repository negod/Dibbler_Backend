/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.dao;

import javax.ejb.Stateless;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dto.ErrorLogDto;
import se.dibbler.generic.entity.ErrorLog;
import se.dibbler.generic.error.GenericError;

/**
 * 
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class ErrorLogDaoBean extends BaseDaoBean<ErrorLog, ErrorLogDto> implements ErrorLogDao<ErrorLog, ErrorLogDto> {

    public ErrorLogDaoBean() {
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
    public Response createLog(ErrorLogDto dto) {
        try {
            Response<ErrorLog> log = super.getMapper().mapFromDtoToEntity(dto);
            if (log.hasNoErrors) {
                Response createdLog = super.create(log.getData());
            }
            return Response.error(dto.getCode());
        } catch (Exception e) {
            return Response.error(dto.getCode());
        }
    }

}
