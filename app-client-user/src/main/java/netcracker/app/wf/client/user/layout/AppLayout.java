package netcracker.app.wf.client.user.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

/**
 * Created by Константин on 29.01.2016.
 */
public class AppLayout extends Composite {
    interface AppLayoutUiBinder extends UiBinder<Widget, AppLayout> {
    }

    private static AppLayoutUiBinder ourUiBinder = GWT.create(AppLayoutUiBinder.class);

    @UiField
    Label loggedUser;
    @UiField
    SimplePanel appContent;

    public AppLayout() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public SimplePanel getAppContent() {
        return appContent;
    }
}