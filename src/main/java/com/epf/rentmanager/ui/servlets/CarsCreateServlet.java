package com.epf.rentmanager.ui.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cars/create")
public class CarsCreateServlet extends HttpServlet {

	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fabricant = request.getParameter("manufacturer");
		String nbPlacesString = request.getParameter("seats");
		String modele = request.getParameter("modele");
		int nbPlaces = Integer.valueOf(nbPlacesString);
		Vehicle vehiculeAjoute = new Vehicle(fabricant, modele, nbPlaces);

		try {

			this.vehicleService.create(vehiculeAjoute);

		} catch (ServiceException e) {
			request.setAttribute(modele, vehiculeAjoute);
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/rentmanager/cars");

	}

}
