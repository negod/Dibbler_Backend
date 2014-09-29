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
import se.geomarket.backend.geomarket.dao.SettingDao;
import se.geomarket.backend.geomarket.dto.SettingDto;
import se.geomarket.backend.geomarket.entity.Setting;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/settings")
@Api(value = "/settings", description = "Handles all users application settings", hidden = true)
public class SettingService extends BaseWs<SettingDto, Setting, SettingDao> {

    @EJB
    SettingDao settingDao;

    @Override
    public SettingDao getDao() {
        return settingDao;
    }

    @Override
    public BaseMapper<SettingDto, Setting> getMapper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(httpMethod = "POST", value = "Add a new Setting", response = String.class, nickname = "insert", notes = "!")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the Id of the created Setting"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response insert(SettingDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "GET", value = "Gets a Setting by Id", response = SettingDto.class, nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a Setting"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a Setting by Id", response = String.class, nickname = "delete")
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
    @ApiOperation(httpMethod = "PUT", value = "Update a Setting", response = String.class, nickname = "update")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the id of the Setting"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response update(SettingDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getAll() {
        return super.getAll();
    }

}
