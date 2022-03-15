package modele;

import java.util.ArrayList;
import java.util.List;

public class Medecin {

	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private String specialiteComplementaire;
	private int departement;
	
	
	//constructeur
	public Medecin(int unId, String unNom, String unPrenom, String uneAdresse, String unTel, String uneSpe, int unDept) {
		this.id=unId;
		this.nom=unNom;
		this.prenom=unPrenom;
		this.adresse=uneAdresse;
		this.tel=unTel;
		this.specialiteComplementaire=uneSpe;
		this.departement=unDept;
	}


	public int getId() {
		return id;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getAdresse() {
		return adresse;
	}


	public String getTel() {
		return tel;
	}


	public String getSpecialite() {
		return specialiteComplementaire;
	}


	public int getDept() {
		return departement;
	}
	
	public String getDeptStr() {
		return String.valueOf(getDept());
	}
	
	//public int compareTo()
	
}
