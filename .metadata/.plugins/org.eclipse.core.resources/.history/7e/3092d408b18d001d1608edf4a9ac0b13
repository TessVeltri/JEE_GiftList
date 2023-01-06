package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import be.veltri.DAO.ReserveDAO;

public class Reserve implements Serializable{

	// Attributs
    private static final long serialVersionUID = -3078588241404049583L;
    private int idReserve;
    private int amount;
    private Gift gift;
    private User user;
    
    private static ReserveDAO reserveDAO = new ReserveDAO();

	// Constructeurs
    public Reserve() {}

	public Reserve(int amount, User user, Gift gift) {
		super();
		this.idReserve = 0;
		this.amount = amount;
		this.user = user;
		this.gift = gift;
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
	
	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	// Méthodes
    
	@Override
	public int hashCode() {
		return Objects.hash(amount, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserve other = (Reserve) obj;
		return amount == other.amount && Objects.equals(user, other.user);
	}
	
	public static ArrayList<Reserve> findAll (){
		return reserveDAO.findAll();
	}
	
	public static ArrayList<Reserve> findAllByUser (User user){
		return reserveDAO.findAllByUser(user);
	}
	
	
}