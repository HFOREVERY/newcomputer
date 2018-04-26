package org.chenweifeng.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportDao {
	public static List<ReportInfo> selectReports(String sql, String[] para) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ReportInfo> list = new ArrayList<ReportInfo>();
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < para.length; i++) {
				pstmt.setString(i+1, para[i]);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportInfo reportInfo = new ReportInfo();
				reportInfo.setReportID(rs.getInt(1));
				reportInfo.setStuID(rs.getString(2));
				reportInfo.setStuName(rs.getString(3));
				reportInfo.setGroupName(rs.getString(4));
				reportInfo.setReportContent(rs.getString(5));
				reportInfo.setReportTimestamp(rs.getTimestamp(6));
				list.add(reportInfo);
			}
		} catch (SQLException e) { 
			// 数据库操作失败
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}
}
