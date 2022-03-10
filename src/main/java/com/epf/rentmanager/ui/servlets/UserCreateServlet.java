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


@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");

		String dateNaissanceStr = request.getParameter("naissance");
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);

		Client clientAjoute = new Client(nom, prenom, email, dateNaissance);

		try {
			this.clientService.create(clientAjoute);

		} catch (ServiceException e) {

			e.printStackTrace();
		}
		
		response.sendRedirect("http://localhost:8080/rentmanager/users");

	}

}