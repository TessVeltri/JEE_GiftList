package be.veltri.DAO;

import java.util.ArrayList;

public interface DAO <T> {
	public boolean create(T obj);
	
	public boolean delete(T obj);
	
	public boolean update(T obj);
	
	public T find(T obj);
	
	public ArrayList<T> findAll ();
}
