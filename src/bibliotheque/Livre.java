package bibliotheque;

public class Livre implements Document {
	private int numero;
	private boolean disponible;
	private String titre;
	private Abonne reserve;
	private Abonne emprunte;
	private ReservationAnnule tmpReservation;

	public Livre(String titre, int numero) {
		this.titre = titre;
		this.numero = numero;
		this.disponible = true;
	}

	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		if (!this.estDisponible()) {
			throw new EmpruntException(numero);
		} else {
			this.emprunte = ab;
			this.changerDisponibiliteDocument(false);
		}
	}

	@Override
	public void reserver(Abonne ab) throws EmpruntException {
		if (!this.estDisponible()) {
			throw new EmpruntException(numero);
		} else {
			this.reserve = ab;
			this.tmpReservation = TimerReservation.lancerTimer(this);
		}
	}

	@Override
	public void retour() throws RetourException {
		if (this.emprunte == null)
			throw new RetourException(numero);

		if (this.tmpReservation != null) {
			this.tmpReservation.cancel();
		}

		this.emprunte = null;
		this.reserve = null;
		this.changerDisponibiliteDocument(true);
	}

	@Override
	public String toString() {
		StringBuilder bd = new StringBuilder("Le livre " + this.titre + " a pour numéro: " + this.numero() + ".");
		if (this.reserve != null)
			bd.append(" Il est réservé par: " + this.reserve.getNomAbonne() + ".");
		if (this.emprunte != null)
			bd.append(" Il est emprunté par: " + this.emprunte.getNomAbonne() + ".");
		return bd.toString();
	}

	@Override
	public int numero() {
		return this.numero;
	}

	public boolean estDisponible() {
		return disponible;
	}

	public void changerDisponibiliteDocument(boolean estDisponible) {
		this.disponible = estDisponible;
	}

}
