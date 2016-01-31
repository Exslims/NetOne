package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.User;

import java.util.List;

/**
 * Created by Константин on 31.01.2016.
 */
public interface EditUserVIew extends IsWidget {
    void setPresenter(EditUserPresenter presenter);
    void setCurrentUser(User user);
    void setStatus(String token);
    void fillAllField();

    interface EditUserPresenter{
        void updateUser(User user);
        void setCurrentUserOnView();
    }
}
