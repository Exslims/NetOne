package netcracker.app.wf.service;

import netcracker.app.wf.back.bo.task.TaskBO;
import netcracker.app.wf.back.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    /*-------*/
    @Autowired
    private TaskBO taskBO;

    public TaskRestController() {
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public Task findById(@PathVariable int id) {
        return this.taskBO.findById(id);
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET, produces = "application/json")
    public List<Task> findByTitle(@PathVariable String title) {
        return this.taskBO.findByTitle(title);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Task> findAll() {
        return this.taskBO.findAll();
    }

}
