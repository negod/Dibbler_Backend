package se.dibbler.backend.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;

import se.dibbler.backend.dao.UsersDao;
import se.dibbler.backend.dto.UsersDto;
import se.dibbler.backend.entity.Users;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.BaseWs;
import se.dibbler.backend.mapper.UsersMapper;

/**
 * Service used for authorization.
 * 
 * @author matros
 *
 */
@Stateless
@Path("/auth")
@Api(value = "/auth", description = "Handles authorization")
public class AuthService extends BaseWs<UsersDto, Users, UsersDao> {

    @EJB
    UsersDao usersDao;

    @Override
    public UsersDao getDao() {
        return usersDao;
    }

    @Override
    public BaseMapper<UsersDto, Users> getMapper() {
        return UsersMapper.getInstance();
    }

    
}
