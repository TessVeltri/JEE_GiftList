package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import be.veltri.ENUMS.EnumStatusList;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.User;

public class GiftListDAO implements DAO<GiftList> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(GiftList obj) {
		try {
			Date myDate = new Date();
			String statusActive = "N";
			if (obj.getLimitDate() != null) {
				String tmp = obj.getLimitDate();
				// tmp.replace('-', '/');
				try {
					myDate = (new SimpleDateFormat("yyyy-MM-dd")).parse(tmp);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (obj.getStatusList().toString() == "Active")
					statusActive = "Y";

			}

			this.conn.createStatement().executeUpdate(
					"INSERT INTO JEE_GiftList (nameList, dateLimit, isActive, occasion, statusList, idOwner) VALUES ('"
							+ obj.getNameList() + "', '" + (new SimpleDateFormat("dd/MM/yyyy").format(myDate)) + "', '"
							+ statusActive.toString() + "','" + obj.getOccasion() + "', '"
							+ obj.getStatusList().toString() + "', '" + obj.getOwner().findId() + "')");
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
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
		GiftList gl = null;
		User owner = new User();
		String date = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_GiftList WHERE nameList = '" + obj.getNameList()
							+ "' AND occasion = '" + obj.getOccasion() + "' AND statusList = '"
							+ obj.getStatusList().toString() + "' AND idOwner = '" + obj.getOwner().findId() + "'");
			if (result.first()) {
				owner.setIdUser(result.getInt("idOwner"));
				owner = owner.findById();
				if (result.getDate("dateLimit") != null)
					date = result.getDate("dateLimit").toLocalDate().toString();
				gl = new GiftList(result.getString("nameList"), date, result.getString("occasion"),
						EnumStatusList.valueOf(result.getString("statusList")), result.getBoolean("isActive"), owner);
				try {
					ResultSet result2 = this.conn
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery("SELECT idUser FROM JEE_ParticipantList WHERE idGiftList = '"
									+ result.getInt("idGiftList") + "'");
					while (result2.next()) {
						User user = new User();
						user.setIdUser(result2.getInt("idUser"));
						user = user.findById();
						gl.addLstParticipant(user);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				try {
					ResultSet result3 = this.conn
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery("SELECT idGift FROM JEE_Gift WHERE idGiftList = '"
									+ result.getInt("idGiftList") + "'");
					while (result3.next()) {
						Gift gift = new Gift();
						gift.setIdGift(result3.getInt("idGift"));
						gift = gift.findById();
						gift.setGiftList(gl);
						gl.addLstGift(gift);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
			return gl;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<GiftList> findAll() {
		ArrayList<GiftList> lstGiftList = new ArrayList<>();
		User owner = new User();
		String date = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_GiftList");
			while (result.next()) {
				owner.setIdUser(result.getInt("idOwner"));
				owner = owner.findById();
				if (result.getDate("dateLimit") != null) {
					date = result.getDate("dateLimit").toLocalDate().toString();
					GiftList lst = new GiftList(result.getString("nameList"), date, result.getString("occasion"),
							EnumStatusList.valueOf(result.getString("statusList")), result.getBoolean("isActive"),
							owner);
					lst.setIdGiftList(result.getInt("idGiftList"));
					lstGiftList.add(lst);
				}
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
		GiftList gl = null;
		User owner = new User();
		String date = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_GiftList WHERE idGiftList = '" + id + "'");
			if (result.first()) {
				owner.setIdUser(result.getInt("idOwner"));
				owner = owner.findById();
				if (result.getDate("dateLimit") != null)
					date = result.getDate("dateLimit").toLocalDate().toString();
				gl = new GiftList(result.getString("nameList"), date, result.getString("occasion"),
						EnumStatusList.valueOf(result.getString("statusList")), result.getBoolean("isActive"), owner);
				gl.setIdGiftList(result.getInt("idGiftList"));
				try {
					ResultSet result2 = this.conn
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery("SELECT idUser FROM JEE_ParticipantList WHERE idGiftList = '"
									+ result.getInt("idGiftList") + "'");
					while (result2.next()) {
						User user = new User();
						user.setIdUser(result2.getInt("idUser"));
						user = user.findById();
						gl.addLstParticipant(user);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				try {
					ResultSet result3 = this.conn
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
							.executeQuery("SELECT idGift FROM JEE_Gift WHERE idGiftList = '"
									+ result.getInt("idGiftList") + "'");
					while (result3.next()) {
						Gift gift = new Gift();
						gift.setIdGift(result3.getInt("idGift"));
						gift = gift.findById();
						gift.setGiftList(gl);
						gl.addLstGift(gift);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
			return gl;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int findId(GiftList obj) {
		int id = 0;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idGiftList FROM JEE_GiftList WHERE nameList = '" + obj.getNameList()
							+ "' AND occasion = '" + obj.getOccasion() + "' AND statusList = '"
							+ obj.getStatusList().toString() + "' AND idOwner = '" + obj.getOwner().findId() + "'");
			if (result.first())
				id = result.getInt("idGiftList");
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
