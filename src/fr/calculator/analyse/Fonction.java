package fr.calculator.analyse;

import fr.calculator.MathException;

public class Fonction implements Terme {

	/**
	 * Le nom de la fonction, par exemple COSINUS.
	 */
	public NomFonction nom;
	/**
	 * Ce qui est passé en paramètre de la fonction. Par exemple, dans "cosinus(2x+3)", param vaut Parenthese(2*x, 3).
	 */
	public Terme param;

	public Fonction(NomFonction nom, Terme t) {
		this.nom = nom;
		this.param = t;
	}

	@Override
	public Terme inverser() {
		return new Division(new Rationnel(1), this);
	}

	@Override
	public Terme negatif() {
		return new Multiplication(new Rationnel(-1), this);
	}

	@Override
	public Terme simplifier() {
		param = param.simplifier();
		return this;
	}

	@Override
	public String toString() {
		return nom + "(" + param + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fonction) {
			Fonction f = (Fonction) obj;
			return nom == f.nom && param.equals(f.param);
		}
		return false;
	}

	public enum NomFonction {
		EXPONENTIELLE("exp"), LOGARITHME_NEPERIEN("ln"), COSINUS("cos"), SINUS("sin");

		private final String str;

		private NomFonction(String str) {
			this.str = str;
		}

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
			case "log":
				throw new MathException(
						"Pour le logarithme néperien, utilisez ln. Les autres logarithmes ne sont pas supportés.");
			default:
				throw new MathException("Fonction inconnue : " + nom);
			}
		}

		@Override
		public String toString() {
			return str;
		}

	}

}
