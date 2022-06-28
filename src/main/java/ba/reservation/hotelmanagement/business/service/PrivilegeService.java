package ba.reservation.hotelmanagement.business.service;

import ba.reservation.hotelmanagement.business.model.Privilege;
import ba.reservation.hotelmanagement.commons.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

 class PrivilegeService extends AbstractService<Privilege, Integer> implements PrivilegeServiceLocal{
    public PrivilegeService() {
        super(Privilege.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
