package com.aispace.erksystem.service.database.table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "service_user_tbl")
@Data
public class ServiceUser implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "org_id", nullable = false)
    private Integer orgId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "service_duration", nullable = false)
    private String serviceDuration;

    @Column(name = "age")
    private Integer age;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @Column(name = "ts", nullable = false)
    private Timestamp timestamp;

//    @ManyToOne
//    @JoinColumn(name = "org_id", referencedColumnName = "org_id", insertable = false, updatable = false)
//    private ServiceProvider serviceProvider; // FK on service_provider_tbl
}
