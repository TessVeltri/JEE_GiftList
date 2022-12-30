package be.veltri.DAO;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import be.veltri.JAVABEANS.Reserve;

public class ReserveDAO implements DAO<Reserve>{

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Reserve obj) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findId(Reserve obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
