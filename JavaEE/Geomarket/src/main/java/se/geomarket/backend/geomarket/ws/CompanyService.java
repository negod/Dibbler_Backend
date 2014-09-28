/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.search.query.dsl.Unit;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.CompanyDto;
import se.geomarket.backend.geomarket.dto.EventDto;
import se.geomarket.backend.geomarket.entity.Company;
import se.geomarket.backend.geomarket.entity.Location;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.CompanyMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/companies")
@Api(value = "/companies", description = "Handles all companies")
public class CompanyService extends BaseWs<CompanyDto, Company, CompanyDao> {

    @EJB
    CompanyDao companyDao;

    @Override
    public CompanyDao getDao() {
        return companyDao;
    }

    @Override
    public BaseMapper<CompanyDto, Company> getMapper() {
        return CompanyMapper.getInstance();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new Company", response = String.class, nickname = "insert", notes = "!")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created Company"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response insert(CompanyDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a Company by id", response = String.class, nickname = "getById", notes = "!")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Company"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("location")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Location> getLocatoions(@QueryParam("longitude") Double longitude, @QueryParam("latitude") Double latitude, @QueryParam("radius") Double radius) {
        try {
            List<Location> locations = companyDao.getCompanyByLocation(longitude, latitude, radius, Unit.KM);
            return locations;
        } catch (Exception e) {
            return new ArrayList<Location>();
        }

    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a Company by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Updates a Company", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Company ID"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response update(CompanyDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Companies", response = CategoryDto.class, nickname = "getAll", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All Companies found"),
        @ApiResponse(code = 500, message = "Could not get the categories")})
    public Response getAll() {
        return super.getAll();
    }

}
