package be.veltri.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.GiftList;

/**
 * Servlet implementation class getInfoListParticipant
 */
public class GetInfoListParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoListParticipant() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String order = request.getParameter("orderId");
		int orderId = Integer.parseInt(order);
		GiftList gl = new GiftList();
		gl.setIdGiftList(orderId);
		gl = gl.findById();
		if (gl != null) {
			request.getSession().setAttribute("giftList", gl);
			response.sendRedirect("infoListParticipant");
		} else {
			response.sendRedirect("home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
