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
import javax.servlet.http.HttpSession;

import com.og.connection.EMF;
import com.og.model.OptionReservation;
import com.og.model.OptionVoiture;
import com.og.service.ReservationOptionService;

/**
 * Servlet implementation class ReservationOptionReservation
 */
@WebServlet("/ReservationOptionReservation")
public class ReservationOptionReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationOptionReservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Je r�cup�re la liste d'options pour la voiture
		// Je pr�pare une liste qui s'occupera de r�cup�r� les valeurs entr�es par
		// l'utilisateur pour la quantit� d'option choisit
		List<OptionVoiture> OptionsVoiture = (List<OptionVoiture>) session.getAttribute("optionsVoiture");
		List<Integer> quantiteOptionsVoiture = new ArrayList<Integer>();

		// Je parcours les options disponibles pour la voiture choisie
		for (OptionVoiture option : OptionsVoiture) {
			// Si la quantit�e enregistr�e ne contient pas de valeurs
			if (request.getParameter("quantity" + option.getIdentifiant()) == "") {
				// J'enregistre 0 comme valeur
				quantiteOptionsVoiture.add(0);
			} else {
				// Sinon j'enregistre la valeur indiqu�e par l'utilisateur
				quantiteOptionsVoiture
						.add(Integer.parseInt(request.getParameter("quantity" + option.getIdentifiant())));
			}
		}

		// Je sauvegarde en session la quantit� d'options choisies par l'utilisateur
		session.setAttribute("quantiteOptionsVoiture", quantiteOptionsVoiture);

		// Je r�cup�re les options disponibles pour la r�servation
		List<OptionReservation> optionsReservation;
		EntityManager em = EMF.getEM();

		try {
			ReservationOptionService serviceOptionReservation = new ReservationOptionService(em);
			optionsReservation = serviceOptionReservation.findAllOptionReservation();
		} finally {
			em.clear();
			em.close();
		}

		// J'assigne � ma vue la liste des options disponibles
		session.setAttribute("optionsReservation", optionsReservation);

		// Je renvois ma vue � l'utilisateur
		RequestDispatcher view = getServletContext()
				.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationOptionReservation.jsp");
		view.forward(request, response);
	}
}
