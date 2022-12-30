package be.veltri.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.veltri.JAVABEANS.GiftList;

public class GiftListDAO implements DAO<GiftList>{

	private WebResource resource;
	private static String apiUrl;
	private Client client;
	private ObjectMapper mapper;

	private static URI getBaseUri() {
		return UriBuilder.fromUri(apiUrl).build();
	}

	public GiftListDAO() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		apiUrl = getApiUrl();
		resource = client.resource(getBaseUri());
		mapper = new ObjectMapper();
	}
	
	@Override
	public boolean create(GiftList obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("nameList", obj.getNameList());
		parameters.add("limitDate", obj.getLimitDate());
		parameters.add("isActive", String.valueOf(obj.isActive()));
		parameters.add("occasion", obj.getOccasion());
		parameters.add("statusList", obj.getStatusList().toString());
		parameters.add("ownerEmail", obj.getOwner().getEmail());
		ClientResponse res= resource
				.path("giftList")
				.path("create")
				.post(ClientResponse.class,parameters);
		int StatusCode=res.getStatus();
		if(StatusCode == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(GiftList obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(GiftList obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GiftList find(GiftList obj) {
		MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("nameList", obj.getNameList());
		paramsPost.add("limitDate", obj.getLimitDate());
		paramsPost.add("occasion", obj.getOccasion());
		paramsPost.add("statusList", obj.getStatusList().toString());
		paramsPost.add("isActive", String.valueOf(obj.isActive()));
		paramsPost.add("ownerEmail", obj.getOwner().getEmail());
		ClientResponse responseJSON = resource.path("giftList").path("find").accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, paramsPost);
		int status = responseJSON.getStatus();
		if (status == 200) {
			String response = responseJSON.getEntity(String.class);
			try {
				return (GiftList) mapper.readValue(response, GiftList.class);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public ArrayList<GiftList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findId(GiftList obj) {
		MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("nameList", obj.getNameList());
		paramsPost.add("limitDate", obj.getLimitDate());
		paramsPost.add("occasion", obj.getOccasion());
		paramsPost.add("statusList", obj.getStatusList().toString());
		paramsPost.add("isActive", String.valueOf(obj.isActive()));
		paramsPost.add("ownerEmail", obj.getOwner().getEmail());
		ClientResponse responseJSON = resource.path("giftList").path("findId").accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, paramsPost);
		int status = responseJSON.getStatus();
		if (status == 200) {
			String response = responseJSON.getEntity(String.class);
			try {
				return (int) mapper.readValue(response, Integer.class);
			} catch (Exception e) {
				System.out.println(e);
				return -1;
			}
		}
		return -1;
	}

	@Override
	public GiftList findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
