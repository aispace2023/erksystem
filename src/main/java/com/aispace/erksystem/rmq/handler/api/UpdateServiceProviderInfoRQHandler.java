package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.UpdateServiceProviderInfoRP_m;
import com.erksystem.protobuf.api.UpdateServiceProviderInfoRQ_m;


/**
 * Created by Ai_Space
 */
public class UpdateServiceProviderInfoRQHandler extends RmqIncomingHandler<UpdateServiceProviderInfoRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        UpdateServiceProviderInfoRP_m res = UpdateServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setOldOrgPwd(msg.getOldOrgPwd())
                .setOldProviderType(msg.getOldProviderType())
                .setOldServiceDuration(msg.getOldServiceDuration())
                .setOldUserNumber(msg.getOldUserNumber())
                .setOldServiceType(msg.getOldServiceType())
                .setNewOrgPwd(msg.getNewOrgPwd())
                .setNewProviderType(msg.getNewProviderType())
                .setNewServiceDuration(msg.getNewServiceDuration())
                .setNewUserNumber(msg.getNewUserNumber())
                .setNewServiceType(msg.getNewServiceType())
                .setResultTypeValue(reasonCode)
                .build();

        reply(res);
    }
}
