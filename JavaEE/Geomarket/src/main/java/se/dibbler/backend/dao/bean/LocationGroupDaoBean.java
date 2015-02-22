/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.LocationGroupDao;
import se.dibbler.backend.dto.LocationGroupDto;
import se.dibbler.backend.entity.LocationGroup;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.LocationGroupMapper;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class LocationGroupDaoBean extends BaseDaoBean<LocationGroup, LocationGroupDto> implements LocationGroupDao<LocationGroup, LocationGroupDto> {

    public LocationGroupDaoBean() {
        super(LocationGroup.class);
    }

    @Override
    public Response<String> create(LocationGroupDto dto) {
        Response<LocationGroup> locationGroup = LocationGroupMapper.getInstance().mapFromDtoToEntity(dto);
        if (locationGroup.hasErrors) {
            return Response.error(locationGroup.getError());
        }
        return super.create(locationGroup.getData());
    }

    @Override
    public Response<String> update(LocationGroupDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
