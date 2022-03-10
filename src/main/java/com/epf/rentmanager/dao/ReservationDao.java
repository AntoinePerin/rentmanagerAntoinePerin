package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private static ReservationDao instance = null;
	
	private ReservationDao() {
		
	}
	
//	public static ReservationDao getInstance() {
//		if(instance == null) {
//			instance = new ReservationDao();
//		}
//		return instance;
//	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET vehicle_id=?, client_id=?, debut=?, fin=? WHERE id=?";

	
	public void create(Reservation reservation) throws DaoException {
		
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);

			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicule());
			pstmt.setDate(3, Date.valueOf(reservation.getDateStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getDateEnd()));
			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			pstmt.setInt(1, id);
			pstmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		
		
		List<Reservation> listReservClient = new ArrayList<Reservation>();
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstmt.setInt(1, clientId);	
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationVehicleId = rs.getInt("vehicle_id");
				
				LocalDate reservationDateDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationDateFin = rs.getDate("fin").toLocalDate();
				listReservClient.add(new Reservation(reservationId,reservationVehicleId,clientId,reservationDateDebut,reservationDateFin));

			}
			pstmt.close();
			conn.close();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		return listReservClient;
		
	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> listReservVehicule = new ArrayList<Reservation>();
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstmt.setInt(1, vehicleId);	
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationClientId = rs.getInt("client_id");
				
				LocalDate reservationDateDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationDateFin = rs.getDate("fin").toLocalDate();
				listReservVehicule.add(new Reservation(reservationId,vehicleId,reservationClientId,reservationDateDebut,reservationDateFin));

			}
			pstmt.close();
			conn.close();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listReservVehicule;
		 
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> listReservations = new ArrayList<Reservation>();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationClientId = rs.getInt("client_id");
				int reservationVehicleId = rs.getInt("vehicle_id");
				
				LocalDate reservationDateDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationDateFin = rs.getDate("fin").toLocalDate();
				listReservations.add(new Reservation(reservationId,reservationVehicleId,reservationClientId,reservationDateDebut,reservationDateFin));

			}
			pstmt.close();
			conn.close();
			return listReservations;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		 
	}
	
	public int count(){
		try {
			Connection conn;
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int nbReservations = rs.getInt("count");
			pstmt.close();
			conn.close();
			return nbReservations;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void update(Reservation reservation) {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESERVATION_QUERY);
			pstmt.setInt(2, reservation.getIdClient());
			pstmt.setInt(1, reservation.getIdVehicule());
			pstmt.setDate(3, Date.valueOf(reservation.getDateStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getDateEnd()));
			pstmt.setInt(5, reservation.getId());
			
			pstmt.execute();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Optional<Reservation> findById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATION_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int reservationId = rs.getInt("id");
			int reservationClientId = rs.getInt("client_id");
			int reservationVehicleId = rs.getInt("vehicle_id");
			
			LocalDate reservationDateDebut = rs.getDate("debut").toLocalDate();
			LocalDate reservationDateFin = rs.getDate("fin").toLocalDate();

			Reservation reservation = new Reservation(reservationId, reservationVehicleId, reservationClientId, reservationDateDebut,reservationDateFin);
			
			pstmt.close();
			conn.close();

			return Optional.of(reservation);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.empty();
	}
}
