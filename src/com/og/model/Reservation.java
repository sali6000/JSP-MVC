package com.og.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reservation database table.
 * 
 */
@Entity
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identifiant;

	@Temporal(TemporalType.DATE)
	private Date date_de_reservation;

	@Temporal(TemporalType.DATE)
	private Date date_de_retour;

	private float prix;

	//bi-directional one-to-one association to Facture
	@OneToOne(mappedBy="reservation", cascade=CascadeType.PERSIST)
	private Facture facture;

	//bi-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(name="FK_personne")
	private Personne personne;

	//bi-directional many-to-many association to OptionReservation
	@ManyToMany
	@JoinTable(
		name="option_comprise_reservation"
		, joinColumns={
			@JoinColumn(name="FK_reservation")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_option_reservation")
			}
		)
	private List<OptionReservation> optionReservations;

	//bi-directional many-to-many association to Agence
	@ManyToMany
	@JoinTable(
		name="reservation_agence"
		, joinColumns={
			@JoinColumn(name="FK_reservation")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_agence")
			}
		)
	private List<Agence> agences;

	//bi-directional many-to-many association to Voiture
	@ManyToMany
	@JoinTable(
		name="reservation_voiture"
		, joinColumns={
			@JoinColumn(name="FK_reservation")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_voiture")
			}
		)
	private List<Voiture> voitures;

	public Reservation() {
	}

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public Date getDate_de_reservation() {
		return this.date_de_reservation;
	}

	public void setDate_de_reservation(Date date_de_reservation) {
		this.date_de_reservation = date_de_reservation;
	}

	public Date getDate_de_retour() {
		return this.date_de_retour;
	}

	public void setDate_de_retour(Date date_de_retour) {
		this.date_de_retour = date_de_retour;
	}

	public float getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public List<OptionReservation> getOptionReservations() {
		return this.optionReservations;
	}

	public void setOptionReservations(List<OptionReservation> optionReservations) {
		this.optionReservations = optionReservations;
	}

	public List<Agence> getAgences() {
		return this.agences;
	}

	public void setAgences(List<Agence> agences) {
		this.agences = agences;
	}

	public List<Voiture> getVoitures() {
		return this.voitures;
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
}