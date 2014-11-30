/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

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

    public MethodResponse<String> create(E entity);

    public MethodResponse<String> create(D dto);

    public MethodResponse<E> getByExtId(String id);

    public MethodResponse<E> getById(Long id);

    public MethodResponse<List<E>> getByNamedQuery(String query);

    public MethodResponse<List<E>> getByNativeQuery(String query);

    public MethodResponse<String> delete(E entity);

    public MethodResponse<String> update(E entity);

    public MethodResponse<List<E>> getAll();

    public MethodResponse<Long> getId(String id);

    public Class getEntityClass();

}
