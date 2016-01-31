package netcracker.app.wf.back.dao.user;



import netcracker.app.wf.back.hibernate.HibernateUtils;
import netcracker.app.wf.back.model.Role;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public User findById(int id) {
        logger.trace("Searching user by id = " + id);
        Session currentSession =  sessionFactory.getCurrentSession();
        return (User) currentSession.createQuery("from User user where user.id = :user_id ")
                .setParameter("user_id", id).uniqueResult();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<User> findByName(String name) {
        logger.trace("Searching users by name = " + name);
        Session currentSession =  sessionFactory.getCurrentSession();
        return HibernateUtils.cast(currentSession.createQuery("from User user where user.name = :username")
                .setParameter("username", name));

    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public User findByEmail(String email) {
        logger.trace("Searching users by email = " + email);
        Session currentSession = sessionFactory.getCurrentSession();
        return (User) currentSession.createQuery("from User user where user.email = :email")
                .setParameter("email", email).uniqueResult();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public User findByLogin(String login) {
        logger.trace("Searching users by login = " + login);
        Session currentSession = sessionFactory.getCurrentSession();
        return (User) currentSession.createQuery("from User user where user.login = :login")
                .setParameter("login", login).uniqueResult();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<User> findByLoginLike(String pattern) {
        logger.trace("Trying to find users with login like " + pattern);
        Session currentSession = sessionFactory.getCurrentSession();
        return HibernateUtils.cast(
                currentSession.createQuery("from User user where user.login like :login")
                        .setParameter("login", "%" + pattern + "%")
        );
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<User> findByNameLike(String pattern) {
        logger.trace("Trying to find users with name like " + pattern);
        Session currentSession = sessionFactory.getCurrentSession();
        return HibernateUtils.cast(
                currentSession.createQuery("from User user where user.name like :name")
                        .setParameter("name", "%" + pattern + "%")
        );
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<User> findByEmailLike(String pattern) {
        logger.trace("Trying to find users with email like " + pattern);
        Session currentSession = sessionFactory.getCurrentSession();
        return HibernateUtils.cast(
                currentSession.createQuery("from User user where user.email like :email")
                        .setParameter("email" , "%" + pattern + "%")
        );
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<User> findAll() {
        logger.trace("Selecting all users...");
        Session currentSession = sessionFactory.getCurrentSession();
        return HibernateUtils.cast(currentSession.createQuery("from User user"));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT , timeout = 1000, rollbackFor = Throwable.class)
    public void save(User user) {
        logger.trace("Saving  " + user.toString());
        Session currentSession = sessionFactory.getCurrentSession();
        resetRelations(user);
        currentSession.persist(user);
        logger.trace("Saved user: " + user.toString());
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT , timeout = 1000, rollbackFor = Throwable.class)
    public void delete(User user) {
        logger.trace("Removing " + user.toString());
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(user);
        logger.trace("User was removed successful");
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT , timeout = 1000, rollbackFor = Throwable.class)
    public void update(User user) {
        logger.trace("Updating  " + user.toString());
        Session currentSession = sessionFactory.getCurrentSession();
        resetRelations(user);
        currentSession.merge(user);
        logger.trace("Updated user: " + user.toString());
    }

    private void resetRelations(User user) {
        Set<Role> roles = user.getRoles();
        if (roles != null)
        for(Role role : roles)
            role.setUser(user);
        Set<Task> tasks = user.getTasks();
        if (tasks != null)
        for (Task task : tasks)
            task.setUser(user);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
