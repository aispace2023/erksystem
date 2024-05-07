package com.aispace.erksystem.service.database.table;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "service_user_tbl")
@Data
public class ServiceUser implements Serializable {
    @Id
    @Column(name = "org_id", nullable = false)
    private Integer orgId;

    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NaturalId
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "service_duration", nullable = false)
    private String serviceDuration;

    @Column(name = "user_number")
    private Integer userNumber;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "service_type", nullable = false)
    private Integer serviceType;

    @Column(name = "ts", nullable = false)
    private Timestamp timestamp;

//    @ManyToOne
//    @JoinColumn(name = "org_id", referencedColumnName = "org_id", insertable = false, updatable = false)
//    private ServiceProvider serviceProvider; // FK on service_provider_tbl
}
