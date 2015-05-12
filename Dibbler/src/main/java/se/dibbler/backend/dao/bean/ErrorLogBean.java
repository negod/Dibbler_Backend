/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
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
