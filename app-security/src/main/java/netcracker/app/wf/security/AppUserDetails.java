package netcracker.app.wf.security;

import org.springframework.security.core.GrantedAuthority;
import netcracker.app.wf.back.model.User;

import java.util.Collection;

/**
 * Created by Константин on 17.01.2016.
 */
public class AppUserDetails extends org.springframework.security.core.userdetails.User {
    private User currentUser;
    public AppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AppUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
