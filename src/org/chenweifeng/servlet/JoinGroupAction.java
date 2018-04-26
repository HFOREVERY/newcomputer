package org.chenweifeng.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chenweifeng.util.DBUtil;

/**
 * Servlet implementation class JoinGroup
 */
@WebServlet("/JoinGroupAction")
public class JoinGroupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinGroupAction() {
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
		// 首先判断校验码是否一致
		// 获取用户提交的校验码
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
			String stuID = request.getParameter("textStuID");
			String stuName = request.getParameter("textStuName");
			String groupName = request.getParameter("textGroupName");
			String roleName = request.getParameter("selectRole");
			String ipAddr = request.getRemoteAddr();
			boolean isExist = false;

			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			// 创建或加入小组前检查是否存在重名小组
			String sqlCheck = "SELECT * FROM test_db.tb_groups WHERE groupname=?";
			try {
				pstmt = conn.prepareStatement(sqlCheck);
				pstmt.setString(1, groupName);
				rs = pstmt.executeQuery();
				isExist = rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (roleName.equals("组长") && isExist == true) {
				// 如果已经存在，则无法创建
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("errorMsg", "该团队已经创建！");
				rd.forward(request, response);
				return;
			}
			if (roleName.equals("组员") && isExist == false) {
				// 如果不存在，则无法加入
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("errorMsg", "不存在该团队，无法加入！");
				rd.forward(request, response);
				return;
			}

			// 否则，建立记录
			int result = 0;
			String sql = "INSERT INTO test_db.tb_groups VALUES(?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, stuID);
				pstmt.setString(2, stuName);
				pstmt.setString(3, groupName);
				pstmt.setString(4, roleName);
				pstmt.setString(5, ipAddr);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeJDBC(rs, pstmt, conn);
			}

			if (result == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
				request.setAttribute("resultMsg", stuName + (roleName.equals("组长") ? "创建小组" : "加入小组") + groupName + "成功");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("errorMsg", (roleName.equals("组长") ? "创建团队" : "加入团队") + "失败！");
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
