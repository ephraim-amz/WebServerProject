package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliEmprunt {
	private static final int PORT_EMPRUNT = 2600;
	private static final String HOST_EMPRUNT = "localhost";

	public static void main(String[] args) {
		Socket emprunt = null;
		try {
			emprunt = new Socket(HOST_EMPRUNT, PORT_EMPRUNT);

			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader sinEmprunt = new BufferedReader(new InputStreamReader(emprunt.getInputStream()));
			PrintWriter soutEmprunt = new PrintWriter(emprunt.getOutputStream(), true);

			System.out.println("Connecté au serveur à l'adresse : " + emprunt.getInetAddress() + " au port numéro : "+ emprunt.getPort());

			for (String livresAEmprunter : sinEmprunt.readLine().split("---")) {
				System.out.println(livresAEmprunter);
			}

			System.out.println(sinEmprunt.readLine());
			String numLivreEmprunt = "";
			do {				
				numLivreEmprunt = clavier.readLine();
			} while (!estNumerique(numLivreEmprunt));
			System.out.println(numLivreEmprunt);
			soutEmprunt.println(numLivreEmprunt);
			
			String numAboEmprunt = "";
			System.out.println(sinEmprunt.readLine());
			
			do {
				numAboEmprunt = clavier.readLine();
			} while (!estNumerique(numAboEmprunt));
			soutEmprunt.println(numAboEmprunt);
			
			if (numAboEmprunt == null || numLivreEmprunt == null)
				System.err.println("Emprunt non possible");

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (emprunt != null)
				emprunt.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	public static boolean estNumerique(String num) {
		try {
			Integer.parseInt(num);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}