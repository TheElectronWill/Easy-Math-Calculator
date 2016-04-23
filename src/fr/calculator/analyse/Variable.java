package fr.calculator.analyse;

public class Variable implements Terme {

	public final String nom;

	public Variable(String nom) {
		if (!nom.equals("x")) {
			throw new IllegalArgumentException("Nom de variable invalide : \"" + nom + "\". Seul x est autorisé.");
		}
		this.nom = nom;
	}

	@Override
	public Terme inverser() {
		return new Division(new NombreEntier(1), this);
	}

	@Override
	public Terme negatif() {
		return new Multiplication(new NombreEntier(-1), this);
	}

	@Override
	public Variable simplifier() {
		return this;
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Variable) && nom == ((Variable) obj).nom;
	}

}
