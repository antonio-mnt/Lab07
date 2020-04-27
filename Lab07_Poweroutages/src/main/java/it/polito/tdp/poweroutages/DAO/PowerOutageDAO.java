package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Nerc;

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
	
	public List<Blackout> getBlackout(Nerc nerc){
		
		String sql = "SELECT nerc_id, DATE_event_began, date_event_finished, customers_affected FROM poweroutages WHERE nerc_id = ? ORDER BY date_event_finished";
		List<Blackout> blackoutList = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, nerc.getId());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int nercId = rs.getInt("nerc_id");
				LocalDateTime dataInizio = rs.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime dataFine = rs.getTimestamp("date_event_finished").toLocalDateTime();
				int nPersone = rs.getInt("customers_affected");

				Blackout b = new Blackout(nercId, dataInizio, dataFine, nPersone);
				blackoutList.add(b);
			}
			
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blackoutList;
			
		
	}
	

}
