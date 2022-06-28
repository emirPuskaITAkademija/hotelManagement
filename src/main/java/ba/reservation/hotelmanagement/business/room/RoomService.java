package ba.reservation.hotelmanagement.business.room;

import ba.reservation.hotelmanagement.business.model.Room;
import ba.reservation.hotelmanagement.business.service.AbstractService;
import ba.reservation.hotelmanagement.commons.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RoomService extends AbstractService<Room, Integer> {
    RoomService() {
        super(Room.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
