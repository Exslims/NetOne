package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.User;

/**
 * Created by ���������� on 03.12.2015.
 */
public interface AddUserView extends IsWidget {
    void setPresenter(AddUserPresenter presenter);
    void setCheckLoginLabel(String token);
    void setStatus(String token);

    interface AddUserPresenter{
        void addUser(User user);
        void checkAvailableLogin(String token);
    }
}
