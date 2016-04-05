package fr.calculator.parser;

public class Puissance implements Term {
	
	public Term n, exposant;
	
	public Puissance(Term n, Term exposant) {
		this.n = n;
		this.exposant = exposant;
	}
	
	@Override
	public Term inverser() {
		return new Division(new NombreEntier(1), this);
	}
	
	public Term diviser(Term t) {
		if (n.equals(t)) {// n^p / n = n^(p-1)
			if (exposant instanceof NombreEntier)
				((NombreEntier) exposant).valeur--;
			else if (exposant instanceof Fraction)
				((Fraction) exposant).ajouter(new NombreEntier(-1));
			else
				exposant = new Parenthese(exposant, new NombreEntier(-1));
			return this;
		} else if (t instanceof Puissance) {
			Puissance pow = (Puissance) t;
			if (n.equals(pow.n)) {// n^p1 / n^p2 = n^(p1-p2)
				if (exposant instanceof NombreEntier) {
					if (pow.exposant instanceof NombreEntier)
						((NombreEntier) exposant).valeur -= ((NombreEntier) pow.exposant).valeur;
					else if (pow.exposant instanceof Fraction)
						exposant = new Fraction(((NombreEntier) exposant).valeur, 1).soustraire((Fraction) pow.exposant);
					else
						exposant = new Parenthese(exposant, new NombreEntier(-1));
					return this;
				}
				if (exposant instanceof Fraction) {
					if (pow.exposant instanceof NombreEntier)
						((Fraction) exposant).soustraire((NombreEntier) pow.exposant);
					else if (pow.exposant instanceof Fraction)
						((Fraction) exposant).soustraire((Fraction) pow.exposant);
					else
						exposant = new Parenthese(exposant, new NombreEntier(-1));
					return this;
				}
			}
		}
		return new Division(this, t);
	}
	
	@Override
	public Puissance negatif() {
		n = n.negatif();
		return this;
	}
	
	@Override
	public Term simplifier() {
		n = n.simplifier();
		exposant = exposant.simplifier();
		if (exposant instanceof NombreEntier) {
			int p = ((NombreEntier) exposant).valeur;
			if (p == 0)
				return new NombreEntier(1);
			if (p == 1)
				return n;
			if (n instanceof NombreEntier) {
				int nValue = ((NombreEntier) n).valeur;
				double result = Math.pow(nValue, p);// on calcule la valeur
				int integerResult = (int) result;
				return integerResult == result ? new NombreEntier(integerResult) : this;// on ne renvoie integerResult
																						// que si la valeur est exacte
			}
			if (n instanceof Fraction) {
				Fraction frac = (Fraction) n;
				Fraction result = frac.clone();
				for (int i = 0; i < p; i++) {
					result.multiplier(frac);
				}
				return result;
			}
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Power: " + n + "^" + exposant;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Puissance) {
			Puissance p = (Puissance) obj;
			return n.equals(p.n) && exposant.equals(p.exposant);
		}
		return false;
	}
	
}
