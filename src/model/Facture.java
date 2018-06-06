package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the facture database table.
 * 
 */
@Entity
@NamedQuery(name="Facture.findAll", query="SELECT f FROM Facture f")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identifiant;

	@Temporal(TemporalType.DATE)
	private Date date_de_facturation;

	//bi-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(name="FK_personne")
	private Personne personne;

	//bi-directional one-to-one association to Reservation
	@OneToOne
	@JoinColumn(name="FK_reservation")
	private Reservation reservation;

	public Facture() {
	}

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public Date getDate_de_facturation() {
		return this.date_de_facturation;
	}

	public void setDate_de_facturation(Date date_de_facturation) {
		this.date_de_facturation = date_de_facturation;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}