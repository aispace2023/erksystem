package com.aispace.erksystem.service.database.type;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ServiceUserId implements Serializable {
    private Long userId;
    private Long orgId;

    public ServiceUserId() {
        // 기본 생성자
    }

    public ServiceUserId(long userId, long orgId) {
        this.userId = userId;
        this.orgId = orgId;
    }

    // equals() 및 hashCode() 메서드 오버라이딩
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceUserId that = (ServiceUserId) o;
        return userId == that.userId &&
                orgId == that.orgId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orgId);
    }
}

