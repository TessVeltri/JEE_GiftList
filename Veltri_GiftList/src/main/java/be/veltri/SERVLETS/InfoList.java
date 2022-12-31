package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.veltri.JAVABEANS.*;

/**
 * Servlet implementation class InfoList
 */
public class InfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idGift = request.getParameter("idGift");
		ArrayList<String> errors = new ArrayList<>();
		User user = (User) request.getSession().getAttribute("user");
		if (idGift != null) {
			// delete
			Gift gift = new Gift();
			gift.setIdGift(Integer.parseInt(idGift));
			gift = gift.findById();
			if (gift.getLstReserve().size() > 0) {
				errors.add("This gift has already been reserved. You can't delete it.");
				request.setAttribute("errorsDeleteGift", errors);
				for (GiftList gl : user.getMyLists()) {
					gl = gl.find();
					for (Gift g : gl.getLstGift()) {
						if (g.getIdGift() == Integer.parseInt(idGift)) {
							request.setAttribute("giftList", gl);
						}
					}
				}
			} else {

				GiftList myList = null;
				for (GiftList gl : user.getMyLists()) {
					gl = gl.find();
					for (Gift g : gl.getLstGift()) {
						if (g.getIdGift() == Integer.parseInt(idGift)) {
							myList = gl;

						}
					}
				}
				boolean delete = gift.delete();
				if (delete) {
					myList.deleteLstGift(gift);
					request.setAttribute("giftList", myList);
				}
			}
		}

		request.getRequestDispatcher("/WEB-INF/JSP/InfoList.jsp").forward(request, response);
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
