package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;
import bibliotheque.EmpruntException;

public class Reservation implements Runnable, Lanceur {
	private Socket socketReservation;

	public Reservation(Socket socketReservation) {
		this.socketReservation = socketReservation;
	}

	@Override
	public void run() {
		BufferedReader sinReservation = null;
		PrintWriter soutReservation = null;

		try {
			sinReservation = new BufferedReader(new InputStreamReader(socketReservation.getInputStream()));
			soutReservation = new PrintWriter(socketReservation.getOutputStream(), true);
			
			soutReservation.println(Bibliotheque.getBibliotheque().getLstDocs());
			System.out.println(Bibliotheque.getBibliotheque().getLstDocs());
			
			soutReservation.println("Saisissez le numéro de l'abonné qui souhaite faire une réservation de document:");
			int numAbonne = Integer.parseInt(sinReservation.readLine());
			soutReservation.println("Saisissez le numéro du document à réserver :");
			int numDocReserve = Integer.parseInt(sinReservation.readLine());
			
			try {
				Bibliotheque.getBibliotheque().reserver(numDocReserve, numAbonne);
			} catch (EmpruntException e) {
				e.printStackTrace();
			}
			
			this.socketReservation.close();
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
		socketReservation.close();
	}
}