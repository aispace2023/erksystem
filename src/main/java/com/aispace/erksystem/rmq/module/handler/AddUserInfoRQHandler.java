package com.aispace.erksystem.rmq.module.handler;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceType;
import com.erksystem.protobuf.api.AddUserInfoRP_m;
import com.erksystem.protobuf.api.AddUserInfoRQ_m;
import com.erksystem.protobuf.api.UserProfileResult_e;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;


/**
 * Created by Ai_Space
 */
public class AddUserInfoRQHandler extends RmqIncomingHandler<AddUserInfoRQ_m> {
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
        if (!ServiceUserDAO.create(user)) {
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

        sendErkApiMsg2API(res);
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

        sendErkApiMsg2API(res);
    }
}
