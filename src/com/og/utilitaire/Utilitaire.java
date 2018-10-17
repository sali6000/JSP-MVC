package com.og.utilitaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitaire {

	// Convertisseur de dates
	public static Date DateFormatConversion(String stringDate, String stringFormatDeDepart, String stringFormatDeRetour)
			throws ParseException {
		// Préparer le format de date dans le même format que celui récupérer
		SimpleDateFormat dateFormatAvant = new SimpleDateFormat(stringFormatDeDepart);

		// Préparer le format de date au format attendu au retour
		SimpleDateFormat dateFormatApres = new SimpleDateFormat(stringFormatDeRetour);

		// Convertir en Date la date récupérée dans le string stringDate
		Date dateFormatDeDepart = dateFormatAvant.parse(stringDate);

		// Convertir en string le format de Date récupérée précédement dans le format
		// attendu
		String stringDateApresConversion = dateFormatApres.format(dateFormatDeDepart);

		// Convertir en Date la date récupérée dans le string précédent
		Date dateFormatDeRetour = dateFormatApres.parse(stringDateApresConversion);

		return dateFormatDeRetour;
	}

}
