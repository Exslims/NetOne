package netcracker.app.wf.back.dao.user;



import netcracker.app.wf.back.hibernate.HibernateUtils;
import netcracker.app.wf.back.model.Role;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public User findById(int id) {
        logger.trace("Searching user by id = " + id);
        Session currentSession =  sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = (User) currentSession.createQuery("from User user where user.id = :user_id ")
                .setParameter("user_id", id).uniqueResult();
        transaction.commit();
        return user;
    }

    @Override
    public List<User> findByName(String name) {
        logger.trace("Searching users by name = " + name);
        Session currentSession =  sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<User> users = HibernateUtils.cast(currentSession.createQuery("from User user where user.name = :username")
                .setParameter("username", name));
        transaction.commit();
        return users;

    }

    @Override
    public User findByEmail(String email) {
        logger.trace("Searching users by email = " + email);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = (User) currentSession.createQuery("from User user where user.email = :email")
                .setParameter("email", email).uniqueResult();
        transaction.commit();
        return user;
    }

    @Override
    public User findByLogin(String login) {
        logger.trace("Searching users by login = " + login);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = (User) currentSession.createQuery("from User user where user.login = :login")
                .setParameter("login", login).uniqueResult();
        transaction.commit();
        return user;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        logger.trace("Selecting all users...");
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<User> userList = HibernateUtils.cast(currentSession.createQuery("from User user"));
        transaction.commit();
        return userList;
    }

    @Override
    public void save(User user) {
        logger.trace("Saving  " + user.toString());
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(user);
        transaction.commit();
        logger.trace("Saved user: " + user.toString());
    }

    @Override
    public void delete(User user) {
        logger.trace("Removing " + user.toString());
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(user);
        transaction.commit();
        logger.trace("User was removed successful");
    }

    @Override
    public void update(User user) {
        logger.trace("Updating  " + user.toString());
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        Set<Role> roles = user.getRoles();
        for(Role role : roles)
            role.setUser(user);

        Set<Task> tasks = user.getTasks();
        for (Task task : tasks)
            task.setUser(user);

        currentSession.update(user);
        transaction.commit();
        logger.trace("Updated user: " + user.toString());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
