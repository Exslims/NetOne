package netcracker.app.wf.service;


import netcracker.app.wf.back.bo.user.UserBO;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.security.AppUserDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRestController extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserBO userBO;

    @RequestMapping(value = "/update-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        this.userBO.update(user);
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        this.userBO.delete(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/add-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user) {
        this.userBO.save(user);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public User findById(@PathVariable int id) {
       return this.userBO.findById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> findByName(@PathVariable String name) {
        return this.userBO.findByName(name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<User> findAll() {
        return this.userBO.findAll();
    }

    @RequestMapping(value = "/logged-user", method = RequestMethod.GET, produces = "application/json")
    public User loggedUser() {
        AppUserDetails details = (AppUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return details.getCurrentUser();
    }

    @RequestMapping(value = "/name-like/{pattern}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> findByNameLike(@PathVariable String pattern) {
        return this.userBO.findByNameLike(pattern);
    }

    @RequestMapping(value = "/check-available-login/{login}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public Boolean checkAvailableLogin(@PathVariable String login) {
        return this.userBO.isLoginAvailable(login);
    }

    @RequestMapping(value = "/login-like/{pattern}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> findByLoginLike(@PathVariable String pattern) {
        return this.userBO.findByLoginLike(pattern);
    }

    @RequestMapping(value = "/email-like/{pattern}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> findByEmailLike(@PathVariable String pattern) {
        return this.userBO.findByEmailLike(pattern);
    }

    @PostConstruct
    public void init(){
        logger.trace(this.getClass() + " was initialized");
    }
}