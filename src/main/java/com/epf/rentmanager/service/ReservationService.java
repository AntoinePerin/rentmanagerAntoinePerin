package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

@Service
public class ReservationService {
	private ReservationDao reservationDao;
	public static ReservationService instance;

	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public void create(Reservation reservation) throws ServiceException {

		if (reservation.dateDebutestApresDateFin()) {
			throw new ServiceException("Date debut est après date fin");
//		}else if (vehiculeAPlusieursReservMemeJour(reservation)) {
//			throw new ServiceException("Voiture ne pas être reservé 2 fois le même jour");
		} else if (estReserveSansPause(reservation)) {
			throw new ServiceException("Voiture ne pas être reservé 30 jours de suite sans pause");
		} else if (reservation.reservationTropLongue()) {
			throw new ServiceException("Voiture ne pas être reservé 7 jours de suite par un même client");
		} else {
			try {
				this.reservationDao.create(reservation);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Reservation> findAll() {
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int count() {
		return this.reservationDao.count();
	}

	public void update(Reservation reservation) throws ServiceException {

		this.reservationDao.update(reservation);

	}

	public Reservation findById(int id) {
		try {
			return this.reservationDao.findById(id).get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void delete(int id) {
		try {
			this.reservationDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Reservation> findResaByClientId(int clientId) {
		try {
			return this.reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findResaByVehicleId(int vehicleId) {
		try {
			return this.reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

// NE MARCHE PAS

//	public boolean vehiculeAPlusieursReservMemeJour(Reservation reservation) throws ServiceException{
//		boolean reservMemeJour = false;	
//		List<Reservation> listReservVehicule = new ArrayList<>();
//		listReservVehicule = findResaByVehicleId(reservation.getIdVehicule());
//			
//		System.out.println(listReservVehicule);
//		
//		for (int i = 0; i < listReservVehicule.size(); i++) {
//			if (reservation.getDateStart().isBefore(listReservVehicule.get(i).getDateEnd()) && listReservVehicule.get(i).getDateStart().isAfter(reservation.getDateEnd()) ) {
//				reservMemeJour = true;
//			}
//		}
//		return reservMemeJour;
//	}

	public boolean estReserveSansPause(Reservation reservation) {

		int nbrJourMax = 30;
		boolean reserveSansPause = false;

		List<Reservation> listReservVehicule = new ArrayList<Reservation>();
		listReservVehicule = findResaByVehicleId(reservation.getIdVehicule());

		listReservVehicule.add(reservation);

		Collections.sort(listReservVehicule);
		Collections.reverse(listReservVehicule);

		try {
			int nbrDeJourDesuite = (int) ChronoUnit.DAYS.between(listReservVehicule.get(0).getDateStart(),
					listReservVehicule.get(0).getDateEnd());

			for (int i = 0; i < listReservVehicule.size(); i++) {

				if (ChronoUnit.DAYS.between(listReservVehicule.get(i).getDateEnd(),
						listReservVehicule.get(i + 1).getDateStart()) < 1) {
					nbrDeJourDesuite += (int) ChronoUnit.DAYS.between(listReservVehicule.get(i + 1).getDateStart(),
							listReservVehicule.get(i + 1).getDateEnd());
					if (nbrDeJourDesuite > nbrJourMax) {
						reserveSansPause = true;
						break;
					}
				} else {
					nbrDeJourDesuite = (int) ChronoUnit.DAYS.between(listReservVehicule.get(i).getDateStart(),
							listReservVehicule.get(i).getDateEnd());
				}
			}

		} catch (IndexOutOfBoundsException e) {
			reserveSansPause = false;
		}

		return reserveSansPause;
	}

	public void deleteReservationClient(Client client) {
		List<Reservation> listReservClient = new ArrayList<>();
		listReservClient = findResaByClientId(client.getId());

		for (int i = 0; i < listReservClient.size(); i++) {
			delete(listReservClient.get(i).getId());
		}

	}

	public void deleteReservationVehicle(Vehicle vehicle) {
		List<Reservation> listReservVehicle = new ArrayList<>();
		listReservVehicle = findResaByVehicleId(vehicle.getId());

		for (int i = 0; i < listReservVehicle.size(); i++) {
			delete(listReservVehicle.get(i).getId());
		}

	}

}
