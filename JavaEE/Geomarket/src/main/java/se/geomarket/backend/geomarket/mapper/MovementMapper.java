/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.MovementDto;
import se.geomarket.backend.geomarket.entity.Movement;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class MovementMapper extends BaseMapper<MovementDto, Movement> {

    private static final MovementMapper INSTANCE = new MovementMapper();

    private MovementMapper() {
    }

    public static MovementMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Movement mapFromDtoToEntity(MovementDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Movement destObject = mapper.map(dto, Movement.class);
        return destObject;
    }

    @Override
    public MovementDto mapFromEntityToDto(Movement entity) {
        Mapper mapper = new DozerBeanMapper();
        MovementDto destObject = mapper.map(entity, MovementDto.class);
        return destObject;
    }

    @Override
    public void updateEntityFromDto(Movement entity, MovementDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
