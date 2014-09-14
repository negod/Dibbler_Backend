/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import se.geomarket.backend.geomarket.dao.UserDao;
import se.geomarket.backend.geomarket.dto.UserDto;
import se.geomarket.backend.geomarket.entity.User;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.ObjectMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/users")
public class UserService extends BaseWs<UserDto, User, UserDao> {

    @Context
    private UriInfo context;
    @EJB
    private UserDao userDao;
    @EJB
    private ObjectMapper mapper;

    @Override
    public ObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public UserDto mapToDto(User entity) {
        return mapper.getDtoMapper().mapUserDto(entity);
    }

    @Override
    public User mapToEntity(UserDto dto) {
        return mapper.getEntityMapper().mapUserEntity(dto);
    }

    @Override
    public User mapToEntity(User entity) {
        return mapper.getEntityMapper().mapUserEntity(entity);
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insert(UserDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public UserDto getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public String delete(@PathParam("id") Long id) {
        return super.delete(id);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String update(UserDto data) {
        return super.update(data);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserDto> getAll() {
        return super.getAll();
    }

}
