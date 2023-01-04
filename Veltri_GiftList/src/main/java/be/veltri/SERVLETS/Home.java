package be.veltri.SERVLETS;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		user = User.login(user.getEmail(), user.getPassword());
		for (GiftList gl : user.getLstGiftList()) {
			LocalDate date = LocalDate.parse(gl.getLimitDate());
			if (date.isBefore(LocalDate.now()) && !gl.getStatusList().equals(EnumStatusList.Expired)) {
				gl.setStatusList(EnumStatusList.Expired);
				boolean update = gl.update();
				if (update) {
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
				}
			}
		}
		for (GiftList gl : user.getMyLists()) {
			LocalDate date = LocalDate.parse(gl.getLimitDate());
			if (date.isBefore(LocalDate.now()) && !gl.getStatusList().equals(EnumStatusList.Expired)) {
				gl.setStatusList(EnumStatusList.Expired);
				boolean update = gl.update();
				if (update) {
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
				}
			}
		}
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
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
