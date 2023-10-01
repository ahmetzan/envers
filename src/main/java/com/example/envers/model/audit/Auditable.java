package com.example.envers.model.audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);

}
