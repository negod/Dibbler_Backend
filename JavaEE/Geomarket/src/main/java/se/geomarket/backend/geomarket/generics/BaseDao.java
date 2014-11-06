/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E> The entity to persist
 * @param <D> The dto to maniulate before persisting
 */
public interface BaseDao<E extends BaseEntity, D extends BaseDto> {

    public EntityManager getEntityManager();

    public DaoResponse create(E entity);

    public DaoResponse create(D dto);

    public E getByExtId(String id);

    public E getById(Long id);

    public List<E> getByNamedQuery(String query);

    public List<E> getByNativeQuery(String query);

    public void delete(Long id);

    public void update(E entity);

    public List<E> getAll();

}
