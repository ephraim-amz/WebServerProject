package bibliotheque;

import java.util.TimerTask;

public class ReservationAnnule extends TimerTask {
	private Document docAnnule;

	public ReservationAnnule(Document docAnnule) {
		this.docAnnule = docAnnule;
	}

	@Override
	public void run() {
		synchronized (docAnnule) {
			try {
				this.docAnnule.retour();
			} catch (RetourException e) {
				e.printStackTrace();
			}
		}
	}

}
