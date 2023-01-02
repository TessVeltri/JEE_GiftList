package be.veltri.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;

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
import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

/**
 * Servlet implementation class VerifAddGift
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class VerifAddGift extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifAddGift() {
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

		String idGiftList = null;
		Part idGiftListPart = request.getPart("idGiftList");
		try (Scanner scanner = new Scanner(idGiftListPart.getInputStream())) {
			idGiftList = scanner.nextLine(); // read from the part
		} catch (Exception ex) {

		}

		GiftList gl = (GiftList) request.getSession().getAttribute("addList");
		Gift gift = null;

		if (idGiftList != null && idGiftList != "") {
			gl = new GiftList();
			gl.setIdGiftList(Integer.parseInt(idGiftList));
			gl = gl.findById();
			gl.setStatusList(EnumStatusList.Active);
			User user = (User) request.getSession().getAttribute("user");

			gl.setOwner(user);

			gift = new Gift(nameGift, desc, Integer.parseInt(price), EnumPriority.valueOf(priority), weblink,
					EnumStatusGift.Free, image, imgName, imgExt, gl);
			boolean create = gift.create();
			if (create) {
				gl.addLstGift(gift);
				request.setAttribute("giftList", gl);
				request.getRequestDispatcher("/infoList").forward(request, response);
			}

		} else {
			gl = (GiftList) request.getSession().getAttribute("addList");
			gift = new Gift(nameGift, desc, Integer.parseInt(price), EnumPriority.valueOf(priority), weblink,
					EnumStatusGift.Free, image, imgName, imgExt, gl);
			gl.addLstGift(gift);
			request.setAttribute("addList", gl);
			request.getRequestDispatcher("/addList").forward(request, response);
		}

	}

}
