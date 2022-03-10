package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {

	private static VehicleDao instance = null;

	private VehicleDao() {
	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLE_QUERY = "SELECT COUNT(id) AS count FROM Vehicle";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, modele=?, nb_places=? WHERE id=?";
	
	
	public void create(Vehicle vehicle) throws DaoException {
		try {
			Connection conn;
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);

			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModele());
			pstmt.setInt(3, vehicle.getNbPlaces());

			pstmt.execute();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return 0; it was a long

	}

	public void delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			pstmt.setInt(1, id);
			pstmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			String vehicleConstructor = rs.getString("constructeur");
			String vehicleModele = rs.getString("modele");
			int vehicleNbPlaces = rs.getInt("nb_places");

			Vehicle vehicle = new Vehicle(id, vehicleConstructor, vehicleModele, vehicleNbPlaces);

			conn.close();

			return Optional.of(vehicle);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> listVehicles = new ArrayList<Vehicle>();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int vehicleId = rs.getInt("id");
				String vehicleConstructeur = rs.getString("constructeur");
				String vehicleModele=rs.getString("modele");
				int vehicleNbPlaces = rs.getInt("nb_places");
				listVehicles.add(new Vehicle(vehicleId, vehicleConstructeur, vehicleModele, vehicleNbPlaces));

			}
			conn.close();
			return listVehicles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public int count() {

		try {
			Connection conn;
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLE_QUERY);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int nbCars = rs.getInt("count");
			conn.close();
			return nbCars;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}
	
	public void update(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE_QUERY);
			ps.setString(1, vehicle.getConstructor());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNbPlaces());
			ps.setInt(4, vehicle.getId());
			ps.execute();
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
