/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoBean.class);

    Class<E> entityClass;

    @Resource
    Validator validator;

    public BaseDaoBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
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
    public abstract MethodResponse<String> create(D dto);

    private MethodResponse<String> getSQLIntegrityConstraintViolation(Throwable exception) {
        Throwable t = exception.getCause();
        while ((t != null) && !(t instanceof SQLIntegrityConstraintViolationException)) {
            t = t.getCause();
        }
        if (t instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException constaint = (SQLIntegrityConstraintViolationException) t;
            return MethodResponse.success(constaint.getMessage());
        }
        return MethodResponse.error(GenericError.FAILURE);
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public MethodResponse create(E entity) {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
            return MethodResponse.success(entity.getExtId());
        } catch (PersistenceException e) {

            MethodResponse<String> constraintError = getSQLIntegrityConstraintViolation(e);
            if (constraintError.hasNoErrors) {
                LOGGER.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: ", e);
                return MethodResponse.error(GenericError.CONSTRAINT_VIOLATION, constraintError.getData());
            }

        } catch (ConstraintViolationException ex) {
            LOGGER.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: ", ex);
            return MethodResponse.error(GenericError.CONSTRAINT_VIOLATION, buildViolationResponse(ex.getConstraintViolations()));
        } catch (Exception e) {
            LOGGER.error("[ Failed to create " + entityClass.getSimpleName() + " ] [ ERROR ] ", e);
        }
        return MethodResponse.error(GenericError.CREATE);
    }

    @Override
    public MethodResponse getById(Long id) {
        try {
            Query q = em.createNativeQuery("select * from " + entityClass.getSimpleName() + " where id = ?", entityClass);
            q.setParameter(1, id);
            return MethodResponse.success((E) q.getSingleResult());
        } catch (Exception e) {
            LOGGER.error("[ Failed to get " + entityClass.getSimpleName() + " ] [  ByID: " + id + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
        }
    }

    @Override
    public MethodResponse getByExtId(String id) {
        try {
            Query q = em.createNativeQuery("select * from " + entityClass.getSimpleName() + " where extId = ?", entityClass);
            q.setParameter(1, id);
            return MethodResponse.success((E) q.getSingleResult());
        } catch (Exception e) {
            LOGGER.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ ByExtID: " + id + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
        }
    }

    @Override
    public MethodResponse getByNamedQuery(String query) {
        try {
            Query q = getEntityManager().createNamedQuery(query, entityClass);
            return MethodResponse.success((List<E>) q.getResultList());
        } catch (Exception e) {
            LOGGER.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By named query: " + query + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
        }
    }

    @Override
    public MethodResponse getByNativeQuery(String query) {
        try {
            Query q = getEntityManager().createNativeQuery(query, entityClass);
            return MethodResponse.success((List<E>) q.getResultList());
        } catch (Exception e) {
            LOGGER.error("[ Failed to get " + entityClass.getSimpleName() + " ] [ By native query: " + query + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
        }
    }

    @Override
    public MethodResponse<String> delete(E entity) {
        try {
            getEntityManager().remove(entity);
            return MethodResponse.success(null);
        } catch (Exception e) {
            LOGGER.error("[ Failed to delete " + entityClass.getSimpleName() + " ] [ Id: " + entity.getId() + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.DELETE, "Error when deleting data in database");
        }
    }

    @Override
    @TransactionAttribute(REQUIRES_NEW)
    public MethodResponse update(E entity) {
        try {
            getEntityManager().merge(entity);
            getEntityManager().flush();
            return MethodResponse.success(entity.getExtId());
        } catch (ConstraintViolationException ex) {
            LOGGER.error("[ Failed to create " + entityClass.getSimpleName() + " ] due to constraint violations [ ERROR ]: ", ex);
            return MethodResponse.error(GenericError.CONSTRAINT_VIOLATION, buildViolationResponse(ex.getConstraintViolations()));
        } catch (Exception e) {
            LOGGER.error("[ Failed to update " + entityClass.getSimpleName() + " ] [ Id: " + entity.getId() + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.UPDATE, "Error when updating data in database");
        }
    }

    @Override
    public MethodResponse getAll() {
        try {
            Query q = em.createQuery("select d from " + entityClass.getSimpleName() + " d", entityClass);
            return MethodResponse.success((List<E>) q.getResultList());
        } catch (Exception e) {
            LOGGER.error("[ Failed to get all from " + entityClass.getSimpleName() + " ] [ ERROR ]: ", e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
        }
    }

    @Override
    public MethodResponse getId(String id) {
        try {
            Query q = em.createQuery("select id from " + entityClass.getSimpleName() + " d where extid = ?", Long.class);
            q.setParameter(1, id);
            return MethodResponse.success((Long) q.getSingleResult());
        } catch (NonUniqueResultException e) {
            LOGGER.error("[ Failed to get id from " + entityClass.getSimpleName() + " with extid {} ] [ ERROR ]: ", id, e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
        } catch (Exception e) {
            LOGGER.error("[ Failed to get id from " + entityClass.getSimpleName() + " with extid {} ] [ ERROR ]: ", id, e);
            return MethodResponse.error(GenericError.READ, "Error when retrieving data from database");
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
