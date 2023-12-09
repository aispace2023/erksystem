package com.aispace.erksystem.rmq.module.handler;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.UpdateServiceProviderInfoRP_m;
import com.erksystem.protobuf.api.UpdateServiceProviderInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;


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
    protected void onFail() {
        UpdateServiceProviderInfoRP_m res = UpdateServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setOldOrgPwd(msg.getOldOrgPwd())
                .setOldServiceDuration(msg.getOldServiceDuration())
                .setOldUserNumber(msg.getOldUserNumber())
                .setOldServiceType(msg.getOldServiceType())
                .setNewOrgPwd(msg.getNewOrgPwd())
                .setNewServiceDuration(msg.getNewServiceDuration())
                .setNewUserNumber(msg.getNewUserNumber())
                .setNewServiceType(msg.getNewServiceType())
                // .setResultType() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
