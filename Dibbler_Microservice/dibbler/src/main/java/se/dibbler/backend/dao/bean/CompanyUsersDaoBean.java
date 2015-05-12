/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.CompanyUsersDao;
import se.dibbler.backend.dto.CompanyUsersDto;
import se.dibbler.backend.entity.CompanyUsers;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDaoBean;
import se.dibbler.generic.error.GenericError;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class CompanyUsersDaoBean extends BaseDaoBean<CompanyUsers, CompanyUsersDto> implements CompanyUsersDao<CompanyUsers, CompanyUsersDto> {

    public CompanyUsersDaoBean() {
        super(CompanyUsers.class, CompanyUsersDto.class);
    }

    @Override
    public Response create(CompanyUsersDto dto) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<String> update(CompanyUsersDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
