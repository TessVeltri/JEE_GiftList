package be.veltri.API;

import java.util.ArrayList;

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

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

@Path("/reserve")
public class ReserveAPI extends Application {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAll() {
		ArrayList<Reserve> allReserve = Reserve.findAll();
		if (allReserve == null)
			return Response.status(Status.BAD_REQUEST).build();
		return Response.status(Status.OK).entity(allReserve).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allByUser")
	public Response findAllByUser(@QueryParam("idUser") int idUser) {
		User user = new User();
		user.setIdUser(idUser);
		user = user.findById();
		ArrayList<Reserve> allReserve = Reserve.findAllByUser(user);
		if (allReserve == null)
			return Response.status(Status.BAD_REQUEST).build();
		return Response.status(Status.OK).entity(allReserve).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(@FormParam("amount") String amount, @FormParam("idGift") String idGift,
			@FormParam("idUser") String idUser) {
		if (amount == null || idGift == null || idUser == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User();
		user.setIdUser(Integer.parseInt(idUser));
		Gift gift = new Gift();
		gift.setIdGift(Integer.parseInt(idGift));
		Reserve res = new Reserve(Integer.parseInt(amount), user, gift);
		boolean create = res.create();
		if (!create)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.CREATED).entity(true).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(@QueryParam("idReserve") int idReserve) {
		if (idReserve == 0)
			return Response.status(Status.BAD_REQUEST).build();
		Reserve res = new Reserve();
		res.setIdReserve(idReserve);
		boolean delete = res.delete();
		if (!delete)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.NO_CONTENT).entity(true).build();
	}
	
}
