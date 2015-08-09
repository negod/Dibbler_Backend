/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service;

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
import se.dibbler.backend.dao.CategoryDao;
import se.dibbler.backend.dao.CategoryTextDao;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dto.CategoryDto;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.entity.Category;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @apiDescription Handles all Categories
 * @author Joakim
 */
@Path("/categories")
@Stateless
public class CategoryService extends BaseWs<CategoryDto, Category, CategoryDao> {

    @EJB
    CategoryDao categoryDao;

    @EJB
    CategoryTextDao categoryText;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public CategoryDao getDao() {
        return categoryDao;
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CategoryDto>
     * @summary Gets a Category by its id
     */
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getById(@PathParam("id") String id) {
        try {
            return super.getById(id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CategoryDto>
     * @summary Gets all the categories by language
     */
    @GET
    @Path("/language/{languageId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getAllByLanguage(@PathParam("languageId") String languageId) {
        try {
            return getErrorLog().createLog(categoryDao.getCategoriesByLanguage(languageId)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CategoryDto>
     * @summary Gets all the Categories
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse getAll() {
        try {
            return super.getAll();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Creates a new category
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(
            @QueryParam("description") String description,
            @QueryParam("defaultName") String defaultName,
            @QueryParam("languageId") String languageId) {
        try {
            return super.insert(new CategoryDto(languageId, defaultName, description));
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Adds a language to the category
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addlanguage")
    public WsResponse addLanguage(
            @QueryParam("categoryId") String categoryId,
            @QueryParam("name") String name,
            @QueryParam("languageId") String language) {
        try {
            return getErrorLog().createLog(categoryDao.addLanguage(categoryId, name, language)).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Inactivates a category
     */
    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse delete(@PathParam("id") Long id) {
        try {
            return super.delete(id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Updates a category
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse update(@QueryParam("description") String description, @PathParam("id") String id) {
        try {
            return getErrorLog().createLog(categoryDao.updateCategoryDescription(description, id)).getWsResponse();
        } catch (Exception e) {
            return Response.error(GenericError.UNHANDELED_EXCEPTION, e.getMessage()).getWsResponse();
        }
    }

    /**
     * @summary Deletes a category in a specific language
     */
    @DELETE
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse deleteLanguage(@PathParam("id") Long id) {
        try {
            return getErrorLog().createLog(categoryText.delete(id)).getWsResponse();
        } catch (Exception e) {
            return Response.error(GenericError.UNHANDELED_EXCEPTION, e.getMessage()).getWsResponse();
        }
    }

    /**
     * @summary Updates a categoryname in a specific language
     */
    @PUT
    @Path("/language/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse updateCategoryName(
            @QueryParam("name") String name,
            @PathParam("id") String categoryNameId) {
        try {
            return getErrorLog().createLog(categoryText.updateCategoryTypeNameByEventTextId(name, categoryNameId)).getWsResponse();
        } catch (Exception e) {
            return Response.error(GenericError.UNHANDELED_EXCEPTION, e.getMessage()).getWsResponse();
        }
    }

}
