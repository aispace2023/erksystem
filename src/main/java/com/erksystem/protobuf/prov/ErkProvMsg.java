// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkProvMsg.proto

// Protobuf Java Version: 3.25.0
package com.erksystem.protobuf.prov;

public final class ErkProvMsg {
  private ErkProvMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddServiceProviderInfoRQ_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AddServiceProviderInfoRQ_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddServiceProviderInfoRP_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AddServiceProviderInfoRP_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UpdateServiceProviderInfoRQ_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UpdateServiceProviderInfoRQ_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UpdateServiceProviderInfoRP_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UpdateServiceProviderInfoRP_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DelServiceProviderInfoRQ_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DelServiceProviderInfoRQ_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DelServiceProviderInfoRP_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DelServiceProviderInfoRP_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddUserInfoRQ_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AddUserInfoRQ_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddUserInfoRP_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AddUserInfoRP_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UpdateUserInfoRQ_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UpdateUserInfoRQ_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UpdateUserInfoRP_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UpdateUserInfoRP_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DelUserInfoRQ_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DelUserInfoRQ_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DelUserInfoRP_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DelUserInfoRP_m_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ErkProvMsg_m_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ErkProvMsg_m_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020ErkProvMsg.proto\"\217\001\n\032AddServiceProvide" +
      "rInfoRQ_m\022\017\n\007OrgName\030\001 \001(\t\022\016\n\006OrgPwd\030\002 \001" +
      "(\t\022\027\n\017ServiceDuration\030\003 \001(\t\022\022\n\nUserNumbe" +
      "r\030\004 \001(\005\022#\n\013ServiceType\030\005 \001(\0162\016.ServiceTy" +
      "pe_e\"\307\001\n\032AddServiceProviderInfoRP_m\022\017\n\007O" +
      "rgName\030\001 \001(\t\022\'\n\nResultType\030\002 \001(\0162\023.OrgPr" +
      "ofileResult_e\022\r\n\005OrgId\030\003 \001(\005\022\016\n\006OrgPwd\030\004" +
      " \001(\t\022\027\n\017ServiceDuration\030\005 \001(\t\022\022\n\nUserNum" +
      "ber\030\006 \001(\005\022#\n\013ServiceType\030\007 \001(\0162\016.Service" +
      "Type_e\"\224\002\n\035UpdateServiceProviderInfoRQ_m" +
      "\022\017\n\007OrgName\030\001 \001(\t\022\022\n\nOld_OrgPwd\030\002 \001(\t\022\033\n" +
      "\023Old_ServiceDuration\030\003 \001(\t\022\026\n\016Old_UserNu" +
      "mber\030\004 \001(\005\022\'\n\017Old_ServiceType\030\005 \001(\0162\016.Se" +
      "rviceType_e\022\022\n\nNew_OrgPwd\030\006 \001(\t\022\033\n\023New_S" +
      "erviceDuration\030\007 \001(\t\022\026\n\016New_UserNumber\030\010" +
      " \001(\005\022\'\n\017New_ServiceType\030\t \001(\0162\016.ServiceT" +
      "ype_e\"\275\002\n\035UpdateServiceProviderInfoRP_m\022" +
      "\017\n\007OrgName\030\001 \001(\t\022\'\n\nResultType\030\002 \001(\0162\023.O" +
      "rgProfileResult_e\022\022\n\nOld_OrgPwd\030\003 \001(\t\022\033\n" +
      "\023Old_ServiceDuration\030\004 \001(\t\022\026\n\016Old_UserNu" +
      "mber\030\005 \001(\005\022\'\n\017Old_ServiceType\030\006 \001(\0162\016.Se" +
      "rviceType_e\022\022\n\nNew_OrgPwd\030\007 \001(\t\022\033\n\023New_S" +
      "erviceDuration\030\010 \001(\t\022\026\n\016New_UserNumber\030\t" +
      " \001(\005\022\'\n\017New_ServiceType\030\n \001(\0162\016.ServiceT" +
      "ype_e\"=\n\032DelServiceProviderInfoRQ_m\022\017\n\007O" +
      "rgName\030\001 \001(\t\022\016\n\006OrgPwd\030\002 \001(\t\"V\n\032DelServi" +
      "ceProviderInfoRP_m\022\017\n\007OrgName\030\001 \001(\t\022\'\n\nR" +
      "esultType\030\002 \001(\0162\023.OrgProfileResult_e\"\257\001\n" +
      "\017AddUserInfoRQ_m\022\017\n\007OrgName\030\001 \001(\t\022\020\n\010Use" +
      "rName\030\002 \001(\t\022\017\n\007UserPwd\030\003 \001(\t\022\027\n\017ServiceD" +
      "uration\030\004 \001(\t\022\013\n\003Age\030\005 \001(\005\022\013\n\003Sex\030\006 \001(\005\022" +
      "\020\n\010UserType\030\007 \001(\005\022#\n\013ServiceType\030\010 \001(\0162\016" +
      ".ServiceType_e\"\351\001\n\017AddUserInfoRP_m\022\017\n\007Or" +
      "gName\030\001 \001(\t\022\020\n\010UserName\030\002 \001(\t\022(\n\nResultT" +
      "ype\030\003 \001(\0162\024.UserProfileResult_e\022\016\n\006UserI" +
      "d\030\004 \001(\005\022\017\n\007UserPwd\030\005 \001(\t\022\027\n\017ServiceDurat" +
      "ion\030\006 \001(\t\022\013\n\003Age\030\007 \001(\005\022\013\n\003Sex\030\010 \001(\005\022\020\n\010U" +
      "serType\030\t \001(\005\022#\n\013ServiceType\030\n \001(\0162\016.Ser" +
      "viceType_e\"\335\002\n\022UpdateUserInfoRQ_m\022\017\n\007Org" +
      "Name\030\001 \001(\t\022\020\n\010UserName\030\002 \001(\t\022\023\n\013Old_User" +
      "Pwd\030\003 \001(\t\022\033\n\023Old_ServiceDuration\030\004 \001(\t\022\017" +
      "\n\007Old_Age\030\005 \001(\005\022\017\n\007Old_Sex\030\006 \001(\005\022\024\n\014Old_" +
      "UserType\030\007 \001(\005\022\'\n\017Old_ServiceType\030\010 \001(\0162" +
      "\016.ServiceType_e\022\023\n\013New_UserPwd\030\t \001(\t\022\033\n\023" +
      "New_ServiceDuration\030\n \001(\t\022\017\n\007New_Age\030\013 \001" +
      "(\005\022\017\n\007New_Sex\030\014 \001(\005\022\024\n\014New_UserType\030\r \001(" +
      "\005\022\'\n\017New_ServiceType\030\016 \001(\0162\016.ServiceType" +
      "_e\"\207\003\n\022UpdateUserInfoRP_m\022\017\n\007OrgName\030\001 \001" +
      "(\t\022\020\n\010UserName\030\002 \001(\t\022(\n\nResultType\030\003 \001(\016" +
      "2\024.UserProfileResult_e\022\023\n\013Old_UserPwd\030\004 " +
      "\001(\t\022\033\n\023Old_ServiceDuration\030\005 \001(\t\022\017\n\007Old_" +
      "Age\030\006 \001(\005\022\017\n\007Old_Sex\030\007 \001(\005\022\024\n\014Old_UserTy" +
      "pe\030\010 \001(\005\022\'\n\017Old_ServiceType\030\t \001(\0162\016.Serv" +
      "iceType_e\022\023\n\013New_UserPwd\030\n \001(\t\022\033\n\023New_Se" +
      "rviceDuration\030\013 \001(\t\022\017\n\007New_Age\030\014 \001(\005\022\017\n\007" +
      "New_Sex\030\r \001(\005\022\024\n\014New_UserType\030\016 \001(\005\022\'\n\017N" +
      "ew_ServiceType\030\017 \001(\0162\016.ServiceType_e\"E\n\017" +
      "DelUserInfoRQ_m\022\017\n\007OrgName\030\001 \001(\t\022\020\n\010User" +
      "Name\030\002 \001(\t\022\017\n\007UserPwd\030\003 \001(\t\"n\n\017DelUserIn" +
      "foRP_m\022\017\n\007OrgName\030\001 \001(\t\022\020\n\010UserName\030\002 \001(" +
      "\t\022(\n\nResultType\030\003 \001(\0162\024.UserProfileResul" +
      "t_e\022\016\n\006Return\030\004 \001(\t\"\265\005\n\014ErkProvMsg_m\022?\n\030" +
      "AddServiceProviderInfoRQ\030\001 \001(\0132\033.AddServ" +
      "iceProviderInfoRQ_mH\000\022?\n\030AddServiceProvi" +
      "derInfoRP\030\002 \001(\0132\033.AddServiceProviderInfo" +
      "RP_mH\000\022E\n\033UpdateServiceProviderInfoRQ\030\003 " +
      "\001(\0132\036.UpdateServiceProviderInfoRQ_mH\000\022E\n" +
      "\033UpdateServiceProviderInfoRP\030\004 \001(\0132\036.Upd" +
      "ateServiceProviderInfoRP_mH\000\022?\n\030DelServi" +
      "ceProviderInfoRQ\030\005 \001(\0132\033.DelServiceProvi" +
      "derInfoRQ_mH\000\022?\n\030DelServiceProviderInfoR" +
      "P\030\006 \001(\0132\033.DelServiceProviderInfoRP_mH\000\022)" +
      "\n\rAddUserInfoRQ\030\007 \001(\0132\020.AddUserInfoRQ_mH" +
      "\000\022)\n\rAddUserInfoRP\030\010 \001(\0132\020.AddUserInfoRP" +
      "_mH\000\022/\n\020UpdateUserInfoRQ\030\t \001(\0132\023.UpdateU" +
      "serInfoRQ_mH\000\022/\n\020UpdateUserInfoRP\030\n \001(\0132" +
      "\023.UpdateUserInfoRP_mH\000\022)\n\rDelUserInfoRQ\030" +
      "\013 \001(\0132\020.DelUserInfoRQ_mH\000\022)\n\rDelUserInfo" +
      "RP\030\014 \001(\0132\020.DelUserInfoRP_mH\000B\005\n\003msg*\226\005\n\014" +
      "ErkMsgType_e\022\026\n\022ErkMsgType_unknown\020\000\022\024\n\020" +
      "ErkServiceConnRQ\020\001\022\024\n\020ErkServiceConnRP\020\002" +
      "\022\027\n\023ErkServiceDisConnRQ\020\003\022\027\n\023ErkServiceD" +
      "isConnRP\020\004\022\034\n\030AddServiceProviderInfoRQ\020\005" +
      "\022\034\n\030AddServiceProviderInfoRP\020\006\022\034\n\030DelSer" +
      "viceProviderInfoRQ\020\007\022\034\n\030DelServiceProvid" +
      "erInfoRP\020\010\022\034\n\030UpdServiceProviderInfoRQ\020\t" +
      "\022\034\n\030UpdServiceProviderInfoRP\020\n\022\021\n\rAddUse" +
      "rInfoRQ\020\013\022\021\n\rAddUserInfoRp\020\014\022\021\n\rDelUserI" +
      "nfoRQ\020\r\022\021\n\rDelUserInfoRp\020\016\022\021\n\rUpdUserInf" +
      "oRQ\020\017\022\021\n\rUpdUserInfoRP\020\020\022\024\n\020EmoRecogCrea" +
      "teRQ\020\021\022\024\n\020EmoRecogCreateRP\020\022\022\024\n\020EmoRecog" +
      "DeleteRQ\020\023\022\024\n\020EmoRecogDeleteRP\020\024\022\024\n\020Phys" +
      "ioEmoRecogRQ\020\025\022\024\n\020SpeechEmoRecogRQ\020\026\022\022\n\016" +
      "FaceEmoRecogRQ\020\027\022\016\n\nEmoRecogRQ\020\030\022\016\n\nEmoR" +
      "ecogRP\020\031\022\030\n\024ErkMsgType_reserved1\020\032\022\030\n\024Er" +
      "kMsgType_reserved2\020\033*\246\002\n\014ReturnCode_e\022\013\n" +
      "\007unknown\020\000\022\006\n\002ok\020\001\022\013\n\007nok_Org\020\002\022\014\n\010nok_U" +
      "ser\020\003\022\017\n\013nok_MsgType\020\004\022\022\n\016nok_EngineType" +
      "\020\005\022\027\n\023nok_EngineCondition\020\006\022\035\n\031nok_Physi" +
      "oEngineCondition\020\007\022\035\n\031nok_SpeechEngineCo" +
      "ndition\020\010\022\036\n\032nok_FaceEmoEngineCondition\020" +
      "\t\022\023\n\017nok_DevPlatform\020\n\022\023\n\017nok_EmotionTyp" +
      "e\020\013\022\017\n\013nok_reason1\020\014\022\017\n\013nok_reason2\020\r*\331\001" +
      "\n\014EngineType_e\022\026\n\022EngineType_unknown\020\000\022\031" +
      "\n\025EngineType_physiology\020\001\022\025\n\021EngineType_" +
      "speech\020\002\022\023\n\017EngineType_face\020\003\022\034\n\030EngineT" +
      "ype_emo_recog_all\020\004\022\030\n\024EngineType_knowle" +
      "dge\020\005\022\030\n\024EngineType_reserved1\020\006\022\030\n\024Engin" +
      "eType_reserved2\020\007*r\n\021EngineCondition_e\022\033" +
      "\n\027EngineCondition_unknown\020\000\022\035\n\031EngineCon" +
      "dition_available\020\001\022!\n\035EngineCondition_no" +
      "t_available\020\002*\355\002\n\rServiceType_e\022\027\n\023Servi" +
      "ceType_unknown\020\000\022\032\n\026ServiceType_physiolo" +
      "gy\020\001\022\026\n\022ServiceType_speech\020\002\022\025\n\021ServiceT" +
      "ype_video\020\003\022!\n\035ServiceType_physiology_sp" +
      "eech\020\004\022 \n\034ServiceType_physiology_video\020\005" +
      "\022\034\n\030ServiceType_speech_video\020\006\022\'\n#Servic" +
      "eType_physiology_speech_video\020\007\022\031\n\025Servi" +
      "ceType_knowledge\020\010\022\033\n\027ServiceType_servic" +
      "e_all\020\t\022\031\n\025ServiceType_reserved1\020\n\022\031\n\025Se" +
      "rviceType_reserved2\020\013*\263\003\n\rEmotionType_e\022" +
      "\027\n\023EmotionType_unknown\020\000\022\027\n\023EmotionType_" +
      "neutral\020\001\022\030\n\024EmotionType_positive\020\002\022\030\n\024E" +
      "motionType_negative\020\003\022\026\n\022EmotionType_str" +
      "ess\020\004\022\023\n\017EmotionType_joy\020\005\022\025\n\021EmotionTyp" +
      "e_happy\020\006\022\027\n\023EmotionType_sadness\020\007\022\025\n\021Em" +
      "otionType_anger\020\010\022\027\n\023EmotionType_arousal" +
      "\020\t\022\032\n\026EmotionType_relaxation\020\n\022\025\n\021Emotio" +
      "nType_peace\020\013\022\027\n\023EmotionType_anxiety\020\014\022\024" +
      "\n\020EmotionType_fear\020\r\022\027\n\023EmotionType_disg" +
      "ust\020\016\022\031\n\025EmotionType_reserved1\020\017\022\031\n\025Emot" +
      "ionType_reserved2\020\020*\344\002\n\022OrgProfileResult" +
      "_e\022\034\n\030OrgProfileResult_unknown\020\000\022\027\n\023OrgP" +
      "rofileResult_ok\020\001\022 \n\034OrgProfileResult_no" +
      "k_OrgName\020\002\022\037\n\033OrgProfileResult_nok_OrgP" +
      "wd\020\003\022(\n$OrgProfileResult_nok_ServiceDura" +
      "tion\020\004\022#\n\037OrgProfileResult_nok_UserNumbe" +
      "r\020\005\022$\n OrgProfileResult_nok_ServiceType\020" +
      "\006\022\033\n\027OrgProfileResult_nok_DB\020\007\022 \n\034OrgPro" +
      "fileResult_nok_reason1\020\010\022 \n\034OrgProfileRe" +
      "sult_nok_reason2\020\t*\320\003\n\023UserProfileResult" +
      "_e\022\035\n\031UserProfileResult_unknown\020\000\022\030\n\024Use" +
      "rProfileResult_ok\020\001\022!\n\035UserProfileResult" +
      "_nok_OrgName\020\002\022\"\n\036UserProfileResult_nok_" +
      "UserName\020\003\022!\n\035UserProfileResult_nok_User" +
      "Pwd\020\004\022)\n%UserProfileResult_nok_ServiceDu" +
      "ration\020\005\022\035\n\031UserProfileResult_nok_Age\020\006\022" +
      "\035\n\031UserProfileResult_nok_Sex\020\007\022\"\n\036UserPr" +
      "ofileResult_nok_UserType\020\010\022%\n!UserProfil" +
      "eResult_nok_ServiceType\020\t\022\034\n\030UserProfile" +
      "Result_nok_DB\020\n\022!\n\035UserProfileResult_nok" +
      "_reason1\020\013\022!\n\035UserProfileResult_nok_reas" +
      "on2\020\014B\037\n\033com.erksystem.protobuf.provP\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_AddServiceProviderInfoRQ_m_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AddServiceProviderInfoRQ_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AddServiceProviderInfoRQ_m_descriptor,
        new java.lang.String[] { "OrgName", "OrgPwd", "ServiceDuration", "UserNumber", "ServiceType", });
    internal_static_AddServiceProviderInfoRP_m_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_AddServiceProviderInfoRP_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AddServiceProviderInfoRP_m_descriptor,
        new java.lang.String[] { "OrgName", "ResultType", "OrgId", "OrgPwd", "ServiceDuration", "UserNumber", "ServiceType", });
    internal_static_UpdateServiceProviderInfoRQ_m_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_UpdateServiceProviderInfoRQ_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UpdateServiceProviderInfoRQ_m_descriptor,
        new java.lang.String[] { "OrgName", "OldOrgPwd", "OldServiceDuration", "OldUserNumber", "OldServiceType", "NewOrgPwd", "NewServiceDuration", "NewUserNumber", "NewServiceType", });
    internal_static_UpdateServiceProviderInfoRP_m_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_UpdateServiceProviderInfoRP_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UpdateServiceProviderInfoRP_m_descriptor,
        new java.lang.String[] { "OrgName", "ResultType", "OldOrgPwd", "OldServiceDuration", "OldUserNumber", "OldServiceType", "NewOrgPwd", "NewServiceDuration", "NewUserNumber", "NewServiceType", });
    internal_static_DelServiceProviderInfoRQ_m_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_DelServiceProviderInfoRQ_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DelServiceProviderInfoRQ_m_descriptor,
        new java.lang.String[] { "OrgName", "OrgPwd", });
    internal_static_DelServiceProviderInfoRP_m_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_DelServiceProviderInfoRP_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DelServiceProviderInfoRP_m_descriptor,
        new java.lang.String[] { "OrgName", "ResultType", });
    internal_static_AddUserInfoRQ_m_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_AddUserInfoRQ_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AddUserInfoRQ_m_descriptor,
        new java.lang.String[] { "OrgName", "UserName", "UserPwd", "ServiceDuration", "Age", "Sex", "UserType", "ServiceType", });
    internal_static_AddUserInfoRP_m_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_AddUserInfoRP_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AddUserInfoRP_m_descriptor,
        new java.lang.String[] { "OrgName", "UserName", "ResultType", "UserId", "UserPwd", "ServiceDuration", "Age", "Sex", "UserType", "ServiceType", });
    internal_static_UpdateUserInfoRQ_m_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_UpdateUserInfoRQ_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UpdateUserInfoRQ_m_descriptor,
        new java.lang.String[] { "OrgName", "UserName", "OldUserPwd", "OldServiceDuration", "OldAge", "OldSex", "OldUserType", "OldServiceType", "NewUserPwd", "NewServiceDuration", "NewAge", "NewSex", "NewUserType", "NewServiceType", });
    internal_static_UpdateUserInfoRP_m_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_UpdateUserInfoRP_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UpdateUserInfoRP_m_descriptor,
        new java.lang.String[] { "OrgName", "UserName", "ResultType", "OldUserPwd", "OldServiceDuration", "OldAge", "OldSex", "OldUserType", "OldServiceType", "NewUserPwd", "NewServiceDuration", "NewAge", "NewSex", "NewUserType", "NewServiceType", });
    internal_static_DelUserInfoRQ_m_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_DelUserInfoRQ_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DelUserInfoRQ_m_descriptor,
        new java.lang.String[] { "OrgName", "UserName", "UserPwd", });
    internal_static_DelUserInfoRP_m_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_DelUserInfoRP_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DelUserInfoRP_m_descriptor,
        new java.lang.String[] { "OrgName", "UserName", "ResultType", "Return", });
    internal_static_ErkProvMsg_m_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_ErkProvMsg_m_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ErkProvMsg_m_descriptor,
        new java.lang.String[] { "AddServiceProviderInfoRQ", "AddServiceProviderInfoRP", "UpdateServiceProviderInfoRQ", "UpdateServiceProviderInfoRP", "DelServiceProviderInfoRQ", "DelServiceProviderInfoRP", "AddUserInfoRQ", "AddUserInfoRP", "UpdateUserInfoRQ", "UpdateUserInfoRP", "DelUserInfoRQ", "DelUserInfoRP", "Msg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}