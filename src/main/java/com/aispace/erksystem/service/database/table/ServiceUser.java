package com.aispace.erksystem.service.database.table;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVICE_USER_TBL")
@Data
public class ServiceUser implements Serializable {
    @NaturalId
    @Column(name = "OrgId", nullable = false)
    private Integer orgId;

    @NaturalId
    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "UserName", nullable = false)
    private String userName;

    @Column(name = "UserPwd")
    private String userPwd;

    @Column(name = "ServiceDuration", nullable = false)
    private String serviceDuration;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Sex")
    private Integer sex;

    @Column(name = "Mbti")
    private Integer mbti;

    @Column(name = "UserType")
    private Integer userType;

    @Column(name = "ServiceType", nullable = false)
    private Integer serviceType;

    @Column(name = "ServiceStatus", nullable = false)
    private Integer serviceStatus;

    @Column(name = "ServiceNumber", nullable = false)
    private Integer serviceNumber;

//    @ManyToOne
//    @JoinColumn(name = "OrgId", referencedColumnName = "OrgId", insertable = false, updatable = false)
//    private ServiceProvider serviceProvider; // FK on SERVICE_PROVIDER_TBL
}
