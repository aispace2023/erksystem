package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.erksystem.protobuf.api.UpdateUserInfoRP_m;
import com.erksystem.protobuf.api.UpdateUserInfoRQ_m;

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
    protected void onFail(int reasonCode, String reason) {
        UpdateUserInfoRP_m res = UpdateUserInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setOldUserPwd(msg.getOldUserPwd())
                .setOldServiceDuration(msg.getOldServiceDuration())
                .setOldAge(msg.getOldAge())
                .setOldSex(msg.getOldSex())
                .setOldMbtiType(msg.getOldMbtiType())
                .setOldUserType(msg.getOldUserType())
                .setOldServiceType(msg.getOldServiceType())
                .setNewUserPwd(msg.getNewUserPwd())
                .setNewServiceDuration(msg.getNewServiceDuration())
                .setNewAge(msg.getNewAge())
                .setNewSex(msg.getNewSex())
                .setNewMbtiType(msg.getNewMbtiType())
                .setNewUserType(msg.getNewUserType())
                .setNewServiceType(msg.getNewServiceType())
                .setResultTypeValue(reasonCode)
                .build();

        reply(res);
    }
}
