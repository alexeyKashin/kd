package ru.kamotive.kd.web.screens.kdexchange;

import com.haulmont.cuba.gui.screen.*;
import ru.kamotive.kd.entity.KdExchange;

@UiController("kd_KdExchange.edit")
@UiDescriptor("kd-exchange-edit.xml")
@EditedEntityContainer("kdExchangeDc")
@LoadDataBeforeShow
public class KdExchangeEdit extends StandardEditor<KdExchange> {
}