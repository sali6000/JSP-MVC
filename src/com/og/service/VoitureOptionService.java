package com.og.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.og.model.OptionVoiture;

public class VoitureOptionService {
	
	protected EntityManager em;
	
	public VoitureOptionService(EntityManager em)
	{
		this.em = em;
	}
	
	// READ BY ID (récupère une option particulière)
	public OptionVoiture findOptionVoiture(int id)
	{
		return em.find(OptionVoiture.class,id);
	}
	
	// READ (récupère toutes les options sous forme de List)
	public List<OptionVoiture> findAllOptionVoiture()
	{
		TypedQuery<OptionVoiture> query = em.createNamedQuery("OptionVoiture.findAll",OptionVoiture.class);
		return query.getResultList();
	}
	
	
	// Récupère la colonne "quantite" relative à une voiture et une option en particulier
	public int getQuantiteOption(int idVoiture, int idOptionVoiture, EntityManager em)
	{
		// En queryNative (en dehors de JPQL), les parametres de requêtes se font à l'aide de positions et la syntaxe suivante
		String stringQuery = "SELECT quantite FROM option_comprise_voiture WHERE FK_voiture = ?1 AND FK_option_voiture = ?2";
		Query query = em.createNativeQuery(stringQuery);
		query.setParameter("1", idVoiture);
		query.setParameter("2", idOptionVoiture);
		return (Integer)query.getSingleResult();
	}

}
