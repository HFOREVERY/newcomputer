package org.chenweifeng.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chenweifeng.util.DBUtil;
import org.chenweifeng.util.ReportInfo;

/**
 * Servlet implementation class TeaQueryProgressAction
 */
@WebServlet("/TeaQueryProgressAction")
public class TeaQueryProgressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaQueryProgressAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		Object roleSession = request.getSession().getAttribute("RoleTeacher");
		if (roleSession != null && roleSession.toString().equals("yes")) {
			// 业务处理
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM testdb.reports ORDER BY timestamp DESC";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				List<ReportInfo> list = new ArrayList<ReportInfo>();
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

				RequestDispatcher rd = request.getRequestDispatcher("teaQueryProgress.jsp");
				request.setAttribute("listReports", list);
				rd.forward(request, response);
			} catch (SQLException e) { 
				// 数据库操作失败
				e.printStackTrace();
				// 失败输出
				// 定义一个转发对象
				RequestDispatcher rd = request.getRequestDispatcher("teaQueryProgress.jsp");
				request.setAttribute("errorMsg", "查询数据提交有误！");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("teaQueryProgress.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
