package org.chenweifeng.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeaVerify
 */
@WebServlet("/TeaVerify")
public class TeaVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pwd = request.getParameter("pwdTeacher");
		String url = request.getParameter("hiddenRequestURL");

		if (pwd.equals("789456")) {
			request.getSession().setAttribute("RoleTeacher", "yes");
			response.sendRedirect(url);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			request.getSession().setAttribute("RoleTeacher", "no");
			request.setAttribute("errorMsg", "验证失败！");
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
