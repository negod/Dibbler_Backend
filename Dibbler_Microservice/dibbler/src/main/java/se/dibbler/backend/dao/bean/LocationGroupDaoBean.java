/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.dibbler.company.dao.CompanyDao;
import se.dibbler.backend.dao.LocationGroupDao;
import se.dibbler.backend.dto.LocationGroupDto;
import se.dibbler.backend.dto.create.LocationGroupCreateDto;
import se.dibbler.company.entity.Company;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.entity.LocationGroup;
import se.dibbler.backend.error.DaoError;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.dao.BaseDaoBean;
import se.dibbler.generic.error.GenericError;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class LocationGroupDaoBean extends BaseDaoBean<LocationGroup, LocationGroupDto> implements LocationGroupDao<LocationGroup, LocationGroupDto> {
    
    @EJB
    CompanyDao companyDao;
    
    public LocationGroupDaoBean() {
        super(LocationGroup.class, LocationGroupDto.class);
    }
    
    @Override
    public Response<String> create(LocationGroupDto dto) {
        Response<LocationGroup> locationGroup = super.mapFromDtoToEntity(dto);
        if (locationGroup.hasErrors) {
            return Response.error(locationGroup.getError());
        }
        return super.create(locationGroup.getData());
    }
    
    @Override
    public Response<String> update(LocationGroupDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }
    
    @Override
    public Response<String> addLocationGroup(LocationGroupCreateDto dto, String companyId) {
        try {
            
            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }
            
            LocationGroup locationGroup = new LocationGroup();
            locationGroup.setName(dto.getName());
            
            List<Location> locations = new ArrayList<>();
            for (String locationId : dto.getLocations()) {
                for (Location location : company.getData().getLocations()) {
                    if (location.getExtId().equalsIgnoreCase(locationId)) {
                        locations.add(location);
                    }
                }
            }
            
            locationGroup.setLocations(locations);
            locationGroup.setCompany(company.getData());
            company.getData().getLocationGroups().add(locationGroup);
            
            return Response.success(company.getData().getExtId());
            
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_ADD_TO_LOCATION_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_ADD_TO_LOCATION_GROUP);
        }
    }
    
    @Override
    public Response<String> addLocationsToLocationGroup(LocationGroupCreateDto dto, String companyId, String locationGroupId) {
        try {
            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }
            
            Response<LocationGroup> group = Response.error(GenericError.NO_RESULT);
            
            for (LocationGroup locationGroup : company.getData().getLocationGroups()) {
                if (locationGroup.getExtId().equalsIgnoreCase(locationGroupId)) {
                    group = Response.success(locationGroup);
                    locationGroup.setName(dto.getName());
                    break;
                }
            }
            
            if (group.hasNoErrors) {
                
                for (String loc : dto.getLocations()) {
                    for (Location location : company.getData().getLocations()) {
                        
                        if (location.getExtId().equalsIgnoreCase(loc)) {
                            Response<Boolean> containsLocation = doesLocationGroupContainLocation(group.getData(), location.getExtId());
                            if (containsLocation.hasNoErrors) {
                                group.getData().getLocations().add(location);
                            }
                        }
                        
                    }
                }
                
            }
            
            return Response.success(company.getData().getExtId());
            
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_ADD_TO_LOCATION_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_ADD_TO_LOCATION_GROUP);
        }
    }
    
    private Response<Boolean> doesLocationGroupContainLocation(LocationGroup group, String locationId) {
        try {
            for (Location location : group.getLocations()) {
                if (location.getExtId().equalsIgnoreCase(locationId)) {
                    return Response.error(GenericError.FAILURE);
                }
            }
            return Response.success(false);
        } catch (Exception e) {
            getLogger().error("[ " + GenericError.FAILURE + " ]", e.getMessage());
            return Response.error(GenericError.FAILURE);
        }
    }
    
    @Override
    public Response<String> removeLocationsFromLocationGroup(List<String> locationIds, String companyId) {
        try {
            return null;
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP);
        }
    }
    
}
