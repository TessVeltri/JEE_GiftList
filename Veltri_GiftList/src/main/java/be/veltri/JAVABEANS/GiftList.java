package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import be.veltri.DAO.GiftListDAO;
import be.veltri.DAO.UserDAO;
import be.veltri.ENUMS.EnumStatusList;

public class GiftList implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8295878032338476601L;
	private int idGiftList;
	private String nameList;
	private String limitDate;
	private String occasion;
	private EnumStatusList statusList;
	private boolean isActive;
	private ArrayList<Gift> lstGift = new ArrayList<>();
	private User owner;
	private ArrayList<User> lstParticipant;

	private static GiftListDAO giftListDAO = new GiftListDAO();

	// Constructeurs
	public GiftList() {
	}

	public GiftList(String nameList, String limitDate, String occasion, EnumStatusList statusList, boolean isActive,
			ArrayList<Gift> lstGift, ArrayList<User> lstParticipant, User owner) {
		super();
		this.idGiftList = 0;
		this.nameList = nameList;
		this.limitDate = limitDate;
		this.occasion = occasion;
		this.statusList = statusList;
		this.isActive = isActive;
		this.lstGift = lstGift;
		this.owner = owner;
		this.lstParticipant = lstParticipant;
	}

	public GiftList(String nameList, String limitDate, String occasion, EnumStatusList statusList, boolean isActive,
			User owner) {
		super();
		this.idGiftList = 0;
		this.nameList = nameList;
		this.limitDate = limitDate;
		this.occasion = occasion;
		this.statusList = statusList;
		this.isActive = isActive;
		this.owner = owner;
		this.lstGift = new ArrayList<>();
		this.lstParticipant = new ArrayList<>();
	}

	// Getters et Setters
	public int getIdGiftList() {
		return idGiftList;
	}

	public void setIdGiftList(int idGiftList) {
		this.idGiftList = idGiftList;
	}
	
	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public EnumStatusList getStatusList() {
		return statusList;
	}

	public void setStatusList(EnumStatusList statusList) {
		this.statusList = statusList;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public ArrayList<Gift> getLstGift() {
		return lstGift;
	}

	public void setLstGift(ArrayList<Gift> lstGift) {
		this.lstGift = lstGift;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ArrayList<User> getLstParticipant() {
		return lstParticipant;
	}

	public void setLstParticipant(ArrayList<User> lstParticipant) {
		this.lstParticipant = lstParticipant;
	}

	public void addLstGift(Gift gift) {
		int cpt = 0;
		for (Gift g : this.getLstGift()) {
			if (g.equals(gift))
				cpt++;
		}
		if (cpt==0)
			this.lstGift.add(gift);
	}

	public void deleteLstGift(Gift gift) {
		int cpt = 0;
		for (Gift g : this.getLstGift()) {
			if (g.equals(gift))
				cpt++;
		}
		if (cpt!=0)
			this.lstGift.remove(gift);
	}

	public void addLstParticipant(User participant) {
		int cpt = 0;
		for (User u : this.getLstParticipant()) {
			if (u.equals(participant))
				cpt++;
		}
		if (cpt==0)
			this.lstParticipant.add(participant);
	}

	public void deleteLstParticipant(User participant) {
		int cpt = 0;
		for (User u : this.getLstParticipant()) {
			if (u.equals(participant))
				cpt++;
		}
		if (cpt!=0)
			this.lstParticipant.remove(participant);
	}

	// Méthodes
	@Override
	public int hashCode() {
		return Objects.hash(isActive, limitDate, lstGift, lstParticipant, nameList, occasion, owner, statusList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiftList other = (GiftList) obj;
		return isActive == other.isActive && Objects.equals(limitDate, other.limitDate)
				&& Objects.equals(lstGift, other.lstGift) && Objects.equals(lstParticipant, other.lstParticipant)
				&& Objects.equals(nameList, other.nameList) && Objects.equals(occasion, other.occasion)
				&& Objects.equals(owner, other.owner) && statusList == other.statusList;
	}

	public GiftList find() {
		return giftListDAO.find(this);
	}
	
	public GiftList findById() {
		return giftListDAO.findById(this.getIdGiftList());
	}

	public boolean create () {
		return giftListDAO.create(this);
	}
	
	public int findId() {
		return giftListDAO.findId(this);
	}
	

}