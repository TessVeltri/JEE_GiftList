package be.veltri.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;

/**
 * Servlet implementation class AddList
 */
public class AddList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idGift = request.getParameter("idGift");
		if (idGift != null) {
			int index = Integer.parseInt(idGift);
			GiftList gl = (GiftList) request.getSession().getAttribute("addList");
			Gift gift = gl.getLstGift().get(index);
			gl.deleteLstGift(gift);
			request.getSession().setAttribute("addList", gl);
		}
		
		request.getRequestDispatcher("/WEB-INF/JSP/AddList.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
