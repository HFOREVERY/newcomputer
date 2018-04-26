package org.chenweifeng.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckCode
 */
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		BufferedImage bi = new BufferedImage(70, 30, BufferedImage.TYPE_INT_RGB);

		Random r = new Random();

		int code = 1000 + r.nextInt(9000);

		Graphics g = bi.getGraphics();
		g.setFont(new Font("黑体", Font.ITALIC, 20));
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(code), 10, 20);

		request.getSession().setAttribute("CheckCode", String.valueOf(code));

		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpeg", out);

		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
