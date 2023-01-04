package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.veltri.ENUMS.EnumPriority;
import be.veltri.ENUMS.EnumStatusGift;
import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

public class NotificationDAO implements DAO<Notification> {

	private Connection conn = DBConnection.getInstance();

	@Override
	public boolean create(Notification obj) {
		try {
			this.conn.createStatement()
					.executeUpdate("INSERT INTO JEE_Notification (commentNotif, isRead, idUser) VALUES ('"
							+ obj.getComment() + "', 'N', '" + obj.getUser().getIdUser() + "')");
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Notification obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Notification obj) {
		try {
			String read = "N";
			if (obj.isRead())
				read = "Y";

			this.conn.createStatement()
					.executeUpdate("UPDATE JEE_Notification SET commentNotif = '" + obj.getComment() + "', isRead = '"
							+ read + "', idUser = '" + obj.getUser().getIdUser() + "' WHERE idNotification = '"
							+ obj.getIdNotif() + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Notification find(Notification obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Notification> findAll() {
		ArrayList<Notification> lstNotif = new ArrayList<>();

		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Notification");
			while (result.next()) {
				User user = new User();
				user.setIdUser(result.getInt("idUser"));
				user = user.findById();
				Notification notif = new Notification(result.getString("commentNotif"), result.getBoolean("isRead"),
						user);
				notif.setIdNotif(result.getInt("idNotification"));
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
		Notification notif = null;
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Notification WHERE idNotification = '" + id + "'");
			if (result.first()) {
				User user = new User();
				user.setIdUser(result.getInt("idUser"));
				user = user.findById();
				notif = new Notification(result.getString("commentNotif"), result.getBoolean("isRead"), user);
				notif.setIdNotif(result.getInt("idNotification"));
			}
			return notif;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int findId(Notification obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
