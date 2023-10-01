package com.example.envers.model.entity;

import com.example.envers.listener.AuditListener;
import com.example.envers.model.audit.Audit;
import com.example.envers.model.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter
@Setter
@Audited
@AuditTable(value = "employee_history")
@EntityListeners(AuditListener.class)
public class Employee implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "active")
    @NotAudited
    private boolean active = true;

        @AttributeOverrides({
            @AttributeOverride(name = "createdBy", column = @Column(name = "created_by")),
            @AttributeOverride(name = "createdDate", column = @Column(name = "created_date")),
            @AttributeOverride(name = "lastModifiedBy", column = @Column(name = "last_modified_by")),
            @AttributeOverride(name = "lastModifiedDate", column = @Column(name = "last_modified_date"))
    })
    @Embedded
    private Audit audit;
}
