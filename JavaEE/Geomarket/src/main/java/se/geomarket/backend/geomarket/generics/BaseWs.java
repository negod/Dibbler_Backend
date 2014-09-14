/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import se.geomarket.backend.geomarket.mapper.ObjectMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <D> Dto class
 * @param <E> Entity class
 * @param <DAO> DAO class
 */
public abstract class BaseWs<D extends BaseDto, E extends BaseEntity, DAO extends BaseDao> {

    public abstract DAO getDao();

    public abstract ObjectMapper getMapper();

    public abstract D mapToDto(E entity);

    public abstract E mapToEntity(D dto);

    public abstract E updateEntity(E oldEntity, D newDto);

    public String insert(D data) {
        try {
            E entity = mapToEntity(data);
            entity.setCreatedDate(new Date());
            entity.setUpdatedDate(new Date());
            entity.setExtId(UUID.randomUUID().toString());
            getDao().create(entity);
            return "User created";
        } catch (Exception e) {
            return "not so good";
        }
    }

    public D getById(String id) {
        try {
            return mapToDto((E) getDao().getByExtId(id));
        } catch (Exception e) {
            return null;
        }
    }

    public String delete(Long id) {
        try {
            E entity = (E) getDao().getById(id);
            getDao().delete(entity);
            return "Delete Ok";
        } catch (Exception e) {
            return "Delete NOT Ok";
        }
    }

    public String update(D data) {
        try {
            E oldEntity = (E) getDao().getByExtId(data.getExtId());
            E newEntity = updateEntity(oldEntity, data);
            getDao().update(newEntity);
            return "UPDATE SUCCESS!";
        } catch (Exception e) {
            return "UPDATE NOT SUCCESS";
        }
    }

    public List<D> getAll() {
        List<D> dtos = new ArrayList<D>();
        try {
            List<E> entities = getDao().getAll();
            for (E entity : entities) {
                dtos.add(mapToDto(entity));
            }
        } catch (Exception e) {

        }
        return dtos;
    }

}
