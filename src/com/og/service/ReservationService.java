package com.og.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.og.model.Agence;
import com.og.model.OptionReservation;
import com.og.model.Personne;
import com.og.model.Reservation;
import com.og.model.Voiture;


public class ReservationService {
	
	protected EntityManager em;
	
	public ReservationService(EntityManager em)
	{
		this.em = em;
	}
	
	// CREATE
	public Reservation createReservation(Date aller,Date retour,float prix, Personne personne,List<OptionReservation> optionsRes,List<Agence> agences,List<Voiture> voitures) throws Exception
	{
		Reservation res = new Reservation();
		res.setDate_de_reservation(aller);
		res.setDate_de_retour(retour);
		res.setPrix(prix);
		res.setPersonne(personne);
		res.setOptionReservations(optionsRes);
		res.setAgences(agences);
		res.setVoitures(voitures);
		this.em.getTransaction().begin();
		this.em.persist(res);
		this.em.flush();
		setAgenceDeRetour(res.getIdentifiant(),agences.get(1).getIdentifiant());
		this.em.getTransaction().commit();
		return res;
	}
	
	// READ BY ID
	public Reservation findReservation(int id)
	{
		return em.find(Reservation.class,id);
	}
	
	// READ (récupère toutes les reservations sous forme de List)
	public List<Reservation> findAllReservations()
	{
		TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findAll",Reservation.class);
		return query.getResultList();
	}
	
	// DELETE
	public void removeReservation(int id)
	{
		Reservation res = em.find(Reservation.class, id);
		if(res != null)
		{
			em.remove(res);
		}
	}
	
	// UPDATE (Met à jour la colonne "retour" de la table intermédiaire "reservation_agence" sur base de l'id reservation et agence)
	// dans le but de préciser dans quel agence le retour de la réservation sera effectué
	public void setAgenceDeRetour(int idReservation, int idAgence)
	{
		try
		{
			String stringQuery = "UPDATE reservation_agence SET Retour = 1 WHERE FK_reservation = ?1 AND FK_agence = ?2";
			Query query = em.createNativeQuery(stringQuery);
			query.setParameter("1", idReservation);
			query.setParameter("2", idAgence);
			query.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
