package be.veltri.DAO;

import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;

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

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.User;

public class GiftDAO implements DAO<Gift> {

	private WebResource resource;
	private static String apiUrl;
	private Client client;
	private ObjectMapper mapper;

	private static URI getBaseUri() {
		return UriBuilder.fromUri(apiUrl).build();
	}

	public GiftDAO() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		apiUrl = getApiUrl();
		resource = client.resource(getBaseUri());
		mapper = new ObjectMapper();
	}

	@Override
	public boolean create(Gift obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();

		String b64 = Base64.getEncoder().encodeToString(obj.getImage());

		parameters.add("name", obj.getName());
		parameters.add("description", obj.getDescription());
		parameters.add("aPrice", String.valueOf(obj.getAveragePrice()));
		parameters.add("priority", obj.getPriority().toString());
		parameters.add("websiteLink", obj.getWebsiteLink());
		parameters.add("statusGift", obj.getStatusGift().toString());
		parameters.add("nameImage", obj.getNameImage());
		parameters.add("extensionImage", obj.getExtensionImage());
		parameters.add("image", b64);
		parameters.add("nameList", obj.getGiftList().getNameList());
		parameters.add("limitDate", obj.getGiftList().getLimitDate());
		parameters.add("isActive", String.valueOf(obj.getGiftList().isActive()));
		parameters.add("occasion", obj.getGiftList().getOccasion());
		parameters.add("statusList", obj.getGiftList().getStatusList().toString());
		parameters.add("userEmail", obj.getGiftList().getOwner().getEmail());
		ClientResponse res = resource.path("gift").path("create").post(ClientResponse.class, parameters);
		int StatusCode = res.getStatus();
		if (StatusCode == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Gift obj) {
		ClientResponse res= resource
				.path("gift")
				.path("delete")
				.queryParam("idGift",String.valueOf(obj.getIdGift()))
				.delete(ClientResponse.class);
		int StatusCode=res.getStatus();
		if(StatusCode == 204) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Gift obj) {
		MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
		parameters.add("idGift", String.valueOf(obj.getIdGift()));
		parameters.add("name", obj.getName());
		parameters.add("description", obj.getDescription());
		parameters.add("aPrice", String.valueOf(obj.getAveragePrice()));
		parameters.add("priority", obj.getPriority().toString());
		parameters.add("websiteLink", obj.getWebsiteLink());
		parameters.add("statusGift", obj.getStatusGift().toString());
		parameters.add("nameImage", obj.getNameImage());
		parameters.add("extensionImage", obj.getExtensionImage());
		
		String b64 = "";
		if(obj.getImage() != null && obj.getImage().length > 0)
			b64 = Base64.getEncoder().encodeToString(obj.getImage());
		parameters.add("image", b64);
		
		ClientResponse res = resource.path("gift").path("update").post(ClientResponse.class, parameters);
		int StatusCode = res.getStatus();
		if (StatusCode == 202) {
			return true;
		}
		return false;
	}

	@Override
	public Gift find(Gift obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Gift> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findId(Gift obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Gift findById(int id) {
		ClientResponse responseJSON = resource.path("gift").path("findById").queryParam("idGift",String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		int status = responseJSON.getStatus();
		if (status == 200) {
			String response = responseJSON.getEntity(String.class);
			try {
				return (Gift) mapper.readValue(response, Gift.class);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

}
