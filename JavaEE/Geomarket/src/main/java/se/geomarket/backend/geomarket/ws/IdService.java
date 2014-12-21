/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import se.geomarket.backend.geomarket.constants.DaoTypes;
import se.geomarket.backend.geomarket.dao.CategoryDao;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dao.CompanyUsersDao;
import se.geomarket.backend.geomarket.dao.EventDao;
import se.geomarket.backend.geomarket.dao.FilterDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dao.MovementDao;
import se.geomarket.backend.geomarket.dao.RolesDao;
import se.geomarket.backend.geomarket.dao.SettingDao;
import se.geomarket.backend.geomarket.dao.UsersDao;
import se.geomarket.backend.geomarket.generics.GenericError;
import se.geomarket.backend.geomarket.generics.Response;
import se.geomarket.backend.geomarket.generics.WsResponse;

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

    @GET
    @Path("/{id}/{type}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "GET", value = "Gets a Object by Id", response = Long.class, nickname = "getIdByExtId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The databaseId for the selected type", response = Long.class),
        @ApiResponse(code = 500, message = "")})
    public WsResponse getById(@PathParam("id") String id, @PathParam("type") DaoTypes daoType) {
        switch (daoType) {
            case CATEGORY:
                return category.getId(id).getWsResponse();
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
            default:
                return Response.error(GenericError.WRONG_PARAMETER).getWsResponse();
        }
    }

}
