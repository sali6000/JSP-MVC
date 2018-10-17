package com.og.servlet.reservation;

import java.io.IOException;
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
import com.og.model.OptionReservation;
import com.og.model.OptionVoiture;
import com.og.model.Personne;
import com.og.model.Voiture;
import com.og.service.PersonneService;
import com.og.service.ReservationService;
import com.og.utilitaire.Utilitaire;

/**
 * Servlet implementation class ReservationAuthentification
 */
@WebServlet("/ReservationValidation")
public class ReservationValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationValidation() {
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

		if (request.getParameter("idPersonne") == null) {
			// Si aucun client n'est identifié...

			// Je prépare une liste de clients venant de ma base de donnée
			List<Personne> personnes = new ArrayList<Personne>();

			EntityManager em = EMF.getEM();

			try {
				PersonneService servicePersonne = new PersonneService(em);
				for (Personne personne : servicePersonne.findAllPersonnes()) {
					personnes.add(personne);
				}
			} finally {
				em.clear();
				em.close();
			}

			// Je renvoit à la vue les clients disponibles pour la sélection
			request.setAttribute("personnes", personnes);

			RequestDispatcher view = getServletContext()
					.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationAuthentification.jsp");
			view.forward(request, response);
		} else {
			// Si un client a été choisit...

			// Je met au format les dates, calcul le prix final, identifie le client et
			// enregistre la réservation
			Personne personne = null;
			float prix = 0;
			HttpSession session = request.getSession();
			EntityManager em = EMF.getEM();

			try {

				// Je récupère toutes les informations venant de la session pour enregistrer la
				// réservation
				List<Voiture> voitures = new ArrayList<Voiture>();
				voitures.add((Voiture) session.getAttribute("voiture"));
				List<Agence> agences = new ArrayList<Agence>();
				agences.add((Agence) session.getAttribute("agenceDeDepart"));
				agences.add((Agence) session.getAttribute("agenceDeRetour"));
				List<OptionVoiture> optionsVoiture = (List<OptionVoiture>) session.getAttribute("optionsVoiture");
				List<OptionReservation> optionsReservation = (List<OptionReservation>) session
						.getAttribute("optionsReservation");
				List<Integer> quantiteOptionsVoiture = (List<Integer>) session.getAttribute("quantiteOptionsVoiture");
				List<String> contenuOptionsReservation = (List<String>) session
						.getAttribute("quantiteOptionsReservation");

				// Je met au format les dates d'aller - retour
				// (NOTICE) SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				// System.out.println(dateFormat.format(aller)); // Affichera 03/08/2018
				Date aller = Utilitaire.DateFormatConversion((String) session.getAttribute("dateDeDebut"), "MM/dd/yyyy",
						"dd/MM/yyyy");
				Date retour = Utilitaire.DateFormatConversion((String) session.getAttribute("dateDeFin"), "MM/dd/yyyy",
						"dd/MM/yyyy");
				
				// Je calcule le prix final
				prix = CalculeDuPrixFinal(aller, retour, voitures.get(0), optionsVoiture, optionsReservation,
						quantiteOptionsVoiture, contenuOptionsReservation);

				// Je sauvegarde en mémoire la personne qui souhaite effectuer la réservation
				PersonneService servicePersonne = new PersonneService(em);
				personne = servicePersonne.findPersonne(Integer.parseInt(request.getParameter("idPersonne")));

				// J'enregistre la réservation
				ReservationService serviceReservation = new ReservationService(em);
				serviceReservation.createReservation(aller, retour, prix, personne, optionsReservation, agences,
						voitures);
			} catch (Exception ex) {
				ex.getMessage();
			} finally {
				em.clear();
				em.close();
			}

			// Je garde le client en mémoire
			session.setAttribute("personne", personne);

			// Je renvoie à la vue les informations concernant le prix de location
			request.setAttribute("prix", prix);

			RequestDispatcher view = getServletContext()
					.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationValidation.jsp");
			view.forward(request, response);
		}
	}

	// Calcul du prix final sur base des dates, voiture et options choisis
	private float CalculeDuPrixFinal(Date aller, Date retour, Voiture voiture, List<OptionVoiture> optionsVoiture,
			List<OptionReservation> optionsReservation, List<Integer> quantiteOptionsVoiture,
			List<String> quantiteOptionsReservation) {
		float prix = 0;

		int nbrJours = (int) Math.abs((aller.getTime() - retour.getTime()) / (24 * 60 * 60 * 1000)) + 1;

		// Je calcul le prix de location de la voiture * le nombre de jour de location
		prix += (nbrJours * voiture.getPrix());

		// Je calcul le prix de chaque options * la quantité, et * le nombre de jours de
		// location
		for (int quantite : quantiteOptionsVoiture) {
			if (quantite > 0)
				prix += ((optionsVoiture.get(quantiteOptionsVoiture.indexOf(quantite)).getPrix()) * quantite)
						* nbrJours;
		}

		// Je rajoute au prix final les options de réservation
		for (String assurance : quantiteOptionsReservation) {
			if (assurance.equals("Oui"))
				prix += optionsReservation.get(quantiteOptionsReservation.indexOf(assurance)).getPrix();
		}
		return prix;
	}
}
