package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceType;
import com.erksystem.protobuf.prov.AddUserInfoRP_m;
import com.erksystem.protobuf.prov.AddUserInfoRQ_m;
import com.erksystem.protobuf.prov.UserProfileResult_e;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkProvMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class AddUserInfoRQHandler extends RmqIncomingHandler<AddUserInfoRQ_m> {
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
        ServiceProvider provider = ServiceProviderDAO.readbyName(msg.getOrgName());
        if (provider == null) throw new IllegalStateException("FAIL");

        ServiceUser user = new ServiceUser();
        user.setOrgId(provider.getOrgId());
        user.setUserName(msg.getUserName());
        user.setUserPwd(msg.getUserPwd());
        user.setServiceDuration(msg.getServiceDuration());
        user.setAge(msg.getAge());
        //user.setSex(msg.getSex());
        //user.setUserType(msg.getUserType());
        user.setServiceType(ServiceType.getName(msg.getServiceType().getNumber()));
        if(!ServiceUserDAO.create(user)) {
            throw new IllegalStateException("FAIL");
        }

        AddUserInfoRP_m res = AddUserInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(user.getUserName())
                .setUserPwd(user.getUserPwd())
                .setServiceDuration(user.getServiceDuration())
                .setAge(user.getAge())
//                .setSex(msg.getSex())
//                .setUserType(msg.getUserType())
                .setServiceType(msg.getServiceType())
                .setResultType(UserProfileResult_e.UserProfileResult_ok)
                .setUserId(user.getUserId())
                .build();

        sendErkProvMsg2API(res);
    }

    @Override
    protected void onFail() {
        AddUserInfoRP_m res = AddUserInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setUserPwd(msg.getUserPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setAge(msg.getAge())
                .setSex(msg.getSex())
                .setUserType(msg.getUserType())
                .setServiceType(msg.getServiceType())
                .setResultType(UserProfileResult_e.UserProfileResult_nok_OrgName)
                //.setUserId() // TODO
                .build();

        sendErkProvMsg2API(res);
    }
}
