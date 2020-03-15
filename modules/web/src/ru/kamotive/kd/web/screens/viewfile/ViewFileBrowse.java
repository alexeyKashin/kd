package ru.kamotive.kd.web.screens.viewfile;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.ClasspathResource;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import ru.kamotive.kd.entity.ViewFile;

import javax.inject.Inject;

@UiController("kd_ViewFile.browse")
@UiDescriptor("view-file-browse.xml")
@LookupComponent("viewFilesTable")

public class ViewFileBrowse extends StandardLookup<ViewFile> {
    @Inject
    private CollectionContainer<ViewFile> viewFilesDc;
    @Inject
    private Image imageField;
    @Inject
    private Metadata metadata;
    @Inject
    private Notifications notifications;


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        // Corpus.prt
        // Planka.dvg
        // Lopatka.sim

        // ru/kamotive/kd/web/screens/img/

        ViewFile lopatkaFile = metadata.create(ViewFile.class);
        lopatkaFile.setName("Lopatka.sim");
        lopatkaFile.setLink("ru/kamotive/kd/web/screens/viewfile/3.jpg");
        viewFilesDc.getMutableItems().add(lopatkaFile);

        ViewFile plankaFile = metadata.create(ViewFile.class);
        plankaFile.setName("Planka.dvg");
        plankaFile.setLink("ru/kamotive/kd/web/screens/viewfile/2.jpg");
        viewFilesDc.getMutableItems().add(plankaFile);


        ViewFile corpusFile = metadata.create(ViewFile.class);
        corpusFile.setName("Corpus.prt");
        corpusFile.setLink("ru/kamotive/kd/web/screens/viewfile/1.jpg");
        viewFilesDc.getMutableItems().add(corpusFile);
    }

    @Subscribe(id = "viewFilesDc", target = Target.DATA_CONTAINER)
    public void onViewFilesDcItemChange(InstanceContainer.ItemChangeEvent<ViewFile> event) {
        imageField.setSource(ClasspathResource.class).setPath(viewFilesDc.getItem().getLink());
    }



}