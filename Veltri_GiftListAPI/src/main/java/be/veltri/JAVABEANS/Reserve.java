package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.ReserveDAO;

public class Reserve implements Serializable{

	// Attributs
    private static final long serialVersionUID = -3078588241404049583L;
    private int idReserve;
    private int amount;
    private User user;
    
    private static ReserveDAO reserveDAO = new ReserveDAO();

	// Constructeurs
    public Reserve() {}

	public Reserve(int amount, User user) {
		super();
		this.idReserve = 0;
		this.amount = amount;
		this.user = user;
	}

	// Getters et Setters
	public int getIdReserve() {
		return idReserve;
	}

	public void setIdReserve(int idReserve) {
		this.idReserve = idReserve;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Méthodes
    
	public static ArrayList<Reserve> getAll (){
		return reserveDAO.findAll();
	}

}