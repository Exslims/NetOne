package netcracker.app.wf.client.admin.mvp.view.widgets.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import netcracker.app.wf.client.admin.mvp.view.widgets.HomeView;
import netcracker.app.wf.client.admin.style.GwtResource;

/**
 * Created by ���������� on 05.12.2015.
 */
public class HomeViewImpl extends Composite implements HomeView {
    private HomeView.HomePresenter presenter;

    interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
    }

    private static HomeViewImplUiBinder ourUiBinder = GWT.create(HomeViewImplUiBinder.class);

    @UiField
    GwtResource res;
    public HomeViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        res.style().ensureInjected();
    }

    @Override
    public void setPresenter(HomePresenter presenter) {
        this.presenter = presenter;
    }
}