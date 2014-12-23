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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.CategoryTextDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.dto.summary.NameSummaryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.generics.WsResponse;
import se.geomarket.backend.geomarket.mapper.CategoryMapper;

/**
 *
 * @author Joakim
 */
@Stateless
@Path("/categories")
@Api(value = "/categories", description = "Handles all categories")
public class CategoryService extends BaseWs<CategoryDto, Category, CategoryDao> {

    @EJB
    CategoryDao categoryDao;
    
    @EJB
    CategoryTextDao categoryText;

    @Override
    public CategoryDao getDao() {
        return categoryDao;
    }

    @Override
    public BaseMapper<CategoryDto, Category> getMapper() {
        return CategoryMapper.getInstance();
    }

    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a Category by Id", response = CategoryDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Category"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @GET
    @Path("/language/{languageId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets all Categorytypes in a certain language", response = NameSummaryDto.class, nickname = "getAllByLanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a list of categories in the requested language"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getAllByLanguage(@PathParam("languageId") String languageId) {
        return categoryDao.getCategoriesByLanguage(languageId).getWsResponse();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Categories", response = CategoryDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns all categories in all languages"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse getAll() {
        return super.getAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new category", response = String.class, nickname = "create")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the created category", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity ( Generic Dao Error )", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request ( Generic Dao Error )", response = String.class)
    })
    public WsResponse insert(
            @ApiParam(value = "The category description", required = true) @QueryParam("description") String description,
            @ApiParam(value = "The default name of the category", required = true) @QueryParam("defaultName") String defaultName,
            @ApiParam(value = "The id of the default language", required = true) @QueryParam("languageId") String languageId) {
        return super.insert(new CategoryDto(defaultName, languageId, description));
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addlanguage")
    @ApiOperation(httpMethod = "POST", value = "Adds a description to the Category in the specified language", response = String.class, nickname = "addlanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated category"),
        @ApiResponse(code = 500, message = "Could not update categories")})
    public WsResponse addLanguage(
            @ApiParam(value = "The id for the category that the new language will be added to", required = true) @QueryParam("categoryId") String categoryId,
            @ApiParam(value = "The name of the category in a new language", required = true) @QueryParam("name") String name,
            @ApiParam(value = "The id of the language to add", required = true) @QueryParam("languageId") String language) {
        return categoryDao.addLanguage(categoryId, name, language).getWsResponse();
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a category bi Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "PUT", value = "Updates a category", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse update(
            @ApiParam(value = "The Category data", required = true) CategoryDto data,
            @ApiParam(value = "The id of the Category", required = true) @PathParam("id") String id) {
        return super.update(data, id);
    }

    @PUT
    @Path("/language/{categoryNameId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates a category", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public WsResponse updateCategoryName(
            @ApiParam(value = "The new CategoryName data", required = true) CategoryDto data,
            @ApiParam(value = "The id of the CategoryName", required = true) @PathParam("categoryNameId") String categoryNameId) {
        return categoryDao.updateCategoryName(data, categoryNameId).getWsResponse();
    }

}
