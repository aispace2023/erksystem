package com.aispace.erksystem.service.database.table;

import com.aispace.erksystem.service.database.type.ServiceType;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "service_user_tbl")
@Data
public class ServiceUser {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "org_id")
    private int orgId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "service_duration")
    private String serviceDuration;

    @Column(name = "age")
    private Byte age;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "ts")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "org_id", insertable = false, updatable = false)
    private ServiceProvider serviceProvider; // FK on service_provider_tbl
}
