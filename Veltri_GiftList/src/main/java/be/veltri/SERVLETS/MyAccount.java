package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class MyAccount
 */
public class MyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAccount() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/MyAccount.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("passwordInput");
		String email = request.getParameter("email");
		String error = "";
		
		if (password == null || email == null || password.equals("") || email.equals("")) {
			error = "Complete both input to modify";
		} else {
			User user = (User) request.getSession().getAttribute("user");			
			boolean emailUsed = false;
			ArrayList<User> allUser = User.getAll();
			for (User u : allUser) {
				if (u.getEmail().equals(user.getEmail()) && u.getIdUser() != user.getIdUser())
					emailUsed = true;
			}
			if (emailUsed) {
				error = "This email is already used";
			} else {
				user.setEmail(email);
				user.setPassword(password);
				boolean update = user.update();
				if (update) {
					request.getSession().setAttribute("user", user);
				} else {
					error = "Something wrong append";
				}
			}
		}
		request.setAttribute("error", error);
		doGet(request, response);
	}

}
