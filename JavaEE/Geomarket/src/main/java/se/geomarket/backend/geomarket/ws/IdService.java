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
import se.geomarket.backend.geomarket.dao.CategoryNameDao;
import se.geomarket.backend.geomarket.dao.CompanyDao;
import se.geomarket.backend.geomarket.dao.CompanyUsersDao;
import se.geomarket.backend.geomarket.dao.EventDao;
import se.geomarket.backend.geomarket.dao.EventTextDao;
import se.geomarket.backend.geomarket.dao.EventTypeDao;
import se.geomarket.backend.geomarket.dao.EventTypeNameDao;
import se.geomarket.backend.geomarket.dao.FilterDao;
import se.geomarket.backend.geomarket.dao.LanguageDao;
import se.geomarket.backend.geomarket.dao.MovementDao;
import se.geomarket.backend.geomarket.dao.RolesDao;
import se.geomarket.backend.geomarket.dao.SettingDao;
import se.geomarket.backend.geomarket.dao.UsersDao;
import se.geomarket.backend.geomarket.generics.GenericError;
import se.geomarket.backend.geomarket.generics.MethodResponse;
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
    CategoryNameDao categoryName;
    @EJB
    CompanyDao company;
    @EJB
    CompanyUsersDao companyUser;
    @EJB
    EventDao event;
    @EJB
    EventTextDao eventText;
    @EJB
    EventTypeDao eventType;
    @EJB
    EventTypeNameDao eventTypeName;
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
    @ApiOperation(httpMethod = "GET", value = "Gets a Category by Id", response = Long.class, nickname = "getIdByExtId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 500, message = "")})
    public WsResponse getById(@PathParam("id") String id, @PathParam("type") DaoTypes daoType) {
        switch (daoType) {
            case CATEGORY:
                return category.getId(id).getWsResponse();
            case CATEGORY_NAME:
                return categoryName.getId(id).getWsResponse();
            case COMPANY:
                return company.getId(id).getWsResponse();
            case COMPANY_USERS:
                return companyUser.getId(id).getWsResponse();
            case EVENT:
                return event.getId(id).getWsResponse();
            case EVENT_TEXT:
                return eventText.getId(id).getWsResponse();
            case EVENT_TYPE:
                return eventType.getId(id).getWsResponse();
            case EVENT_TYPE_NAME:
                return eventTypeName.getId(id).getWsResponse();
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
                return MethodResponse.error(GenericError.WRONG_PARAMETER).getWsResponse();
        }

    }

}
