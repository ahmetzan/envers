package com.example.envers.listener;

import com.example.envers.model.audit.Audit;
import com.example.envers.model.audit.Auditable;
import com.example.envers.util.HeaderUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Objects;

@Component("entityAuditListener")
public class AuditListener {

    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();

        if (audit == null) {
            audit = new Audit();
            auditable.setAudit(audit);
        }
        String username = Objects.nonNull(audit.getCreatedBy()) && !audit.getCreatedBy().isBlank() ? audit.getCreatedBy() : "System";

        HttpServletRequest request = getServletRequest();

        if (null != request && !HeaderUtils.getUsername().isBlank()) {
            username = HeaderUtils.getUsername();
        }

        Date now = new Date();
        audit.setCreatedDate(now);
        audit.setCreatedBy(username);
        audit.setLastModifiedBy(username);
        audit.setLastModifiedDate(now);
    }

    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        String username = "System";

        HttpServletRequest request = getServletRequest();


        if (null != request && !HeaderUtils.getUsername().isBlank()) {
            username = HeaderUtils.getUsername();
        }

        Audit audit = auditable.getAudit();
        if (null != audit) {
            audit.setLastModifiedDate(new Date());
            audit.setLastModifiedBy(username);
        }
    }

    private HttpServletRequest getServletRequest() {
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
        if (attribs instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) attribs).getRequest();
        }

        return null;
    }
}
