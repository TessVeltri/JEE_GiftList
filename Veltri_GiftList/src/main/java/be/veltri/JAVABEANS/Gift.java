package be.veltri.JAVABEANS;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.GiftDAO;
import be.veltri.DAO.GiftListDAO;
import be.veltri.ENUMS.EnumPriority;
import be.veltri.ENUMS.EnumStatusGift;

public class Gift implements Serializable{

	// Attributs
    private static final long serialVersionUID = -8053277678643929851L;
    private String name;
    private String description;
    private int averagePrice;
    private EnumPriority priority;
    private String websiteLink;
    private EnumStatusGift statusGift;
    private byte [ ] image;
    private String nameImage;
    private String extensionImage;
    private ArrayList<Reserve> lstReserve;
    private GiftList giftList;
    
    private static GiftDAO giftDAO = new GiftDAO();
    
    // Constructeurs 
    public Gift() {}

	public Gift(String name, String description, int averagePrice, EnumPriority priority, String websiteLink,
			EnumStatusGift statusGift, byte[] image, String nameImage, String extensionImage, GiftList giftList) {
		super();
		this.name = name;
		this.description = description;
		this.averagePrice = averagePrice;
		this.priority = priority;
		this.websiteLink = websiteLink;
		this.statusGift = statusGift;
		this.image = image;
		this.nameImage = nameImage;
		this.extensionImage = extensionImage;
		this.giftList = giftList;
		this.lstReserve = new ArrayList<>();
	}
	
	public Gift(String name, String description, int averagePrice, EnumPriority priority, String websiteLink,
			EnumStatusGift statusGift, byte[] image, String nameImage, String extensionImage, GiftList giftList, 
			ArrayList<Reserve> lstReserve) {
		super();
		this.name = name;
		this.description = description;
		this.averagePrice = averagePrice;
		this.priority = priority;
		this.websiteLink = websiteLink;
		this.statusGift = statusGift;
		this.image = image;
		this.nameImage = nameImage;
		this.extensionImage = extensionImage;
		this.giftList = giftList;
		this.lstReserve = lstReserve;
	}

	// Getters et Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(int averagePrice) {
		this.averagePrice = averagePrice;
	}

	public EnumPriority getPriority() {
		return priority;
	}

	public void setPriority(EnumPriority priority) {
		this.priority = priority;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public EnumStatusGift getStatusGift() {
		return statusGift;
	}

	public void setStatusGift(EnumStatusGift statusGift) {
		this.statusGift = statusGift;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public String getExtensionImage() {
		return extensionImage;
	}

	public void setExtensionImage(String extensionImage) {
		this.extensionImage = extensionImage;
	}

	public GiftList getGiftList() {
		return giftList;
	}

	public void setGiftList(GiftList giftList) {
		this.giftList = giftList;
	}

	public ArrayList<Reserve> getLstReserve() {
		return lstReserve;
	}

	public void setLstReserve(ArrayList<Reserve> lstReserve) {
		this.lstReserve = lstReserve;
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
	
	public boolean create () {
		return giftDAO.create(this);
	}
    
}