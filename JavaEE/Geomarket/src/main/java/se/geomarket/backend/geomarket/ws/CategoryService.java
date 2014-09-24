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
import javax.ws.rs.core.Response;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dto.CategoryDto;
import se.geomarket.backend.geomarket.entity.Category;
import se.geomarket.backend.geomarket.entity.CategoryName;
import se.geomarket.backend.geomarket.entity.Language;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.CategoryMapper;
import se.geomarket.backend.geomarket.utils.EntityUtils;
import se.geomarket.backend.geomarket.utils.ResponseUtil;

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
    LanguageDao languageDao;

    @Override
    public CategoryDao getDao() {
        return categoryDao;
    }

    @Override
    public BaseMapper<CategoryDto, Category> getMapper() {
        return CategoryMapper.getInstance();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new category", response = String.class, nickname = "insert", notes = "This is not implemented Use /create instead!!")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response insert(CategoryDto data) {
        return ResponseUtil.getMethodNotSupportedError("User method [ create ] instead");
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a Category by Id", response = CategoryDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Category"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a category bi Id", response = String.class, nickname = "delete")
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
    @ApiOperation(httpMethod = "PUT", value = "Updates a category", response = String.class, nickname = "update", notes = "This Method is not supported")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Method not accessible"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response update(
            @ApiParam(value = "The new Category data", required = true) CategoryDto data,
            @ApiParam(value = "The id of the Category", required = true) @PathParam("id") String id) {
        return ResponseUtil.getMethodNotSupportedError();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a list of all Categories", response = CategoryDto.class, nickname = "getAll", notes = "")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All Categories found"),
        @ApiResponse(code = 500, message = "Could not get the categories")})
    public Response getAll() {
        return super.getAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/create")
    @ApiOperation(httpMethod = "POST", value = "Creates a new category with default language and value set", response = String.class, nickname = "create", notes = "Returns the id of the created category")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the created category"),
        @ApiResponse(code = 500, message = "Could not get the categories")})
    public Response insert(
            @ApiParam(value = "The category description", required = true) @QueryParam("description") String descr,
            @ApiParam(value = "The default name of the category", required = true) @QueryParam("defaultName") String defName,
            @ApiParam(value = "The id of the default language", required = true) @QueryParam("languageId") String language) {

        try {

            Language languageEntity = (Language) languageDao.getByExtId(language);

            Category category = new Category();
            category.setDescription(descr);
            category.setDefaultName(defName);

            CategoryName categoryname = (CategoryName) EntityUtils.setEntityCreateData(new CategoryName());
            categoryname.setLanguage(languageEntity);
            categoryname.setName(defName);
            categoryname.setCategory(category);

            category.getNames().add(categoryname);

            super.insert(category);

            return Response.ok(category.getExtId()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addlanguage")
    @ApiOperation(httpMethod = "POST", value = "Adds a description to the Category in the specified language", response = String.class, nickname = "addlanguage", notes = "Returns the id of the updated category")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the updated category"),
        @ApiResponse(code = 500, message = "Could not update categories")})
    public Response addLanguage(
            @ApiParam(value = "The id for the Category that the new language will be added to", required = true) @QueryParam("categoryId") String categoryId, 
            @ApiParam(value = "The name of the category in a new language", required = true) @QueryParam("name") String name, 
            @ApiParam(value = "The id of the language to add", required = true) @QueryParam("languageId") String language) {
        try {
            return Response.ok(categoryDao.addLanguage(categoryId, name, language)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
