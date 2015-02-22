/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E> The Entity to persist
 * @param <D> The Dto to manipulate before persisting as Entity
 */
public abstract class BaseDaoBean<E extends BaseEntity, D extends BaseDto> implements BaseDao<E, D> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseDaoBean.class);

    Class<E> entityClass;

    @Resource
    Validator validator;

    public BaseDaoBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @PersistenceContext(unitName = "geomarket_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class getEntityClass() {
        return entityClass;
    }

    @Override
    public abstract Response<String> create(D dto);

    @Override
    public abstract Response<String> update(D dto, String extId);

    private Response<String> getSQLIntegrityConstraintViolation(Throwable exception) {
        Throwable t = exception.getCause();
        while ((t != null) && !(t instanceof SQLIntegrityConstraintViolationException)) {
            t = t.getCause();
        }
        if (t instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException constaint = (SQLIntegrityConstraintViolationException) t;
            return Response.success(constaint.getMessage());
        }
        return Response.error(GenericError.FAILURE);
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public Response create(E entity) {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
            return Response.success(entity.getExtId());
        } catch (PersistenceException e) {

            Response<String> constraintError = getSQLIntegrityConstraintViolation(e);
            if (constraintError.hasNoErrors) {
                LOG.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: {}", e.getMessage());
                return Response.error(GenericError.CONSTRAINT_VIOLATION, constraintError.getData());
            }

        } catch (ConstraintViolationException ex) {
            LOG.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: {}", ex.getMessage());
            return Response.error(GenericError.CONSTRAINT_VIOLATION, buildViolationResponse(ex.getConstraintViolations()));
        } catch (Exception e) {
            LOG.error("[ Failed to create " + entityClass.getSimpleName() + " ] [ ERROR ] ", e.getMessage());
        }
        return Response.error(GenericError.CREATE);
    }

    @Override
    public Response<E> getById(Long id) {
        try {
            Query q = em.createNativeQuery("select * from " + entityClass.getSimpleName() + " where id = ?", entityClass);
            q.setParameter(1, id);
            return Response.success((E) q.getSingleResult());
        } catch (NoResultException e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [  ByID: " + id + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.NO_RESULT);
        } catch (Exception e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [  ByID: " + id + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response<E> getByExtId(String id) {
        try {
            Query q = em.createNativeQuery("select * from " + entityClass.getSimpleName() + " where extId = ?", entityClass);
            q.setParameter(1, id);
            return Response.success((E) q.getSingleResult());
        } catch (NoResultException e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [  ByExtID: " + id + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.NO_RESULT);
        } catch (Exception e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ ByExtID: " + id + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response<List<E>> getListByNamedQuery(String query, HashMap<String, ? extends Object> params) {
        try {
            Query q = getEntityManager().createNamedQuery(query, entityClass);

            for (Entry<String, ? extends Object> param : params.entrySet()) {

                if (param.getValue() instanceof Date) {
                    q.setParameter(param.getKey(), (Date) param.getValue(), TemporalType.TIMESTAMP);
                } else {
                    q.setParameter(param.getKey(), param.getValue());
                }
            }

            List<E> resultList = (List<E>) q.getResultList();

            if (resultList.isEmpty()) {
                LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: List empty");
                return Response.error(GenericError.NO_RESULT);
            } else {
                return Response.success(resultList);
            }

        } catch (NoResultException e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.NO_RESULT);
        } catch (Exception e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response<E> getSingleByNamedQuery(String query, HashMap<String, ? extends Object> params) {

        try {
            Query q = getEntityManager().createNamedQuery(query, entityClass);

            for (Entry<String, ? extends Object> param : params.entrySet()) {
                q.setParameter(param.getKey(), param.getValue());
            }

            return Response.success((E) q.getSingleResult());
        } catch (NoResultException e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.NO_RESULT);
        } catch (Exception e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.READ);
        }

    }

    @Override
    public Response<List<E>> getByNativeQuery(String query) {
        try {
            Query q = getEntityManager().createNativeQuery(query, entityClass);
            List<E> resultList = (List<E>) q.getResultList();

            if (resultList.isEmpty()) {
                LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By native query: " + query + " ] [ ERROR ]: List empty");
                return Response.error(GenericError.NO_RESULT);
            } else {
                return Response.success(resultList);
            }
        } catch (NoResultException e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By native query: " + query + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.NO_RESULT);
        } catch (Exception e) {
            LOG.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By native query: " + query + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response delete(E entity) {
        try {
            getEntityManager().remove(entity);
            getEntityManager().flush();
            return Response.success(null);
        } catch (PersistenceException e) {

            Response<String> constraintError = getSQLIntegrityConstraintViolation(e);
            if (constraintError.hasNoErrors) {
                LOG.error("[ Failed to delete " + entityClass.getSimpleName() + " with ID: {} ] due to constraint violations [ ERROR ]: {}", entity.getId(), e.getMessage());
                return Response.error(GenericError.CONSTRAINT_VIOLATION, constraintError.getData());
            }

        } catch (ConstraintViolationException ex) {
            LOG.error("[ Failed to delete " + entityClass.getSimpleName() + " with ID: {} ] due to constraint violations [ ERROR ]: {}", entity.getId(), ex.getMessage());
            return Response.error(GenericError.CONSTRAINT_VIOLATION, buildViolationResponse(ex.getConstraintViolations()));
        } catch (Exception e) {
            LOG.error("[ Failed to delete " + entityClass.getSimpleName() + " ] [ Id: " + entity.getId() + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.DELETE);
        }
        return Response.error(GenericError.DELETE);
    }

    @Override
    public Response<String> delete(Long id) {
        try {
            Response<E> entity = this.getById(id);
            if (entity.hasErrors) {
                return Response.error(entity.getError());
            }
            return this.delete(entity.getData());
        } catch (Exception e) {
            LOG.error("[ Failed to delete " + entityClass.getSimpleName() + " ] [ Id: " + id + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.DELETE);
        }
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public Response update(E entity) {
        try {
            getEntityManager().merge(entity);
            getEntityManager().flush();
            return Response.success(entity.getExtId());
        } catch (ConstraintViolationException ex) {
            LOG.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: {}", ex.getMessage());
            return Response.error(GenericError.CONSTRAINT_VIOLATION, buildViolationResponse(ex.getConstraintViolations()));
        } catch (Exception e) {
            LOG.error("[ Failed to update " + entityClass.getSimpleName() + " ] [ Id: " + entity.getId() + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.UPDATE, "Error when updating data in database");
        }
    }

    @Override
    public Response<List<E>> getAll() {
        try {
            Query q = em.createQuery("select d from " + entityClass.getSimpleName() + " d", entityClass);
            List<E> resultList = (List<E>) q.getResultList();

            if (resultList.isEmpty()) {
                LOG.error("[ Failed to get all from  " + entityClass.getSimpleName() + " ] [ ERROR ]: List empty");
                return Response.error(GenericError.NO_RESULT);
            } else {
                return Response.success(resultList);
            }
        } catch (Exception e) {
            LOG.error("[ Failed to get all from " + entityClass.getSimpleName() + " ] [ ERROR ]: {}", e.getMessage());
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response<Long> getId(String id) {
        try {
            Query q = em.createQuery("select id from " + entityClass.getSimpleName() + " d where extid = ?", Long.class);
            q.setParameter(1, id);
            return Response.success((Long) q.getSingleResult());
        } catch (NonUniqueResultException e) {
            LOG.error("[ Failed to get id from " + entityClass.getSimpleName() + " with extid {} ] [ ERROR ]: {}", id, e.getMessage());
            return Response.error(GenericError.READ);
        } catch (Exception e) {
            LOG.error("[ Failed to get id from " + entityClass.getSimpleName() + " with extid {} ] [ ERROR ]: {}", id, e.getMessage());
            return Response.error(GenericError.READ);
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
