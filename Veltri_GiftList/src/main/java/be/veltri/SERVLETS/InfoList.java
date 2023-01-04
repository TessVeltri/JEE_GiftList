package be.veltri.SERVLETS;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.ENUMS.EnumStatusList;
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
		String idUser = request.getParameter("idUser");
		String idGiftList = request.getParameter("idGiftList");
		String limitDate = request.getParameter("limitDateModify");
		String isActive = request.getParameter("isActive");
		ArrayList<String> errors = new ArrayList<>();
		GiftList giftList = new GiftList();
		if (idGiftList != null) {
			giftList.setIdGiftList(Integer.parseInt(idGiftList));
			giftList = giftList.findById();
		}
		if (isActive != null) {
			// modifier le switch active/inactive
			EnumStatusList status;
			if (isActive.equals(EnumStatusList.Active.toString()))
				status = EnumStatusList.Inactive;
			else 
				status = EnumStatusList.Active;
			giftList.setStatusList(status);
			boolean update = giftList.update();
			if (update)
				request.setAttribute("giftList", giftList);
		}
		if (limitDate != null) {
			// modifier la date (format : 2023-02-26)
			LocalDate lmtDate = LocalDate.parse(limitDate);
			if (lmtDate.isBefore(LocalDate.now()) || lmtDate.isEqual(LocalDate.now())) {
				errors.add("Choose a date after today.");
				request.setAttribute("errors", errors);
				request.setAttribute("giftList", giftList);
			} else {
				giftList.setLimitDate(limitDate);
				LocalDate date = LocalDate.parse(limitDate);
				if (date.isAfter(LocalDate.now())) {
					giftList.setStatusList(EnumStatusList.Active);
				}
				boolean update = giftList.update();
				if (update)
					request.setAttribute("giftList", giftList);
			}
		}
		if (idGift != null) {
			// delete gift
			Gift gift = new Gift();
			gift.setIdGift(Integer.parseInt(idGift));
			gift = gift.findById();
			if (gift.getLstReserve().size() > 0) {
				errors.add("This gift has already been reserved. You can't delete it.");
				giftList = giftList.findById();
				request.setAttribute("errors", errors);
				request.setAttribute("giftList", giftList);
			} else {
				GiftList myList = new GiftList();
				myList.setIdGiftList(Integer.parseInt(idGiftList));
				boolean delete = gift.delete();
				if (delete) {
					//myList.deleteLstGift(gift);
					myList = myList.findById();
					request.setAttribute("giftList", myList);
				}
			}
		}
		
		if (idUser != null) {
			// delete participant 
			User participant = new User();
			participant.setIdUser(Integer.parseInt(idUser));
			participant = participant.findById();
			
			boolean delete = participant.deleteParticipation(giftList);
			if (delete) {
				giftList.deleteLstParticipant(participant);
				request.setAttribute("giftList", giftList);
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
