package fr.calculator.analyse;

public class Multiplication implements Term {
	
	public Term a, b;
	
	public Multiplication(Term a, Term b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Term negatif() {
		a = a.negatif();
		return this;
	}
	
	@Override
	public Term inverser() {
		return new Division(new NombreEntier(1), this);
	}
	
	@Override
	public Term simplifier() {
		a = a.simplifier();
		b = b.simplifier();
		return simplifier(a, b, true);
	}
	
	/**
	 * Simplifie cette multiplication. Les paramètres a et b sont présents uniquement pour pouvoir en changer l'ordre,
	 * c'est-à-dire pour pouvoir mettre b en premier et a en deuxième. Ils ne sont ni modifiés ni remplacés par d'autres
	 * termes.
	 * 
	 * @param a le premier terme
	 * @param b le deuxième terme
	 * @param mayChangeOrder true si l'ordre de a et b peut être changé.
	 * @return une simplification de cette multiplication, ou cette multiplication si elle ne peut pas être simplifiée.
	 */
	private Term simplifier(final Term a, final Term b, final boolean mayChangeOrder) {
		if (a instanceof NombreEntier) {
			NombreEntier n = (NombreEntier) a;
			if (b instanceof NombreEntier) {// n*n2
				NombreEntier n2 = (NombreEntier) b;
				return new NombreEntier(n.valeur * n2.valeur);
			}
			if (b instanceof Fraction) {// n*fraction
				Fraction fraction = (Fraction) b;
				return fraction.multiplier(n).simplifier();
			}
			if (b instanceof Multiplication) {// n(a*b)
				Multiplication m = (Multiplication) b;
				if (m.a instanceof NombreEntier) {// n(n2*b)
					NombreEntier n2 = (NombreEntier) m.a;
					return new Multiplication(new NombreEntier(n.valeur * n2.valeur), m.b);
				}
				if (m.b instanceof NombreEntier) {// n(a*n2)
					NombreEntier n2 = (NombreEntier) m.b;
					return new Multiplication(new NombreEntier(n.valeur * n2.valeur), m.a);
				}
				if (m.a instanceof Fraction) {// n(fraction*b)
					Fraction fraction = (Fraction) m.a;
					return new Multiplication(fraction.multiplier(n).simplifier(), m.b);
				}
				if (m.b instanceof Fraction) {// n(a*fraction)
					Fraction fraction = (Fraction) m.b;
					return new Multiplication(fraction.multiplier(n).simplifier(), m.a);
				}
			}
		} else if (a instanceof Fraction) {
			Fraction fraction = (Fraction) a;
			if (b instanceof NombreEntier) {// fraction*n
				NombreEntier n = (NombreEntier) b;
				return fraction.multiplier(n).simplifier();
			}
			if (b instanceof Fraction) {// fraction*fraction
				Fraction fraction2 = (Fraction) b;
				return fraction.multiplier(fraction2).simplifier();
			}
			if (b instanceof Multiplication) {// fraction(a*b)
				Multiplication m = (Multiplication) b;
				if (m.a instanceof NombreEntier) {// fraction(n*b)
					NombreEntier n = (NombreEntier) m.a;
					return new Multiplication(fraction.multiplier(n).simplifier(), m.b);
				}
				if (m.b instanceof NombreEntier) {// fraction(a*n)
					NombreEntier n = (NombreEntier) m.b;
					return new Multiplication(fraction.multiplier(n).simplifier(), m.a);
				}
				if (m.a instanceof Fraction) {// fraction(fraction2*b)
					Fraction fraction2 = (Fraction) m.a;
					return new Multiplication(fraction.multiplier(fraction2).simplifier(), m.b);
				}
				if (m.b instanceof Fraction) {// fraction(a*fraction2)
					Fraction fraction2 = (Fraction) m.b;
					return new Multiplication(fraction.multiplier(fraction2).simplifier(), m.a);
				}
			}
		}
		return mayChangeOrder ? simplifier(b, a, false) : this;// Si ça n'a pas déjà été fait, on essaie en inversant a et
																// b. Sinon on renvoie "this".
	}
	
	@Override
	public String toString() {
		return "Multiplication: (" + a + ")*(" + b + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Multiplication) {
			Multiplication m = (Multiplication) obj;
			return a.equals(m.a) && b.equals(m.b);
		}
		return false;
	}
	
}
