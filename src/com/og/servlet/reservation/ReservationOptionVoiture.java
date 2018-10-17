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
import com.og.model.OptionVoiture;
import com.og.model.Voiture;
import com.og.service.VoitureOptionService;
import com.og.service.VoitureService;

/**
 * Servlet implementation class ReservationDetailVoiture
 */
@WebServlet("/ReservationOptionVoiture")
public class ReservationOptionVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationOptionVoiture() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Je pr�pare une liste contenant les options disponibles pour la voiture,
		// les quantit�es d'options disponibles pour la voiture se trouve sur une table
		// interm�diaire,
		// je pr�pare donc une liste d'entiers pour r�cup�rer ces valeurs directement de
		// MySQL
		List<OptionVoiture> optionsVoitures;
		List<Integer> quantiteOptionVoiture;

		// J'utilise la session d�j� existante pour enregistrer les informations
		// choisies par l'utilisateur
		HttpSession session = request.getSession();

		// Je pr�pare l'acc�s � la BD
		EntityManager em = EMF.getEM();

		try {
			// Je r�cup�re la voiture gra�e � l'ID que je lui donne en parametre,
			// je r�cup�re les options de la voiture concern�e
			// je pr�pare le tableau qui r�cup�rera la quantit�e disponible pour les options
			// de la voiture concern�e
			VoitureService serviceVoiture = new VoitureService(em);
			Voiture voiture = serviceVoiture.findVoiture(Integer.parseInt(request.getParameter("idVehicule")));
			optionsVoitures = voiture.getOptionVoitures();
			quantiteOptionVoiture = new ArrayList<Integer>();

			// Pour chaques options disponibles pour la voiture choisie
			VoitureOptionService serviceOptionVoiture = new VoitureOptionService(em);
			for (OptionVoiture optionVoiture : optionsVoitures) {
				// Je r�cup�re la quantit�e disponible pour chaqu'unes des options et je les met
				// dans le tableau r�serv�
				quantiteOptionVoiture.add(serviceOptionVoiture.getQuantiteOption(voiture.getIdentifiant(),
						optionVoiture.getIdentifiant(), em));
			}

			// Je garde en m�moire la voiture choisie par l'utilisateur
			session.setAttribute("voiture", voiture);
		} finally {
			em.clear();
			em.close();
		}

		// J'enregistre en session les options disponibles pour la voiture
		session.setAttribute("optionsVoiture", optionsVoitures);

		// J'assigne la quantit� des options disponibles � ma vue
		request.setAttribute("quantiteOptionsVoiture", quantiteOptionVoiture);

		// Je renvois ma vue � l'utilisateur
		RequestDispatcher view = getServletContext()
				.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationOptionVoiture.jsp");
		view.forward(request, response);
	}
}
