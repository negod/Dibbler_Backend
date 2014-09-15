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

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <D> Dto class
 * @param <E> Entity class
 * @param <DAO> DAO class
 */
public abstract class BaseWs<D extends BaseDto, E extends BaseEntity, DAO extends BaseDao> {

    public abstract DAO getDao();

    public abstract BaseMapper<D, E> getMapper();

    public String insert(D data) {
        try {
            E entity = getMapper().mapFromDtoToEntity(data);
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
            return getMapper().mapFromEntityToDto((E) getDao().getByExtId(id));
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

    public String update(D data, String id) {
        try {
            E oldEntity = (E) getDao().getByExtId(id);
            getMapper().updateEntityFromDto(oldEntity, data);
            getDao().update(oldEntity);
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
                dtos.add(getMapper().mapFromEntityToDto(entity));
            }
        } catch (Exception e) {

        }
        return dtos;
    }

}
