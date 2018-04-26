package org.chenweifeng.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chenweifeng.util.DBUtil;

/**
 * Servlet implementation class DeleteReportAction
 */
@WebServlet("/DeleteReportAction")
public class DeleteReportAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReportAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		// 业务处理
		Enumeration<String> reportIDNames = request.getParameterNames();

		int reportID = -1;
		while (reportIDNames.hasMoreElements()) {
			String name = reportIDNames.nextElement();
			if (name.startsWith("reportID-")) {
				reportID = Integer.valueOf(name.substring(name.indexOf("-") + 1));
				break;
			}
		}

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "DELETE FROM testdb.reports WHERE idreports=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportID);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { // 数据库操作失败
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		
		if (result == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
			request.setAttribute("resultMsg", "删除成功！");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
			request.setAttribute("resultMsg", "删除失败！");
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
