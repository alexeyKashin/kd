package ru.kamotive.kd.web.screens.kdexchange;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import ru.kamotive.kd.entity.ExchangeStatus;
import ru.kamotive.kd.entity.KdExchange;
import ru.kamotive.kd.web.screens.viewfile.ViewFileBrowse;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

@UiController("kd_KdExchangeReceiver.browse")
@UiDescriptor("kd-exchange-receiver-browse.xml")
@LookupComponent("kdExchangesTable")
@LoadDataBeforeShow
public class KdExchangeReceiverBrowse extends StandardLookup<KdExchange> {
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionLoader<KdExchange> kdExchangesDl;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private GroupTable<KdExchange> kdExchangesTable;
    @Inject
    private Notifications notifications;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        kdExchangesDl.setParameter("currentUserId", userSession.getCurrentOrSubstitutedUser().getId());
        kdExchangesDl.load();
    }

    @Subscribe("showDataTbl")
    public void onShowDataTblClick(Button.ClickEvent event) {
        KdExchange kdExchange = kdExchangesTable.getSingleSelected();

        if (kdExchange.getState().equals(ExchangeStatus.DONE) || kdExchange.getState().equals(ExchangeStatus.DELIVERED)){
            ViewFileBrowse viewFileBrowse = screenBuilders.screen(this)
                    .withScreenClass(ViewFileBrowse.class)
                    .build();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            viewFileBrowse.setProcessName("ID: " + kdExchange.getId() + ", получатель: " + kdExchange.getReceiver().getName()
                    + ", отправитель: " + kdExchange.getAuthor().getName() + ", дата запуска: "
                    + sdf.format(kdExchange.getStartDate()) + ", дата получения: " + sdf.format(kdExchange.getReceiveDate()));
            viewFileBrowse.setFileString(kdExchange.getDocumentLinks());
            viewFileBrowse.show();
        } else {
            notifications.create().withCaption("Доставка данных еще не выполнена!").show();
        }
    }
    
    
}