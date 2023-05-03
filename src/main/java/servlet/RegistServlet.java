package servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.DBconfig;
import object.Admin;


@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Regist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		String user_name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String text = request.getParameter("text");

		Regist regist = new Regist();
		Admin admin = regist.check(user_name, email, password);

		if(admin.isRegist_flag()) {
			System.out.println("新規登録");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/list.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("登録失敗");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/regist.jsp");
			dispatcher.forward(request, response);
		}

	}

}