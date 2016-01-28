package netcracker.app.wf.back.bo.task;


import netcracker.app.wf.back.model.Task;

import java.util.List;

public interface TaskBO {
    List<Task> findAll();
    List<Task> findByTitle(String title);
    Task findById(int id);
}
