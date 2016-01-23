package netcracker.app.wf.security;

import netcracker.app.wf.back.dao.user.UserDAO;
import netcracker.app.wf.back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

/**
 * Created by Константин on 17.01.2016.
 */
public class AppUserDetailsService implements UserDetailsService {
    private UserDAO dao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dao.findByLogin(s);
        AppUserDetails appUserDetails = new AppUserDetails(user.getLogin(),user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        appUserDetails.setCurrentUser(user);
        return appUserDetails;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
}
