package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur implements Runnable {
	private static final int PORT_RESERVATION = 2500;
	private static final int PORT_EMPRUNT = 2600;
	private static final int PORT_RETOUR = 2700;
	private ServerSocket serveurBibliotheque;

	public Serveur(int portServeur) throws IOException {
		this.serveurBibliotheque = new ServerSocket(portServeur);
	}

	@Override
	public void run() {
		Lanceur l;
		try {
			while (true) {
				switch (this.serveurBibliotheque.getLocalPort()) {
					case PORT_EMPRUNT:
						l = new Emprunt(this.serveurBibliotheque.accept());
						l.lancer();
						break;
					case PORT_RESERVATION:
						l = new Reservation(this.serveurBibliotheque.accept());
						l.lancer();
						break;
					case PORT_RETOUR:
						l = new Retour(this.serveurBibliotheque.accept());
						l.lancer();
						break;
					default:
						System.err.println("Le port de ce serveur ne correspond à aucun des ports clients.");
						break;
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
