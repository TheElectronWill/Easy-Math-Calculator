package fr.calculator.analyse;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import fr.calculator.resolution.MathSimplifieur;

public class Parenthese implements Term {

	public List<Term> termes;

	public Parenthese(List<Term> termes) {
		this.termes = termes;
	}

	public Parenthese(Term... termes) {
		this.termes = Arrays.asList(termes);
	}

	@Override
	public Parenthese negatif() {
		// Multiplie chaque terme par -1
		ListIterator<Term> it = termes.listIterator();
		while (it.hasNext()) {
			Term t = it.next();
			it.set(t.negatif());
		}
		return this;
	}

	@Override
	public Term inverser() {
		return new Division(new NombreEntier(1), this);
	}

	@Override
	public Term simplifier() {
		if (termes.isEmpty())
			return new NombreEntier(0);
		if (termes.size() == 1)
			return termes.get(0).simplifier();

		termes = MathSimplifieur.simplify(termes);
		boolean canCalculate = true;
		for (Term t : termes) {
			if (!(t instanceof NombreEntier || t instanceof Fraction)) {
				canCalculate = false;
				break;
			}
		}
		if (canCalculate) {
			int numerator = 0, denominator = 1;
			for (Term t : termes) {
				if (t instanceof NombreEntier) {
					numerator += ((NombreEntier) t).valeur * denominator;
				} else if (t instanceof Fraction) {
					Fraction f = (Fraction) t;
					numerator = (numerator * f.denom) + (f.num * denominator);
					denominator *= f.denom;
				}
			}
			return denominator == 1 ? new NombreEntier(numerator) : new Fraction(numerator, denominator);
		}
		return this;
	}

	@Override
	public String toString() {
		return termes.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Parenthese) && termes.equals(((Parenthese) obj).termes);
	}

}
