package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.ENUMS.EnumPriority;
import be.veltri.ENUMS.EnumStatusGift;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.User;

public class GiftDAO implements DAO<Gift>{

	private Connection conn = DBConnection.getInstance();
	
	@Override
	public boolean create(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Gift find(Gift obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gift findById(int id) {
		Gift gift = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Gift WHERE idGift = '" + id + "'");
			if (result.first())
				gift = new Gift(result.getString("nameGift"), result.getString("description"), result.getInt("averagePrice"),
						EnumPriority.valueOf(result.getString("priority")), result.getString("websiteLink"), 
						EnumStatusGift.valueOf(result.getString("statusGift")), result.getBytes("image"), result.getString("nameImage"),
						result.getString("extensionImage"));
			return gift;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Gift> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
