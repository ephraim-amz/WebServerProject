package bibliotheque;

import java.util.HashMap;
import java.util.Map;

public class Bibliotheque {
	private Map<Integer, Document> documents;
	private Map<Integer, Abonne> abonnes;
	private static Bibliotheque bibliotheque = creerBibliotheque();

	private Bibliotheque() {
		this.documents = new HashMap<>();
		this.abonnes = new HashMap<>();
	}

	private static Bibliotheque creerBibliotheque() {
		Bibliotheque b = new Bibliotheque();
		b.ajouterDocument("Manon des Sources");
		b.ajouterDocument("Rouge et Noir");
		b.ajouterDocument("La Belle et la Bête");
		b.ajouterAbonne("Joseph");
		b.ajouterAbonne("Jonathan");
		return b;
	}

	public void ajouterDocument(String titre) {
		documents.put(documents.size(), new Livre(titre, documents.size()));
	}

	public void ajouterAbonne(String nomAbonne) {
		abonnes.put(abonnes.size(), new Abonne(nomAbonne, abonnes.size()));
	}

	public Document getDocument(int numDocument) {
		if (!documents.containsKey(numDocument))
			throw new IllegalArgumentException("Ce document n'existe pas");
		return documents.get(numDocument);
	}

	public String getLstDocs() {
		StringBuilder bd = new StringBuilder();
		for (int i = 0; i < documents.size(); i++) {
			bd.append(getDocument(i));
			if (i < documents.size() - 1)
				bd.append("---");
		}
		return bd.toString();
	}

	public Abonne getABonne(int numAbonne) {
		if (!abonnes.containsKey(numAbonne))
			throw new IllegalArgumentException("Cet abonné n'existe pas");
		return abonnes.get(numAbonne);
	}

	public static Bibliotheque getBibliotheque() {
		return bibliotheque;
	}

	public void reserver(int numDoc, int numAbo) throws EmpruntException {
		if (!this.abonnes.containsKey(numAbo))
			throw new EmpruntException(numAbo);
		if (!this.documents.containsKey(numDoc)) {
			throw new EmpruntException(numDoc);
		}
		Document d = this.getDocument(numDoc);
		synchronized (d) {
			d.reserver(this.getABonne(numAbo));
		}
	}

	public void emprunter(int numDoc, int numAbo) throws EmpruntException {
		if (!this.abonnes.containsKey(numAbo))
			throw new EmpruntException(numAbo);
		if (!this.documents.containsKey(numDoc)) {
			throw new EmpruntException(numDoc);
		}
		Document d = this.getDocument(numDoc);
		synchronized (d) {
			d.emprunter(this.getABonne(numAbo));
		}
	}

	public void retournerDocument(int numDoc) throws RetourException {
		if (!this.documents.containsKey(numDoc))
			throw new RetourException(numDoc);
		Document d = this.getDocument(numDoc);
		synchronized (d) {
			d.retour();
		}
	}

}
