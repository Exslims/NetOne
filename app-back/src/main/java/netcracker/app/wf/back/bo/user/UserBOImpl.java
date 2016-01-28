package netcracker.app.wf.back.bo.user;


import netcracker.app.wf.back.dao.user.UserDAO;
import netcracker.app.wf.back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userBo")
public class UserBOImpl implements UserBO {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public Boolean isLoginAvailable(String login) {
        User user = userDAO.findByLogin(login);
        return (user == null);
    }

    @Override
    public List<User> findByLoginLike(String pattern) {
        return userDAO.findByLoginLike(pattern);
    }

    @Override
    public List<User> findByNameLike(String pattern) {
        return userDAO.findByNameLike(pattern);
    }

    @Override
    public List<User> findByEmailLike(String pattern) {
        return userDAO.findByEmailLike(pattern);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
