package netcracker.app.wf.client.user;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import netcracker.app.wf.client.user.mvp.view.widgets.HomeView;
import netcracker.app.wf.client.user.mvp.view.widgets.home.HomeViewimpl;

/**
 * Created by Константин on 29.01.2016.
 */
public class ClientFactoryImpl implements ClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);

    private final HomeView homeView = new HomeViewimpl();
    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public HomeView getHomeView() {
        return homeView;
    }
}
