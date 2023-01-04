package be.veltri.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.User;

public class NotificationDAO implements DAO<Notification> {

	private WebResource resource;
	private static String apiUrl;
	private Client client;
	private ObjectMapper mapper;

	private static URI getBaseUri() {
		return UriBuilder.fromUri(apiUrl).build();
	}

	public NotificationDAO() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		apiUrl = getApiUrl();
		resource = client.resource(getBaseUri());
		mapper = new ObjectMapper();
	}
	
	@Override
	public boolean create(Notification obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("comment", obj.getComment());
		parameters.add("isRead", String.valueOf(obj.isRead()));
		parameters.add("idUser", String.valueOf(obj.getUser().getIdUser()));
		ClientResponse res= resource
				.path("notif")
				.path("create")
				.post(ClientResponse.class,parameters);
		int StatusCode=res.getStatus();
		if(StatusCode == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Notification obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Notification obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("idNotif", String.valueOf(obj.getIdNotif()));
		parameters.add("comment", obj.getComment());
		parameters.add("isRead", String.valueOf(obj.isRead()));
		parameters.add("idUser", String.valueOf(obj.getUser().getIdUser()));
		
		ClientResponse res = resource.path("notif").path("update").post(ClientResponse.class, parameters);
		int StatusCode = res.getStatus();
		if (StatusCode == 202) {
			return true;
		}
		return false;
	}

	@Override
	public Notification find(Notification obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Notification> findAll() {
		String res = resource.path("notif").path("all").get(String.class);
		ArrayList<Notification> notifs = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			notifs = mapper.readValue(res, new TypeReference<ArrayList<Notification>>() {
			});
			return notifs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int findId(Notification obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notification findById(int id) {
		ClientResponse responseJSON = resource.path("notif").path("findById").queryParam("idNotif",String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		int status = responseJSON.getStatus();
		if (status == 200) {
			String response = responseJSON.getEntity(String.class);
			try {
				return (Notification) mapper.readValue(response, Notification.class);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

}
