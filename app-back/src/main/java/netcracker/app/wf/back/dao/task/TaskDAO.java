package netcracker.app.wf.back.dao.task;


import netcracker.app.wf.back.model.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> findAll();
    List<Task> findByTitle(String title);
    Task findById(int id);
}
