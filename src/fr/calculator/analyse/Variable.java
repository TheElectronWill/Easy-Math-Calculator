package fr.calculator.analyse;

public class Variable implements Term {

	public final String nom;

	public Variable(String name) {
		this.nom = name;
	}

	@Override
	public Term inverser() {
		return new Division(new NombreEntier(1), this);
	}

	@Override
	public Term negatif() {
		return new Multiplication(new NombreEntier(-1), this);
	}

	@Override
	public Variable simplifier() {
		return this;
	}

	@Override
	public String toString() {
		return "Literal: " + nom;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Variable) && nom == ((Variable) obj).nom;
	}

}
