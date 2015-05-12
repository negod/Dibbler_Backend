/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.dao;

import se.dibbler.generic.dao.BaseMapper;
import se.dibbler.generic.entity.BaseEntity;
import se.dibbler.generic.control.Response;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.slf4j.Logger;
import se.dibbler.generic.dto.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E> The entity to persist
 * @param <D> The dto to maniulate before persisting
 */
public interface BaseDao<E extends BaseEntity, D extends BaseDto> {

    public EntityManager getEntityManager();
    
    public Session getHibernateSession();

    public Logger getLogger();

    public Response<String> create(E entity);

    public Response<String> createBatch(List<E> entityList);

    public Response<String> create(D dto);

    public Response<E> getByExtId(String id);

    public Response<E> getById(Long id);

    public Response<List<E>> getListByNamedQuery(String query, HashMap<String, ? extends Object> params);

    public Response<E> getSingleByNamedQuery(String query, HashMap<String, ? extends Object> params);

    public Response<List<E>> getByNativeQuery(String query);

    public Response<String> delete(E entity);

    public Response<String> delete(Long id);

    public Response<String> update(E entity);

    public Response<String> update(D dto, String extId);

    public Response<List<E>> getAll();

    public Response<Long> getId(String id);

    public Class getEntityClass();

    public BaseMapper<D, E> getMapper();

}
