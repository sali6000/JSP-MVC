package com.og.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.og.model.Personne;

public class PersonneService {
	
	protected EntityManager em;
	
	public PersonneService(EntityManager em)
	{
		this.em = em;
	}
	
	public Personne findPersonne(int id)
	{
		return em.find(Personne.class,id);
	}
	
	// READ
	public List<Personne> findAllPersonnes()
	{
		TypedQuery<Personne> query = em.createNamedQuery("Personne.findAll",Personne.class);
		return query.getResultList();
	}
}
