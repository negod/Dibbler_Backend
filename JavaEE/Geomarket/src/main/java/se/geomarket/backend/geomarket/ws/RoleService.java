/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.ws;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import se.geomarket.backend.geomarket.dao.UserDao;
import se.geomarket.backend.geomarket.dto.RoleDto;
import se.geomarket.backend.geomarket.entity.Role;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Stateless
@Path("/roles")
public class RoleService extends BaseWs<RoleDto, Role, UserDao>{

    @Override
    public UserDao getDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseMapper<RoleDto, Role> getMapper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
