package serveur;

import java.io.IOException;

public class AppliServeur {

	private static final int PORT_RESERVATION = 2500;
	private static final int PORT_EMPRUNT = 2600;
	private static final int PORT_RETOUR = 2700;

	public static void main(String[] args) {
		try {
			Thread tEmprunt = new Thread(new Serveur(PORT_EMPRUNT));
			Thread tRetour = new Thread(new Serveur(PORT_RETOUR));
			Thread tReservation = new Thread(new Serveur(PORT_RESERVATION));

			tEmprunt.start();
			tRetour.start();
			tReservation.start();

			System.out.println("Les serveurs ont été crées");
		} catch (IOException e) {
			System.err.println("Problème de connexion sur les serveurs car : " + e.getLocalizedMessage() + "\n" + e.getMessage()
					+ "\n" + e.toString());
		}

	}

}
