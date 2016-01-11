package netcracker.app.wf.service;


import netcracker.app.wf.back.dao.user.UserDAO;
import netcracker.app.wf.back.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRestController extends HttpServlet {
    Logger logger = Logger.getLogger(UserRestController.class);
    @Autowired
    private volatile UserDAO userDAO;

    @RequestMapping(value = "/update-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        this.userDAO.update(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/add-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user) {
        this.userDAO.save(user);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public User findById(@PathVariable int id) {
       return this.userDAO.findById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> findByName(@PathVariable String name) {
        return this.userDAO.findByName(name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @PostConstruct
    public void init(){
        logger.trace("User rest controller was initilize");
    }

}