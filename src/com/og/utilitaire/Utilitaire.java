package com.og.utilitaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitaire {

	// Convertisseur de dates
	public static Date DateFormatConversion(String stringDate, String stringFormatDeDepart, String stringFormatDeRetour)
			throws ParseException {
		// Pr�parer le format de date dans le m�me format que celui r�cup�rer
		SimpleDateFormat dateFormatAvant = new SimpleDateFormat(stringFormatDeDepart);

		// Pr�parer le format de date au format attendu au retour
		SimpleDateFormat dateFormatApres = new SimpleDateFormat(stringFormatDeRetour);

		// Convertir en Date la date r�cup�r�e dans le string stringDate
		Date dateFormatDeDepart = dateFormatAvant.parse(stringDate);

		// Convertir en string le format de Date r�cup�r�e pr�c�dement dans le format
		// attendu
		String stringDateApresConversion = dateFormatApres.format(dateFormatDeDepart);

		// Convertir en Date la date r�cup�r�e dans le string pr�c�dent
		Date dateFormatDeRetour = dateFormatApres.parse(stringDateApresConversion);

		return dateFormatDeRetour;
	}

}
