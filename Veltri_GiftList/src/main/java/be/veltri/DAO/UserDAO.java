package be.veltri.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import be.veltri.JAVABEANS.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UserDAO implements DAO<User> {
	private WebResource resource;
	private static String apiUrl;
	private Client client;
	private ObjectMapper mapper;

	private static URI getBaseUri() {
		return UriBuilder.fromUri(apiUrl).build();
	}

	public UserDAO() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		apiUrl = getApiUrl();
		resource = client.resource(getBaseUri());
		mapper = new ObjectMapper();
	}

	@Override
	public boolean create(User obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("name", obj.getName());
		parameters.add("firstname", obj.getFirstname());
		parameters.add("email", obj.getEmail());
		parameters.add("password", obj.getPassword());
		ClientResponse res= resource
				.path("user")
				.path("create")
				.post(ClientResponse.class,parameters);
		int StatusCode=res.getStatus();
		if(StatusCode == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(User obj) {
		MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("email", obj.getEmail());
		paramsPost.add("password", obj.getPassword());
		ClientResponse responseJSON = resource.path("user").path("login").accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, paramsPost);
		int status = responseJSON.getStatus();
		if (status == 200) {
			String response = responseJSON.getEntity(String.class);
			try {
				return (User) mapper.readValue(response, User.class);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public ArrayList<User> findAll() {
		String res = resource.path("user").path("all").get(String.class);
		ArrayList<User> users = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			users = mapper.readValue(res, new TypeReference<ArrayList<User>>() {
			});
			return users;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int findId(User obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
