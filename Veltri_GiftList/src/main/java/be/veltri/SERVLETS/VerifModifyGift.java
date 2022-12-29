package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import be.veltri.ENUMS.EnumPriority;
import be.veltri.ENUMS.EnumStatusGift;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;

/**
 * Servlet implementation class VerifModifyGift
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class VerifModifyGift extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifModifyGift() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nameGift = null;
		Part nameGiftPart = request.getPart("nameGift");
		try (Scanner scanner = new Scanner(nameGiftPart.getInputStream())) {
			nameGift = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		String price = null;
		Part pricePart = request.getPart("price");
		try (Scanner scanner = new Scanner(pricePart.getInputStream())) {
			price = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		String desc = null;
		Part descPart = request.getPart("descGift");
		try (Scanner scanner = new Scanner(descPart.getInputStream())) {
			desc = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		String priority = null;
		Part priorityPart = request.getPart("priority");
		try (Scanner scanner = new Scanner(priorityPart.getInputStream())) {
			priority = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		String weblink = null;
		Part weblinkPart = request.getPart("weblink");
		try (Scanner scanner = new Scanner(weblinkPart.getInputStream())) {
			weblink = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		Part img = request.getPart("image");
		String imgName = img.getSubmittedFileName();
		int i = imgName.lastIndexOf(".");
		String imgExt = imgName.substring(i + 1);
		byte[] image = img.getInputStream().readAllBytes();

		String where = null;
		Part wherePart = request.getPart("where");
		try (Scanner scanner = new Scanner(wherePart.getInputStream())) {
			where = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		String idGift = null;
		Part idGiftPart = request.getPart("idGiftModify");
		try (Scanner scanner = new Scanner(idGiftPart.getInputStream())) {
			idGift = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		if (where.equals("local")) {
			int index = Integer.parseInt(idGift);
			GiftList gl = (GiftList) request.getSession().getAttribute("addList");
			Gift gift = gl.getLstGift().get(index);

			gift.setName(nameGift);
			gift.setAveragePrice(Integer.parseInt(price));
			gift.setDescription(desc);
			gift.setPriority(EnumPriority.valueOf(priority));
			gift.setWebsiteLink(weblink);

			if (imgName != null && imgName != "") {
				gift.setImage(image);
				gift.setNameImage(imgName);
				gift.setExtensionImage(imgExt);

			}

			request.getSession().setAttribute("addList", gl);
			request.getRequestDispatcher("/addList").forward(request,response);
		} else {
			// todo partie update db
		}

	}

}
