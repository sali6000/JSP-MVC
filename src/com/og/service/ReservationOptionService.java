package com.og.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.og.model.OptionReservation;

public class ReservationOptionService {
	
	protected EntityManager em;
	
	public ReservationOptionService(EntityManager em)
	{
		this.em = em;
	}
	
	public OptionReservation findOptionVoiture(int id)
	{
		return em.find(OptionReservation.class,id);
	}
	
	// READ
	public List<OptionReservation> findAllOptionReservation()
	{
		TypedQuery<OptionReservation> query = null;
		try
		{
			query = em.createNamedQuery("OptionReservation.findAll",OptionReservation.class);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return query.getResultList();
	}
}
