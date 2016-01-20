package netcracker.app.wf.client.admin.style;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Константин on 20.01.2016.
 */
public interface GwtResource extends ClientBundle {
    @Source("netcracker/app/wf/css/stylesheets.css")
    Style style();


    public interface Style extends CssResource{
        String link();
        String mainPanel();
        String content();
        String hPanel();
        String footer();
        String contentPanel();
        String sucNotify();
        String errorNotify();
        String notifyLabel();
        String table();
    }
}
