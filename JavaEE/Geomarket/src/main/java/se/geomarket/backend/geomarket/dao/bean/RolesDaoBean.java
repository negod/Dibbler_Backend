/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.RolesDao;
import se.geomarket.backend.geomarket.dto.RolesDto;
import se.geomarket.backend.geomarket.entity.Roles;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;
import se.geomarket.backend.geomarket.mapper.RolesMapper;

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
    public MethodResponse create(RolesDto dto) {
        MethodResponse<Roles> entity = RolesMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return MethodResponse.error(entity.getErrorCode());
        }
        return super.create(entity.getData());
    }

}
