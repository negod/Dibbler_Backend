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
import se.dibbler.backend.constants.RegExp;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dao.LocationDao;
import se.dibbler.backend.dto.LocationDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class LocationDaoBean extends BaseDaoBean<Location, LocationDto> implements LocationDao<Location, LocationDto> {

    @EJB
    CompanyDao companyDao;

    @EJB
    LocationDao locationDao;

    public LocationDaoBean() {
        super(Location.class, LocationDto.class);
    }

    @Override
    public Response<String> create(LocationDto dto) {
        Response<Location> location = super.mapFromDtoToEntity(dto);
        if (location.hasNoErrors) {
            return super.create(location.getData());
        } else {
            return Response.error(location.getError());
        }
    }

    @Override
    public Response<String> addLocationToCompany(LocationDto dto, String companyId) {
        try {

            Response<Location> location = Response.error(GenericError.NO_RESULT);

            if (dto.getId() != null && !dto.getId().isEmpty()) {
                if (dto.getId().matches(RegExp.GUID)) {
                    location = super.getByExtId(dto.getId());
                }
            }

            if (location.hasErrors) {
                location = super.mapFromDtoToEntity(dto);
                if (location.hasErrors) {
                    return Response.error(location.getError());
                }
            }

            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }

            if (company.getData().getLocations() == null) {
                List<Location> companyLocations = new ArrayList<>();
                companyLocations.add(location.getData());
                company.getData().setLocations(companyLocations);
            } else {
                company.getData().getLocations().add(location.getData());
            }

            return Response.success(company.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_ADD_TO_COMPANY.getErrorText() + " ]", e);
            return Response.error(DaoError.LOCATION_ADD_TO_COMPANY);
        }

    }

    @Override
    public Response<String> removeLocationInCompany(String locationId, String companyId) {
        try {
            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }

            //List<Location> locations = new ArrayList<Location>();
            for (Location location : company.getData().getLocations()) {
                if (location.getExtId().equalsIgnoreCase(locationId)) {
                    company.getData().getLocations().remove(location);
                }
            }

            /*company.getData().getLocations().clear();
             company.getData().setLocations(locations);*/
            return companyDao.update(company.getData());

        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP);
        }
    }

    @Override
    public Response<String> updateLocationInCompany(LocationDto dto, String companyId) {
        try {
            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }

            for (Location location : company.getData().getLocations()) {
                if (location.getExtId().equalsIgnoreCase(dto.getId())) {
                    location.setLatitude(dto.getLatitude());
                    location.setLongitude(dto.getLongitude());
                }
            }

            return Response.success(company.getData().getExtId());
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_UPDATE_IN_COMPANY.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_UPDATE_IN_COMPANY);
        }

    }

    @Override
    public Response<String> addLocationGroup(String name, String companyId) {
        try {

            Response<Company> company = companyDao.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }
            return null;
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP);
        }
    }

    @Override
    public Response<String> removeLocationFromLocationGroup(String locationGroupId, String location) {
        try {
            return null;
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_REMOVE_LOCATION_FROM_GROUP);
        }
    }

    @Override
    public Response<String> addLocationsToLocationGroup(String locationGroupId, List<String> locationId) {
        try {
            return null;
        } catch (Exception e) {
            getLogger().error("[ " + DaoError.LOCATION_ADD_TO_LOCATION_GROUP.getErrorText() + " ]", e.getMessage());
            return Response.error(DaoError.LOCATION_ADD_TO_LOCATION_GROUP);
        }
    }

    @Override
    public Response<String> update(LocationDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
