package fr.calculator.analyse;

public class Puissance implements Terme {

	public Terme n, exposant;

	public Puissance(Terme n, int exposant) {
		this(n, new Rationnel(exposant));
	}

	public Puissance(Terme n, Terme exposant) {
		this.n = n;
		this.exposant = exposant;
	}

	@Override
	public Terme inverser() {
		return new Division(new Rationnel(1), this);
	}

	public Terme multiplier(Terme t) {
		if (n.equals(t)) {
			if (exposant instanceof Rationnel) {
				((Rationnel) exposant).ajouter(1);
			} else {
				exposant = new Parenthese(exposant, new Rationnel(+1));
			}
			return this;
		} else if (t instanceof Puissance) {
			Puissance p = (Puissance) t;
			if (n.equals(p.n)) {// n^p1 * n^p2 = n^(p1+p2)
				if (exposant instanceof Rationnel) {
					if (p.exposant instanceof Rationnel) {
						((Rationnel) exposant).ajouter((Rationnel) p.exposant);
					} else {
						exposant = new Parenthese(exposant, new Rationnel(+1));
					}
					return this;
				}
			}
		}
		return new Multiplication(this, t);
	}

	public Terme diviser(Terme t) {
		if (n.equals(t)) {// n^p / n = n^(p-1)
			if (exposant instanceof Rationnel) {
				((Rationnel) exposant).soustraire(1);
			} else {
				exposant = new Parenthese(exposant, new Rationnel(-1));
			}
			return this;
		} else if (t instanceof Puissance) {
			Puissance p = (Puissance) t;
			if (n.equals(p.n)) {// n^p1 / n^p2 = n^(p1-p2)
				if (exposant instanceof Rationnel) {
					if (p.exposant instanceof Rationnel) {
						((Rationnel) exposant).soustraire((Rationnel) p.exposant);
					} else {
						exposant = new Parenthese(exposant, new Rationnel(-1));
					}
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
	public Terme simplifier() {
		n = n.simplifier();
		exposant = exposant.simplifier();
		if (exposant instanceof Rationnel) {
			Rationnel p = (Rationnel) exposant;
			if (p.num == 0) {//p vaut 0
				return new Rationnel(1);
			}
			if (p.num == p.denom) {//p vaut 1
				return n;
			}
			if (n instanceof Rationnel) {//q^p avec q rationnel
				Rationnel q = (Rationnel) n;
				if (p.denom == 1) {//q^p avec p entier -> calcul en valeur exacte
					Rationnel result = q.clone();
					for (int i = 0; i < p.num; i++) {
						result.multiplier(q);
					}
				} else {//q^p avec p non entier -> calcul en valeur approchée
					double result = Math.pow(q.num / q.denom, p.num / p.denom);
					return new Rationnel(result);
				}
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return n + "^" + exposant;
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
