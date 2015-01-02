/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.geomarket.dao.CompanyUsersDao;
import se.dibbler.backend.geomarket.dto.CompanyUsersDto;
import se.dibbler.backend.geomarket.entity.CompanyUsers;
import se.dibbler.backend.geomarket.generics.BaseDaoBean;
import se.dibbler.backend.geomarket.generics.GenericError;
import se.dibbler.backend.geomarket.generics.Response;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class CompanyUsersDaoBean extends BaseDaoBean<CompanyUsers, CompanyUsersDto> implements CompanyUsersDao<CompanyUsers, CompanyUsersDto> {

    public CompanyUsersDaoBean() {
        super(CompanyUsers.class);
    }

    @Override
    public Response create(CompanyUsersDto dto) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
