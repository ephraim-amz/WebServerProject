package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;
import bibliotheque.EmpruntException;

public class Emprunt implements Runnable, Lanceur {
	private Socket socketEmprunt;

	public Emprunt(Socket socketEmprunt) {
		this.socketEmprunt = socketEmprunt;
	}

	@Override
	public void run() {

		try {
			BufferedReader sinEmprunt = null;
			PrintWriter soutEmprunt = null;
			try {
				sinEmprunt = new BufferedReader(new InputStreamReader(socketEmprunt.getInputStream()));
				soutEmprunt = new PrintWriter(socketEmprunt.getOutputStream(), true);

				soutEmprunt.println(Bibliotheque.getBibliotheque().getLstDocs());
				System.out.println(Bibliotheque.getBibliotheque().getLstDocs());

				soutEmprunt.println("Saisissez le numéro du document à emprunter :");
				int numDocument = Integer.parseInt(sinEmprunt.readLine());
				soutEmprunt.println("Saisissez le numéro de l'abonné qui souhaite emprunter un document :");
				int numAbonne = Integer.parseInt(sinEmprunt.readLine());

				try {
					Bibliotheque.getBibliotheque().emprunter(numDocument, numAbonne);
				} catch (EmpruntException e) {
					e.printStackTrace();
				}

				this.socketEmprunt.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lancer() {
		new Thread(this).start();
	}

	@Override
	protected void finalize() throws Throwable {
		socketEmprunt.close();
	}
}