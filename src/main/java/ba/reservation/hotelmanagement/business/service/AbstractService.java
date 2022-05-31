package ba.reservation.hotelmanagement.business.service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractService<E>{

    private Class<E> entityClass;

    public AbstractService(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(E entity){
        getEntityManager().persist(entity);
    }

    public void edit(E entity){
        getEntityManager().merge(entity);
    }

    public void remove(E entity){
        getEntityManager().remove(entity);
    }

    public E find(Object id){
        return getEntityManager().find(entityClass, id);
    }

    public List<E> findAll(){
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
