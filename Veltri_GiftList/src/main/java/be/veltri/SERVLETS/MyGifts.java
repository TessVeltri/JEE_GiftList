package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class MyGifts
 */
public class MyGifts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyGifts() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		ArrayList<Reserve> allReserve = Reserve.findAllByUser(user);
		user.setLstReserve(allReserve);
//		for (GiftList gl : user.getLstGiftList()) {
//			for (Gift gift : gl.getLstGift()) {
//				for (Reserve r : gift.getLstReserve()) {
//					if (r.getUser().getIdUser() == user.getIdUser()) {
//						user.addLstReserve(r);
//					}
//				}
//			}
//		}

		for (GiftList gl : user.getLstGiftList()) {
			gl = gl.findById();
			
			for (Gift gift : gl.getLstGift()) {
				boolean reserveFound = false;
				for (Reserve reserve : allReserve) {
					
					if (reserve.getGift().getIdGift() == gift.getIdGift() && reserveFound == false) {
						reserve.getGift().setGiftList(gl);
						reserveFound = true;
					}
				}
			}
		}
		//Gift test = allReserve.get(0).getGift().findById();
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/JSP/MyGifts.jsp").forward(request, response);

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
