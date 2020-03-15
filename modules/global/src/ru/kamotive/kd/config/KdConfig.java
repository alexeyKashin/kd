package ru.kamotive.kd.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;

@Source(type = SourceType.DATABASE)
public interface KdConfig extends Config {

    @Property("kd.camunda.restUrl")
    String getCamundaRestUrl();

    @Property("kd.caminda.processCode")
    String getCamundaProcessCode();
}