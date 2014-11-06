/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E> The Entity to persist
 * @param <D> The Dto to manipulate before persisting as Entity
 */
public abstract class BaseDaoBean<E extends BaseEntity, D extends BaseDto> implements BaseDao<E, D> {

    Logger logger = LoggerFactory.getLogger(BaseDaoBean.class);

    Class<E> entityClass;

    @Resource
    Validator validator;

    public BaseDaoBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @PersistenceContext(unitName = "geomarket_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public abstract DaoResponse create(D dto);

    @Override
    public DaoResponse create(E entity) {
        try {
            getEntityManager().persist(entity);
            return new DaoResponse(entity.getExtId());
        } catch (ConstraintViolationException ex) {
            logger.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: {} ", ex);
            return new DaoResponse(buildViolationResponse(ex.getConstraintViolations()));
        } catch (Exception e) {
            logger.error("[ Failed to create " + entityClass.getSimpleName() + " ] [ ERROR ] {} ", e);
        }
        return new DaoResponse("Failed to create " + entityClass.getSimpleName());
    }

    @Override
    public E getById(Long id) {
        try {
            Query q = em.createNamedQuery("select d from " + entityClass.getSimpleName() + " where d.id =:id", entityClass);
            q.setParameter("id", id);
            return (E) q.getSingleResult();
        } catch (Exception e) {
            logger.error("[ Failed to get " + entityClass.getSimpleName() + " ] [  ByID: " + id + " ] [ ERROR ]: {}", e);
            return null;
        }
    }

    @Override
    public E getByExtId(String id) {
        try {
            Query q = em.createNativeQuery("select * from " + entityClass.getSimpleName() + " where extId = ?", entityClass);
            q.setParameter(1, id);
            return (E) q.getSingleResult();
        } catch (Exception e) {
            logger.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ ByExtID: " + id + " ] [ ERROR ]: {}", e);
            return null;
        }
    }

    @Override
    public List<E> getByNamedQuery(String query) {
        try {
            Query q = getEntityManager().createNamedQuery(query, entityClass);
            return q.getResultList();
        } catch (Exception e) {
            logger.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: {}", e);
            return null;
        }
    }

    @Override
    public List<E> getByNativeQuery(String query) {
        try {
            Query q = getEntityManager().createNativeQuery(query, entityClass);
            return q.getResultList();
        } catch (Exception e) {
            logger.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By native query: " + query + " ] [ ERROR ]: {}", e);
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            E entity = getEntityManager().find(entityClass, id);
            getEntityManager().remove(entity);
        } catch (Exception e) {
            logger.error("[ Failed to delete " + entityClass.getSimpleName() + " ] [ Id: " + id + " ] [ ERROR ]: {}", e);
        }
    }

    @Override
    public void update(E entity) {
        try {

            getEntityManager().merge(entity);
        } catch (Exception e) {
            logger.error("[ Failed to update " + entityClass.getSimpleName() + " ] [ Id: " + entity.getId() + " ] [ ERROR ]: {}", e);
        }
    }

    @Override
    public List<E> getAll() {
        try {
            Query q = em.createQuery("select d from " + entityClass.getSimpleName() + " d", entityClass);
            return q.getResultList();
        } catch (Exception e) {
            logger.error("[ Failed to get all from " + entityClass.getSimpleName() + " ] [ ERROR ]: {}", e);
            return null;
        }
    }

    private Set<String> buildViolationResponse(Set<ConstraintViolation<?>> constaints) {
        Set<String> validations = new HashSet<>();
        if (!constaints.isEmpty()) {
            for (ConstraintViolation<?> val : constaints) {
                validations.add(val.getPropertyPath().toString() + " " + val.getMessage());
            }
        }
        return validations;
    }

}
