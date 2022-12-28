package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.Objects;

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
	
}