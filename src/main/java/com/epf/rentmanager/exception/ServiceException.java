package com.epf.rentmanager.exception;

public class ServiceException extends Exception {

	public ServiceException() {
		super("Erreur dans le service");
	}
	
	public ServiceException(String msgErreur) {
		super(msgErreur);
	}
}
