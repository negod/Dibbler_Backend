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
import se.dibbler.backend.dao.ErrorLogDao;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <D> Dto class
 * @param <E> Entity class
 * @param <DAO> DAO class
 */
public abstract class BaseWs<D extends BaseDto, E extends BaseEntity, DAO extends BaseDao> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseWs.class);

    public abstract DAO getDao();

    public abstract ErrorLogDao getErrorLog();

    public WsResponse<String> insert(D data) {
        try {
            if (data == null) {
                return Response.error(GenericError.WRONG_PARAMETER).getWsResponse();
            }
            Response<D> response = getDao().create(data);
            return getErrorLog().createLog(response).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to insert data into: {} ] Error : {}", getDao().getEntityClass().getSimpleName(), e.getMessage());
            return getErrorLog().createLog(Response.error(GenericError.UNHANDELED_EXCEPTION, e, "[ Failed to insert data into: " + getDao().getEntityClass().getSimpleName())).getWsResponse();
        }
    }

    public WsResponse<D> getById(String id) {
        try {
            Response<E> response = getDao().getByExtId(id);
            if (response.hasErrors) {
                getErrorLog().createLog(response).getWsResponse();
            }
            return getDao().getMapper().mapFromEntityToDto(response.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to get data with id: {} from: {}] Error : {} ", id, getDao().getEntityClass().getSimpleName(), e.getMessage());
            return getErrorLog().createLog(Response.error(GenericError.UNHANDELED_EXCEPTION, e, "[ Failed to get data with id: " + id + " from: " + getDao().getEntityClass().getSimpleName() + " ]")).getWsResponse();
        }
    }

    public WsResponse<String> delete(Long id) {
        try {
            Response<E> entity = getDao().getById(id);
            if (entity.hasErrors) {
                getErrorLog().createLog(entity).getWsResponse();
            }
            return getDao().delete(entity.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to delete data with id: {} from: {} ] Error : {} ", id, getDao().getEntityClass().getSimpleName(), e.getMessage());
            return getErrorLog().createLog(Response.error(GenericError.UNHANDELED_EXCEPTION, e, "[ Failed to delete data with id: " + id + " from: " + getDao().getEntityClass().getSimpleName() + " ]")).getWsResponse();
        }
    }

    public WsResponse<String> update(D data, String id) {
        try {
            Response<E> oldEntity = getDao().getByExtId(id);
            if (oldEntity.hasErrors) {
                getErrorLog().createLog(oldEntity).getWsResponse();
            }

            Response<E> updatedEntity = getDao().getMapper().updateEntityFromDto(oldEntity.getData(), data);
            if (updatedEntity.hasErrors) {
                getErrorLog().createLog(updatedEntity).getWsResponse();
            }

            return getDao().update(updatedEntity.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to update data with id: {} table: {} ] Error : {} ", id, getDao().getEntityClass().getSimpleName(), e.getMessage());
            return getErrorLog().createLog(Response.error(GenericError.UNHANDELED_EXCEPTION, e, "[ Failed to update data with id: " + id + " from: " + getDao().getEntityClass().getSimpleName() + " ]")).getWsResponse();
        }
    }

    public WsResponse<List<D>> getAll() {
        List<D> dtos = new ArrayList<>();
        try {
            Response<List<E>> entities = getDao().getAll();
            if (entities.hasErrors) {
                getErrorLog().createLog(entities).getWsResponse();
            }
            return getDao().getMapper().mapToDtoList(entities.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to get all data from {} ] Error : {} ", getDao().getEntityClass().getSimpleName(), e.getMessage());
            return getErrorLog().createLog(Response.error(GenericError.UNHANDELED_EXCEPTION, e, "[ FFailed to get all data from: " + getDao().getEntityClass().getSimpleName() + " ]")).getWsResponse();
        }

    }

}
