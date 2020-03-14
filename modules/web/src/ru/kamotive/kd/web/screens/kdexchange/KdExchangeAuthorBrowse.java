package ru.kamotive.kd.web.screens.kdexchange;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import ru.kamotive.kd.entity.KdExchange;

import javax.inject.Inject;

@UiController("kd_KdExchangeAuthor.browse")
@UiDescriptor("kd-exchange-author-browse.xml")
@LookupComponent("kdExchangesTable")
@LoadDataBeforeShow
public class KdExchangeAuthorBrowse extends StandardLookup<KdExchange> {

    @Inject
    private UserSession userSession;
    @Inject
    private CollectionLoader<KdExchange> kdExchangesDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        kdExchangesDl.setParameter("currentUserId", userSession.getCurrentOrSubstitutedUser().getId());
        kdExchangesDl.load();
    }


}