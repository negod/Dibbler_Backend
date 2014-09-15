/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import se.geomarket.backend.geomarket.dao.MovementDao;
import se.geomarket.backend.geomarket.dto.MovementDto;
import se.geomarket.backend.geomarket.entity.Movement;
import se.geomarket.backend.geomarket.generics.BaseMapper;
import se.geomarket.backend.geomarket.generics.BaseWs;
import se.geomarket.backend.geomarket.mapper.MovementMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@Path("/movements")
public class MovementService extends BaseWs<MovementDto, Movement, MovementDao> {

    @EJB
    MovementDao movementDao;

    @Override
    public MovementDao getDao() {
        return movementDao;
    }

    @Override
    public BaseMapper<MovementDto, Movement> getMapper() {
        return MovementMapper.getInstance();
    }

}
