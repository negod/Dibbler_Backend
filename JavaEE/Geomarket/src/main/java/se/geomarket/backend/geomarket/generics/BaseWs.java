/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

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

    
    public Response insert(D data) {
        try {
            DaoResponse response = getDao().create(data);
            if (response.isOk()) {
                return Response.ok(response.getId()).build();
            } else {
                return Response.ok(response.getValidationErrors()).build();
            }
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getById(String id) {
        try {
            return Response.ok(getMapper().mapFromEntityToDto((E) getDao().getByExtId(id))).build();
        } catch (Exception e) {
            return null;
        }
    }

    public Response delete(Long id) {
        try {
            E entity = (E) getDao().getById(id);
            getDao().delete(id);
            return Response.ok(entity.getExtId()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response update(D data, String id) {
        try {
            E oldEntity = (E) getDao().getByExtId(id);
            getMapper().updateEntityFromDto(oldEntity, data);
            getDao().update(oldEntity);
            return Response.ok(oldEntity.getExtId()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    public Response getAll() {
        List<D> dtos = new ArrayList<>();
        try {
            List<E> entities = getDao().getAll();
            for (E entity : entities) {
                dtos.add(getMapper().mapFromEntityToDto(entity));
            }
            return Response.ok(dtos).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

}
