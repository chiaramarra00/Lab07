package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutage> getPowerOutagesByNerc(Nerc nerc) {
		
		String sql = "select * "
				+ "from PowerOutages "
				+ "where nerc_id=?";
		List<PowerOutage> powerOutagesList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage po = new PowerOutage(res.getInt("id"),res.getInt("event_type_id"),res.getInt("tag_id"),res.getInt("area_id"),nerc,res.getInt("responsible_id"),res.getInt("customers_affected"),res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime(),res.getInt("demand_loss"));
				powerOutagesList.add(po);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutagesList;
	}
	

}
