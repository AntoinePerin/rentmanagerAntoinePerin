package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation implements Comparable<Reservation> {

	private int id;
	private int idVehicule;
	private int idClient;
	private LocalDate dateStart;
	private LocalDate dateEnd;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(int id, int idVehicule, int idClient, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.idVehicule = idVehicule;
		this.idClient = idClient;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public Reservation(int idVehicule, int idClient, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.idVehicule = idVehicule;
		this.idClient = idClient;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public int getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Reservation reservation) {
		// TODO Auto-generated method stub
		return (int) ChronoUnit.DAYS.between(this.dateStart, reservation.getDateStart());
	}
	
	@Override
	public String toString() {
		return "id"+ this.id + " ,date debut : "+ this.dateStart +" ,date fin : " + this.dateEnd;
		
	}
	
	public boolean reservationTropLongue() {
		boolean tropLong = false;
		int nbrJour= (int) ChronoUnit.DAYS.between(this.getDateStart(), this.getDateEnd());
		if(nbrJour>7) {
			tropLong=true;
		}
		return tropLong;
	}
	
	public boolean dateDebutestApresDateFin() {
		boolean estApresDateFin = false;
		if (this.dateStart.isAfter(dateEnd)) {
			estApresDateFin = true;
		}
		return estApresDateFin;
	}

}
