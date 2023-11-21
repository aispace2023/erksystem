package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.UpdateUserInfoRP_m;
import com.erksystem.protobuf.prov.UpdateUserInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkProvMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class UpdateUserInfoRQHandler extends RmqIncomingHandler<UpdateUserInfoRQ_m> {
    /*
    message UpdateUserInfoRQ{
      string OrgName = 1;
      string UserName = 2;
      string Old_UserPwd = 3;
      string Old_ServiceDuration = 4;
      int32 Old_Age = 5;
      int32 Old_Sex = 6;
      int32 Old_UserType = 7;
      ServiceType_e Old_ServiceType = 8;
      string New_UserPwd = 9;
      string New_ServiceDuration = 10;
      int32 New_Age = 11;
      int32 New_Sex = 12;
      int32 New_UserType = 13;
      ServiceType_e New_ServiceType = 14;
    }

    message UpdateUserInfoRP{
      string OrgName = 1;
      string UserName = 2;
      UserProfileResult_e ResultType = 3;
      string Old_UserPwd = 4;
      string Old_ServiceDuration = 5;
      int32 Old_Age = 6;
      int32 Old_Sex = 7;
      int32 Old_UserType = 8;
      ServiceType_e Old_ServiceType = 9;
      string New_UserPwd = 10;
      string New_ServiceDuration = 11;
      int32 New_Age = 12;
      int32 New_Sex = 13;
      int32 New_UserType = 14;
      ServiceType_e New_ServiceType = 15;
    }
     */
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

        sendErkProvMsg2API(res);
    }
}
