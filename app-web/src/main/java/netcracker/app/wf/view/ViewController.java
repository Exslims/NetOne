package netcracker.app.wf.view;

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
    @RequestMapping("/{module}/")
    public String getView(@PathVariable("module") String module, HttpServletRequest httpServletRequest, Model model){
        model.addAllAttributes(httpServletRequest.getParameterMap());
        model.addAttribute("module",model);
        return "view." + module;
    }
}
