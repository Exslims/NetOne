package netcracker.app.wf.client.user;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import netcracker.app.wf.client.user.mvp.view.widgets.HomeView;

/**
 * Created by Константин on 29.01.2016.
 */
public interface ClientFactory {
    EventBus getEventBus();
    PlaceController getPlaceController();

    HomeView getHomeView();
}
