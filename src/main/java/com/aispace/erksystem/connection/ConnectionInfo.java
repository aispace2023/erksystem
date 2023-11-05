package com.aispace.erksystem.connection;

import com.erksystem.protobuf.api.ServiceType_e;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Ai_Space
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ConnectionInfo {
    final Integer userId;
    final Integer orgId;
    ServiceType_e serviceType;
    long lastHbTime = System.currentTimeMillis();

    public void dealloc() {

    }

    public String getLogPrefix() {
        return "(" + userId + ") (" + orgId + ") ";
    }
}
