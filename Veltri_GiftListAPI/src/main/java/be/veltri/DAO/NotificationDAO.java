package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.User;

public class NotificationDAO implements DAO<Notification> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(Notification obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Notification obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Notification obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Notification find(Notification obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Notification> findAll() {
		ArrayList<Notification> lstNotif = new ArrayList<>();
		User user = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Notification");
			while (result.next()) {
				user = User.findById(result.getInt("idUser"));
				Notification notif = new Notification(result.getString("commentNotif"), result.getBoolean("isRead"),
						user);
				lstNotif.add(notif);
			}
			result.close();
			return lstNotif;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Notification findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}