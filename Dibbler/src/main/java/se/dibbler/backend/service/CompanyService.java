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
import javax.ws.rs.core.MediaType;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dto.CompanyDto;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.dto.create.CompanyCreateDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/companies")
public class CompanyService extends BaseWs<CompanyDto, Company, CompanyDao> {

    @EJB
    CompanyDao companyDao;

    @EJB
    ErrorLogDao errorLog;

    @Override
    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @Override
    public CompanyDao getDao() {
        return companyDao;
    }

    /**
     * @inputType se.dibbler.backend.dto.create.CompanyCreateDto
     * @summary Creates a Company. Adds the company as a branch if
     * parentCompanyId is attached in the request
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insert(CompanyCreateDto data) {
        try {
            return companyDao.create(data).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CompanyDto>
     * @summary Gets a Company by its id
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
     * @inputType se.dibbler.backend.dto.create.CompanyCreateDto
     * @summary Adds a company as a branchCompany to an existing company.
     */
    @POST
    @Path("branch/{parentid}/{branchid}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse insertNewBranchCompany(@PathParam("parentid") String parentCompany, @PathParam("branchid") String branch) {
        try {
            return companyDao.addBranch(parentCompany, branch).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CompanyDto>
     * @summary Gets all branches connencted to a specific company
     */
    @GET
    @Path("/branch/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getBranchCompaniesByCompanyId(@PathParam("companyId") String companyId) {
        try {
            return companyDao.getBranches(companyId).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CompanyDto>
     * @summary Gets a parent company connencted to a specific company
     */
    @GET
    @Path("/parent/{companyId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getParentCompanyByCompanyId(@PathParam("companyId") String companyId) {
        try {
            return companyDao.getParent(companyId).getWsResponse();
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @summary Deletes a Company by its id
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
     * @inputType se.dibbler.backend.dto.CompanyDto
     * @summary Updates A Company ( NOT Branch or parent )
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public WsResponse update(CompanyDto data, @PathParam("id") String id) {
        try {
            return super.update(data, id);
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

    /**
     * @responseType java.util.List<se.dibbler.backend.dto.CompanyDto>
     * @summary Gets all Companies attached to the logged in user
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

}
