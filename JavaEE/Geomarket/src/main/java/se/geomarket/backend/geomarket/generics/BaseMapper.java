/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <D> The Dto
 * @param <E> The Entity
 *
 */
public abstract class BaseMapper<D extends BaseDto, E extends BaseEntity> {

    public List<E> mapToEntityList(List<D> dtoList) {
        List<E> entityList = new ArrayList<E>();
        for (D dto : dtoList) {
            entityList.add(mapFromDtoToEntity(dto));
        }
        return entityList;
    }

    public List<D> mapToDtoList(List<E> entityList) {
        List<D> dtoList = new ArrayList<D>();
        for (E entity : entityList) {
            dtoList.add(mapFromEntityToDto(entity));
        }
        return dtoList;
    }

    public abstract E mapFromDtoToEntity(D dto);

    public abstract D mapFromEntityToDto(E entity);

    public abstract void updateEntityFromDto(E entity, D dto);

}
