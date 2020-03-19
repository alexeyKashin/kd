package ru.kamotive.kd.web.screens.viewfile;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;
import ru.kamotive.kd.entity.ViewFile;

import javax.inject.Inject;
import javax.inject.Named;

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
    @Inject
    private Label<String> kdNameLbl;
    @Named("tabSheet.dopFileView")
    private VBoxLayout dopFileView;
    @Inject
    private CollectionContainer<ViewFile> viewFilesDc2;

    private String processName;
    private String fileString;

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (processName == null || processName.isEmpty()) {
            kdNameLbl.setVisible(false);
            // Corpus.prt
            // Planka.dvg
            // Lopatka.sim

            // ru/kamotive/kd/web/screens/img/

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
        } else {
            kdNameLbl.setVisible(true);
            kdNameLbl.setValue(processName);

            ViewFile lopatkaFile = metadata.create(ViewFile.class);
            lopatkaFile.setName("images_preview.qaf");
            viewFilesDc.getMutableItems().add(lopatkaFile);

            ViewFile plankaFile = metadata.create(ViewFile.class);
            plankaFile.setName("MODEL$.jt");
            viewFilesDc.getMutableItems().add(plankaFile);


            ViewFile corpusFile = metadata.create(ViewFile.class);
            corpusFile.setName("model1.prt");
            viewFilesDc.getMutableItems().add(corpusFile);

            ViewFile kronshtain = metadata.create(ViewFile.class);
            kronshtain.setName("qafmetdata.qaf");
            viewFilesDc.getMutableItems().add(kronshtain);

            ViewFile kronshtainGeom = metadata.create(ViewFile.class);
            kronshtainGeom.setName("Kronshtein_geom.3D");
            kronshtainGeom.setLink("ru/kamotive/kd/web/screens/viewfile/Kronshtein_Geom.html");
            viewFilesDc.getMutableItems().add(kronshtainGeom);

            imageField.setVisible(false);
            browseFrame.setVisible(false);
        }

        if (fileString == null || fileString.isEmpty()) {
            dopFileView.setVisible(false);
            dopFileView.setEnabled(false);
        } else {
            dopFileView.setVisible(true);
            dopFileView.setEnabled(true);

            if (fileString.contains(",")) {
                String[] files = fileString.split(",");
                for (String file : files) {
                    file = file.substring(file.lastIndexOf("/") + 1);
                    ViewFile vFile = metadata.create(ViewFile.class);
                    vFile.setName(file);
                    viewFilesDc2.getMutableItems().add(vFile);
                }
            } else {
                ViewFile file = metadata.create(ViewFile.class);
                file.setName(fileString);
                viewFilesDc2.getMutableItems().add(file);
            }
        }
    }

    @Subscribe(id = "viewFilesDc", target = Target.DATA_CONTAINER)
    public void onViewFilesDcItemChange(InstanceContainer.ItemChangeEvent<ViewFile> event) {
        String path = viewFilesDc.getItem().getLink();
        if (path == null)
            return;

        if (path.contains("jpg")) {
            imageField.setVisible(true);
            imageField.setSource(ClasspathResource.class).setPath(path);
            browseFrame.setVisible(false);
        } else if (path.contains("html")) {
            browseFrame.setVisible(true);
            browseFrame.setSource(ClasspathResource.class).setPath(path);
            imageField.setVisible(false);
        } else {
            browseFrame.setVisible(false);
            imageField.setVisible(false);
        }
    }



}