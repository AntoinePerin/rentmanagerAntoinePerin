package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/updateReservation")
public class ReservationUpdateServlet extends HttpServlet {
	
	private int id;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("listClient", clientService.findAll());
			request.setAttribute("listVehicle", vehicleService.findAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		id = Integer.valueOf(request.getQueryString().substring(3));
		Reservation reservation = new Reservation();
		reservation = this.reservationService.findById(id);
		
		try {
			request.setAttribute("idUser", reservation.getIdClient());
			request.setAttribute("nomUser", this.clientService.findById(reservation.getIdClient()).getName());
			request.setAttribute("prenomUser", this.clientService.findById(reservation.getIdClient()).getLastname());
			
			request.setAttribute("idVehicule", reservation.getIdVehicule());
			request.setAttribute("constructorVehicule", this.vehicleService.findById(reservation.getIdVehicule()).getConstructor());
			request.setAttribute("modeleVehicule", this.vehicleService.findById(reservation.getIdVehicule()).getModele());
			
			request.setAttribute("begin", reservation.getDateStart());
			request.setAttribute("end", reservation.getDateEnd());
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/update.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idVehiculeStr = request.getParameter("car");
		int idVehicule = Integer.valueOf(idVehiculeStr);
		
		String idClientStr = request.getParameter("client");
		int idClient = Integer.valueOf(idClientStr);

		String dateDebutStr = request.getParameter("begin");
        LocalDate dateDebut = LocalDate.parse(dateDebutStr);
        
        String dateFinStr = request.getParameter("end");
        LocalDate dateFin = LocalDate.parse(dateFinStr);
        
        Reservation reservationUpdate = new Reservation(id, idVehicule,idClient,dateDebut,dateFin);

        try {
			this.reservationService.update(reservationUpdate);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8080/rentmanager/rents");

	}
}
