/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.dibbler.backend.dto.MovementDto;
import se.dibbler.backend.entity.Movement;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class MovementMapper extends BaseMapper<MovementDto, Movement> {

    private static final MovementMapper INSTANCE = new MovementMapper();

    public static MovementMapper getInstance() {
        return INSTANCE;
    }

    public MovementMapper() {
        super(MovementDto.class, Movement.class);
    }

}
