package be.veltri.API;

import java.util.Base64;

import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
			@FormParam("image") String image, @FormParam("nameList") String nameList,
			@FormParam("limitDate") String limitDate, @FormParam("isActive") String isActive,
			@FormParam("occasion") String occasion, @FormParam("statusList") String statusList,
			@FormParam("userEmail") String userEmail) {
		if (name == null || description == null || averagePrice == null || priorityGift == null || statusGift == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusList statusLst = EnumStatusList.valueOf(statusList);
		EnumStatusGift status = EnumStatusGift.valueOf(statusGift);
		EnumPriority priority = EnumPriority.valueOf(priorityGift);
		User owner = new User("", "", userEmail, "");
		GiftList gl = new GiftList(nameList, limitDate, occasion, statusLst, Boolean.parseBoolean(isActive),
				owner.find());
		// img
		byte[] img = Base64.getDecoder().decode(image);
		Gift gift = new Gift(name, description, Integer.parseInt(averagePrice), priority, websiteLink, status, img,
				nameImage, extensionImage, gl);
		boolean create = gift.create();
		if (!create)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.CREATED).entity(true).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(@FormParam("idGift") String idGift, @FormParam("name") String name, @FormParam("description") String description,
			@FormParam("aPrice") String averagePrice, @FormParam("priority") String priorityGift,
			@FormParam("websiteLink") String websiteLink, @FormParam("statusGift") String statusGift,
			@FormParam("nameImage") String nameImage, @FormParam("extensionImage") String extensionImage,
			@FormParam("image") String image) {
		if (idGift == null || name == null || description == null || averagePrice == null || priorityGift == null || statusGift == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusGift status = EnumStatusGift.valueOf(statusGift);
		EnumPriority priority = EnumPriority.valueOf(priorityGift);
		int id = Integer.parseInt(idGift);
		byte[] img = Base64.getDecoder().decode(image);
		Gift gift = new Gift(name, description, Integer.parseInt(averagePrice), priority, websiteLink, status, img,
				nameImage, extensionImage, null);
		gift.setIdGift(id);
		boolean update = gift.update();
		if (!update)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.ACCEPTED).entity(true).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findById")
	public Response findById(@QueryParam("idGift") int idGift) {
		if (idGift == 0)
			return Response.status(Status.BAD_REQUEST).build();
		Gift gift = new Gift();
		gift.setIdGift(idGift);
		gift = gift.findById();
		if (gift == null)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.OK).entity(gift).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(@QueryParam("idGift") int idGift) {
		if (idGift == 0)
			return Response.status(Status.BAD_REQUEST).build();
		Gift gift = new Gift();
		gift.setIdGift(idGift);
		gift = gift.findById();
		boolean delete = gift.delete();
		if (!delete)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.NO_CONTENT).entity(true).build();
	}
}
