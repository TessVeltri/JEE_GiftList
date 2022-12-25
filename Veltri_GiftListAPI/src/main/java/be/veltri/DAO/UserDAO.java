package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(User obj) {
		User user = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_User WHERE emailUser = '" + obj.getEmail() + "'");
			if (result.first())
				user = new User(result.getString("nameUser"), result.getString("firstnameUser"), obj.getEmail(),
						result.getString("passwordUser"));
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
			if (result.first())
				user = new User(result.getString("nameUser"), result.getString("firstnameUser"),
						result.getString("emailUser"), result.getString("passwordUser"));
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
			if (result.first())
				user = new User(result.getString("nameUser"), result.getString("firstnameUser"), obj.getEmail(),
						obj.getPassword());
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
					user.getMyLists().add(gl);

				if (gl.getLstParticipant().contains(user))
					user.getLstGiftList().add(gl);
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

}
