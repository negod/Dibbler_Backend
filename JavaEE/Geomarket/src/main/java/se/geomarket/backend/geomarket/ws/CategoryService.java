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
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
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
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse getAllByLanguage(@PathParam("languageId") String languageId) {
        return categoryDao.getCategoriesByLanguage(languageId).getWsResponse();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Categories", response = CategoryDto.class, nickname = "getAll")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns all categories in all languages"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1002, message = "Error when reading from database", response = String.class),
        @ApiResponse(code = 1006, message = "Error when mapping from Entity to Dto", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
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
        @ApiResponse(code = 1000, message = "Error when inserting to database", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse insert(
            @ApiParam(value = "The category description", required = true) @QueryParam("description") String description,
            @ApiParam(value = "The default name of the category", required = true) @QueryParam("defaultName") String defaultName,
            @ApiParam(value = "The id of the default language", required = true) @QueryParam("languageId") String languageId) {
        return super.insert(new CategoryDto(languageId, defaultName, description));
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addlanguage")
    @ApiOperation(httpMethod = "POST", value = "Adds a description to the Category in the specified language", response = String.class, nickname = "addlanguage")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated category"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1000, message = "Error when inserting to database", response = String.class),
        @ApiResponse(code = 1001, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1005, message = "Error when mapping from Dto to Entity", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
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
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1004, message = "Error when deleting from database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates a category", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated Category"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse update(
            @ApiParam(value = "The Category data", required = true) @QueryParam("description") String description,
            @ApiParam(value = "The id of the Category", required = true) @PathParam("id") String id) {
        return categoryDao.updateCategoryDescription(description, id).getWsResponse();
    }

    @DELETE
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "DELETE", value = "Deletes an CategoryName language by Id", response = String.class, nickname = "delete")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1004, message = "Error when deleting from database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse deleteLanguage(@ApiParam(value = "The id of the language for the CategoryName", required = true) @PathParam("id") Long id) {
        return categoryText.delete(id).getWsResponse();
    }

    @PUT
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "PUT", value = "Updates a category", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Unhandled exception", response = String.class),
        @ApiResponse(code = 1003, message = "Error when updating in database", response = String.class),
        @ApiResponse(code = 1004, message = "Contraint violation when inserting to database", response = String.class),
        @ApiResponse(code = 1008, message = "Wrong parameters or null in request", response = String.class),
        @ApiResponse(code = 1009, message = "Could not find any data for the requested id", response = String.class)
    })
    public WsResponse updateCategoryName(
            @ApiParam(value = "The new CategoryName data", required = true) @QueryParam("name") String name,
            @ApiParam(value = "The id of the CategoryName", required = true) @PathParam("id") String categoryNameId) {
        return categoryText.updateCategoryTypeNameByEventTextId(name, categoryNameId).getWsResponse();
    }

}
