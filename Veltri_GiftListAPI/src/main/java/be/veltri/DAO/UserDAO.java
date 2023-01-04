package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import be.veltri.JAVABEANS.GiftList;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

public class UserDAO implements DAO<User> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(User obj) {
		try {
			this.conn.createStatement()
					.executeUpdate("INSERT INTO JEE_User (nameUser, firstnameUser, emailUser, passwordUser) VALUES ('"
							+ obj.getName() + "', '" + obj.getFirstname() + "', '" + obj.getEmail() + "','"
							+ obj.getPassword() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		try {
			this.conn.createStatement()
					.executeUpdate("UPDATE JEE_User SET nameUser = '" + obj.getName() + "', firstnameUser = '"
							+ obj.getFirstname() + "', emailUser = '" + obj.getEmail() + "', passwordUser = '"
							+ obj.getPassword() + "' WHERE idUser = '" + obj.getIdUser() + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User find(User obj) {
		User user = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_User WHERE emailUser = '" + obj.getEmail() + "'");
			if (result.first()) {
				user = new User(result.getString("nameUser"), result.getString("firstnameUser"), obj.getEmail(),
						result.getString("passwordUser"));
				user.setIdUser(result.getInt("idUser"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<User> findAll() {
		ArrayList<User> lstUser = new ArrayList<>();
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_User");
			while (result.next()) {
				User user = new User(result.getString("nameUser"), result.getString("firstnameUser"),
						result.getString("emailUser"), result.getString("passwordUser"));
				user.setIdUser(result.getInt("idUser"));
				lstUser.add(user);
			}
			result.close();
			return lstUser;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findById(int id) {
		User user = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_User WHERE idUser = '" + id + "'");
			if (result.first()) {
				user = new User(result.getString("nameUser"), result.getString("firstnameUser"),
						result.getString("emailUser"), result.getString("passwordUser"));
				user.setIdUser(id);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User login(User obj) {
		User user = null;
//		ArrayList<Notification> allNotif = Notification.getAll();
//		ArrayList<Reserve> allReserve = Reserve.getAll();
		ArrayList<GiftList> allGiftList = GiftList.getAll();
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_User WHERE emailUser = '" + obj.getEmail()
							+ "' AND passwordUser = '" + obj.getPassword() + "'");
			if (result.first()) {
				user = new User(result.getString("nameUser"), result.getString("firstnameUser"), obj.getEmail(),
						obj.getPassword());
				user.setIdUser(result.getInt("idUser"));
			}

//			for (Notification n : allNotif) {
//				if (user.getEmail().equals(n.getUser().getEmail()))
//					user.getLstNotification().add(n);
//			}
//			for (Reserve r : allReserve) {
//				if (user.getEmail().equals(r.getUser().getEmail()))
//					user.getLstReserve().add(r);
//			}
			for (GiftList gl : allGiftList) {
				if (user.getEmail().equals(gl.getOwner().getEmail()))
					user.addMyList(gl);

				for (User u : gl.getLstParticipant()) {
					if (u.getEmail().equals(user.getEmail()))
						user.addLstGiftList(gl);
				}
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int findId(User obj) {
		int id = 0;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idUser FROM JEE_User WHERE emailUser = '" + obj.getEmail() + "'");
			if (result.first())
				id = result.getInt("idUser");
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean deleteParticipation(User user, GiftList gl) {
		int idUser = user.getIdUser();
		int idGiftList = gl.getIdGiftList();
		try {
			this.conn.createStatement().executeUpdate("DELETE FROM JEE_ParticipantList WHERE idUser = '" + idUser + "' "
					+ "AND idGiftList = '" + idGiftList + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean addParticipation(User user, GiftList gl) {
		int idUser = user.getIdUser();
		int idGiftList = gl.getIdGiftList();
		try {
			this.conn.createStatement().executeUpdate("INSERT INTO JEE_ParticipantList (idUser, idGiftList) "
					+ " VALUES ('" + idUser + "' , '" + idGiftList + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
