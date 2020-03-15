package ru.kamotive.kd.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "kd_CamundaInstance")
public class CamundaInstance extends BaseUuidEntity {
    private static final long serialVersionUID = 4978436531502002406L;

    @MetaProperty
    protected String instanceId;

    @MetaProperty
    protected String definitionId;

    @MetaProperty
    protected String businessKey;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}