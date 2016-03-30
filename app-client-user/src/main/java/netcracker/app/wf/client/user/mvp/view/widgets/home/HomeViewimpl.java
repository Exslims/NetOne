package netcracker.app.wf.client.user.mvp.view.widgets.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import netcracker.app.wf.client.user.mvp.view.widgets.HomeView;

/**
 * Created by Константин on 29.01.2016.
 */
public class HomeViewImpl extends Composite implements HomeView {
    private HomeView.HomePresenter presenter;
    @Override
    public void setPresenter(HomePresenter presenter) {
        this.presenter = presenter;
    }

    interface HomeViewimplUiBinder extends UiBinder<Widget, HomeViewImpl> {
    }

    private static HomeViewimplUiBinder ourUiBinder = GWT.create(HomeViewimplUiBinder.class);

    public HomeViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}