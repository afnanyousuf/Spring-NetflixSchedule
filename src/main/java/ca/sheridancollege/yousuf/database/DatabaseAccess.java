package ca.sheridancollege.yousuf.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.yousuf.beans.ShowAppt;

@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	
	public void insertAppointment(ShowAppt appt) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		
		String query = "INSERT INTO appointment(showName, showTime, showInfo) VALUES (:showName, :showTime, :showInfo)";
		
		namedParameter.addValue("showName", appt.getShowName());
		namedParameter.addValue("showTime", appt.getShowTime());
		namedParameter.addValue("showInfo", appt.getShowInfo());
		
		int rowsAffected = jdbc.update(query, namedParameter);
		if(rowsAffected>0) {
			System.out.print("successfully ran sql insert");
		}
	}
	
	public List<ShowAppt> getShows(){
		
		String query = "Select * from appointment";
		List<ShowAppt> showList = jdbc.query(query, new BeanPropertyRowMapper<ShowAppt>(ShowAppt.class));
		
		return showList;
		
	}
	
	public void deleteAppointment(int id) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "DELETE FROM appointment WHERE id = :id";
		namedParameter.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameter);
		if(rowsAffected>0) {
			System.out.println("Deleted appt " + id + " from db");
		}
		
	}
	
	public List<ShowAppt> getApptById(int id) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM appointment WHERE id = :id";
		namedParameter.addValue("id", id);
		return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<ShowAppt>(ShowAppt.class));
		
	}
	
	
	
}
