package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/detailCar")
public class CarsDetailsServlet extends HttpServlet {
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int vehicleId = Integer.valueOf(request.getQueryString().substring(3));
		try {
			request.setAttribute("nomConstructeur", vehicleService.findById(vehicleId).getConstructor());
			request.setAttribute("nomModele", vehicleService.findById(vehicleId).getModele());
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("listResaTaille", this.reservationService.findResaByVehicleId(vehicleId).size());
		request.setAttribute("listResa", this.reservationService.findResaByVehicleId(vehicleId));
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);

	}
	
}
