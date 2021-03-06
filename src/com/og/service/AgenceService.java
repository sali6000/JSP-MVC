package com.og.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.og.model.Agence;

public class AgenceService {
	

	protected EntityManager em;
	
	public AgenceService(EntityManager em)
	{
		this.em = em;
	}
	
	public Agence findAgence(int id)
	{
		return em.find(Agence.class,id);
	}
	
	// READ
	public List<Agence> findAllAgences()
	{
		TypedQuery<Agence> query = em.createNamedQuery("Agence.findAll",Agence.class);
		return query.getResultList();
	}
	
	// R�cup�re la colonne "quantite" relative � une voiture et une option en particulier
	public int getQuantiteOption(int idVoiture, int idOptionVoiture, EntityManager em) throws Exception
	{
		// En queryNative (en dehors de JPQL), les parametres de requ�tes se font � l'aide de positions et la syntaxe suivante
		String stringQuery = "SELECT quantite FROM option_comprise_voiture WHERE FK_voiture = ?1 AND FK_option_voiture = ?2";
		Query query = em.createNativeQuery(stringQuery);
		query.setParameter("1", idVoiture);
		query.setParameter("2", idOptionVoiture);
		return (Integer)query.getSingleResult();
	}

}
