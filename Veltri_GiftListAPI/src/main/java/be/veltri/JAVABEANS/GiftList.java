package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.veltri.DAO.GiftListDAO;
import be.veltri.ENUMS.EnumStatusList;

public class GiftList implements Serializable{

	// Attributs
    private static final long serialVersionUID = 8295878032338476601L;
    private String nameList;
    private String limitDate;
    private String occasion;
    private EnumStatusList statusList;
    private boolean isActive;
    private ArrayList<Gift> lstGift = new ArrayList<>();
    private User owner;
    private ArrayList<User> lstParticipant;
    
    private static GiftListDAO giftlistDAO = new GiftListDAO();

    // Constructeurs
	public GiftList() {}

	public GiftList(String nameList, String limitDate, String occasion, EnumStatusList statusList, boolean isActive,
			ArrayList<Gift> lstGift, User owner) {
		super();
		this.nameList = nameList;
		this.limitDate = limitDate;
		this.occasion = occasion;
		this.statusList = statusList;
		this.isActive = isActive;
		this.lstGift = lstGift;
		this.owner = owner;
		this.lstParticipant = new ArrayList<>();
	}
	
	public GiftList(String nameList, String limitDate, String occasion, EnumStatusList statusList, boolean isActive,
			User owner, Gift gift) {
		super();
		this.nameList = nameList;
		this.limitDate = limitDate;
		this.occasion = occasion;
		this.statusList = statusList;
		this.isActive = isActive;
		this.owner = owner;
		this.lstGift.add(gift);
		this.lstParticipant = new ArrayList<>();
	}

	// Getters et Setters
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
	
	// Méthodes
	
	public static ArrayList<GiftList> getAll(){
		return giftlistDAO.findAll();
	}
    


}