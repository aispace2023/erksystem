package com.aispace.erksystem.rmq.module.handler;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.UpdateUserInfoRP_m;
import com.erksystem.protobuf.api.UpdateUserInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

/**
 * Created by Ai_Space
 */
public class UpdateUserInfoRQHandler extends RmqIncomingHandler<UpdateUserInfoRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        UpdateUserInfoRP_m res = UpdateUserInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setOldUserPwd(msg.getOldUserPwd())
                .setOldServiceDuration(msg.getOldServiceDuration())
                .setOldAge(msg.getOldAge())
                .setOldSex(msg.getOldSex())
                .setOldUserType(msg.getOldUserType())
                .setOldServiceType(msg.getOldServiceType())
                .setNewUserPwd(msg.getNewUserPwd())
                .setNewServiceDuration(msg.getNewServiceDuration())
                .setNewAge(msg.getNewAge())
                .setNewSex(msg.getNewSex())
                .setNewUserType(msg.getNewUserType())
                .setNewServiceType(msg.getNewServiceType())
                //.setResultType() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
