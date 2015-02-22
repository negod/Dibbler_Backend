/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.RolesDao;
import se.dibbler.backend.dto.RolesDto;
import se.dibbler.backend.entity.Roles;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.RolesMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class RolesDaoBean extends BaseDaoBean<Roles, RolesDto> implements RolesDao<Roles, RolesDto> {

    public RolesDaoBean() {
        super(Roles.class);
    }

    @Override
    public Response create(RolesDto dto) {
        Response<Roles> entity = RolesMapper.getInstance().mapFromDtoToEntity(dto);
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
