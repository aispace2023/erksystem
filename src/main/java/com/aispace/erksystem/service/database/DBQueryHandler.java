package com.aispace.erksystem.service.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * MariaDB Sql Handler
 *
 * @author youjin Choi
 */
@Slf4j
public class DBQueryHandler {
    private static final DBManager dbManager = DBManager.getInstance();
    private static final String TB_NAME = "record_history_";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DBQueryHandler handler = null;

    private DBQueryHandler() {
    }

    public static DBQueryHandler getInstance() {
        if (handler == null) handler = new DBQueryHandler();
        return handler;
    }

    // insert,update data
//    public boolean insertData(SessionInfo sessionInfo) {
//        boolean result = false;
//        String tableName = TB_NAME + sessionInfo.getTableInitial();
//        String insertSql = "INSERT IGNORE INTO " + tableName +
//                " (record_id, task_id, start_time, expire_date, file_path, rx_file_name, tx_file_name, cn, sn, codec, service_info )" +
//                " VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
//        try (Connection conn = dbManager.getConnect(); PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
//            // sessionInfo 기준 데이터 insert
//            pstmt.setString(1, sessionInfo.getSessionId());
//            pstmt.setString(2, sessionInfo.getTaskId());
//            pstmt.setString(3, new SimpleDateFormat(DATE_FORMAT).format(sessionInfo.getStartTime()));
//            pstmt.setString(4, sessionInfo.getExpiryDate());
//            pstmt.setString(5, sessionInfo.getFilePath());
//            pstmt.setString(6, sessionInfo.getRxFileName());
//            pstmt.setString(7, sessionInfo.getTxFileName());
//            pstmt.setString(8, sessionInfo.getCn());
//            pstmt.setString(9, sessionInfo.getSn());
//            pstmt.setString(10, sessionInfo.getCodec());
//            pstmt.setString(11, sessionInfo.getServiceInfo());
//            pstmt.executeUpdate();
//            result = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public boolean updateData(SessionInfo sessionInfo) {
//        boolean result = false;
//        String sessionId = sessionInfo.getSessionId();
//        String tableName = TB_NAME + sessionInfo.getTableInitial();
//        String updateSql = "UPDATE " + tableName +
//                "SET end_time='" + sessionInfo.getEndTime() + "'" +
//                " WHERE record_id='" + sessionId + "'";
//        try (Connection conn = dbManager.getConnect(); PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
//            // sessionInfo 기준 데이터 insert
//            pstmt.setString(1, new SimpleDateFormat(DATE_FORMAT).format(sessionInfo.getEndTime()));
//            pstmt.executeUpdate();
//            result = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }


}
