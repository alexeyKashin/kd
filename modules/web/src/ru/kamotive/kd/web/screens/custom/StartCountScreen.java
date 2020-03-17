package ru.kamotive.kd.web.screens.custom;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import ru.kamotive.kd.entity.Task;
import ru.kamotive.kd.entity.TaskStatusEnum;

import javax.inject.Inject;

@UiController("kd_StartCountScreen")
@UiDescriptor("start-count-screen.xml")
public class StartCountScreen extends Screen {
    @Inject
    private PickerField<User> executorField;

    @Inject
    private Metadata metadata;

    @Inject
    private DataManager dataManager;
    @Inject
    private UserSession userSession;

    @Inject
    private Notifications notifications;

    @Subscribe("startProcessBtn")
    public void onStartProcessBtnClick(Button.ClickEvent event) {
        Task newTask = metadata.create(Task.class);
        newTask.setUser(executorField.getValue());
        newTask.setSummary("Получение исходных данных");
        newTask.setDescription("Получение исходных данных");
        newTask.setState(TaskStatusEnum.NEW);

        dataManager.commit(newTask);

        newTask = metadata.create(Task.class);
        newTask.setUser(executorField.getValue());
        newTask.setSummary("Проведение расчета");
        newTask.setDescription("Проведение расчета");
        newTask.setState(TaskStatusEnum.NEW);

        dataManager.commit(newTask);

        newTask = metadata.create(Task.class);
        newTask.setUser(executorField.getValue());
        newTask.setSummary("Выгрузка результатов");
        newTask.setDescription("Выгрузка результатов");
        newTask.setState(TaskStatusEnum.NEW);

        dataManager.commit(newTask);

        newTask = metadata.create(Task.class);
        newTask.setUser(userSession.getCurrentOrSubstitutedUser());
        newTask.setSummary("Приемка результата");
        newTask.setDescription("Приемка результата");
        newTask.setState(TaskStatusEnum.NEW);

        dataManager.commit(newTask);

        notifications.create().withCaption("Процесс запущен успешно!").show();
        closeWithDefaultAction();
    }


}