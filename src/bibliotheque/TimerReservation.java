package bibliotheque;

import java.util.Timer;

public class TimerReservation {
	private static Timer t = new Timer();
	private static final long DUREE_RESERVATION = 7200000;

	private TimerReservation() {
		throw new IllegalStateException("Classe non instanciable");
	}

	public static ReservationAnnule lancerTimer(Document d) {
		ReservationAnnule res = new ReservationAnnule(d);
		t.schedule(res, DUREE_RESERVATION);
		return res;
	}

}
