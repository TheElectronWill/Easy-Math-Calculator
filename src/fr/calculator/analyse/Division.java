package fr.calculator.analyse;

public class Division implements Terme {

	public Terme a, b;

	public Division(Terme a, Terme b) {
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
		Terme temp = a;
		a = b;
		b = temp;
		return this;
	}

	@Override
	public Terme simplifier() {
		a = a.simplifier();
		b = b.simplifier();
		if (a.equals(b)) {
			return new Rationnel(1);
		}
		if (b instanceof Rationnel && ((Rationnel) b).num == ((Rationnel) b).denom) {//b vaut 1
			return a;
		}
		if (a instanceof Rationnel) {
			Rationnel q1 = (Rationnel) a;
			if (b instanceof Rationnel) {
				return q1.diviser((Rationnel) b);
			}
			if (b instanceof Multiplication) {
				Multiplication m = (Multiplication) b;
				if (m.a instanceof Rationnel) {
					return new Multiplication(new Division(a, m.a), m.b).simplifier();
				}
				if (m.b instanceof Rationnel) {
					return new Multiplication(new Division(a, m.b), m.a).simplifier();
				}
			} else if (b instanceof Division) {
				return new Multiplication(a, b.inverser()).simplifier();
			}
		} else if (a instanceof Puissance) {
			return ((Puissance) a).diviser(b).simplifier();
		} else if (b instanceof Puissance) {
			return new Puissance(a, 1).diviser((Puissance) b).simplifier();
		}
		return this;
	}

	@Override
	public String toString() {
		return "(" + a + ")/(" + b + ")";
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
