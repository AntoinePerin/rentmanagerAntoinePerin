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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {

	private static ClientDao instance = null;

	
	private ClientDao() {
	}

//	public static ClientDao getInstance() {
//		if (instance == null) {
//			instance = new ClientDao();
//		}
//		return instance;
//	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?";


	public void create(Client client) throws DaoException {

		try {
			Connection conn;
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);

			pstmt.setString(1, client.getName());
			pstmt.setString(2, client.getLastname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthDate()));
			pstmt.execute();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public long delete(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);
			pstmt.setInt(1, id);
			pstmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	public Optional<Client> findById(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			// System.out.println(rs);

			rs.next();

			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthDate = rs.getDate("naissance").toLocalDate();

			Client client = new Client(id, clientLastname, clientFirstname, clientEmail, clientBirthDate);

			conn.close();

			return Optional.of(client);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.empty();

	}

	public List<Client> findAll() throws DaoException {
		List<Client> listClients = new ArrayList<Client>();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int clientId = rs.getInt("id");
				String clientLastname = rs.getString("nom");
				String clientFirstname = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthDate = rs.getDate("naissance").toLocalDate();
				listClients.add(new Client(clientId, clientLastname, clientFirstname, clientEmail, clientBirthDate));

			}

			return listClients;

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
			PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENTS_QUERY);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int nbClients = rs.getInt("count");
			conn.close();
			return nbClients;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}
	
	public void update(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT_QUERY);
			ps.setString(1, client.getName());
			ps.setString(2, client.getLastname());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getBirthDate()));
			ps.setInt(5, client.getId());
			ps.execute();
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
