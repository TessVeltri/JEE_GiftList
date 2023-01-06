package be.veltri.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.ENUMS.EnumStatusGift;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class VerifAddReserve
 */
public class VerifAddReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifAddReserve() {
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
		String totally = request.getParameter("totally");
		String partially = request.getParameter("partially");
		GiftList gl = (GiftList) request.getSession().getAttribute("giftList");
		Gift gift = (Gift) request.getSession().getAttribute("gift");
		User user = (User) request.getSession().getAttribute("user");
		int amount = 0;
		String error = "";
		
		if (Boolean.parseBoolean(totally) == true) {
			amount = gift.getAveragePrice();
			gift.setStatusGift(EnumStatusGift.Reserved);
		} else { 
			amount = Integer.parseInt(partially);
			if (amount == 0)
				error = "You can't reserve a gift for 0 €";
			int rest = 0;
			int total = 0;
			for (Reserve r : gift.getLstReserve()) {
				total += r.getAmount();
			}
			rest = gift.getAveragePrice() - total;
			if (rest == amount) {
				gift.setStatusGift(EnumStatusGift.Reserved);
			} else {
				gift.setStatusGift(EnumStatusGift.Shared);
			}
		}
		if (!error.equals("")) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/addReserve").forward(request, response);
		}else {
			Reserve res = new Reserve(amount, user, gift);
			boolean create = res.create();
			if (create) {
				boolean update = gift.update();
				if (update) {
					for (Gift g : gl.getLstGift()) {
						if (g.getIdGift() == gift.getIdGift()) {
							g.setStatusGift(gift.getStatusGift());
							g.addLstReserve(res);
						}
					}
					request.getSession().setAttribute("giftList", gl);
					response.sendRedirect("infoListParticipant");
				}
			}
		}
		
	}

}
