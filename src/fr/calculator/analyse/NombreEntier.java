package fr.calculator.analyse;

/**
 * @author TheElectronWill
 * @deprecated utiliser {@link Rationnel} à la place.
 */
@Deprecated
public class NombreEntier implements Terme {

	public int valeur;

	public NombreEntier(int value) {
		this.valeur = value;
	}

	@Override
	public NombreEntier negatif() {
		valeur = -valeur;
		return this;
	}

	@Override
	public Rationnel inverser() {
		return new Rationnel(1, valeur);
	}

	@Override
	public NombreEntier simplifier() {
		return this;
	}

	@Override
	public String toString() {
		return String.valueOf(valeur);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof NombreEntier) && valeur == ((NombreEntier) obj).valeur;
	}

}
