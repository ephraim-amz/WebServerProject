package bibliotheque;

public class RetourException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int numDocRetour;
	
	public RetourException(int numero) {
		this.numDocRetour=numero;
	}
	
	@Override
	public String toString() {
		return "Le livre num�ro " + this.numDocRetour + " ne peut pas �tre retourn� car il n'a pas �t� emprunt�.";
	}

}
