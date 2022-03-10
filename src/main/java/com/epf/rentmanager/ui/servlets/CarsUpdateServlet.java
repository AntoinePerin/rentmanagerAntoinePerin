package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/updateCar")
public class CarsUpdateServlet extends HttpServlet {
	
	private int id;
	
	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		id = Integer.valueOf(request.getQueryString().substring(3));
		
		Vehicle vehicle = new Vehicle();
		
		try {
			vehicle= this.vehicleService.findById(id);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("constructor", vehicle.getConstructor());
		request.setAttribute("modele", vehicle.getModele());
		request.setAttribute("nbPlaces", vehicle.getNbPlaces());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fabricant = request.getParameter("manufacturer");
		String nbPlacesString = request.getParameter("seats");
		String modele = request.getParameter("modele");
		int nbPlaces = Integer.valueOf(nbPlacesString);
		Vehicle vehiculeUpdate = new Vehicle(id, fabricant, modele, nbPlaces);

		try {
			this.vehicleService.update(vehiculeUpdate);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8080/rentmanager/cars");

	}
}
