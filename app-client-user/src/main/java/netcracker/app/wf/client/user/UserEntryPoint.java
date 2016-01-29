package netcracker.app.wf.client.user;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import netcracker.app.wf.client.user.layout.AppLayout;
import netcracker.app.wf.client.user.mvp.place.HomePlace;
import org.fusesource.restygwt.client.Defaults;

/**
 * Created by Константин on 29.01.2016.
 */
@SuppressWarnings("all")
public class UserEntryPoint implements EntryPoint {
    private SimplePanel containerWidget;
    private HomePlace defaultPlace = new HomePlace();
    @Override
    public void onModuleLoad() {
        Defaults.setServiceRoot("/");

        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

        AppLayout mainLayout = new AppLayout();
        containerWidget = mainLayout.getAppContent();

        ActivityMapper activityMapper = new ActivityMapperImpl(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper,eventBus);
        activityManager.setDisplay(containerWidget);

        UserPlaceHistoryMapper historyMapper = GWT.create(UserPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController,eventBus,defaultPlace);

        RootPanel.get("userContent").add(mainLayout);
        historyHandler.handleCurrentHistory();
    }
}
