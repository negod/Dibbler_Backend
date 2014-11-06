/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

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

    public static final Logger logger = LoggerFactory.getLogger(BaseMapper.class);

    Class<E> entityClass;
    Class<D> dtoClass;

    public BaseMapper(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public List<E> mapToEntityList(List<D> dtoList) {
        List<E> entityList = new ArrayList<>();
        for (D dto : dtoList) {
            entityList.add(mapFromDtoToEntity(dto));
        }
        return entityList;
    }

    public List<D> mapToDtoList(List<E> entityList) {
        List<D> dtoList = new ArrayList<>();
        for (E entity : entityList) {
            dtoList.add(mapFromEntityToDto(entity));
        }
        return dtoList;
    }

    public E mapFromDtoToEntity(D dto) {
        try {
            E entity = Mapper.getInstance().getMapper().map(dto, entityClass);
            return entity;
        } catch (Exception e) {
            logger.error("[ Failed to map from dto {} to entity {} [ DTO EXT_ID: {} ] Error : {}", dto.getClass().getName(), entityClass.getName(), dto.getId(), e);
            return null;
        }
    }

    public D mapFromEntityToDto(E entity) {
        try {
            return Mapper.getInstance().getMapper().map(entity, dtoClass);
        } catch (Exception e) {
            logger.error("[ Failed to map from entity {} to dto {} [ ENTITY_ID: {} ] Error : {}", entity.getClass().getName(), dtoClass.getName(), entity.getId(), e);
            return null;
        }
    }

    public void updateEntityFromDto(E entity, D dto) {
        try {
            Mapper.getInstance().getMapper().map(dto, entity);
        } catch (Exception e) {
            logger.error("[ Failed to update entity {} from dto {} [ ENTITY_ID: {} ] Error : {}", entity.getClass().getName(), dto.getClass().getName(), entity.getId(), e);
        }
    }
    
    

}
