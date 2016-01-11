package netcracker.app.wf.client.admin;

import netcracker.app.wf.back.model.User;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by ���������� on 05.12.2015.
 */

@Path("/service/users")
public interface UserService extends RestService {

    @GET
    @Path("/all")
    public void getAllUsers(MethodCallback<List<User>> callback);
    @GET
    @Path("/name/{name}")
    public void getUserByName(@PathParam("name") String name, MethodCallback<List<User>> callback);
    @GET
    @Path("/id/{id}")
    public void getUserById(@PathParam("id") int id, MethodCallback<User> callback);
    @POST
    @Path("/add-user")
    public void addUser(User user, MethodCallback<Void> callback);
    @POST
    @Path("/update-user")
    public void updateUser(User user, MethodCallback<Void> callback);
}
