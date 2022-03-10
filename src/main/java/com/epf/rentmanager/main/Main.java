package com.epf.rentmanager.main;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.config.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

public class Main {
	public static void main(String arg[]) {	
//		try {
//			System.out.println(ClientService.getInstance().findById(1));
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		LocalDate dateBirthTest = LocalDate.of(2000,10,10);
//		Client clientTest = new Client("test3","test","test",dateBirthTest);	
//		
//		try {
//			ClientService.getInstance().create(clientTest);
//		} catch (ServiceException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try {
//			System.out.println(ClientService.getInstance().findAll());
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			ClientService.getInstance().delete(7);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			System.out.println(ClientService.getInstance().findAll());
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			System.out.println(VehicleService.getInstance().findAll());
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//		VehicleService vehicleService = context.getBean(VehicleService.class);
//		ReservationService reservationService = context.getBean(ReservationService.class);
//		
//		reservationService.estReserveSansPause();
		
		
//		try {
//			System.out.println(vehicleService.findById(3));
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		
//		try {
//			System.out.println(vehicleService.findAll());
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Vehicle vehiculeTest = new Vehicle("Peugeot","207", 8);
//		System.out.println(vehiculeTest);
//		try {
//			vehicleService.create(vehiculeTest);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			System.out.println(vehicleService.findAll());
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		
//		System.out.println(VehicleService.getInstance().count());
//		System.out.println(ClientService.getInstance().count());
		
	}
}
