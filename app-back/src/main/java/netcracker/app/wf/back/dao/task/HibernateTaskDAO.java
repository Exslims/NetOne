package netcracker.app.wf.back.dao.task;

import netcracker.app.wf.back.hibernate.HibernateUtils;
import netcracker.app.wf.back.model.Task;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HibernateTaskDAO implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<Task> findAll() {
        Session currentSession =  sessionFactory.getCurrentSession();
        return HibernateUtils.cast(currentSession.createQuery("from Task task"));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<Task> findByTitle(String title) {
        logger.trace("Searching task by title = " + title);
        Session currentSession =  sessionFactory.getCurrentSession();
        return HibernateUtils.cast(currentSession
                .createQuery("from Task task where task.title = :task_title ")
                .setParameter("task_title", title));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public Task findById(int id) {
        logger.trace("Searching task by id = " + id);
        Session currentSession =  sessionFactory.getCurrentSession();
        return (Task) currentSession
                .createQuery("from Task task where task.id = :task_id ")
                .setInteger("task_id", id).uniqueResult();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
