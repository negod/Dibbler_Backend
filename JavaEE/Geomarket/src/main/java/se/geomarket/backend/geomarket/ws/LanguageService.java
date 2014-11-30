/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.languagesupport.LanguageDto;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.generics.WsResponse;
import se.geomarket.backend.geomarket.mapper.LanguageMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/languages")
@Api(value = "/languages", description = "Handles all Languages")
public class LanguageService extends BaseWs<LanguageDto, Language, LanguageDao> {

    @EJB
    LanguageDao languageDao;

    @Override
    public LanguageDao getDao() {
        return languageDao;
    }

    @Override
    public BaseMapper<LanguageDto, Language> getMapper() {
        return LanguageMapper.getInstance();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new language", response = String.class, nickname = "insert")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the created language"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse insert(@ApiParam(value = "The new language", required = true) LanguageDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a language by id", response = LanguageDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a language"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getById(
            @ApiParam(value = "The id for the language", required = true) @PathParam("id") String id) {
        return super.getById(id);
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a language by id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse delete(
            @ApiParam(value = "The id for the language", required = true) @PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Update a language", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the Language"),
        @ApiResponse(code = 500, message = "Internal server error")})
    @Override
    public WsResponse update(
            @ApiParam(value = "The new language data", required = true) LanguageDto data,
            @ApiParam(value = "The id for the language", required = true) @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Languages", response = CategoryDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All CompanyUsers found"),
        @ApiResponse(code = 500, message = "Could not get the Languages")})
    @Override
    public WsResponse getAll() {
        return super.getAll();
    }

}
