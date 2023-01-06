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

import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

public class ReserveDAO implements DAO<Reserve> {

	private WebResource resource;
	private static String apiUrl;
	private Client client;
	private ObjectMapper mapper;

	private static URI getBaseUri() {
		return UriBuilder.fromUri(apiUrl).build();
	}

	public ReserveDAO() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		apiUrl = getApiUrl();
		resource = client.resource(getBaseUri());
		mapper = new ObjectMapper();
	}

	@Override
	public boolean create(Reserve obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("amount", String.valueOf(obj.getAmount()));
		parameters.add("idGift", String.valueOf(obj.getGift().getIdGift()));
		parameters.add("idUser", String.valueOf(obj.getUser().getIdUser()));
		ClientResponse res= resource
				.path("reserve")
				.path("create")
				.post(ClientResponse.class,parameters);
		int StatusCode=res.getStatus();
		if(StatusCode == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Reserve obj) {
		ClientResponse res= resource
				.path("reserve")
				.path("delete")
				.queryParam("idReserve",String.valueOf(obj.getIdReserve()))
				.delete(ClientResponse.class);
		int StatusCode=res.getStatus();
		if(StatusCode == 204) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Reserve obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reserve find(Reserve obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reserve> findAll() {
		String res = resource.path("reserve").path("all").get(String.class);
		ArrayList<Reserve> reserves = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			reserves = mapper.readValue(res, new TypeReference<ArrayList<Reserve>>() {
			});
			return reserves;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int findId(Reserve obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Reserve findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Reserve> findAllByUser(User user) {
		String res = resource.path("reserve").path("allByUser").queryParam("idUser", String.valueOf(user.getIdUser()))
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		ArrayList<Reserve> reserves = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			reserves = mapper.readValue(res, new TypeReference<ArrayList<Reserve>>() {
			});
			return reserves;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
