package ru.kamotive.kd.web.screens.kdexchange;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import ru.kamotive.kd.core.ExecuteTaskService;
import ru.kamotive.kd.entity.ExchangeStatus;
import ru.kamotive.kd.entity.KdExchange;

import javax.inject.Inject;

@UiController("kd_KdExchange.edit")
@UiDescriptor("kd-exchange-edit.xml")
@EditedEntityContainer("kdExchangeDc")
@LoadDataBeforeShow
public class KdExchangeEdit extends StandardEditor<KdExchange> {
    @Inject
    private ExecuteTaskService executeTaskService;

    @Subscribe("confirmBtn")
    public void onConfirmBtnClick(Button.ClickEvent event) {
        KdExchange exchange = getEditedEntity();
        executeTaskService.executeTask(exchange);
        exchange.setState(ExchangeStatus.DONE);
        exchange.setTaskId(null);
        commitChanges();
    }

}