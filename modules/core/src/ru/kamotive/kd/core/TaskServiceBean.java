package ru.kamotive.kd.core;

import com.google.gson.internal.$Gson$Types;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.entity.Task;
import ru.kamotive.kd.entity.TaskStatusEnum;

import javax.inject.Inject;

@Service(TaskService.NAME)
public class TaskServiceBean implements TaskService {
    @Inject
    private Metadata metadata;
    @Inject
    private Persistence persistence;

    @Override
    public void proceedCloseTask(Task task) {
        if (task.getBpCode() == null || task.getTaskCode() == null)
            return;

        persistence.runInTransaction(em -> {
            switch (task.getBpCode()) {
                case "process":
                    closeProcessTask(task);
                    break;
                case "count":
                    closeCountTask(task);
                    break;
                default:
                    break;
            }
        });
    }

    private void closeCountTask(Task task) {
        EntityManager em = persistence.getEntityManager();

        Task newTask = metadata.create(Task.class);

        switch (task.getTaskCode()) {
            case "getData":
                newTask.setUser(task.getUser());
                newTask.setSummary("Проведение расчета");
                newTask.setDescription("Проведение расчета");
                newTask.setState(TaskStatusEnum.NEW);
                newTask.setInitiator(task.getInitiator());
                newTask.setBpCode(task.getBpCode());
                newTask.setTaskCode("doCalc");
                em.persist(newTask);
                break;
            case "doCalc":
                newTask.setUser(task.getUser());
                newTask.setSummary("Выгрузка результатов");
                newTask.setDescription("Выгрузка результатов");
                newTask.setState(TaskStatusEnum.NEW);
                newTask.setInitiator(task.getInitiator());
                newTask.setBpCode(task.getBpCode());
                newTask.setTaskCode("loadResult");
                em.persist(newTask);
                break;
            case "loadResult":
                newTask.setUser(task.getInitiator());
                newTask.setSummary("Приемка результата");
                newTask.setDescription("Приемка результата");
                newTask.setState(TaskStatusEnum.NEW);
                newTask.setBpCode(task.getBpCode());
                newTask.setTaskCode("accept");
                em.persist(newTask);
                break;
            default:
                break;
        }
    }

    private void closeProcessTask(Task task) {
        EntityManager em = persistence.getEntityManager();

        Task newTask = metadata.create(Task.class);

        switch (task.getTaskCode()) {
            case "createCallKd":
                newTask.setUser(task.getUser());
                newTask.setSummary("Подготовить требования");
                newTask.setDescription("Подготовить требования");
                newTask.setState(TaskStatusEnum.NEW);
                newTask.setInitiator(task.getInitiator());
                newTask.setBpCode(task.getBpCode());
                newTask.setTaskCode("createRequest");
                em.persist(newTask);
                break;
            case "createRequest":
                newTask.setUser(task.getInitiator());
                newTask.setSummary("Подтвержить получение");
                newTask.setDescription("Подготовить требования");
                newTask.setBpCode(task.getBpCode());
                newTask.setTaskCode("confirmKd");
                newTask.setState(TaskStatusEnum.NEW);
                em.persist(newTask);
                break;
            default:
                break;
        }
    }
}