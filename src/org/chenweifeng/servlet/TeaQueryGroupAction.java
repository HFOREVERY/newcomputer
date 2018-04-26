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
import org.chenweifeng.util.StuInfo;

/**
 * Servlet implementation class TeaQueryAction
 */
@WebServlet("/TeaQueryGroupAction")
public class TeaQueryGroupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeaQueryGroupAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Object roleSession = request.getSession().getAttribute("RoleTeacher");
		if (roleSession != null && roleSession.toString().equals("yes")) {
			// 业务处理
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM test_db.tb_groups ORDER BY groupname";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				List<StuInfo> list = new ArrayList<StuInfo>();
				while (rs.next()) {
					StuInfo stuInfo = new StuInfo();
					stuInfo.setStuID(rs.getString(1));
					stuInfo.setStuName(rs.getString(2));
					stuInfo.setGroupName(rs.getString(3));
					stuInfo.setLeader(rs.getBoolean(4));
					list.add(stuInfo);
				}

				RequestDispatcher rd = request.getRequestDispatcher("teaQueryGroup.jsp");
				request.setAttribute("listSelected", list);
				rd.forward(request, response);
			} catch (SQLException e) { // 数据库操作失败

				e.printStackTrace();
				// 失败输出
				// 定义一个转发对象
				RequestDispatcher rd = request.getRequestDispatcher("teaQueryGroup.jsp");
				request.setAttribute("errorMsg", "查询数据提交有误！");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("teaQueryGroup.jsp");
			rd.forward(request, response);
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
