package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class VerifAddList
 */
public class VerifAddList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifAddList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GiftList gl = (GiftList) request.getSession().getAttribute("addList");
		String nameList = request.getParameter("nameList");
		String limitDate = request.getParameter("limitDate");
		String isActive = request.getParameter("isActive");
		String occasion = request.getParameter("occasion");
		User owner = (User)request.getSession().getAttribute("user");
		
		gl.setOwner(owner);
		gl.setNameList(nameList);
		gl.setLimitDate(limitDate);
		gl.setActive(Boolean.parseBoolean(isActive));
		gl.setOccasion(occasion);
		
		boolean createList = gl.create();
		if (createList) {
			ArrayList<Integer> lstGiftErr = new ArrayList<>();
			int index = 1;
			
			for (Gift g : gl.getLstGift()) {
				boolean createGift = g.create();
				if (!createGift)
					lstGiftErr.add(index);
				index++;
			}
			if (lstGiftErr.size()==0) {
				// redirect
			} else {
				// cadeau intel pas passé
			}
		} else {
			// une erreur est survenue
		}
		
	}

}
