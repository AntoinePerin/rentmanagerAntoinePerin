package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void create(Client client) throws ServiceException {

		if (!client.legalAge()) {
			throw new ServiceException("Client doit avoir plus de 18 ans");
		} else if (!client.nomAssezLong()) {
			throw new ServiceException("Nom client doit faire au moins 3 caractères");
		} else if (!client.prenomAssezLong()) {
			throw new ServiceException("Prénom client doit faire au moins 3 caractères");
		} else if (aMemeAdresseMail(client)) {
			throw new ServiceException("Adresse mail déjà prise");
		} else {
			try {
				String nomEnMajuscule = client.getName().toUpperCase();
				client.setName(nomEnMajuscule);
				this.clientDao.create(client);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Client findById(int id) throws ServiceException {

		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<Client> findAll() throws ServiceException {

		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public long delete(int id) throws ServiceException {

		try {
			return this.clientDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public int count() {
		return this.clientDao.count();
	}

	public void update(Client client) throws ServiceException {
		try {
			String nomEnMajuscule = client.getName().toUpperCase();
			client.setName(nomEnMajuscule);
			this.clientDao.update(client);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean aMemeAdresseMail(Client client) {
		List<Client> listClients = new ArrayList<>();
		try {
			listClients = this.clientDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean memeAdresseMail = false;

		for (int i = 0; i < listClients.size(); i++) {
			if (listClients.get(i).getEmail().equals(client.getEmail())) {
				memeAdresseMail = true;
				break;
			}
		}
		return memeAdresseMail;
	}

}
