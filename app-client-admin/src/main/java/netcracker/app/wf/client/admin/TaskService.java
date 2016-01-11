package netcracker.app.wf.client.admin;

import netcracker.app.wf.back.model.Task;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by ���������� on 06.12.2015.
 */
@Path("/service/tasks")
public interface TaskService extends RestService {
    @GET
    @Path("/all")
    public void getAllTasks(MethodCallback<List<Task>> callback);

    @GET
    @Path("/title/{title}")
    public void getTaskByTitle(@PathParam("title") String title, MethodCallback<List<Task>> callback);

    @GET
    @Path("/id/{id}")
    public void getTaskById(@PathParam("id") int id, MethodCallback<Task> callback);

}
