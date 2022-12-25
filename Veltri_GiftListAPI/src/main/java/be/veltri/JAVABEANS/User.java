package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.UserDAO;

public class User implements Serializable{

	// Attributs
    private static final long serialVersionUID = 6393298679126542829L;
    private String name;
    private String firstname;
    private String email;
    private String password;
    private ArrayList<GiftList> myLists;
    private ArrayList<GiftList> lstGiftList;
    private ArrayList<Notification> lstNotification;
    private ArrayList<Reserve> lstReserve;
    
    private static UserDAO userDAO = new UserDAO();

	// Constructeurs
    public User() {}

	public User(String name, String firstname, String email, String password, ArrayList<GiftList> myLists,
			ArrayList<GiftList> lstGiftList, ArrayList<Notification> lstNotification, ArrayList<Reserve> lstReserve) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.myLists = myLists;
		this.lstGiftList = lstGiftList;
		this.lstNotification = lstNotification;
		this.lstReserve = lstReserve;
	}
	
	public User(String name, String firstname, String email, String password) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.myLists = new ArrayList<>();
		this.lstGiftList = new ArrayList<>();
		this.lstNotification = new ArrayList<>();
		this.lstReserve = new ArrayList<>();
	}

	// Getters et Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<GiftList> getMyLists() {
		return myLists;
	}

	public void setMyLists(ArrayList<GiftList> myLists) {
		this.myLists = myLists;
	}

	public ArrayList<GiftList> getLstGiftList() {
		return lstGiftList;
	}

	public void setLstGiftList(ArrayList<GiftList> lstGiftList) {
		this.lstGiftList = lstGiftList;
	}

	public ArrayList<Notification> getLstNotification() {
		return lstNotification;
	}

	public void setLstNotification(ArrayList<Notification> lstNotification) {
		this.lstNotification = lstNotification;
	}

	public ArrayList<Reserve> getLstReserve() {
		return lstReserve;
	}

	public void setLstReserve(ArrayList<Reserve> lstReserve) {
		this.lstReserve = lstReserve;
	}
	
	public void addMyList (GiftList gl) {
		if (!this.getMyLists().contains(gl))
			this.myLists.add(gl);
	}
	
	public void deleteMyList (GiftList gl) {
		if (this.getMyLists().contains(gl))
			this.myLists.remove(gl);
	}
	
	public void addLstGiftList (GiftList gl) {
		if (!this.getLstGiftList().contains(gl))
			this.lstGiftList.add(gl);
	}
	
	public void deleteLstGiftList (GiftList gl) {
		if (this.getLstGiftList().contains(gl))
			this.lstGiftList.remove(gl);
	}
	
	public void addLstNotification (Notification notif) {
		if (!this.getLstNotification().contains(notif))
			this.lstNotification.add(notif);
	}
	
	public void deleteLstNotification (Notification notif) {
		if (this.getLstNotification().contains(notif))
			this.lstNotification.remove(notif);
	}
	
	public void addLstReserve (Reserve res) {
		if (!this.getLstReserve().contains(res))
			this.lstReserve.add(res);
	}
	
	public void deleteLstReserve (Reserve res) {
		if (this.getLstReserve().contains(res))
			this.lstReserve.remove(res);
	}
	
	// Méthodes
    public static User login (String email, String password) {
    	User find = new User ("", "", email, password);
    	return userDAO.login(find);
    }

    public static User findById(int id) {
    	return userDAO.findById(id);
    }
    
    public static ArrayList<User> findAll(){
    	return userDAO.findAll();
    }
    
    public boolean create() {
    	return userDAO.create(this);
    }
    
    public User find () {
    	return userDAO.find(this);
    }
    
    public int findId () {
    	return userDAO.findId(this);
    }


}