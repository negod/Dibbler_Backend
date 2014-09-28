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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.geomarket.backend.geomarket.dao.FilterDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.FilterDto;
import se.geomarket.backend.geomarket.entity.Filter;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.FilterMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/filters")
@Api(value = "/filters", description = "Handles the users filters")
public class FilterService extends BaseWs<FilterDto, Filter, FilterDao> {

    @EJB
    FilterDao filterDao;

    @Override
    public FilterDao getDao() {
        return filterDao;
    }

    @Override
    public BaseMapper<FilterDto, Filter> getMapper() {
        return FilterMapper.getInstance();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new Filter", response = String.class, nickname = "insert", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created Filter"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response insert(FilterDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a Filter User by Id", response = FilterDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Filter"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Update a Filter", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the Filter"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response update(FilterDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all CompanyUsers", response = CategoryDto.class, nickname = "getAll", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All CompanyUsers found"),
        @ApiResponse(code = 500, message = "Could not get the CompanyUsers")})
    public Response getAll() {
        return super.getAll();
    }

}
