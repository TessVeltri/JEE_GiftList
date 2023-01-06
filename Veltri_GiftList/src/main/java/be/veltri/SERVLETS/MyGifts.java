package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.ENUMS.EnumStatusGift;
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
		String id = request.getParameter("idReserve");
		ArrayList<Reserve> allReserve = Reserve.findAllByUser(user);
		user.setLstReserve(allReserve);
		
		for (GiftList gl : user.getLstGiftList()) {
			gl = gl.findById();
			for (Gift gift : gl.getLstGift()) {
				gift = gift.findById();
				boolean reserveFound = false;
				for (Reserve reserveGift : gift.getLstReserve()) {
					for (Reserve reserveUser : user.getLstReserve()) {
						if (reserveUser.getIdReserve() == reserveGift.getIdReserve() && reserveFound == false) {
							reserveUser.setGift(gift);
							gift.setGiftList(gl);
							reserveFound = true;
						}
					}
				}
			}
		}
		
		// Delete reserve
		if (id != null && !id.equals("")) {
			int idReserve = Integer.parseInt(id);
			Reserve res = new Reserve();
			res.setIdReserve(idReserve);
			boolean delete = res.delete();
			if (delete) {
				for(int i = 0 ; i< user.getLstReserve().size() ; i++) {
					Reserve r = user.getLstReserve().get(i);
					if (r.getIdReserve() == idReserve) {
						user.deleteLstReserve(r);
						Gift gift = r.getGift();
						gift.deleteLstReserve(r);
						if (gift.getLstReserve().size() == 0) 
							gift.setStatusGift(EnumStatusGift.Free);
						else 
							gift.setStatusGift(EnumStatusGift.Shared);
						gift.update();
					}
				}
			}
		}
		
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
