package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliRetour {
	private static final int PORT_RETOUR = 2700;
	private static final String HOST_RETOUR = "localhost";

	public static void main(String[] args) {
		Socket retour = null;
		try {
			retour = new Socket(HOST_RETOUR, PORT_RETOUR);

			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader sinRetour = new BufferedReader(new InputStreamReader(retour.getInputStream()));
			PrintWriter soutRetour = new PrintWriter(retour.getOutputStream(), true);

			System.out.println("Connecté au serveur à l'adresse : " + retour.getInetAddress() + " au port numéro : "
					+ retour.getPort());

			for (String docEmpruntes : sinRetour.readLine().split("---")) {
				System.out.println(docEmpruntes);
			}

			System.out.println(sinRetour.readLine());
			String documentRetourne = "";

			do {
				documentRetourne = clavier.readLine();
			} while (!AppliEmprunt.estNumerique(documentRetourne));
			System.out.println(documentRetourne);
			soutRetour.println(documentRetourne);

			if (documentRetourne == null)
				System.err.println("Retour du document non possible.");

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (retour != null)
				retour.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}