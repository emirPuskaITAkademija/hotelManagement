package ba.reservation.hotelmanagement.business.service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractService<E> {

    private Class<E> entityClass;

    public AbstractService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(E entity) {
        EntityManager entityManager = getEntityManager();
        // begin transaction
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entity)) {
            // persist object - add to entity manager
            entityManager.persist(entity);
            // flush em - save to DB
            entityManager.flush();
        }
        // commit transaction at all
        entityManager.getTransaction().commit();
    }

    public void edit(E entity) {
        getEntityManager().merge(entity);
    }

    public void remove(E entity) {
        getEntityManager().remove(entity);
    }

    public E find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<E> findAll() {
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
