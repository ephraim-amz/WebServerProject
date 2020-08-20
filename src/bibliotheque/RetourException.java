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
		return "Le livre numéro " + this.numDocRetour + " ne peut pas être retourné car il n'a pas été emprunté.";
	}

}
