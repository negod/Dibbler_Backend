/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <D> The Dto
 * @param <E> The Entity
 *
 */
public class BaseMapper<D extends BaseDto, E extends BaseEntity> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseMapper.class);

    Class<E> entityClass;
    Class<D> dtoClass;

    public BaseMapper(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public Response<List<E>> mapToEntityList(List<D> dtoList) {
        List<E> entityList = new ArrayList<>();
        for (D dto : dtoList) {
            Response<E> entity = mapFromDtoToEntity(dto);
            if (entity.hasNoErrors) {
                entityList.add(entity.getData());
            }
        }
        return Response.success(entityList);
    }

    public Response<List<D>> mapToDtoList(List<E> entityList) {
        List<D> dtoList = new ArrayList<>();
        for (E entity : entityList) {
            Response<D> dto = mapFromEntityToDto(entity);
            if (dto.hasNoErrors) {
                dtoList.add(dto.getData());
            }

        }
        return Response.success(dtoList);
    }

    public Response<E> mapFromDtoToEntity(D dto) {
        try {
            E entity = Mapper.getInstance().getMapper().map(dto, entityClass);
            return Response.success(entity);
        } catch (Exception e) {
            LOGGER.error("[ Failed to map from dto {} to entity {} [ DTO EXT_ID: {} ] Error : {}", dto.getClass().getName(), entityClass.getName(), dto.getId(), e);
            return Response.error(GenericError.DTO_TO_ENTITY);
        }
    }

    public Response<D> mapFromEntityToDto(E entity) {
        try {
            D dto = Mapper.getInstance().getMapper().map(entity, dtoClass);
            return Response.success(dto);
        } catch (Exception e) {
            LOGGER.error("[ Failed to map from entity {} to dto {} [ ENTITY_ID: {} ] Error : {}", entity.getClass().getName(), dtoClass.getName(), entity.getId(), e);
            return Response.error(GenericError.ENTITY_TO_DTO);
        }
    }

    public Response<E> updateEntityFromDto(E entity, D dto) {
        try {
            Mapper.getInstance().getMapper().map(dto, entity);
            return Response.success(entity);
        } catch (Exception e) {
            LOGGER.error("[ Failed to update entity {} from dto {} [ ENTITY_ID: {} ] Error : {}", entity.getClass().getName(), dto.getClass().getName(), entity.getId(), e);
            return Response.error(GenericError.UPDATE_ENTITY);
        }
    }

}
