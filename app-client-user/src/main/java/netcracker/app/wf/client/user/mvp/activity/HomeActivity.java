package netcracker.app.wf.client.user.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.user.ClientFactory;
import netcracker.app.wf.client.user.mvp.view.widgets.HomeView;

/**
 * Created by Константин on 29.01.2016.
 */
public class HomeActivity extends AbstractActivity implements HomeView.HomePresenter {
    private ClientFactory factory;
    public HomeActivity(ClientFactory factory){
        this.factory = factory;
    }
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        HomeView homeView = factory.getHomeView();
        homeView.setPresenter(this);
        panel.setWidget(homeView.asWidget());
    }
}
