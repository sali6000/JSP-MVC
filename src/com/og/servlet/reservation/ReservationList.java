package com.og.servlet.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.og.connection.EMF;
import com.og.model.Reservation;
import com.og.model.Voiture;
import com.og.service.VoitureService;

/**
 * Servlet implementation class ReservationListServlet
 */
@WebServlet("/ReservationList")
public class ReservationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager em = EMF.getEM();

		// Je récupère les voitures présentes dans la base de donnée
		List<Voiture> listVoitures = null;

		try {
			VoitureService serviceVoiture = new VoitureService(em);
			listVoitures = serviceVoiture.findAllVoitures();
		} finally {
			em.clear();
			em.close();
		}

		// Je récupère toutes les informations concernant les réservations présentes
		// dans la base de donnée
		List<Reservation> listReservations = getAllAboutReservations(listVoitures);

		request.setAttribute("Reservations", listReservations);
		RequestDispatcher view = getServletContext()
				.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationList.jsp");
		view.forward(request, response);
	}

	// Les voitures étant owner au niveau de la relation voiture - réservation,
	// je parcours la list des voitures disponibles pour accéder aux informations de
	// leurs réservation
	private List<Reservation> getAllAboutReservations(List<Voiture> listVoitures) {
		List<Reservation> listRes = new ArrayList<Reservation>();
		List<Voiture> voituresConcernantReservation = null;

		for (Voiture voiture : listVoitures) {
			if (!voiture.getReservations().isEmpty()) {
				voituresConcernantReservation = new ArrayList<Voiture>();
				voituresConcernantReservation.add(voiture);
				for (Reservation reservation : voiture.getReservations()) {
					reservation.setVoitures(voituresConcernantReservation);
					listRes.add(reservation);
				}
			}
		}
		return listRes;
	}

}
