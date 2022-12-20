package be.veltri.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class InfoList
 */
public class GetInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetInfoList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		String from = request.getParameter("from");
		String order = request.getParameter("orderId");
		int orderId = Integer.parseInt(order);
		if (from.equals("my")) {
			GiftList gl = user.getMyLists().get(orderId);
			request.setAttribute("giftList", gl);
			request.getRequestDispatcher("/infoList").forward(request,response);
		} else {
			GiftList gl = user.getLstGiftList().get(orderId);
			request.setAttribute("giftList", gl);
			request.getRequestDispatcher("/infoList").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}