package be.veltri.API;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
}
