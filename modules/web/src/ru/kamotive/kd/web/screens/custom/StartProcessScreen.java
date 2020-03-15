package ru.kamotive.kd.web.screens.custom;

import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kamotive.kd.core.StartProcessService;
import ru.kamotive.kd.dto.CamundaProcessResult;
import ru.kamotive.kd.dto.ProcessResult;

import javax.inject.Inject;

@UiController("kd_StartProcessScreen")
@UiDescriptor("start-process-screen.xml")
public class StartProcessScreen extends Screen {
    private Logger log = LoggerFactory.getLogger(StartProcessScreen.class);

    @Inject
    private TextField<String> innField;
    @Inject
    private PickerField<User> userField;
    @Inject
    private Dialogs dialogs;
    @Inject
    private UserSession userSession;
    @Inject
    private StartProcessService startProcessService;
    @Inject
    private Notifications notifications;

    @Subscribe("startProcessBtn")
    public void onStartProcessBtnClick(Button.ClickEvent event) {
        User user = userField.getValue();
        if (user == null) {
            dialogs.createMessageDialog().withCaption("Внимание").withMessage("Не выбран получатель").show();
            return;
        }

        if (user.getId().equals(userSession.getCurrentOrSubstitutedUser().getId())) {
            dialogs.createMessageDialog().withCaption("Внимание").withMessage("Получатель совпадает с отправителем").show();
            return;
        }

        CamundaProcessResult result = startProcessService
                .startProcess(userSession.getCurrentOrSubstitutedUser(), user, innField.getValue());
        if (result.getResult().equals(ProcessResult.OK)) {
            notifications.create().withCaption("Процесс успешно запущен")
                    .withPosition(Notifications.Position.BOTTOM_RIGHT).show();
        } else {
            notifications.create().withCaption("Ошибка при запуске процесса. Инофрмация выведена в лог")
                    .withPosition(Notifications.Position.BOTTOM_RIGHT).show();
            log.error(result.getMessage());
        }
        closeWithDefaultAction();

    }
}