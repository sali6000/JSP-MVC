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
	
	// READ BY ID (r�cup�re une option particuli�re)
	public OptionVoiture findOptionVoiture(int id)
	{
		return em.find(OptionVoiture.class,id);
	}
	
	// READ (r�cup�re toutes les options sous forme de List)
	public List<OptionVoiture> findAllOptionVoiture()
	{
		TypedQuery<OptionVoiture> query = em.createNamedQuery("OptionVoiture.findAll",OptionVoiture.class);
		return query.getResultList();
	}
	
	
	// R�cup�re la colonne "quantite" relative � une voiture et une option en particulier
	public int getQuantiteOption(int idVoiture, int idOptionVoiture, EntityManager em)
	{
		// En queryNative (en dehors de JPQL), les parametres de requ�tes se font � l'aide de positions et la syntaxe suivante
		String stringQuery = "SELECT quantite FROM option_comprise_voiture WHERE FK_voiture = ?1 AND FK_option_voiture = ?2";
		Query query = em.createNativeQuery(stringQuery);
		query.setParameter("1", idVoiture);
		query.setParameter("2", idOptionVoiture);
		return (Integer)query.getSingleResult();
	}

}
