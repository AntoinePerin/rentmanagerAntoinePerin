package com.epf.rentmanager.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public void create(Vehicle vehicle) throws ServiceException {

		if (!vehicle.aNombreDePlaceCorrect()) {
			throw new ServiceException("une voiture doit avoir son nombre de place compris entre 2 et 9");
		} else if (!vehicle.aUnModele()) {
			throw new ServiceException("une voiture doit avoir un modele");
		} else if (!vehicle.aUnConstructeur()) {
			throw new ServiceException("une voiture doit avoir un constructeur");
		} else {
			try {
				this.vehicleDao.create(vehicle);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

	}

	public Vehicle findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() {
		return this.vehicleDao.count();
	}

	public void delete(int id) throws ServiceException {
		try {
			this.vehicleDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Vehicle vehicle) throws ServiceException {
		try {
			this.vehicleDao.update(vehicle);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
