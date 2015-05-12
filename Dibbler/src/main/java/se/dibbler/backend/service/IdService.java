/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import se.dibbler.backend.constants.DaoTypes;
import se.dibbler.backend.dao.CategoryDao;
import se.dibbler.backend.dao.CategoryTextDao;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dao.CompanyUsersDao;
import se.dibbler.backend.dao.ErrorLogDao;
import se.dibbler.backend.dao.EventDao;
import se.dibbler.backend.dao.EventTypeDao;
import se.dibbler.backend.dao.EventTypeTextDao;
import se.dibbler.backend.dao.FilterDao;
import se.dibbler.backend.dao.LanguageDao;
import se.dibbler.backend.dao.MovementDao;
import se.dibbler.backend.dao.RolesDao;
import se.dibbler.backend.dao.SettingDao;
import se.dibbler.backend.dao.UsersDao;
import se.dibbler.backend.dto.ErrorLogDto;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.generics.WsResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/50ac693e-ab2a-4863-b466-afa64547f21c")
public class IdService {

    @EJB
    CategoryDao category;
    @EJB
    CategoryTextDao categoryText;
    @EJB
    CompanyDao company;
    @EJB
    CompanyUsersDao companyUser;
    @EJB
    EventDao event;
    @EJB
    FilterDao filter;
    @EJB
    LanguageDao language;
    @EJB
    MovementDao movement;
    @EJB
    RolesDao roles;
    @EJB
    SettingDao settings;
    @EJB
    UsersDao users;
    @EJB
    EventTypeTextDao eventTypeText;
    @EJB
    EventTypeDao eventType;

    @EJB
    ErrorLogDao errorLog;

    public ErrorLogDao getErrorLog() {
        return errorLog;
    }

    @GET
    @Path("/{id}/{type}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public WsResponse getById(@PathParam("id") String id, @PathParam("type") DaoTypes daoType) {
        try {
            switch (daoType) {
                case CATEGORY:
                    return category.getId(id).getWsResponse();
                case CATEGORY_NAME:
                    return categoryText.getId(id).getWsResponse();
                case COMPANY:
                    return company.getId(id).getWsResponse();
                case COMPANY_USERS:
                    return companyUser.getId(id).getWsResponse();
                case EVENT:
                    return event.getId(id).getWsResponse();
                case FILTER:
                    return filter.getId(id).getWsResponse();
                case LANGUAGE:
                    return language.getId(id).getWsResponse();
                case MOVMENT:
                    return movement.getId(id).getWsResponse();
                case ROLES:
                    return roles.getId(id).getWsResponse();
                case USERS:
                    return users.getId(id).getWsResponse();
                case EVENTTYPE:
                    return eventType.getId(id).getWsResponse();
                case EVENTTEXT:
                    return eventTypeText.getId(id).getWsResponse();
                default:
                    return Response.error(GenericError.WRONG_PARAMETER).getWsResponse();
            }
        } catch (Exception e) {
            return errorLog.createLog(new ErrorLogDto(GenericError.UNHANDELED_EXCEPTION, e)).getWsResponse();
        }
    }

}
