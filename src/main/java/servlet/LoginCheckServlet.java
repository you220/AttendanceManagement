import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sql.Login;
import sql.Register;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");

		List<String> caveatList = new ArrayList<>();

		if(name == null || name == ""){
			caveatList.add("名前が入力されていません。");
		}
		
		if(mail == null || mail == ""){
			caveatList.add("メールが入力されていません。");
		}

		if(password == null || password==""){
			caveatList.add("パスワードが入力されていません。");
		}
	
		String view=null;

		boolean caveatFlg = false; 
		if(caveatList.size()!=0){
			caveatFlg = true;
		}
	
		DBconect db_info = new DBconect();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "SELECT * FROM users WHERE mail = ?";
		try(
			Connection connection = DriverManager.getConnection(url, user, pass);
			PreparedStatement statement = connection.prepareStatement(sql)){
				
				statement.setString(1, mail);
				ResultSet results = statement.executeQuery();

				
				Integer id = null;
				String getMail = null;
				String getPass = null;
				
				while(results.next()){
					id = results.getInt("id");
					getMail = results.getString("mail");
					getPass = results.getString("password");
				}
				if(password.equals(getPass)){
					HttpSession session = request.getSession();
					
					session.setAttribute("loginFlg", "loginFlg");
					session.setAttribute("loginId", id);
				}else{
					caveatFlg = true;
					caveatList.add("ログインできませんでした。");
				}

			


				connection.close();

		}catch (Exception e) {
			request.setAttribute("message","Exception:"+e.getMessage());
		}

		if(caveatFlg){
			request.setAttribute("caveatList",caveatList);
			view = "./WEB-INF/views/error.jsp";	
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}else{
			String redirectUrl = "/attendanceManagement/list";

			response.sendRedirect(redirectUrl);
		}

	}
}