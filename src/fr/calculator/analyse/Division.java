package fr.calculator.analyse;

public class Division implements Term {
	
	public Term a, b;
	
	public Division(Term a, Term b) {
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
		Term temp = a;
		a = b;
		b = temp;
		return this;
	}
	
	@Override
	public Term simplifier() {
		a = a.simplifier();
		b = b.simplifier();
		if (a instanceof NombreEntier) {
			NombreEntier n = (NombreEntier) a;
			if (b instanceof NombreEntier) {// n/n2
				NombreEntier n2 = (NombreEntier) b;
				return new Fraction(n.valeur, n2.valeur).simplifier();
			}
			if (b instanceof Fraction) {// n/(num/den) = (n*den)/num
				Fraction fraction = (Fraction) b;
				return new Fraction(n.valeur * fraction.denom, fraction.num).simplifier();
			}
		} else if (a instanceof Fraction) {
			Fraction fraction = (Fraction) b;
			if (b instanceof NombreEntier) {// (num/den)/n
				NombreEntier n = (NombreEntier) b;
				return fraction.diviser(n).simplifier();
			}
			if (b instanceof Fraction) {// (num/den)/(num2/den2) = (num*den2)/(den*num2)
				Fraction fraction2 = (Fraction) b;
				return fraction.diviser(fraction2).simplifier();
			}
		} else if (a instanceof Puissance) {
			Puissance pow = (Puissance) a;
			return pow.diviser(b).simplifier();
		}
		if (b instanceof Puissance) {
			Puissance powB = (Puissance) b;
			Puissance powA = new Puissance(a, new NombreEntier(1));
			return powA.diviser(powB).simplifier();
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Division: (" + a + ")/(" + b + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Division) {
			Division div = (Division) obj;
			return a.equals(div.a) && b.equals(div.b);
		}
		return false;
	}
	
}
