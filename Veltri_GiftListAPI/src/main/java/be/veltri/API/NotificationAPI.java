package be.veltri.API;

import java.util.ArrayList;
import java.util.Base64;

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
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.User;

@Path("/notif")
public class NotificationAPI extends Application {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(@FormParam("comment") String comment, @FormParam("isRead") String isRead,
			@FormParam("idUser") String idUser) {
		if (comment == null || isRead == null || idUser == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User();
		user.setIdUser(Integer.parseInt(idUser));
		Notification notif = new Notification(comment, Boolean.parseBoolean(isRead), user);
		boolean create = notif.create();
		if (!create)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.CREATED).entity(true).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAll() {
		ArrayList<Notification> allNotifs = Notification.findAll();
		if (allNotifs == null)
			return Response.status(Status.BAD_REQUEST).build();
		return Response.status(Status.OK).entity(allNotifs).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(@FormParam("idNotif") String idNotif, @FormParam("comment") String comment, @FormParam("isRead") String isRead,
			@FormParam("idUser") String idUser) {
		if (idNotif == null || comment == null || isRead == null || idUser == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User();
		user.setIdUser(Integer.parseInt(idUser));
		user = user.findById();
		Notification notif = new Notification(comment, Boolean.parseBoolean(isRead), user);
		notif.setIdNotif(Integer.parseInt(idNotif));
		boolean update = notif.update();
		if (!update)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.ACCEPTED).entity(true).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findById")
	public Response findById(@QueryParam("idNotif") int idNotif) {
		if (idNotif == 0)
			return Response.status(Status.BAD_REQUEST).build();
		Notification notif = new Notification();
		notif.setIdNotif(idNotif);
		notif = notif.findById();
		if (notif == null)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.OK).entity(notif).build();
	}
}
