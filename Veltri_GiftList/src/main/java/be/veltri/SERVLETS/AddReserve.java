package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class AddReserve
 */
public class AddReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		boolean resMe = false;
		String idGift = request.getParameter("idGift");
		String error = "";
		Gift gift = new Gift();
		gift.setIdGift(Integer.parseInt(idGift));
		gift = gift.findById();

		int total = 0;
		for (Reserve r : gift.getLstReserve()) {
			if (r.getUser().getIdUser() == user.getIdUser())
				resMe = true;
			total += r.getAmount();
		}
		if (resMe) {
			error = "This gift is already reserved by you";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/infoListParticipant").forward(request, response);
		} else {
			if (total == gift.getAveragePrice()) {
				// Reserved completely
				error = "This gift is already reserved";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/infoListParticipant").forward(request, response);
			} else {
				// No reserve or Shared, can reserve partially
				request.getSession().setAttribute("gift", gift);
				request.getRequestDispatcher("/WEB-INF/JSP/AddReserve.jsp").forward(request, response);
			}
			
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
