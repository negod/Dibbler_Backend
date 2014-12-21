/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import org.slf4j.Logger;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E> The entity to persist
 * @param <D> The dto to maniulate before persisting
 */
public interface BaseDao<E extends BaseEntity, D extends BaseDto> {

    public EntityManager getEntityManager();

    public Logger getLogger();

    public Response<String> create(E entity);

    public Response<String> create(D dto);

    public Response<E> getByExtId(String id);

    public Response<E> getById(Long id);

    public Response<List<E>> getListByNamedQuery(String query, HashMap<String, ? extends Object> params);

    public Response<E> getSingleByNamedQuery(String query, HashMap<String, ? extends Object> params);

    public Response<List<E>> getByNativeQuery(String query);

    public Response<String> delete(E entity);

    public Response<String> update(E entity);

    public Response<List<E>> getAll();

    public Response<Long> getId(String id);

    public Class getEntityClass();

}
