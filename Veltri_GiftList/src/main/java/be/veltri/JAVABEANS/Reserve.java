package be.veltri.JAVABEANS;

import java.io.Serializable;

public class Reserve implements Serializable{

	// Attributs
    private static final long serialVersionUID = -3078588241404049583L;
    private int amount;
    private User user;

	// Constructeurs
    public Reserve() {}

	public Reserve(int amount, User user) {
		super();
		this.amount = amount;
		this.user = user;
	}

	// Getters et Setters
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
    

}