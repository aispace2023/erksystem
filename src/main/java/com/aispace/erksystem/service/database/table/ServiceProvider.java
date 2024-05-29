package com.aispace.erksystem.service.database.table;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVICE_PROVIDER_TBL")
@Data
public class ServiceProvider implements Serializable {
    @Id
    @Column(name = "OrgId", nullable = false)
    private Integer orgId;

    @NaturalId
    @Column(name = "OrgName", nullable = false)
    private String orgName;

    @Column(name = "OrgPwd", nullable = false)
    private String orgPwd;

    @Column(name = "ProviderType", nullable = false)
    private Integer providerType;

    @Column(name = "ServiceDuration", nullable = false)
    private String serviceDuration;

    @Column(name = "UserNumber", nullable = false)
    private Integer userNumber;

    @Column(name = "ServiceType", nullable = false)
    private Integer serviceType;
}
