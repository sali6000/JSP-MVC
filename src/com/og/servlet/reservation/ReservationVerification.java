package com.og.servlet.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.og.model.OptionReservation;

/**
 * Servlet implementation class ReservationVerification
 */
@WebServlet("/ReservationVerification")
public class ReservationVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationVerification() {
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

		// Je récupère la liste d'options pour la réservation
		List<OptionReservation> OptionsReservation = (List<OptionReservation>) session
				.getAttribute("optionsReservation");
		List<String> quantityOptionsReservation = new ArrayList<String>();

		// Je parcour les options selectionnés par le client
		for (OptionReservation option : OptionsReservation) {
			if (request.getParameter("get" + option.getIdentifiant()) != null) {
				quantityOptionsReservation
						.add((request.getParameter("get" + option.getIdentifiant()) == "false") ? "Non" : "Oui");
			} else {
				quantityOptionsReservation.add("Non");
			}
		}

		// Je garde en session les options de réservation selectionnées par le client
		session.setAttribute("quantiteOptionsReservation", quantityOptionsReservation);

		// Je renvois ma vue à l'utilisateur
		RequestDispatcher view = getServletContext()
				.getRequestDispatcher("/WEB-INF/ReservationViews/ReservationVerification.jsp");
		view.forward(request, response);
	}
}
