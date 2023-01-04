package be.veltri.API;

import java.util.ArrayList;
import java.util.Base64;

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
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Response find(@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("firstname") String firstname) {
		if (email == null || password == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User (name, firstname, email, password);
		user = user.find();
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findById")
	public Response findById(@QueryParam("idUser") int idUser) {
		if (idUser == 0)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User();
		user.setIdUser(idUser);
		user = user.findById();
		if (user == null)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.OK).entity(user).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteParticipation")
	public Response deleteParticipation(@QueryParam("idGiftList") int idGiftList, @QueryParam("idUser") int idUser) {
		if (idUser == 0 || idGiftList == 0)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User();
		user.setIdUser(idUser);
		user = user.findById();
		
		GiftList gl = new GiftList();
		gl.setIdGiftList(idGiftList);
		gl = gl.findById();
		
		boolean delete = user.deleteParticipation(gl);
		if (!delete)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.NO_CONTENT).entity(true).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addParticipation")
	public Response addParticipation(@FormParam("idGiftList") int idGiftList, @FormParam("idUser") int idUser) {
		if (idUser == 0 || idGiftList == 0)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User();
		user.setIdUser(idUser);
		user = user.findById();
		
		GiftList gl = new GiftList();
		gl.setIdGiftList(idGiftList);
		gl = gl.findById();
		
		boolean created = user.addParticipation(gl);
		if (!created)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.OK).entity(true).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(@FormParam("idUser") String idUser, @FormParam("name") String name, @FormParam("firstname") String firstname,
			@FormParam("email") String email, @FormParam("password") String password) {
		if (idUser == null || name == null || firstname == null || email == null || password == null)
			return Response.status(Status.BAD_REQUEST).build();
		User user = new User(name, firstname, email, password);
		user.setIdUser(Integer.parseInt(idUser));
		boolean update = user.update();
		if (!update)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.ACCEPTED).entity(true).build();
	}
}
