package fr.calculator.analyse;

public class NombreEntier implements Term {

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
	public Fraction inverser() {
		return new Fraction(1, valeur);
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
