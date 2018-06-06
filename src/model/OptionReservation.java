package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the option_reservation database table.
 * 
 */
@Entity
@Table(name="option_reservation")
@NamedQuery(name="OptionReservation.findAll", query="SELECT o FROM OptionReservation o")
public class OptionReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identifiant;

	private byte assurance;

	private String nom;

	private float prix;

	//bi-directional many-to-many association to Reservation
	@ManyToMany(mappedBy="optionReservations")
	private List<Reservation> reservations;

	public OptionReservation() {
	}

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public byte getAssurance() {
		return this.assurance;
	}

	public void setAssurance(byte assurance) {
		this.assurance = assurance;
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

}