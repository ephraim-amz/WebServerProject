package bibliotheque;

public class EmpruntException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int nummDocEmprunt;

	public EmpruntException(int numero) {
		this.nummDocEmprunt = numero;
	}

	@Override
	public String toString() {
		return "Le livre num�ro " + this.nummDocEmprunt + " ne peut pas �tre emprunt� ou r�serv�";
	}

}
