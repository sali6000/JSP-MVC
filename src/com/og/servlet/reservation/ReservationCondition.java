package com.og.servlet.reservation;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.og.connection.EMF;
import com.og.model.Agence;
import com.og.service.AgenceService;

/**
 * Servlet implementation class Reservation
 */
@WebServlet("/ReservationCondition")
public class ReservationCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Agence> list;
		EntityManager em = EMF.getEM();

		// Je récupère les agences disponibles
		try {
			AgenceService service = new AgenceService(em);
			list = service.findAllAgences();
		} finally {
			em.clear();
			em.close();
		}

		// J'assigne les agences disponibles à ma vue
		request.setAttribute("agences", list);
		RequestDispatcher view = getServletContext()
				.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationCondition.jsp");
		view.forward(request, response);
	}
}
