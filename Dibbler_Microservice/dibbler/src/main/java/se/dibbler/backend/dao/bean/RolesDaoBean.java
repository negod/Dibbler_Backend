/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.RolesDao;
import se.dibbler.backend.dto.RolesDto;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDaoBean;
import se.dibbler.generic.error.GenericError;
import se.dibbler.generic.user.Role;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class RolesDaoBean extends BaseDaoBean<Role, RolesDto> implements RolesDao<Role, RolesDto> {

    public RolesDaoBean() {
        super(Role.class, RolesDto.class);
    }

    @Override
    public Response create(RolesDto dto) {
        Response<Role> entity = super.mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return super.create(entity.getData());
    }

    @Override
    public Response<String> update(RolesDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
