package com.og.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.og.model.Voiture;

public class VoitureService {
	
	protected EntityManager em;
	
	public VoitureService(EntityManager em)
	{
		this.em = em;
	}
	
	// READ BY ID (récupère une voiture particulière)
	public Voiture findVoiture(int id)
	{
		return em.find(Voiture.class,id);
	}
	
	// READ (récupère toutes les voitures sous forme de List)
	public List<Voiture> findAllVoitures()
	{
		TypedQuery<Voiture> query = em.createNamedQuery("Voiture.findAll",Voiture.class);
		return query.getResultList();
	}
	

}
