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
import javax.servlet.http.HttpSession;

import org.chenweifeng.util.DBUtil;

/**
 * Servlet implementation class SubmitProgressAction
 */
@WebServlet("/SubmitProgressAction")
public class SubmitProgressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitProgressAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String checkCode = request.getParameter("textCheckCode");
		// 拿到 先前存储在session中的校验码，进行比较
		HttpSession session = request.getSession();

		String checkCodeInSession = (String) session.getAttribute("CheckCode");
		// 如果不一致
		if (!checkCode.equals(checkCodeInSession)) {
			// 直接转发到首页，加上错误信息
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("checkCodeErrorMsg", "校验码出错！");
			rd.forward(request, response);
		} else {
			// 业务处理
			String stuID = request.getParameter("textStuID");
			String stuName = request.getParameter("textStuName");
			String reportContent = request.getParameter("textProgressContent");
			String groupName = "";

			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT GroupName FROM testdb.groups WHERE StuID=? AND StuName=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, stuID);
				pstmt.setString(2, stuName);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					groupName = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("submitprogress.jsp");
				request.setAttribute("errorMsg", "查询数据提交有误！");
				rd.forward(request, response);
				return;
			}

			if (groupName.equals("")) {
				RequestDispatcher rd = request.getRequestDispatcher("submitprogress.jsp");
				request.setAttribute("errorMsg", "尚未加入任何团队！");
				rd.forward(request, response);
				return;
			}

			int result = 0;
			sql = "INSERT INTO testdb.reports(stuid, stuname, groupname, content, ip) VALUES(?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, stuID);
				pstmt.setString(2, stuName);
				pstmt.setString(3, groupName);
				pstmt.setString(4, reportContent);
				pstmt.setString(5, request.getRemoteAddr());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeJDBC(rs, pstmt, conn);
			}

			if (result == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("submitprogress.jsp");
				request.setAttribute("InfoMsg", "进度报告提交成功，请查询确认！");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("submitprogress.jsp");
				request.setAttribute("errorMsg", "进度报告提交失败！");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
