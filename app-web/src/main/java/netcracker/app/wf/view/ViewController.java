package netcracker.app.wf.view;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Константин on 10.01.2016.
 */
@Controller
public class ViewController {
    private Logger logger = Logger.getLogger(ViewController.class);
    @RequestMapping("/{module}/")
    public String getView(@PathVariable("module") String module, HttpServletRequest httpServletRequest, Model model){
        model.addAllAttributes(httpServletRequest.getParameterMap());
        model.addAttribute("module",model);
        logger.trace("Getting " + module + " page");
        return "view." + module;
    }
}
