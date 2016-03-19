package fr.calculator.parser;

public class Multiplication implements Term {
	
	public Term a, b;
	
	public Multiplication(Term a, Term b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Term negate() {
		a = a.negate();
		return this;
	}
	
	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}
	
	@Override
	public Term simplify() {
		a = a.simplify();
		b = b.simplify();
		return simplify(a, b, true);
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
	private Term simplify(final Term a, final Term b, final boolean mayChangeOrder) {
		if (a instanceof IntegerTerm) {
			IntegerTerm n = (IntegerTerm) a;
			if (b instanceof IntegerTerm) {// n*n2
				IntegerTerm n2 = (IntegerTerm) b;
				return new IntegerTerm(n.value * n2.value);
			}
			if (b instanceof Fraction) {// n*fraction
				Fraction fraction = (Fraction) b;
				return fraction.multiply(n).simplify();
			}
			if (b instanceof Multiplication) {// n(a*b)
				Multiplication m = (Multiplication) b;
				if (m.a instanceof IntegerTerm) {// n(n2*b)
					IntegerTerm n2 = (IntegerTerm) m.a;
					return new Multiplication(new IntegerTerm(n.value * n2.value), m.b);
				}
				if (m.b instanceof IntegerTerm) {// n(a*n2)
					IntegerTerm n2 = (IntegerTerm) m.b;
					return new Multiplication(new IntegerTerm(n.value * n2.value), m.a);
				}
				if (m.a instanceof Fraction) {// n(fraction*b)
					Fraction fraction = (Fraction) m.a;
					return new Multiplication(fraction.multiply(n).simplify(), m.b);
				}
				if (m.b instanceof Fraction) {// n(a*fraction)
					Fraction fraction = (Fraction) m.b;
					return new Multiplication(fraction.multiply(n).simplify(), m.a);
				}
			}
		} else if (a instanceof Fraction) {
			Fraction fraction = (Fraction) a;
			if (b instanceof IntegerTerm) {// fraction*n
				IntegerTerm n = (IntegerTerm) b;
				return fraction.multiply(n).simplify();
			}
			if (b instanceof Fraction) {// fraction*fraction
				Fraction fraction2 = (Fraction) b;
				return fraction.multiply(fraction2).simplify();
			}
			if (b instanceof Multiplication) {// fraction(a*b)
				Multiplication m = (Multiplication) b;
				if (m.a instanceof IntegerTerm) {// fraction(n*b)
					IntegerTerm n = (IntegerTerm) m.a;
					return new Multiplication(fraction.multiply(n).simplify(), m.b);
				}
				if (m.b instanceof IntegerTerm) {// fraction(a*n)
					IntegerTerm n = (IntegerTerm) m.b;
					return new Multiplication(fraction.multiply(n).simplify(), m.a);
				}
				if (m.a instanceof Fraction) {// fraction(fraction2*b)
					Fraction fraction2 = (Fraction) m.a;
					return new Multiplication(fraction.multiply(fraction2).simplify(), m.b);
				}
				if (m.b instanceof Fraction) {// fraction(a*fraction2)
					Fraction fraction2 = (Fraction) m.b;
					return new Multiplication(fraction.multiply(fraction2).simplify(), m.a);
				}
			}
		}
		return mayChangeOrder ? simplify(b, a, false) : this;// Si ça n'a pas déjà été fait, on essaie en inversant a et
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
