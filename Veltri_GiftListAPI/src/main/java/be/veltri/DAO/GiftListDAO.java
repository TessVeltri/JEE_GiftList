package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.User;

public class GiftListDAO implements DAO<GiftList> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(GiftList obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(GiftList obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(GiftList obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GiftList find(GiftList obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GiftList> findAll() {
		ArrayList<GiftList> lstGiftList = new ArrayList<>();
		User owner = null;
		String date = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_GiftList");
			while (result.next()) {
				owner = User.findById(result.getInt("idOwner"));
				if (result.getDate("dateLimit") != null)
					date = result.getDate("dateLimit").toLocalDate().toString();
				GiftList lst = new GiftList(result.getString("nameList"), date,
						result.getString("occasion"), EnumStatusList.valueOf(result.getString("statusList")),
						result.getBoolean("isActive"), owner);
				try {
					ResultSet result2 = this.conn
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery("SELECT * FROM JEE_ParticipantList WHERE idGiftList = '"
									+ result.getInt("idGiftList") + "'");
					while (result2.next()) {
						User user = User.findById(result2.getInt("idUser"));
						lst.getLstParticipant().add(user);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				try {
					ResultSet result3 = this.conn
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery("SELECT * FROM JEE_Gift WHERE idGiftList = '"
									+ result.getInt("idGiftList") + "'");
					while (result3.next()) {
						Gift gift = Gift.findById(result3.getInt("idGift"));
						lst.getLstGift().add(gift);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				lstGiftList.add(lst);
			}
			result.close();
			return lstGiftList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public GiftList findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}