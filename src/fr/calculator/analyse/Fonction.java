package fr.calculator.analyse;

public class Fonction implements Term {
	
	/**
	 * Le nom de la fonction, par exemple COSINUS.
	 */
	public NomFonction nom;
	/**
	 * Ce qui est passé en paramètre de la fonction. Par exemple, dans "cosinus(2x+3)", t vaut Parenthese(2*x, 3).
	 */
	public Term t;
	
	public Fonction(NomFonction nom, Term t) {
		this.nom = nom;
		this.t = t;
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
	public Term simplifier() {
		return this;
	}
	
	@Override
	public String toString() {
		return nom + "(" + t + ")";
	}
	
	public enum NomFonction {
		EXPONENTIELLE, LOGARITHME_NEPERIEN, COSINUS, SINUS;
	}
	
}
