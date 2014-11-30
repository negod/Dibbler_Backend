/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.CompanyUsersDao;
import se.geomarket.backend.geomarket.dto.CompanyUsersDto;
import se.geomarket.backend.geomarket.entity.CompanyUsers;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.GenericError;
import se.geomarket.backend.geomarket.generics.MethodResponse;

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
    public MethodResponse create(CompanyUsersDto dto) {
        return MethodResponse.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
