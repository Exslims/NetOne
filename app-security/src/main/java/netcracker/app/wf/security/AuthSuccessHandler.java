package netcracker.app.wf.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Константин on 27.01.2016.
 */
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger log = Logger.getLogger(AuthSuccessHandler.class);
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        log.trace("Enter to AuthSuccessHandler...");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        String targetUrl = "";
        log.trace("Role of current user: " + role);
        if(role.contains("USER")){
            targetUrl = "/view/user/";
        }else if(role.contains("ADMIN")){
            targetUrl = "/view/admin/";
        }
        log.trace("Return target url: " + targetUrl);
        return targetUrl;
    }
}
