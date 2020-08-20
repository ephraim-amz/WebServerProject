package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;
import bibliotheque.RetourException;

public class Retour implements Runnable, Lanceur {
	private Socket socketRetour;

	public Retour(Socket socketRetour) {
		this.socketRetour = socketRetour;
	}

	@Override
	public void run() {
		try {
			BufferedReader sinRetour = null;
			PrintWriter soutRetour = null;
			try {

				sinRetour = new BufferedReader(new InputStreamReader(socketRetour.getInputStream()));
				soutRetour = new PrintWriter(socketRetour.getOutputStream(), true);
				
				soutRetour.println(Bibliotheque.getBibliotheque().getLstDocs());
				System.out.println(Bibliotheque.getBibliotheque().getLstDocs());
				 
				soutRetour.println("Saisissez le numéro du document à retourner:");
				int numDocumentRetourne = Integer.parseInt(sinRetour.readLine());
				
				try {
					Bibliotheque.getBibliotheque().retournerDocument(numDocumentRetourne);
				} catch (RetourException e) {
					e.printStackTrace();
				}
				
				
				this.socketRetour.close();
			}

			catch (NumberFormatException e) {
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
		socketRetour.close();
	}
}