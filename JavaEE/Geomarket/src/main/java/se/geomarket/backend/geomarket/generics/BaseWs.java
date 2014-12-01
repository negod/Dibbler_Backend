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
 * @param <D> Dto class
 * @param <E> Entity class
 * @param <DAO> DAO class
 */
public abstract class BaseWs<D extends BaseDto, E extends BaseEntity, DAO extends BaseDao> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseWs.class);

    public abstract DAO getDao();

    public abstract BaseMapper<D, E> getMapper();

    public WsResponse insert(D data) {
        try {
            if (data == null) {
                return MethodResponse.error(GenericError.WRONG_PARAMETER).getWsResponse();
            }
            return getDao().create(data).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to insert data into: {} ] Error : {}", getDao().getEntityClass().getSimpleName(), e);
            return MethodResponse.error(GenericError.UNHANDELED_EXCEPTION).getWsResponse();
        }
    }

    public WsResponse getById(String id) {
        try {
            MethodResponse<E> response = getDao().getByExtId(id);
            if (response.hasErrors) {
                return response.getWsResponse();
            }

            return getMapper().mapFromEntityToDto(response.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to get data with id: {} from: {}] Error : {} ", id, getDao().getEntityClass().getSimpleName(), e);
            return MethodResponse.error(GenericError.UNHANDELED_EXCEPTION).getWsResponse();
        }
    }

    public WsResponse delete(Long id) {
        try {
            MethodResponse<E> entity = getDao().getById(id);
            if (entity.hasErrors) {
                return entity.getWsResponse();
            }

            return getDao().delete(entity.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to delete data with id: {} from: {} ] Error : {} ", id, getDao().getEntityClass().getSimpleName(), e);
            return MethodResponse.error(GenericError.UNHANDELED_EXCEPTION).getWsResponse();
        }
    }

    public WsResponse update(D data, String id) {
        try {
            MethodResponse<E> oldEntity = getDao().getByExtId(id);
            if (oldEntity.hasErrors) {
                return oldEntity.getWsResponse();
            }

            MethodResponse<E> updatedEntity = getMapper().updateEntityFromDto(oldEntity.getData(), data);
            if (updatedEntity.hasErrors) {
                return updatedEntity.getWsResponse();
            }

            return getDao().update(oldEntity.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to update data with id: {} table: {} ] Error : {} ", id, getDao().getEntityClass().getSimpleName(), e);
            return MethodResponse.error(GenericError.UNHANDELED_EXCEPTION).getWsResponse();
        }
    }

    public WsResponse getAll() {
        List<D> dtos = new ArrayList<>();
        try {
            MethodResponse<List<E>> entities = getDao().getAll();
            if (entities.hasErrors) {
                return entities.getWsResponse();
            }

            return getMapper().mapToDtoList(entities.getData()).getWsResponse();
        } catch (Exception e) {
            LOGGER.error("[ Failed to get all data from {} ] Error : {} ", getDao().getEntityClass().getSimpleName(), e);
            return MethodResponse.error(GenericError.UNHANDELED_EXCEPTION).getWsResponse();
        }

    }

}
