package com.aispace.erksystem.service.database.table;

import com.aispace.erksystem.service.database.type.ServiceType;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "service_provider_tbl")
@Data
public class ServiceProvider implements Serializable {
    @Id
    @Column(name = "org_id", nullable = false)
    private Integer orgId;

    @NaturalId
    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Column(name = "org_pwd", nullable = false)
    private String orgPwd;

    @Column(name = "service_duration", nullable = false)
    private String serviceDuration;

    @Column(name = "user_number", nullable = false)
    private Integer userNumber;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @Column(name = "ts", nullable = false)
    private Timestamp timestamp;
}
