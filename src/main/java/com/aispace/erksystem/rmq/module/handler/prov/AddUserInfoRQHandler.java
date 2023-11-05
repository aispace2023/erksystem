package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.AddUserInfoRP;
import com.erksystem.protobuf.prov.AddUserInfoRQ;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkProvMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class AddUserInfoRQHandler extends RmqIncomingHandler<AddUserInfoRQ> {
    /*
    message AddUserInfoRQ{

        string OrgName = 1;
        string UserName = 2;
        string UserPwd = 3;
        string ServiceDuration = 4;
        int32 Age = 5;
        int32 Sex = 6;
        int32 UserType = 7;
        ServiceType_e ServiceType = 8;
    }

    message AddUserInfoRP{
        string OrgName = 1;
        string UserName = 2;
        UserProfileResult_e ResultType = 3;
        int32 UserId = 4;
        string UserPwd = 5;
        string ServiceDuration = 6;
        int32 Age = 7;
        int32 Sex = 8;
        int32 UserType = 9;
        ServiceType_e ServiceType = 10;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        AddUserInfoRP res = AddUserInfoRP.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setUserPwd(msg.getUserPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setAge(msg.getAge())
                .setSex(msg.getSex())
                .setUserType(msg.getUserType())
                .setServiceType(msg.getServiceType())
                //.setResultType() // TODO
                //.setUserId() // TODO
                .build();

        sendErkProvMsg2API(res);
    }
}
