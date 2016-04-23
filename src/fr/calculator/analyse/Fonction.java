package fr.calculator.analyse;

public class Fonction implements Terme {
	
	/**
	 * Le nom de la fonction, par exemple COSINUS.
	 */
	public NomFonction nom;
	/**
	 * Ce qui est passé en paramètre de la fonction. Par exemple, dans "cosinus(2x+3)", t vaut Parenthese(2*x, 3).
	 */
	public Terme t;
	
	public Fonction(NomFonction nom, Terme t) {
		this.nom = nom;
		this.t = t;
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
	public Terme simplifier() {
		return this;
	}
	
	@Override
	public String toString() {
		return nom + "(" + t + ")";
	}
	
	public enum NomFonction {
		EXPONENTIELLE, LOGARITHME_NEPERIEN, COSINUS, SINUS;
		
		static NomFonction get(String nom) {
			switch (nom) {
				case "exp":
				case "e":
				case "e^":
					return EXPONENTIELLE;
				case "ln":
					return LOGARITHME_NEPERIEN;
				case "cos":
				case "cosinus":
					return COSINUS;
				case "sin":
				case "sinus":
					return SINUS;
				default:
					throw new RuntimeException("Fonction inconnue: " + nom);
			}
		}
	}
	
}
