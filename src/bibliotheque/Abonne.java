package bibliotheque;

public class Abonne {
	private int numero;
	private String nomAbonne;

	public Abonne(String nomAbonne, int numero) {
		this.nomAbonne = nomAbonne;
		this.numero = numero;
	}

	public String getNomAbonne() {
		return nomAbonne;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "L'abonne numéro: " + this.numero + " a pour nom " + this.nomAbonne;
	}
}
