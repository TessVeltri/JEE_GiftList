package be.veltri.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class AddGift
 */
public class AddGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGift() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GiftList addList = (GiftList) request.getSession().getAttribute("addList");
		String nameList = request.getParameter("nameList");
		String dateLimit = request.getParameter("limitDate");
		String isActive = request.getParameter("isActive");
		String occasion = request.getParameter("occasion");
		if (addList == null) {
			 addList = new GiftList (nameList, dateLimit, occasion, EnumStatusList.Active, Boolean.parseBoolean(isActive), null);
		} else {
			addList.setNameList(nameList);
			addList.setLimitDate(dateLimit);
			addList.setActive(Boolean.parseBoolean(isActive));
			addList.setOccasion(occasion);
		}
		
		request.getSession().setAttribute("addList", addList);
		request.getRequestDispatcher("/WEB-INF/JSP/AddGift.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
