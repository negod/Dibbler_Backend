/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.MovementDao;
import se.dibbler.backend.dto.MovementDto;
import se.dibbler.backend.entity.Movement;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.MovementMapper;

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