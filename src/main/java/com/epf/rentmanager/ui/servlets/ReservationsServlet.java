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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;


@WebServlet("/rents")
public class ReservationsServlet extends HttpServlet {

	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("listReservation", this.reservationService.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		int id = Integer.valueOf(request.getParameter("id"));
//		System.out.println(id);
//
//		try {
//
//			this.vehicleService.delete(id);
//
//		} catch (ServiceException e) {
//
//			e.printStackTrace();
//		}
//		response.sendRedirect("http://localhost:8080/rentmanager/cars");
		doGet(request, response);
	}

}
