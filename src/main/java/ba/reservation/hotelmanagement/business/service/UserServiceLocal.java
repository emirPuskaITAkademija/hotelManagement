package ba.reservation.hotelmanagement.business.service;

import ba.reservation.hotelmanagement.business.model.User;

import java.util.List;

public interface UserServiceLocal {
    /**
     * Login method logic
     *
     * @param username
     * @param password
     * @return user or null if user not exist
     */
    User login(String username, String password);

    List<User> findAll();

    void create(User user);

    void edit(User user);

    void remove(User user);

    void removeById(Integer id);

    User find(Integer id);
}
