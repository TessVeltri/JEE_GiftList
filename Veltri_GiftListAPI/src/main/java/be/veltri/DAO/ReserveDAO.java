package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

public class ReserveDAO implements DAO<Reserve> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(Reserve obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Reserve obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reserve obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reserve find(Reserve obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reserve> findAll() {
		ArrayList<Reserve> lstReserve = new ArrayList<>();
		User user = new User();
		Gift gift = new Gift();
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Reserve");
			while (result.next()) {
				user.setIdUser(result.getInt("idUser"));
				user = user.findById();
				gift.setIdGift(result.getInt("idGift"));
				gift = gift.findById();
				Reserve reserve = new Reserve(result.getInt("amount"), user, gift);
				lstReserve.add(reserve);
			}
			result.close();
			return lstReserve;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reserve findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findId(Reserve obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Reserve> findAllByUser(User user) {
		ArrayList<Reserve> lstReserve = new ArrayList<>();
		
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(""
							+ "SELECT JEE_Gift.idGift, JEE_Gift.nameGift, JEE_GiftList.idGiftList, JEE_GiftList.nameList, JEE_Reserve.amount " 
							+ "FROM JEE_Reserve "
							+ "INNER JOIN JEE_Gift ON JEE_Gift.idGift = JEE_Reserve.idGift "
							+ "INNER JOIN JEE_User ON JEE_User.idUser = JEE_Reserve.idUser "
							+ "INNER JOIN JEE_GiftList ON JEE_GiftList.idGiftList = JEE_Gift.idGiftList "
							+ "WHERE JEE_User.idUser = '" + user.getIdUser() + "'");
			while (result.next()) {
				Gift gift = new Gift();
				GiftList gl = new GiftList();
				
				gift.setIdGift(result.getInt("idGift"));
				gift.setName(result.getString("nameGift"));
				gl.setIdGiftList(result.getInt("idGiftList"));
				gl.setNameList(result.getString("nameList"));
				
				gift.setGiftList(gl);
				Reserve reserve = new Reserve(result.getInt("amount"), user, gift);
				lstReserve.add(reserve);
			}
			result.close();
			return lstReserve;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
