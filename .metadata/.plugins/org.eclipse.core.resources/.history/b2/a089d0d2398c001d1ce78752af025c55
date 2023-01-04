package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.NotificationDAO;

public class Notification implements Serializable{

	// Attributs
    private static final long serialVersionUID = -8955457559899937470L;
    private int idNotif;
    private String comment;
    private boolean isRead;
    private User user;
    
    private static NotificationDAO notifDAO = new NotificationDAO();
    
    // Constructeurs
	public Notification() {}

	public Notification(String comment, boolean isRead, User user) {
		super();
		this.idNotif = 0;
		this.comment = comment;
		this.isRead = isRead;
		this.user = user;
	}

	// Getters et Setters
	public int getIdNotif() {
		return idNotif;
	}

	public void setIdNotif(int idNotif) {
		this.idNotif = idNotif;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	// Méthodes

	public static ArrayList<Notification> getAll (){
		return notifDAO.findAll();
	}

}