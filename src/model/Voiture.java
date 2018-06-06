package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the voiture database table.
 * 
 */
@Entity
@NamedQuery(name="Voiture.findAll", query="SELECT v FROM Voiture v")
public class Voiture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identifiant;

	private int annee;

	private int boite_vitesse;

	private String nom;

	private float prix;

	//bi-directional many-to-many association to Reservation
	@ManyToMany(mappedBy="voitures")
	private List<Reservation> reservations;

	//bi-directional many-to-one association to Agence
	@ManyToOne
	@JoinColumn(name="FK_agence")
	private Agence agence;

	//bi-directional many-to-one association to Categorie
	@ManyToOne
	@JoinColumn(name="FK_categorie")
	private Categorie categorie;

	//bi-directional many-to-one association to Carburant
	@ManyToOne
	@JoinColumn(name="FK_carburant")
	private Carburant carburant;

	//bi-directional many-to-many association to OptionVoiture
	@ManyToMany
	@JoinTable(
		name="option_comprise_voiture"
		, joinColumns={
			@JoinColumn(name="FK_voiture")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_option_voiture")
			}
		)
	private List<OptionVoiture> optionVoitures;

	public Voiture() {
	}

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public int getAnnee() {
		return this.annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getBoite_vitesse() {
		return this.boite_vitesse;
	}

	public void setBoite_vitesse(int boite_vitesse) {
		this.boite_vitesse = boite_vitesse;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Agence getAgence() {
		return this.agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Carburant getCarburant() {
		return this.carburant;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}

	public List<OptionVoiture> getOptionVoitures() {
		return this.optionVoitures;
	}

	public void setOptionVoitures(List<OptionVoiture> optionVoitures) {
		this.optionVoitures = optionVoitures;
	}

}