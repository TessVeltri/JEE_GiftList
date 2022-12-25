package be.veltri.API;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.veltri.JAVABEANS.User;

@Path("/user")
public class UserAPI extends Application {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@FormParam("email") String email, @FormParam("password") String password) {
		if (email == null || password == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = User.login(email, password);
		if (user == null)
			return Response.status(Status.NO_CONTENT).build();
		return Response.status(Status.OK).entity(user).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAll() {
		ArrayList<User> allUser = User.findAll();
		if (allUser == null)
			return Response.status(Status.BAD_REQUEST).build();
		return Response.status(Status.OK).entity(allUser).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(@FormParam("name") String name, @FormParam("firstname") String firstname,
			@FormParam("email") String email, @FormParam("password") String password) {
		if (name == null || firstname == null || email == null || password == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User (name, firstname, email, password);
		boolean create = user.create();
		if (!create)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else 
			return Response.status(Status.CREATED).entity(true).build();
	}
}
