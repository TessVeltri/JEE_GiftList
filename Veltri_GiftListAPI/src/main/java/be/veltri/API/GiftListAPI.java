package be.veltri.API;

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

import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.User;

@Path("/giftList")
public class GiftListAPI extends Application {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Response find(@FormParam("nameList") String nameList, @FormParam("limitDate") String limitDate,
			@FormParam("occasion") String occasion, @FormParam("statusList") String statusList,
			@FormParam("isActive") String isActive, @FormParam("ownerEmail") String ownerEmail) {
		if (nameList == null || limitDate == null || occasion == null || statusList == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusList status = EnumStatusList.valueOf(statusList);
		User owner = new User("","",ownerEmail, "");
		GiftList gl = new GiftList(nameList, limitDate, occasion, status, Boolean.parseBoolean(isActive), owner.find());
		GiftList result = gl.find();
		if (result == null)
			return Response.status(Status.NO_CONTENT).build();
		return Response.status(Status.OK).entity(result).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findId")
	public Response findId(@FormParam("nameList") String nameList, @FormParam("limitDate") String limitDate,
			@FormParam("occasion") String occasion, @FormParam("statusList") String statusList,
			@FormParam("isActive") String isActive, @FormParam("ownerEmail") String ownerEmail) {
		if (nameList == null || limitDate == null || occasion == null || statusList == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusList status = EnumStatusList.valueOf(statusList);
		User owner = new User("","",ownerEmail, "");
		GiftList gl = new GiftList(nameList, limitDate, occasion, status, Boolean.parseBoolean(isActive), owner.find());
		int idList = gl.findId();
		if (idList<0)
			return Response.status(Status.NO_CONTENT).build();
		return Response.status(Status.OK).entity(idList).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(@FormParam("nameList") String nameList, @FormParam("limitDate") String limitDate,
			@FormParam("occasion") String occasion, @FormParam("statusList") String statusList,
			@FormParam("isActive") String isActive, @FormParam("ownerEmail") String ownerEmail) {
		if (nameList == null || limitDate == null || occasion == null || statusList == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusList status = EnumStatusList.valueOf(statusList);
		User owner = new User("","",ownerEmail, "");
		GiftList gl = new GiftList (nameList, limitDate, occasion, status, Boolean.parseBoolean(isActive), owner.find());
		boolean create = gl.create();
		if (!create)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else 
			return Response.status(Status.CREATED).entity(true).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findById")
	public Response findById(@QueryParam("idGiftList") int idGiftList) {
		if (idGiftList == 0)
			return Response.status(Status.BAD_REQUEST).build();
		GiftList gl = new GiftList();
		gl.setIdGiftList(idGiftList);
		gl = gl.findById();
		if (gl == null)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.OK).entity(gl).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(@FormParam("idGiftList") String idGiftList, @FormParam("nameList") String nameList, @FormParam("limitDate") String limitDate,
			@FormParam("occasion") String occasion, @FormParam("statusList") String statusList,
			@FormParam("isActive") String isActive, @FormParam("ownerEmail") String ownerEmail) {
		if (idGiftList == null || nameList == null || limitDate == null || occasion == null || statusList == null)
			return Response.status(Status.BAD_REQUEST).build();
		EnumStatusList status = EnumStatusList.valueOf(statusList);
		User owner = new User("","",ownerEmail, "");
		GiftList gl = new GiftList (nameList, limitDate, occasion, status, Boolean.parseBoolean(isActive), owner.find());
		gl.setIdGiftList(Integer.parseInt(idGiftList));
		boolean update = gl.update();
		if (!update)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else 
			return Response.status(Status.ACCEPTED).entity(true).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(@QueryParam("idGiftList") int idGiftList) {
		if (idGiftList == 0)
			return Response.status(Status.BAD_REQUEST).build();
		GiftList giftList = new GiftList();
		giftList.setIdGiftList(idGiftList);
		giftList = giftList.findById();
		boolean delete = giftList.delete();
		if (!delete)
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		else
			return Response.status(Status.NO_CONTENT).entity(true).build();
	}

}
