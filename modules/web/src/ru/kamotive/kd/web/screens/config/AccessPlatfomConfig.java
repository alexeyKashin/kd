package ru.kamotive.kd.web.screens.config;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("kd_AccessPlatfomConfig")
@UiDescriptor("access-platfom-config.xml")
public class AccessPlatfomConfig extends Screen {
    @Inject
    private Notifications notifications;

    @Subscribe("leftCallBtn")
    public void onLeftCallBtnClick(Button.ClickEvent event) {
        notifications.create().withCaption("Заявка заведена!").show();
        closeWithDefaultAction();
    }
}