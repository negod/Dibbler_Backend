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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public String insert(SettingDto data) {
        return super.insert(data);
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public SettingDto getById(@PathParam("id") String id) {
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
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public String update(SettingDto data, @PathParam("id") String id) {
        return super.update(data, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public List<SettingDto> getAll() {
        return super.getAll();
    }

}
