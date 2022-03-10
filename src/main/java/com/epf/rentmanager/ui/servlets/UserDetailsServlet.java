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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/users/detailsUser")
public class UserDetailsServlet extends HttpServlet {

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
		
		int clientId = Integer.valueOf(request.getQueryString().substring(3));
		try {
			request.setAttribute("prenomUser", clientService.findById(clientId).getLastname());
			request.setAttribute("nomUser", clientService.findById(clientId).getName());
			request.setAttribute("emailUser", clientService.findById(clientId).getEmail());
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("listResaTaille", this.reservationService.findResaByClientId(clientId).size());
		request.setAttribute("listResa", this.reservationService.findResaByClientId(clientId));
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
