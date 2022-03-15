package modele;

public class Compte {
	
	private String nom;
	private String prenom;
	private String tel;
	private String login;
	private String mdp;

	public Compte(String unLogin, String unMdp, String unNom, String unPrenom, String unTel) {
		this.nom=unNom;
		this.prenom=unPrenom;
		this.tel=unTel;
		this.login=unLogin;
		this.mdp=unMdp;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTel() {
		return tel;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}
