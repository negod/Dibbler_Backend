/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import javax.ejb.Local;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDao;
import se.dibbler.generic.dto.BaseDto;
import se.dibbler.generic.entity.BaseEntity;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 */
@Local
public interface LocationDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response<String> addLocationToCompany(LocationDto dto, String companyId);

    public Response<String> removeLocationInCompany(String locationId, String companyId);

    public Response<String> updateLocationInCompany(LocationDto dto, String companyId);

    

}
