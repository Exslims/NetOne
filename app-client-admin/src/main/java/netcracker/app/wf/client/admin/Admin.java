package netcracker.app.wf.client.admin;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import netcracker.app.wf.client.admin.layout.AppLayout;
import netcracker.app.wf.client.admin.mvp.place.HomePlace;
import org.fusesource.restygwt.client.Defaults;

/**
 * Created by ���������� on 01.12.2015.
 */
public class Admin implements EntryPoint {
    private SimplePanel containerWidget;
    private HomePlace defaultPlace = new HomePlace();
    public void onModuleLoad() {
        Defaults.setServiceRoot("/");

        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();


        AppLayout mainLayout = new AppLayout(clientFactory);
        containerWidget = mainLayout.getAppContentHolder();


        ActivityMapper activityMapper = new ActivityMapperImpl(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper,eventBus);
        activityManager.setDisplay(containerWidget);

        PlaceHistoryMapperImpl historyMapper = GWT.create(PlaceHistoryMapperImpl.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController,eventBus,defaultPlace);

        RootPanel.get("content").add(mainLayout);
        historyHandler.handleCurrentHistory();
    }
}
