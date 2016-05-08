package fr.calculator.analyse;

import fr.calculator.resolution.MathSimplifieur;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Parenthese implements Terme {

	public List<Terme> termes;

	public Parenthese(List<Terme> termes) {
		this.termes = termes;
	}

	public Parenthese(Terme... termes) {
		this.termes = Arrays.asList(termes);
	}

	@Override
	public Parenthese negatif() {
		// Multiplie chaque terme par -1
		ListIterator<Terme> it = termes.listIterator();
		while (it.hasNext()) {
			Terme t = it.next();
			it.set(t.negatif());
		}
		return this;
	}

	@Override
	public Terme inverser() {
		return new Division(new Rationnel(1), this);
	}

	@Override
	public Terme simplifier() {
		switch (termes.size()) {
			case 0:
				return new Rationnel(0);
			case 1:
				return termes.get(0).simplifier();
			default:
				termes = MathSimplifieur.simplifierTermes(termes);
				return termes.size() == 1 ? termes.get(0) : this;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Iterator<Terme> it = termes.iterator();
		while (it.hasNext()) {
			Terme t = it.next();
			sb.append(t);
			if (it.hasNext()) {
				sb.append(" + ");
			}
		}
		sb.append(']');
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Parenthese) && termes.equals(((Parenthese) obj).termes);
	}

}
