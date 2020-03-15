package ru.kamotive.kd.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@NamePattern("%s|name")
@MetaClass(name = "kd_ViewFile")
public class ViewFile extends BaseUuidEntity {
    private static final long serialVersionUID = -5803116005242239709L;

    @MetaProperty
    protected String name;

    @MetaProperty
    protected String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}