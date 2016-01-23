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
        String footer();
        String menuLink();
        String menuWidget();
        String menuHead();
        String contentWidget();
        String contentHead();
        String sucNotify();
        String errorNotify();
        String notifyLabel();
        String table();
        String tableHead();
    }
}
