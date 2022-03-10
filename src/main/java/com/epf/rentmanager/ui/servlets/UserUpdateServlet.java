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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;


@WebServlet("/users/updateUser")
public class UserUpdateServlet extends HttpServlet {
	
	private int id;
	
	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		id = Integer.valueOf(request.getQueryString().substring(3));
		
		Client client = new Client();
		
		try {
			client= this.clientService.findById(id);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("nom", client.getName());
		request.setAttribute("prenom", client.getLastname());
		request.setAttribute("email", client.getEmail());
		request.setAttribute("naissance", client.getBirthDate());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");
		String dateNaissanceStr = request.getParameter("naissance");
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);

		Client clientUpdate = new Client(id, nom, prenom, email, dateNaissance);
		
		try {
			this.clientService.update(clientUpdate);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("http://localhost:8080/rentmanager/users");

	}
}
