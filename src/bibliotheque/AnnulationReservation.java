package bibliotheque;

import java.util.TimerTask;

public class AnnulationReservation extends TimerTask{
	private Document docnnule;
	
	public AnnulationReservation(Document d) {
		this.docnnule=d;
	}
	
	@Override
	public void run() {
		synchronized (docnnule) {
			try {
				docnnule.retour();
			} catch (RetourException e) {
				System.err.println(e);
			}
		}
	}
	
}