package org.chenweifeng.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chenweifeng.*;
import org.chenweifeng.util.DBUtil;
import org.chenweifeng.util.StuInfo;

/**
 * Servlet implementation class QueryAction
 */
@WebServlet("/QueryGroupAction")
public class QueryGroupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryGroupAction() {
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
		// 业务处理
		String stuID = request.getParameter("textStuID");
		String stuName = request.getParameter("textStuName");

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "SELECT * FROM testdb.groups WHERE StuID=? AND StuName=?";
		String sql = "SELECT * FROM test_db.tb_groups WHERE groupname = (SELECT groupname FROM test_db.tb_groups WHERE userid=? AND username=?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuID);
			pstmt.setString(2, stuName);
			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				RequestDispatcher rd = request.getRequestDispatcher("query.jsp");
//
//				request.setAttribute("stuID", rs.getString(1));
//				request.setAttribute("stuName", rs.getString(2));
//				request.setAttribute("groupName", rs.getString(3));
//				request.setAttribute("isLeader", rs.getBoolean(4) ? "是" : "否");
//
//				rd.forward(request, response);
//			}
			
			List<StuInfo> list = new ArrayList<StuInfo>();
			while (rs.next()) {
				StuInfo stuInfo = new StuInfo();
				stuInfo.setStuID(rs.getString(1));
				stuInfo.setStuName(rs.getString(2));
				stuInfo.setGroupName(rs.getString(3));
				stuInfo.setLeader(rs.getBoolean(4));
				list.add(stuInfo);
			}

			RequestDispatcher rd = request.getRequestDispatcher("querygroup.jsp");

			if (list.isEmpty()) {
				request.setAttribute("errorMsg", "未查到任何有效记录，请检查输入是否 正确！");
			}
			request.setAttribute("listSelected", list);
			rd.forward(request, response);
		} catch (SQLException e) { // 数据库操作失败
			e.printStackTrace();
			// 失败输出
			// 定义一个转发对象
			RequestDispatcher rd = request.getRequestDispatcher("querygroup.jsp");
			request.setAttribute("errorMsg", "查询数据提交有误！");
			rd.forward(request, response);
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
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
