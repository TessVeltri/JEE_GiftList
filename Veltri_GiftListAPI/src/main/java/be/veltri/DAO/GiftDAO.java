package be.veltri.DAO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.veltri.ENUMS.EnumPriority;
import be.veltri.ENUMS.EnumStatusGift;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

public class GiftDAO implements DAO<Gift> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(Gift obj) {
		try {

			String sql = "INSERT INTO JEE_Gift (idGiftList, nameGift, description, averagePrice, priority, statusGift, websiteLink, image, nameImage, extensionImage) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setLong(1, obj.getGiftList().findId());
			ps.setString(2, obj.getName());
			ps.setString(3, obj.getDescription());
			ps.setLong(4, obj.getAveragePrice());
			ps.setString(5, obj.getPriority().toString());
			ps.setString(6, obj.getStatusGift().toString());
			ps.setString(7, obj.getWebsiteLink());

			InputStream targetStream = new ByteArrayInputStream(obj.getImage());
			ps.setBinaryStream(8, targetStream);

			ps.setString(9, obj.getNameImage());
			ps.setString(10, obj.getExtensionImage());

			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Gift obj) {
		try {
			this.conn.createStatement().executeUpdate("DELETE FROM JEE_Gift WHERE idGift = '" + obj.getIdGift() + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Gift obj) {
		try {
			String sql = "UPDATE JEE_Gift SET image = ?, description = ?, "
					+ "averagePrice = ?, nameGift = ?, statusGift = ?, extensionImage = ?, priority = ?, websiteLink = ?, "
					+ "nameImage = ? WHERE idGift = '" + obj.getIdGift() + "'";

			PreparedStatement ps = this.conn.prepareStatement(sql);

			InputStream targetStream = new ByteArrayInputStream(obj.getImage());
			ps.setBinaryStream(1, targetStream);
			ps.setString(2, obj.getDescription());
			ps.setLong(3, obj.getAveragePrice());
			ps.setString(4, obj.getName());
			ps.setString(5, obj.getStatusGift().toString());
			ps.setString(6, obj.getExtensionImage());
			ps.setString(7, obj.getPriority().toString());
			ps.setString(8, obj.getWebsiteLink());
			ps.setString(9, obj.getNameImage());

			int result = ps.executeUpdate();

			if (result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Gift find(Gift obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gift findById(int id) {
		Gift gift = null;
		GiftList gl = new GiftList();
		User user = new User();
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Gift WHERE idGift = '" + id + "'");
			if (result.first())
				gift = new Gift(result.getString("nameGift"), result.getString("description"),
						result.getInt("averagePrice"), EnumPriority.valueOf(result.getString("priority")),
						result.getString("websiteLink"), EnumStatusGift.valueOf(result.getString("statusGift")),
						result.getBytes("image"), result.getString("nameImage"), result.getString("extensionImage"),
						gl);
			gift.setIdGift(result.getInt("idGift"));
			try {
				ResultSet result2 = this.conn
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT * FROM JEE_Reserve WHERE idGift = '" + result.getInt("idGift") + "'");
				while (result2.next()) {
					user.setIdUser(result2.getInt("idUser"));
					user = user.findById();
					Reserve res = new Reserve(result2.getInt("amount"), user);
					gift.addLstReserve(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return gift;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Gift> findAll() {
//		ArrayList<Gift> lstGift = new ArrayList<>();
//		try {
//			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
//					.executeQuery("SELECT * FROM Jee_Gift");
//			while (result.next()) {
//				Gift gift = new Gift();
//				lstGift.add(gift);
//			}
//			result.close();
//			return lstGift;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
		return null;
	}

	@Override
	public int findId(Gift obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
