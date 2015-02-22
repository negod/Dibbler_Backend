/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.generics.BaseDao;
import se.dibbler.backend.generics.BaseDto;
import se.dibbler.backend.generics.BaseEntity;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 */
@Local
public interface LocationDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addLocationToCompany(LocationDto dto, String companyId);

    public Response<String> removeLocationInCompany(LocationDto dto, String companyId);

    public Response<String> updateLocationInCompany(LocationDto dto, String companyId);

    public Response<String> addLocationGroup(String name, String companyId);

    public Response<String> removeLocationFromLocationGroup(String locationGroupId, String location);

    public Response<String> addLocationsToLocationGroup(String locationGroupId, List<String> locationId);

}
