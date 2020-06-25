package it.polito.tdp.denvercrimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.denvercrimes.model.Event;


public class EventsDAO {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			List<Event> list = new ArrayList<>() ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> getYears(){
		String sql = "SELECT DISTINCT(YEAR(reported_date)) AS year FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			List<Integer> list = new ArrayList<>() ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					int a= res.getInt("year");
					list.add(a);
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Integer> getVertex(){
		String sql = "SELECT DISTINCT(district_id) AS id FROM EVENTS ORDER BY id asc" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			List<Integer> list = new ArrayList<>() ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					int a= res.getInt("id");
					list.add(a);
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}
	
	public double getArcoLat(int anno, int distretto) {
		
		String sql = "SELECT district_id,AVG(geo_lat) AS lat FROM EVENTS WHERE YEAR(reported_date) =? AND district_id=?" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			st.setInt(2, distretto);
			ResultSet res = st.executeQuery() ;
			res.next();
			double mediaLat = res.getDouble("lat");
		
			
			
			conn.close();
			return mediaLat;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return - 1;
		}
	}
	
	public double getArcoLong(int anno, int distretto) {
		
		String sql = "SELECT district_id,AVG(geo_lon) AS lat FROM EVENTS WHERE YEAR(reported_date) =? AND district_id=?" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			st.setInt(2, distretto);
			ResultSet res = st.executeQuery() ;
			res.next();
			double mediaLong = res.getDouble("lat");
		
			
			
			conn.close();
			return mediaLong;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return - 1;
		}
	}
	
	public List<Integer> getMonths(int anno){
		String sql = "SELECT DISTINCT(Month(reported_date)) AS month FROM EVENTS WHERE YEAR(reported_date) =? ORDER BY month asc" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			List<Integer> list = new ArrayList<>() ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					int a= res.getInt("month");
					list.add(a);
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Integer> getDay(int anno,int mese){
		String sql = "SELECT DISTINCT(DAY(reported_date)) AS day FROM EVENTS WHERE YEAR(reported_date) =? ORDER BY DAY asc" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			List<Integer> list = new ArrayList<>() ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					int a= res.getInt("day");
					list.add(a);
				} catch (Throwable t) {
					t.printStackTrace();
					
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Event> getEventByDay(int anno, int mese, int giorni){
		String sql = "SELECT * FROM EVENTS WHERE YEAR(reported_date) =? and MONTH(reported_date)=? AND DAY(reported_date)=? ORDER BY reported_date asc" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			st.setInt(2 ,mese);
			st.setInt(3, giorni);
			List<Event> list = new ArrayList<>() ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public Integer getDistrettoStazione(int anno) {
		String sql = "SELECT district_id AS id,COUNT(*) FROM EVENTS WHERE YEAR(reported_date) =? GROUP BY district_id ORDER BY COUNT(*) desc" ;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			ResultSet res = st.executeQuery() ;
			res.next();
			int distretto = res.getInt("id");
		
			
			
			conn.close();
			return distretto;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return - 1;
		}
	}

	
	
}	
