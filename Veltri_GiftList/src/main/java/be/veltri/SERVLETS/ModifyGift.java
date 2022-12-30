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
 * Servlet implementation class ModifyGift
 */
public class ModifyGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyGift() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String where = request.getParameter("where");
		String idGift = request.getParameter("idGift");
		int index = Integer.parseInt(idGift);
		if (where.equals("local")) {
			GiftList gl = (GiftList) request.getSession().getAttribute("addList");
			Gift gift = gl.getLstGift().get(index);
			
			request.setAttribute("modifyGift", gift);
			request.setAttribute("where", where);
			request.setAttribute("idGift", idGift);
		} else {
			// vient de la DB
			Gift g = new Gift();
			g.setIdGift(index);
			Gift gift = g.findById();
			
			request.setAttribute("modifyGift", gift);
			request.setAttribute("where", where);
			request.setAttribute("idGift", idGift);
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/JSP/ModifyGift.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
