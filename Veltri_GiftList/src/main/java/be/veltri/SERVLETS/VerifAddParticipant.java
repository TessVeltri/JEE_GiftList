package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class VerifAddParticipant
 */
public class VerifAddParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifAddParticipant() {
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

		String idUser = request.getParameter("idUser");
		String idGiftList = request.getParameter("idGiftList");
		ArrayList<String> errors = new ArrayList<String>();

		GiftList gl = new GiftList();
		gl.setIdGiftList(Integer.parseInt(idGiftList));
		gl = gl.findById();

		User participant = new User();
		participant.setIdUser(Integer.parseInt(idUser));
		participant = participant.findById();

		for (User p : gl.getLstParticipant()) {
			if (p.getIdUser() == participant.getIdUser())
				errors.add("User is already in this list.");
		}

		boolean created = false;
		if (errors.size() == 0) {
			created = participant.addParticipation(gl);
			if (!created) {
				// ???
			}

		}
		gl = gl.findById();
		
		request.setAttribute("errorsDeleteGift", errors);
		request.setAttribute("giftList", gl);
		request.getRequestDispatcher("/WEB-INF/JSP/InfoList.jsp").forward(request, response);
	}

}
