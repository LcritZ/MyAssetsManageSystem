package dao;

import entity.User;

import java.util.List;

/**
 * Created by Lcrit_Z on 2017/5/5.
 */
public interface UserDao {
    boolean isRegester(User user);

     List<User> getAllUser();

    boolean addUser(User user);

    boolean deleteUser(String name);
}
