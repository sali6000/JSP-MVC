package com.og.servlet.reservation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import com.og.model.Agence;
import com.og.model.Reservation;
import com.og.model.Voiture;
import com.og.service.AgenceService;
import com.og.utilitaire.Utilitaire;

/**
 * Servlet implementation class Reservation
 */
@WebServlet("/ReservationDisponibilite")
public class ReservationDisponibilite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationDisponibilite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date dateAllerChoisie = null;
		Date dateRetourChoisie = null;

		// Je convertit les dates étant des String au format Date
		try {
			dateAllerChoisie = Utilitaire.DateFormatConversion(request.getParameter("Debut"), "MM/dd/yyyy",
					"dd/MM/yyyy");
			dateRetourChoisie = Utilitaire.DateFormatConversion(request.getParameter("Fin"), "MM/dd/yyyy",
					"dd/MM/yyyy");
		} catch (ParseException ex) {
			ex.getMessage();
		}

		// Si la date d'aller choisie est après la date de retour, je renvoie ma servlet
		// précédente avec un message d'erreur
		if (dateAllerChoisie.after(dateRetourChoisie)) {
			request.setAttribute("errorDate", true);
			RequestDispatcher rd = request.getRequestDispatcher("ReservationCondition");
			rd.forward(request, response);
		}

		// Je réserve un emplacement mémoire pour la liste de voitures que je renverrais
		// à ma vue
		List<Voiture> listVoituresDisponibles;

		// Je prépare l'accès à la BD
		EntityManager em = EMF.getEM();

		try {
			// J'ouvre la connexion à la BD pour le service que je souhaite
			AgenceService service = new AgenceService(em);

			// Je récupère les agences concernées graçe aux ID que je leurs donne en
			// parametre
			Agence agenceDeDepart = service.findAgence(Integer.parseInt(request.getParameter("Depart")));
			Agence agenceDeRetour = service.findAgence(Integer.parseInt(request.getParameter("Retour")));

			// Je garde en session l'agence de départ et de retour ainsi que les dates de
			// début et fin de réservation
			HttpSession session = request.getSession();
			session.setAttribute("agenceDeDepart", agenceDeDepart);
			session.setAttribute("agenceDeRetour", agenceDeRetour);
			session.setAttribute("dateDeDebut", request.getParameter("Debut"));
			session.setAttribute("dateDeFin", request.getParameter("Fin"));

			// Je filtre les voitures disponibles à l'agence de départ sur base des dates de
			// réservation données
			listVoituresDisponibles = FiltrerVoituresDisponibles(agenceDeDepart.getVoitures(), dateAllerChoisie,
					dateRetourChoisie);
		} finally {
			// Je nettoie la persistence des données et referme la connexion à la BD
			em.clear();
			em.close();
		}

		// J'assigne à ma vue la list de voitures disponibles
		request.setAttribute("voitures", listVoituresDisponibles);

		// Je renvois ma vue à l'utilisateur
		RequestDispatcher view = getServletContext()
				.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationDisponibilite.jsp");
		view.forward(request, response);
	}

	private List<Voiture> FiltrerVoituresDisponibles(List<Voiture> listVoituresDisponibles, Date dateAllerChoisie,
			Date dateRetourChoisie) {
		List<Voiture> voituresASupprimer = new ArrayList<Voiture>();

		for (Voiture v : listVoituresDisponibles) // List contenant les voitures disponible à l'agence de départ
		{
			for (Reservation res : v.getReservations()) // Réservations faites (et prévues) à l'agence de départ
			{
				// Pour chaques voitures disponibles à l'agence de départ,
				// vérifié si elle est disponible sur base des dates de réservations du véhicule
				// choisit
				// en les comparants avec les dates indiquées par l'utilisateur
				if (!dateAllerChoisie.after(res.getDate_de_retour())) {
					if (!dateAllerChoisie.before(res.getDate_de_reservation())
							|| !dateRetourChoisie.before(res.getDate_de_reservation())) {
						voituresASupprimer.add(v);
					}
				}
			}
		}

		// Supprimer les voitures de la liste principal à partir d'une 2ème liste pour
		// ne pas supprimer
		// de voitures en cours de lecture (risque d'erreurs)
		listVoituresDisponibles.removeAll(voituresASupprimer);

		return listVoituresDisponibles;
	}
}
