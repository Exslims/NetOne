package netcracker.app.wf.back.bo.task;


import netcracker.app.wf.back.dao.task.TaskDAO;
import netcracker.app.wf.back.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskBo")
public class TaskBOImpl implements TaskBO {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    public List<Task> findByTitle(String title) {
        return taskDAO.findByTitle(title);
    }

    @Override
    public Task findById(int id) {
        return taskDAO.findById(id);
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
}
