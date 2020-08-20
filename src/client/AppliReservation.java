package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliReservation {
	private static final int PORT_RESERVATION = 2500;
	private static final String HOST_RESERVATION = "localhost";

	public static void main(String[] args) {
		Socket reservation = null;
		try {
			reservation = new Socket(HOST_RESERVATION, PORT_RESERVATION);

			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader sinReservation = new BufferedReader(new InputStreamReader(reservation.getInputStream()));
			PrintWriter soutReservation = new PrintWriter(reservation.getOutputStream(), true);

			System.out.println("Connect� au serveur � l'adresse : " + reservation.getInetAddress() + " au port num�ro : "
					+ reservation.getPort());

			for (String livresAReserver : sinReservation.readLine().split("---")) {
				System.out.println(livresAReserver);
			}

			System.out.println(sinReservation.readLine());
			String numReservation = "";
			do {
				numReservation = clavier.readLine();
			} while (!AppliEmprunt.estNumerique(numReservation));
			System.out.println(numReservation);
			soutReservation.println(numReservation);

			String numAboReservation = "";
			System.out.println(sinReservation.readLine());
			do {
				numAboReservation = clavier.readLine();
			} while (!AppliEmprunt.estNumerique(numAboReservation));
			System.out.println(numAboReservation);
			soutReservation.println(numAboReservation);

			if (numReservation == null || numAboReservation == null)
				System.err.println("Réservation non possible car les champs nécessaires n'ont pas été renseignés");

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (reservation != null)
				reservation.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}