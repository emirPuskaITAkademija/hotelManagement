package ba.reservation.hotelmanagement.business.service;

import ba.reservation.hotelmanagement.business.model.Privilege;

import java.util.List;

public interface PrivilegeServiceLocal {
    List<Privilege> findAll();
}
