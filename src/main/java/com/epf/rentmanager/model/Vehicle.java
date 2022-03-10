package com.epf.rentmanager.model;

public class Vehicle {
	
	private int id;
	private String constructor;
	private String modele;
	private int nbPlaces;
	
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vehicle(String constructor, String modele, int nbPlaces) {
		super();
		this.modele = modele;
		this.constructor = constructor;
		this.nbPlaces = nbPlaces;
	}
	
	public Vehicle(int id, String constructor, String modele, int nbPlaces) {
		//super();
		this.id = id;
		this.modele=modele;
		this.constructor = constructor;
		this.nbPlaces = nbPlaces;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConstructor() {
		return constructor;
	}
	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}
	
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructor=" + constructor + ", modele=" + modele + ", Nombre de places=" + nbPlaces +"]";
	}
	
    public boolean aUnModele(){
        boolean aUnModele = false;
        if (this.getModele()!=null) {
        	aUnModele = true;
        }
        return aUnModele;
    }
    
    public boolean aUnConstructeur(){
        boolean aUnConstructeur = false;
        if (this.getConstructor()!=null) {
        	aUnConstructeur = true;
        }
        return aUnConstructeur;
    }
    
    public boolean aNombreDePlaceCorrect(){
        boolean aNombreDePlaceCorrect = false;
        int nb_places=this.getNbPlaces();
        if (nb_places>=2 && nb_places<=9 ) {
        	aNombreDePlaceCorrect = true;
        }
        return aNombreDePlaceCorrect;
    }
    
    
	
	
}
