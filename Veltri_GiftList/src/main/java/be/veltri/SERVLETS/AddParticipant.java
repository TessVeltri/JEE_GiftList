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
 * Servlet implementation class AddParticipant
 */
public class AddParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddParticipant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String firstname = request.getParameter("firstname");	
		String idGiftList = request.getParameter("idGiftList");
		
		if ((name != null && !name.equals(""))||(firstname !=null && !firstname.equals(""))) {
			ArrayList<User> lstU = new ArrayList<>();
			ArrayList<User> lstUsers = new ArrayList<>();
			// recherche dans db
			lstU = User.getAll();
			for (User u : lstU) {
				if (u.getName().toLowerCase().equals(name.toLowerCase()))
					lstUsers.add(u);
				else
					if (u.getFirstname().toLowerCase().equals(firstname.toLowerCase()))
						lstUsers.add(u);
			}
			request.setAttribute("lstUsers", lstUsers);
			request.setAttribute("idGiftList", idGiftList);
		}
		
		request.getRequestDispatcher("/WEB-INF/JSP/AddParticipant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
