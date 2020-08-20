package bibliotheque;

import java.util.Timer;

public class TimerAnnulationReservation {
	private static Timer dureeReservation = new Timer();
	private static final long tmpsReservation = 7200;
	private AnnulationReservation r;

	public TimerAnnulationReservation(Document docAnnule) {
		this.r = new AnnulationReservation(docAnnule);
		dureeReservation.schedule(r, tmpsReservation);
	}
	

}
