package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.mvp.view.widgets.HomeView;

/**
 * Created by ���������� on 04.12.2015.
 */
public class HomeActivity extends AbstractActivity implements HomeView.HomePresenter {
    private ClientFactory clientFactory;

    public HomeActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        HomeView homeView = clientFactory.getHomeView();
        homeView.setPresenter(this);
        panel.setWidget(homeView.asWidget());
    }
}
