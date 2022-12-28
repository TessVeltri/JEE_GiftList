package be.veltri.API;

import java.util.Base64;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.veltri.ENUMS.EnumPriority;
import be.veltri.ENUMS.EnumStatusGift;
import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.*;

@Path("/gift")
public class GiftAPI extends Application {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("aPrice") String averagePrice, @FormParam("priority") String priorityGift,
			@FormParam("websiteLink") String websiteLink, @FormParam("statusGift") String statusGift,
			@FormParam("nameImage") String nameImage, @FormParam("extensionImage") String extensionImage,
			@FormParam("image") String image, @FormParam("nameList") String nameList, @FormParam("limitDate") String limitDate,
			@FormParam("isActive") String isActive, @FormParam("occasion") String occasion, @FormParam("statusList") String statusList,
			@FormParam("userEmail") String userEmail) {
		if (name == null || description == null || averagePrice == null || priorityGift == null || statusGift == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusList statusLst = EnumStatusList.valueOf(statusList);
		EnumStatusGift status = EnumStatusGift.valueOf(statusGift);
		EnumPriority priority = EnumPriority.valueOf(priorityGift);
		User owner = new User("","",userEmail, "");
		GiftList gl = new GiftList(nameList, limitDate, occasion, statusLst, Boolean.parseBoolean(isActive), owner.find());
		// img
		byte [] img = Base64.getDecoder().decode(image);
		Gift gift = new Gift(name, description, Integer.parseInt(averagePrice), priority, websiteLink, status, img, nameImage, extensionImage, gl);
		boolean create = gift.create();
		if (!create)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else 
			return Response.status(Status.CREATED).entity(true).build();
	}
}