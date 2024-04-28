package com.aispace.erksystem.rmq.module;

import com.erksystem.protobuf.api.*;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author kangmoo Heo
 */
public class RmqMsgBuilder {
    public static ErkMsgHead_s getErkMsgHead(ErkMsgType_e MsgType, QueueInfo_s QueueInfo, int OrgId, int UserId) {
        return ErkMsgHead_s.newBuilder()
                .setMsgType(MsgType)
                .setQueueInfo(QueueInfo)
                .setOrgId(OrgId)
                .setUserId(UserId)
                .build();
    }

    public static ErkMsgDataHead_s getErkMsgDataHead(ErkMsgType_e MsgType, QueueInfo_s QueueInfo, int OrgId, int UserId) {
        return ErkMsgDataHead_s.newBuilder()
                .setMsgType(MsgType)
                .setQueueInfo(QueueInfo)
                .setOrgId(OrgId)
                .setUserId(UserId)
                .build();
    }

    public static ErkInterMsgHead_s getErkInterMsgHead(ErkInterMsgType_e MsgType, int OrgId, int UserId) {
        return ErkInterMsgHead_s.newBuilder()
                .setMsgType(MsgType)
                .setOrgId(OrgId)
                .setUserId(UserId)
                .build();
    }

    public static QueueInfo_s getQueueInfo(String ToQueueName, String FromQueueName) {
        return QueueInfo_s.newBuilder()
                .setToQueueName(ToQueueName)
                .setFromQueueName(FromQueueName)
                .build();
    }

    public static ErkEngineInfo_s getErkEngineInfo(EngineType_e EngineType, EngineCondition_e EngineCondition, String IpAddr, String ReceiveQueueName, String SendQueueName) {
        return ErkEngineInfo_s.newBuilder()
                .setEngineType(EngineType)
                .setEngineCondition(EngineCondition)
                .setIpAddr(IpAddr)
                .setReceiveQueueName(ReceiveQueueName)
                .setSendQueueName(SendQueueName)
                .build();
    }

    private static ErkApiMsg getErkApiMsg(Message message) {
        ErkApiMsg.Builder builder = ErkApiMsg.newBuilder();
        try {
            Method method = Arrays.stream(builder.getClass().getDeclaredMethods())
                    .filter(o -> o.getName().startsWith("set"))
                    .filter(o -> o.getParameterTypes().length == 1)
                    .filter(o -> o.getParameterTypes()[0].equals(message.getClass()))
                    .findAny()
                    .orElseThrow();
            method.invoke(builder, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.build();
    }


    public static ErkApiMsg getAddServiceProviderInfoRQ(QueueInfo_s QueueInfo, String OrgName, String
            OrgPwd, String ServiceDuration, int UserNumber, ServiceType_e ServiceType) {
        return getErkApiMsg(
                AddServiceProviderInfoRQ_m.newBuilder()
                        .setMsgType(ErkMsgType_e.AddServiceProviderInfoRQ)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setOrgPwd(OrgPwd)
                        .setServiceDuration(ServiceDuration)
                        .setUserNumber(UserNumber)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getAddServiceProviderInfoRP(QueueInfo_s QueueInfo, String OrgName, OrgProfileResult_e
            ResultType, int OrgId, String OrgPwd, String ServiceDuration, int UserNumber, ServiceType_e ServiceType) {
        return getErkApiMsg(
                AddServiceProviderInfoRP_m.newBuilder()
                        .setMsgType(ErkMsgType_e.AddServiceProviderInfoRP)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setResultType(ResultType)
                        .setOrgId(OrgId)
                        .setOrgPwd(OrgPwd)
                        .setServiceDuration(ServiceDuration)
                        .setUserNumber(UserNumber)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getUpdateServiceProviderInfoRQ(QueueInfo_s QueueInfo, String OrgName, String
            Old_OrgPwd, String Old_ServiceDuration, int Old_UserNumber, ServiceType_e Old_ServiceType, String
                                                                   New_OrgPwd, String New_ServiceDuration, int New_UserNumber, ServiceType_e New_ServiceType) {
        return getErkApiMsg(
                UpdateServiceProviderInfoRQ_m.newBuilder()
                        .setMsgType(ErkMsgType_e.UpdUserInfoRQ)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setOldOrgPwd(Old_OrgPwd)
                        .setOldServiceDuration(Old_ServiceDuration)
                        .setOldUserNumber(Old_UserNumber)
                        .setOldServiceType(Old_ServiceType)
                        .setNewOrgPwd(New_OrgPwd)
                        .setNewServiceDuration(New_ServiceDuration)
                        .setNewUserNumber(New_UserNumber)
                        .setNewServiceType(New_ServiceType)
                        .build());
    }

    public static ErkApiMsg getUpdateServiceProviderInfoRP(QueueInfo_s QueueInfo, String
            OrgName, OrgProfileResult_e ResultType, String Old_OrgPwd, String Old_ServiceDuration,
                                                           int Old_UserNumber, ServiceType_e Old_ServiceType, String New_OrgPwd, String New_ServiceDuration,
                                                           int New_UserNumber, ServiceType_e New_ServiceType) {
        return getErkApiMsg(
                UpdateServiceProviderInfoRP_m.newBuilder()
                        .setMsgType(ErkMsgType_e.UpdServiceProviderInfoRP)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setResultType(ResultType)
                        .setOldOrgPwd(Old_OrgPwd)
                        .setOldServiceDuration(Old_ServiceDuration)
                        .setOldUserNumber(Old_UserNumber)
                        .setOldServiceType(Old_ServiceType)
                        .setNewOrgPwd(New_OrgPwd)
                        .setNewServiceDuration(New_ServiceDuration)
                        .setNewUserNumber(New_UserNumber)
                        .setNewServiceType(New_ServiceType)
                        .build());
    }

    public static ErkApiMsg getDelServiceProviderInfoRQ(QueueInfo_s QueueInfo, String OrgName, String OrgPwd) {
        return getErkApiMsg(
                DelServiceProviderInfoRQ_m.newBuilder()
                        .setMsgType(ErkMsgType_e.DelServiceProviderInfoRQ)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setOrgPwd(OrgPwd)
                        .build());
    }

    public static ErkApiMsg getDelServiceProviderInfoRP(QueueInfo_s QueueInfo, String OrgName, OrgProfileResult_e
            ResultType) {
        return getErkApiMsg(
                DelServiceProviderInfoRP_m.newBuilder()
                        .setMsgType(ErkMsgType_e.DelServiceProviderInfoRP)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setResultType(ResultType)
                        .build());
    }

    public static ErkApiMsg getAddUserInfoRQ(QueueInfo_s QueueInfo, String OrgName, String UserName, String
            UserPwd, String ServiceDuration, int Age, SexType_e Sex, UserType_e UserType, ServiceType_e ServiceType) {
        return getErkApiMsg(
                AddUserInfoRQ_m.newBuilder()
                        .setMsgType(ErkMsgType_e.AddUserInfoRQ)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setUserName(UserName)
                        .setUserPwd(UserPwd)
                        .setServiceDuration(ServiceDuration)
                        .setAge(Age)
                        .setSex(Sex)
                        .setUserType(UserType)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getAddUserInfoRP(QueueInfo_s QueueInfo, String OrgName, String
            UserName, UserProfileResult_e ResultType, int UserId, String UserPwd, String ServiceDuration, int Age, SexType_e Sex,
                                             UserType_e UserType, ServiceType_e ServiceType) {
        return getErkApiMsg(
                AddUserInfoRP_m.newBuilder()
                        .setMsgType(ErkMsgType_e.AddUserInfoRP)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setUserName(UserName)
                        .setResultType(ResultType)
                        .setUserId(UserId)
                        .setUserPwd(UserPwd)
                        .setServiceDuration(ServiceDuration)
                        .setAge(Age)
                        .setSex(Sex)
                        .setUserType(UserType)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getUpdateUserInfoRQ(QueueInfo_s QueueInfo, String OrgName, String UserName, String
            Old_UserPwd, String Old_ServiceDuration, int Old_Age, SexType_e Old_Sex, UserType_e Old_UserType, ServiceType_e
                                                        Old_ServiceType, String New_UserPwd, String New_ServiceDuration, int New_Age, SexType_e New_Sex,
                                                UserType_e New_UserType, ServiceType_e New_ServiceType) {
        return getErkApiMsg(
                UpdateUserInfoRQ_m.newBuilder()
                        .setMsgType(ErkMsgType_e.UpdUserInfoRQ)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setUserName(UserName)
                        .setOldUserPwd(Old_UserPwd)
                        .setOldServiceDuration(Old_ServiceDuration)
                        .setOldAge(Old_Age)
                        .setOldSex(Old_Sex)
                        .setOldUserType(Old_UserType)
                        .setOldServiceType(Old_ServiceType)
                        .setNewUserPwd(New_UserPwd)
                        .setNewServiceDuration(New_ServiceDuration)
                        .setNewAge(New_Age)
                        .setNewSex(New_Sex)
                        .setNewUserType(New_UserType)
                        .setNewServiceType(New_ServiceType)
                        .build());
    }

    public static ErkApiMsg getUpdateUserInfoRP(QueueInfo_s QueueInfo, String OrgName, String
            UserName, UserProfileResult_e ResultType, String Old_UserPwd, String Old_ServiceDuration, int Old_Age,
                                                SexType_e Old_Sex, UserType_e Old_UserType, ServiceType_e Old_ServiceType, String New_UserPwd, String New_ServiceDuration,
                                                int New_Age, SexType_e New_Sex, UserType_e New_UserType, ServiceType_e New_ServiceType) {
        return getErkApiMsg(
                UpdateUserInfoRP_m.newBuilder()
                        .setMsgType(ErkMsgType_e.UpdUserInfoRP)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setUserName(UserName)
                        .setResultType(ResultType)
                        .setOldUserPwd(Old_UserPwd)
                        .setOldServiceDuration(Old_ServiceDuration)
                        .setOldAge(Old_Age)
                        .setOldSex(Old_Sex)
                        .setOldUserType(Old_UserType)
                        .setOldServiceType(Old_ServiceType)
                        .setNewUserPwd(New_UserPwd)
                        .setNewServiceDuration(New_ServiceDuration)
                        .setNewAge(New_Age)
                        .setNewSex(New_Sex)
                        .setNewUserType(New_UserType)
                        .setNewServiceType(New_ServiceType)
                        .build());
    }

    public static ErkApiMsg getDelUserInfoRQ(QueueInfo_s QueueInfo, String OrgName, String UserName, String UserPwd) {
        return getErkApiMsg(
                DelUserInfoRQ_m.newBuilder()
                        .setMsgType(ErkMsgType_e.DelUserInfoRQ)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setUserName(UserName)
                        .setUserPwd(UserPwd)
                        .build());
    }

    public static ErkApiMsg getDelUserInfoRP(QueueInfo_s QueueInfo, String OrgName, String
            UserName, UserProfileResult_e ResultType, String Return) {
        return getErkApiMsg(
                DelUserInfoRP_m.newBuilder()
                        .setMsgType(ErkMsgType_e.DelUserInfoRP)
                        .setQueueInfo(QueueInfo)
                        .setOrgName(OrgName)
                        .setUserName(UserName)
                        .setResultType(ResultType)
                        .setReturn(Return)
                        .build());
    }

    public static ErkApiMsg getErkServiceConnRQ(QueueInfo_s QueueInfo, int OrgId, int UserId, long MsgTime) {
        return getErkApiMsg(
                ErkServiceConnRQ_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.ErkServiceConnRQ, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .build());
    }

    public static ErkApiMsg getErkServiceConnRP(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                long MsgTime, ReturnCode_e ReturnCode, ServiceType_e ServiceType) {
        return getErkApiMsg(
                ErkServiceConnRP_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.ErkServiceConnRP, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setReturnCode(ReturnCode)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getErkServiceDisConnRQ(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                   long MsgTime, ServiceType_e ServiceType) {
        return getErkApiMsg(
                ErkServiceDisConnRQ_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.ErkServiceDisConnRQ, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getErkServiceDisConnRP(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                   long MsgTime, ReturnCode_e ReturnCode) {
        return getErkApiMsg(
                ErkServiceDisConnRP_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.ErkServiceDisConnRP, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setReturnCode(ReturnCode)
                        .build());
    }

    public static ErkApiMsg getEmoServiceStartRQ(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                 long MsgTime, ServiceType_e ServiceType) {
        return getErkApiMsg(
                EmoServiceStartRQ_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.EmoServiceStartRQ, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setServiceType(ServiceType)
                        .build());
    }

    public static ErkApiMsg getEmoServiceStartRP(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                 long MsgTime, ReturnCode_e ReturnCode, ErkEngineInfo_s PhysioEngineInfo, ErkEngineInfo_s
                                                         SpeechEngineInfo, ErkEngineInfo_s FaceEngineInfo, ErkEngineInfo_s KnowledgeEngineInfo, ErkEngineInfo_s
                                                         ServiceEngineInfo) {
        return getErkApiMsg(
                EmoServiceStartRP_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.EmoServiceStartRP, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setReturnCode(ReturnCode)
                        .setPhysioEngineInfo(PhysioEngineInfo)
                        .setSpeechEngineInfo(SpeechEngineInfo)
                        .setFaceEngineInfo(FaceEngineInfo)
                        .setKnowledgeEngineInfo(KnowledgeEngineInfo)
                        .setServiceEngineInfo(ServiceEngineInfo)
                        .build());
    }

    public static ErkApiMsg getEmoServiceStopRQ(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                long MsgTime, ServiceType_e ServiceType, String PhysioEngine_ReceiveQueueName, String
                                                        PhysioEngine_SendQueueName, String SpeechEngine_ReceiveQueueName, String SpeechEngine_SendQueueName, String
                                                        FaceEngine_ReceiveQueueName, String FaceEngine_SendQueueName, String KnowledgeEngine_ReceiveQueueName, String
                                                        KnowledgeEngine_SendQueueName) {
        return getErkApiMsg(
                EmoServiceStopRQ_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.EmoServiceStopRQ, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setServiceType(ServiceType)
                        .setPhysioEngineReceiveQueueName(PhysioEngine_ReceiveQueueName)
                        .setPhysioEngineSendQueueName(PhysioEngine_SendQueueName)
                        .setSpeechEngineReceiveQueueName(SpeechEngine_ReceiveQueueName)
                        .setSpeechEngineSendQueueName(SpeechEngine_SendQueueName)
                        .setFaceEngineReceiveQueueName(FaceEngine_ReceiveQueueName)
                        .setFaceEngineSendQueueName(FaceEngine_SendQueueName)
                        .setKnowledgeEngineReceiveQueueName(KnowledgeEngine_ReceiveQueueName)
                        .setKnowledgeEngineSendQueueName(KnowledgeEngine_SendQueueName)
                        .build());
    }

    public static ErkApiMsg getEmoServiceStopRP(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                                long MsgTime, ReturnCode_e ReturnCode, ErkEngineInfo_s PhysioEngineInfo, ErkEngineInfo_s
                                                        SpeechEngineInfo, ErkEngineInfo_s FaceEngineInfo, ErkEngineInfo_s KnowledgeEngineInfo, ErkEngineInfo_s
                                                        ServiceEngineInfo) {
        return getErkApiMsg(
                EmoServiceStopRP_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.EmoServiceStopRP, QueueInfo, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setReturnCode(ReturnCode)
                        .setPhysioEngineInfo(PhysioEngineInfo)
                        .setSpeechEngineInfo(SpeechEngineInfo)
                        .setFaceEngineInfo(FaceEngineInfo)
                        .setKnowledgeEngineInfo(KnowledgeEngineInfo)
                        .setServiceEngineInfo(ServiceEngineInfo)
                        .build());
    }

    public static ErkApiMsg getErkEngineCreateRQ(int OrgId, int UserId, long MsgTime, ServiceType_e
            ServiceType, String PhysioEngine_ReceiveQueueName, String PhysioEngine_SendQueueName, String
                                                         SpeechEngine_ReceiveQueueName, String SpeechEngine_SendQueueName, String FaceEngine_ReceiveQueueName, String
                                                         FaceEngine_SendQueueName, String KnowledgeEngine_ReceiveQueueName, String KnowledgeEngine_SendQueueName) {
        return getErkApiMsg(
                ErkEngineCreateRQ_m.newBuilder()
                        .setErkInterMsgHead(getErkInterMsgHead(ErkInterMsgType_e.ErkEngineCreateRQ, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setServiceType(ServiceType)
                        .setPhysioEngineReceiveQueueName(PhysioEngine_ReceiveQueueName)
                        .setPhysioEngineSendQueueName(PhysioEngine_SendQueueName)
                        .setSpeechEngineReceiveQueueName(SpeechEngine_ReceiveQueueName)
                        .setSpeechEngineSendQueueName(SpeechEngine_SendQueueName)
                        .setFaceEngineReceiveQueueName(FaceEngine_ReceiveQueueName)
                        .setFaceEngineSendQueueName(FaceEngine_SendQueueName)
                        .setKnowledgeEngineReceiveQueueName(KnowledgeEngine_ReceiveQueueName)
                        .setKnowledgeEngineSendQueueName(KnowledgeEngine_SendQueueName)
                        .build());
    }

    public static ErkApiMsg getErkEngineCreateRP(int OrgId, int UserId, long MsgTime, ReturnCode_e
            ReturnCode, ErkEngineInfo_s PhysioEngineInfo, ErkEngineInfo_s SpeechEngineInfo, ErkEngineInfo_s
                                                         FaceEngineInfo, ErkEngineInfo_s KnowledgeEngineInfo, ErkEngineInfo_s ServiceEngineInfo) {
        return getErkApiMsg(
                ErkEngineCreateRP_m.newBuilder()
                        .setErkInterMsgHead(getErkInterMsgHead(ErkInterMsgType_e.ErkEngineCreateRP, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setReturnCode(ReturnCode)
                        .setPhysioEngineInfo(PhysioEngineInfo)
                        .setSpeechEngineInfo(SpeechEngineInfo)
                        .setFaceEngineInfo(FaceEngineInfo)
                        .setKnowledgeEngineInfo(KnowledgeEngineInfo)
                        .setServiceEngineInfo(ServiceEngineInfo)
                        .build());
    }

    public static ErkApiMsg getErkEngineDeleteRQ(int OrgId, int UserId, long MsgTime, ServiceType_e
            ServiceType, String PhysioEngine_ReceiveQueueName, String PhysioEngine_SendQueueName, String
                                                         SpeechEngine_ReceiveQueueName, String SpeechEngine_SendQueueName, String FaceEngine_ReceiveQueueName, String
                                                         FaceEngine_SendQueueName, String KnowledgeEngine_ReceiveQueueName, String KnowledgeEngine_SendQueueName) {
        return getErkApiMsg(
                ErkEngineDeleteRQ_m.newBuilder()
                        .setErkInterMsgHead(getErkInterMsgHead(ErkInterMsgType_e.ErkEngineDeleteRQ, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setServiceType(ServiceType)
                        .setPhysioEngineReceiveQueueName(PhysioEngine_ReceiveQueueName)
                        .setPhysioEngineSendQueueName(PhysioEngine_SendQueueName)
                        .setSpeechEngineReceiveQueueName(SpeechEngine_ReceiveQueueName)
                        .setSpeechEngineSendQueueName(SpeechEngine_SendQueueName)
                        .setFaceEngineReceiveQueueName(FaceEngine_ReceiveQueueName)
                        .setFaceEngineSendQueueName(FaceEngine_SendQueueName)
                        .setKnowledgeEngineReceiveQueueName(KnowledgeEngine_ReceiveQueueName)
                        .setKnowledgeEngineSendQueueName(KnowledgeEngine_SendQueueName)
                        .build());
    }

    public static ErkApiMsg getErkEngineDeleteRP(int OrgId, int UserId, long MsgTime, ReturnCode_e
            ReturnCode, ErkEngineInfo_s PhysioEngineInfo, ErkEngineInfo_s SpeechEngineInfo, ErkEngineInfo_s
                                                         FaceEngineInfo, ErkEngineInfo_s KnowledgeEngineInfo, ErkEngineInfo_s ServiceEngineInfo) {
        return getErkApiMsg(
                ErkEngineDeleteRP_m.newBuilder()
                        .setErkInterMsgHead(getErkInterMsgHead(ErkInterMsgType_e.ErkEngineDeleteRP, OrgId, UserId))
                        .setMsgTime(MsgTime)
                        .setReturnCode(ReturnCode)
                        .setPhysioEngineInfo(PhysioEngineInfo)
                        .setSpeechEngineInfo(SpeechEngineInfo)
                        .setFaceEngineInfo(FaceEngineInfo)
                        .setKnowledgeEngineInfo(KnowledgeEngineInfo)
                        .setServiceEngineInfo(ServiceEngineInfo)
                        .build());
    }

    public static ErkApiMsg getHeartBeatRQ(QueueInfo_s QueueInfo, int OrgId, int UserId, String QueueName) {
        return getErkApiMsg(
                HeartBeatRQ_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.ErkMsgType_unknown, QueueInfo, OrgId, UserId))
                        .setQueueName(QueueName)
                        .build());
    }

    public static ErkApiMsg getHeartBeatRP(QueueInfo_s QueueInfo, int OrgId, int UserId, int Status) {
        return getErkApiMsg(
                HeartBeatRP_m.newBuilder()
                        .setErkMsgHead(getErkMsgHead(ErkMsgType_e.ErkMsgType_unknown, QueueInfo, OrgId, UserId))
                        .setStatus(Status)
                        .build());
    }

//    public static ErkApiMsg getEmoRecogRQ(QueueInfo_s QueueInfo, int OrgId, int UserId, long DataTimeStamp,
//                                          int MsgDataLength, ByteString MsgDataFrame) {
//        return getErkApiMsg(
//                EmoRecogRQ_m.newBuilder()
//                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.EmoRecogRQ, QueueInfo, OrgId, UserId))
//                        .setDataTimeStamp(DataTimeStamp)
//                        .setMsgDataLength(MsgDataLength)
//                        .setMsgDataFrame(MsgDataFrame)
//                        .build());
//    }

    public static ErkApiMsg getEmoRecogRP(QueueInfo_s QueueInfo, int OrgId, int UserId, ReturnCode_e ReturnCode,
                                          long EmoRecogTime, EmotionType_e Emotion, float Accuracy) {
        return getErkApiMsg(
                EmoRecogRP_m.newBuilder()
                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.EmoRecogRP, QueueInfo, OrgId, UserId))
                        .setReturnCode(ReturnCode)
                        .setEmoRecogTime(EmoRecogTime)
                        .setEmotion(Emotion)
                        .setAccuracy(Accuracy)
                        .build());
    }

//    public static ErkApiMsg getPhysioEmoRecogRQ(QueueInfo_s QueueInfo, int OrgId, int UserId, long DataTimeStamp,
//                                                int MsgDataLength, ByteString MsgDataFrame) {
//        return getErkApiMsg(
//                PhysioEmoRecogRQ_m.newBuilder()
//                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.PhysioEmoRecogRQ, QueueInfo, OrgId, UserId))
//                        .setDataTimeStamp(DataTimeStamp)
//                        .setMsgDataLength(MsgDataLength)
//                        .setMsgDataFrame(MsgDataFrame)
//                        .build());
//    }

    public static ErkApiMsg getPhysioEmoRecogRP(QueueInfo_s QueueInfo, int OrgId, int UserId, ReturnCode_e
            ReturnCode, long EmoRecogTime, EmotionType_e Emotion, float Accuracy) {
        return getErkApiMsg(
                PhysioEmoRecogRP_m.newBuilder()
                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.PhysioEmoRecogRP, QueueInfo, OrgId, UserId))
                        .setReturnCode(ReturnCode)
                        .setEmoRecogTime(EmoRecogTime)
                        .setEmotion(Emotion)
                        .setAccuracy(Accuracy)
                        .build());
    }

//    public static ErkApiMsg getSpeechEmoRecogRQ(QueueInfo_s QueueInfo, int OrgId, int UserId, long DataTimeStamp,
//                                                int MsgDataLength, ByteString MsgDataFrame) {
//        return getErkApiMsg(
//                SpeechEmoRecogRQ_m.newBuilder()
//                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.SpeechEmoRecogRQ, QueueInfo, OrgId, UserId))
//                        .setDataTimeStamp(DataTimeStamp)
//                        .setMsgDataLength(MsgDataLength)
//                        .setMsgDataFrame(MsgDataFrame)
//                        .build());
//    }

    public static ErkApiMsg getSpeechEmoRecogRP(QueueInfo_s QueueInfo, int OrgId, int UserId, ReturnCode_e
            ReturnCode, long EmoRecogTime, EmotionType_e Emotion, float Accuracy) {
        return getErkApiMsg(
                SpeechEmoRecogRP_m.newBuilder()
                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.SpeechEmoRecogRP, QueueInfo, OrgId, UserId))
                        .setReturnCode(ReturnCode)
                        .setEmoRecogTime(EmoRecogTime)
                        .setEmotion(Emotion)
                        .setAccuracy(Accuracy)
                        .build());
    }

//    public static ErkApiMsg getFaceEmoRecogRQ(QueueInfo_s QueueInfo, int OrgId, int UserId, long DataTimeStamp,
//                                              int MsgDataLength, ByteString MsgDataFrame) {
//        return getErkApiMsg(
//                FaceEmoRecogRQ_m.newBuilder()
//                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.FaceEmoRecogRQ, QueueInfo, OrgId, UserId))
//                        .setDataTimeStamp(DataTimeStamp)
//                        .setMsgDataLength(MsgDataLength)
//                        .setMsgDataFrame(MsgDataFrame)
//                        .build());
//    }

    public static ErkApiMsg getFaceEmoRecogRP(QueueInfo_s QueueInfo, int OrgId, int UserId, ReturnCode_e ReturnCode,
                                              long EmoRecogTime, EmotionType_e Emotion, float Accuracy) {
        return getErkApiMsg(
                FaceEmoRecogRP_m.newBuilder()
                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.FaceEmoRecogRP, QueueInfo, OrgId, UserId))
                        .setReturnCode(ReturnCode)
                        .setEmoRecogTime(EmoRecogTime)
                        .setEmotion(Emotion)
                        .setAccuracy(Accuracy)
                        .build());
    }

    public static ErkApiMsg getEmoRecogNoti(QueueInfo_s QueueInfo, int OrgId, int UserId,
                                            long EmoRecogTime, EmotionType_e Emotion, float Accuracy) {
        return getErkApiMsg(
                EmoRecogNoti_m.newBuilder()
                        .setErkMsgDataHead(getErkMsgDataHead(ErkMsgType_e.ErkMsgType_unknown, QueueInfo, OrgId, UserId))
                        .setEmoRecogTime(EmoRecogTime)
                        .setEmotion(Emotion)
                        .setAccuracy(Accuracy)
                        .build());
    }
}
