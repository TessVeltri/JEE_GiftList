package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import be.veltri.DAO.UserDAO;

public class User implements Serializable{

	// Attributs
    private static final long serialVersionUID = 6393298679126542829L;
    private int idUser;
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
		this.idUser = 0;
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
		this.idUser = 0;
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
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
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
		int cpt = 0;
		for (GiftList g : this.getMyLists()) {
			if (g.equals(gl))
				cpt++;
		}
		if (cpt==0)
			this.myLists.add(gl);
	}
	
	public void deleteMyList (GiftList gl) {
		int cpt = 0;
		for (GiftList g : this.getMyLists()) {
			if (g.equals(gl))
				cpt++;
		}
		if (cpt!=0)
			this.myLists.remove(gl);
	}
	
	public void addLstGiftList (GiftList gl) {
		int cpt = 0;
		for (GiftList g : this.getLstGiftList()) {
			if (g.equals(gl))
				cpt++;
		}
		if (cpt==0)
			this.lstGiftList.add(gl);
	}
	
	public void deleteLstGiftList (GiftList gl) {
		int cpt = 0;
		for (GiftList g : this.getLstGiftList()) {
			if (g.equals(gl))
				cpt++;
		}
		if (cpt!=0)
			this.lstGiftList.remove(gl);
	}
	
	public void addLstNotification (Notification notif) {
		int cpt = 0;
		for (Notification n : this.getLstNotification()) {
			if (n.equals(notif))
				cpt++;
		}
		if (cpt==0)
			this.lstNotification.add(notif);
	}
	
	public void deleteLstNotification (Notification notif) {
		int cpt = 0;
		for (Notification n : this.getLstNotification()) {
			if (n.equals(notif))
				cpt++;
		}
		if (cpt!=0)
			this.lstNotification.remove(notif);
	}
	
	public void addLstReserve (Reserve res) {
		int cpt = 0;
		for (Reserve r : this.getLstReserve()) {
			if (r.equals(res))
				cpt++;
		}
		if (cpt==0)
			this.lstReserve.add(res);
	}
	
	public void deleteLstReserve (Reserve res) {
		int cpt = 0;
		for (Reserve r : this.getLstReserve()) {
			if (r.equals(res))
				cpt++;
		}
		if (cpt!=0)
			this.lstReserve.remove(res);
	}
	
	// Méthodes
	
	@Override
	public int hashCode() {
		return Objects.hash(email, firstname, lstGiftList, lstNotification, lstReserve, myLists, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(lstGiftList, other.lstGiftList)
				&& Objects.equals(lstNotification, other.lstNotification)
				&& Objects.equals(lstReserve, other.lstReserve) && Objects.equals(myLists, other.myLists)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

    public static User login (String email, String password) {
    	User find = new User ("", "", email, password);
    	return userDAO.login(find);
    }

	public static ArrayList<User> getAll() {
    	return userDAO.findAll();
    }
    
    public boolean create () {
    	return userDAO.create(this);
    }
    
    public User findById() {
    	return userDAO.findById(this.getIdUser());
    }
    
    public boolean deleteParticipation (GiftList gl) {
    	return userDAO.deleteParticipation (this, gl);
    }
    
    public boolean addParticipation (GiftList gl) {
    	return userDAO.addParticipation (this, gl);
    }
    
    public User find () {
    	return userDAO.find(this);
    }

}