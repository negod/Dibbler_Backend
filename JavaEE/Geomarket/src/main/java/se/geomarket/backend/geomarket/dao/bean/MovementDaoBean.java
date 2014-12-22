/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.MovementDao;
import se.geomarket.backend.geomarket.dto.MovementDto;
import se.geomarket.backend.geomarket.entity.Movement;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.Response;
import se.geomarket.backend.geomarket.mapper.MovementMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class MovementDaoBean extends BaseDaoBean<Movement, MovementDto> implements MovementDao<Movement, MovementDto> {

    public MovementDaoBean() {
        super(Movement.class);
    }

    @Override
    public Response create(MovementDto dto) {
        Response<Movement> entity = MovementMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return super.create(entity.getData());
    }

}
