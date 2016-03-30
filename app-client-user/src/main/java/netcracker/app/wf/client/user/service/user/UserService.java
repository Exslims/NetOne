package netcracker.app.wf.client.user.service.user;

import netcracker.app.wf.back.model.User;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Константин on 29.01.2016.
 */
@Path("/service/users")
public interface UserService extends RestService {
    @GET
    @Path("/logged-user")
    public void getLoggedUser(MethodCallback<User> callback);
}
