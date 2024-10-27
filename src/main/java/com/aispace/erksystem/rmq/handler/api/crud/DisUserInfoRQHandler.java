package com.aispace.erksystem.rmq.handler.api.crud;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.erksystem.protobuf.api.*;

import static com.erksystem.protobuf.api.UserProfileResult_e.*;

/**
 * Created by Ai_Space
 */
public class DisUserInfoRQHandler extends RmqIncomingHandler<DisUserInfoRQ_m> {
    @Override
    protected void handle() {
        ServiceProvider sp = ServiceProviderDAO.read(msg.getOrgName());
        if (sp == null) {
            throw new RmqHandleException(UserProfileResult_unknown.getNumber(), "Invalid Info");
        }

        ServiceUser userInfo = ServiceUserDAO.read(msg.getUserName());
        if (userInfo == null || !userInfo.getUserPwd().equals(msg.getUserPwd())) {
            throw new RmqHandleException(UserProfileResult_unknown.getNumber(), "Invalid Info");
        }

        DisUserInfoRP_m res = DisUserInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.DisUserInfoRP)
                .setOrgName(msg.getOrgName())
                .setUserName(userInfo.getUserName())
                .setResultType(UserProfileResult_ok)
                .setUserId(userInfo.getUserId())
                .setUserPwd(userInfo.getUserPwd())
                .setServiceDuration(userInfo.getServiceDuration())
                .setAge(userInfo.getAge())
                .setSexValue(userInfo.getSex())
                .setMbtiTypeValue(userInfo.getMbti())
                .setUserTypeValue(userInfo.getUserType())
                .setServiceTypeValue(userInfo.getServiceType())
                .setServiceStatusValue(userInfo.getServiceStatus())
                .setServiceNumber(userInfo.getServiceNumber())
                .build();

        reply(res);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        reply(DisUserInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.DisUserInfoRP)
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setResultTypeValue(reasonCode)
                .build());
    }
}
