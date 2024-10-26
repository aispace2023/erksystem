package com.aispace.erksystem.rmq.handler.api.connection;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgHead_s;
import com.erksystem.protobuf.api.HeartBeatRP_m;
import com.erksystem.protobuf.api.HeartBeatRQ_m;

/**
 * Created by Ai_Space
 */
public class HeartBeatRqHandler extends RmqIncomingHandler<HeartBeatRQ_m> {
    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();

        connectionManager.findConnectionInfo(orgId, userId).orElseThrow()
                .updateAccessTime();

        reply(HeartBeatRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()))
                .setStatus(1)
                .build());
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        HeartBeatRP_m res = HeartBeatRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()))
                .setStatus(0)
                .build();

        reply(res);
    }
}
