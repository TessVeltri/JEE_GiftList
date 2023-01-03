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
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ArrayList<String> errors = new ArrayList<>();
		HttpSession session=request.getSession();
		
		if (email == null || email.equals("") || password == null || password.equals("")) {
			errors.add("Complete all fields");
			User u = new User("","",email, password);
			session.setAttribute("userConnect", u);
			request.setAttribute("errorsConnect", errors);
			request.getRequestDispatcher("/connection").forward(request,response);
		} else {
			User user = User.login(email, password);
			if (user == null)
				errors.add("Password or email incorrect");
	
			if (errors.size() == 0) {
				if(!session.isNew()) {
					session.invalidate();
					session=request.getSession();
				}
				session.setAttribute("user", user);
				response.sendRedirect("home");
			} else {
				User u = new User("","",email, password);
				session.setAttribute("userConnect", u);
				request.setAttribute("errorsConnect", errors);
				response.sendRedirect("connection");
			}
		}
	}
}
