package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class CreateUser
 */
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		ArrayList<String> errors = new ArrayList<>();
		
		if (name == null || firstname == null || email == null || password == null || confirmPassword == null
				|| name.equals("") || firstname.equals("") || email.equals("") || password.equals("")
				|| confirmPassword.equals("")) {
			errors.add("Complete all fields");
		} 
		if (!password.equals(confirmPassword)) {
			errors.add("Your confirm password is incorrect");
		}
		boolean emailUsed = false;
		ArrayList<User> allUser = User.getAll();
		for (User u : allUser) {
			if (u.getEmail().equals(email))
				emailUsed = true;
		}
		if (emailUsed) {
			errors.add("This email is already used");
		}
		User insertUser = new User(name, firstname, email, password);
		request.getSession().setAttribute("insertUser", insertUser);
		request.setAttribute("errorsInsert", errors);
		if (errors.size() == 0) {
			boolean insert = insertUser.create();
			if (insert)
				request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request,response);
			 else 
				System.out.println("Erreur");
			
		} else {
			request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp").forward(request,response);
		}
		
	}

}