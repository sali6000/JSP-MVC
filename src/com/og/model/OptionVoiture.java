package com.og.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the option_voiture database table.
 * 
 */
@Entity
@Table(name="option_voiture")
@NamedQuery(name="OptionVoiture.findAll", query="SELECT o FROM OptionVoiture o")
public class OptionVoiture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int identifiant;

	private String nom;

	private float prix;

	//bi-directional many-to-many association to Voiture
	@ManyToMany(mappedBy="optionVoitures")
	private List<Voiture> voitures;

	public OptionVoiture() {
	}

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
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

	public List<Voiture> getVoitures() {
		return this.voitures;
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
	


}