package netcracker.app.wf.back.dao.user;


import netcracker.app.wf.back.model.User;

import java.util.List;

public interface UserDAO {
    User findById(int id);
    List<User> findByName(String name);
    User findByEmail(String email);
    User findByLogin(String login);
    List<User> findByLoginLike(String pattern);
    List<User> findByNameLike(String pattern);
    List<User> findByEmailLike(String pattern);
    List<User> findAll();
    void save(User user);
    void delete(User user);
    void update(User user);
}
