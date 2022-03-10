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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/users/deleteUser")
public class UserDeleteServlet extends HttpServlet {

	@Autowired
	ClientService clientService;

	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getQueryString().substring(3));
		try {
			this.reservationService.deleteReservationClient(this.clientService.findById(id));
			this.clientService.delete(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/rentmanager/users");
	}
}