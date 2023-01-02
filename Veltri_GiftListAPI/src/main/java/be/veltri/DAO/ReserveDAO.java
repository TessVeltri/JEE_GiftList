package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.JAVABEANS.Gift;
import be.veltri.JAVABEANS.Notification;
import be.veltri.JAVABEANS.Reserve;
import be.veltri.JAVABEANS.User;

public class ReserveDAO implements DAO<Reserve>{

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
		try {
			ResultSet result = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM JEE_Reserve");
			while (result.next()) {
				user.setIdUser(result.getInt("idUser"));
				user = user.findById();
				Reserve reserve = new Reserve(result.getInt("amount"), user);
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

}
