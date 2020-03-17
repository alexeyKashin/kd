package ru.kamotive.kd.web.screens.viewfile;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.BrowserFrame;
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
    @Inject
    private BrowserFrame browseFrame;

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

        ViewFile kronshtain = metadata.create(ViewFile.class);
        kronshtain.setName("Kronshtein.3D");
        kronshtain.setLink("ru/kamotive/kd/web/screens/viewfile/Kronshtein.html");
        viewFilesDc.getMutableItems().add(kronshtain);

        ViewFile kronshtainGeom = metadata.create(ViewFile.class);
        kronshtainGeom.setName("Kronshtein_geom.3D");
        kronshtainGeom.setLink("ru/kamotive/kd/web/screens/viewfile/Kronshtein_Geom.html");
        viewFilesDc.getMutableItems().add(kronshtainGeom);

        imageField.setVisible(false);
        browseFrame.setVisible(false);
    }

    @Subscribe(id = "viewFilesDc", target = Target.DATA_CONTAINER)
    public void onViewFilesDcItemChange(InstanceContainer.ItemChangeEvent<ViewFile> event) {
        String path = viewFilesDc.getItem().getLink();
        if (path.contains("jpg")) {
            imageField.setVisible(true);
            imageField.setSource(ClasspathResource.class).setPath(path);
            browseFrame.setVisible(false);
        } else {
            browseFrame.setVisible(true);
            browseFrame.setSource(ClasspathResource.class).setPath(path);
            imageField.setVisible(false);
        }
    }



}