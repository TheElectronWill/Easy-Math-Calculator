package fr.calculator.analyse;

public class Multiplication implements Terme {

	public Terme a, b;

	public Multiplication(Terme... termes) {
		if (termes.length < 3) {
			throw new IllegalArgumentException("Il faut au moins 3 termes");
		}
		Multiplication m = new Multiplication(termes[0], termes[1]);
		for (int i = 2; i < termes.length - 1; i++) {
			m = new Multiplication(m, termes[i]);
		}
		a = termes[termes.length - 1];
		b = m;
	}

	public Multiplication(Terme a, Terme b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public Terme negatif() {
		a = a.negatif();
		return this;
	}

	@Override
	public Terme inverser() {
		return new Division(new Rationnel(1), this);
	}

	@Override
	public Terme simplifier() {
		a = a.simplifier();
		b = b.simplifier();
		return simplifier(a, b, true);
	}

	/** Simplifie cette multiplication. Les paramètres a et b sont présents uniquement pour pouvoir en changer
	 * l'ordre, c'est-à-dire pour pouvoir mettre b en premier et a en deuxième. Ils ne sont ni modifiés ni
	 * remplacés par d'autres termes.
	 *
	 * @param a le premier terme
	 * @param b le deuxième terme
	 * @param mayChangeOrder true si l'ordre de a et b peut être changé.
	 * @return une simplification de cette multiplication, ou cette multiplication si elle ne peut pas être
	 * simplifiée. */
	private Terme simplifier(final Terme a, final Terme b, final boolean mayChangeOrder) {
		if (a instanceof Variable && b instanceof Variable) {
			return new Puissance(a, 2);
		}
		if (a instanceof Fonction && b instanceof Fonction) {
			Fonction fa = (Fonction) a, fb = (Fonction) b;
			if (fa.param.equals(fb.param)) {
				return new Puissance(fa, 2);
			}
			return this;//non simplifiable
		}
		if (a instanceof Puissance) {
			return ((Puissance) a).multiplier(b).simplifier();
		}
		if (a instanceof Rationnel) {
			Rationnel q1 = (Rationnel) a;
			if (b instanceof Rationnel) {
				Rationnel q2 = (Rationnel) b;
				return q1.multiplier(q2).simplifier();
			}
			if (b instanceof Multiplication) {
				Multiplication m = (Multiplication) b;
				if (m.a instanceof Rationnel) {
					Rationnel q2 = (Rationnel) m.a;
					return new Multiplication(q1.multiplier(q2), m.b).simplifier();
				}
				if (m.b instanceof Rationnel) {
					Rationnel q2 = (Rationnel) m.b;
					return new Multiplication(q1.multiplier(q2), m.a).simplifier();
				}
			} else if (b instanceof Division) {
				Division d = (Division) b;//division c/d
				if (d.a instanceof Rationnel) {// a/(c/d) = a/c * d
					Rationnel q2 = (Rationnel) d.a;
					return new Multiplication(q1.diviser(q2), d.b).simplifier();
				}
				if (d.b instanceof Rationnel) {// a/(c/d) = a*d / c
					Rationnel q2 = (Rationnel) d.b;
					return new Division(q1.multiplier(q2), d.a).simplifier();
				}
			}
		}
		return mayChangeOrder ? simplifier(b, a, false) : this;// Si ça n'a pas déjà été fait, on essaie en inversant a et b.
	}

	@Override
	public String toString() {
		if (a instanceof Rationnel && b instanceof Rationnel && ((Rationnel) a).denom == 1 && ((Rationnel) b).denom == 1) {
			return a + "*" + b;
		}
		return "(" + a + ")*(" + b + ")";
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
