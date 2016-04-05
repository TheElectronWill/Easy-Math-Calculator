package fr.calculator.resolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.calculator.analyse.Fraction;
import fr.calculator.analyse.NombreEntier;
import fr.calculator.analyse.Parenthese;
import fr.calculator.analyse.Term;

/**
 * Utilitaire pour simplifier les expressions mathématiques.
 * 
 * @author Guillaume
 * 		
 */
public class MathSimplifieur {
	
	private MathSimplifieur() {}
	
	public static List<Term> simplify(Term... terms) {
		return simplify(Arrays.asList(terms));
	}
	
	public static List<Term> simplify(List<Term> terms) {
		Fraction constant = new Fraction(0, 1);// somme de toutes les constantes
		List<Term> simplifiedTerms = new ArrayList<>();
		for (Term t : terms) {
			t = t.simplifier();
			if (t instanceof Parenthese) {// parenthèse seule -> on développe
				for (Term pt : ((Parenthese) t).termes) {
					pt = pt.simplifier();
					if (pt instanceof NombreEntier) {
						constant.ajouter((NombreEntier) t);
					} else if (pt instanceof Fraction) {
						constant.ajouter((Fraction) t);
					} else {
						simplifiedTerms.add(pt);
					}
				}
			} else if (t instanceof NombreEntier) {
				constant.ajouter((NombreEntier) t);
			} else if (t instanceof Fraction) {
				constant.ajouter((Fraction) t);
			} else {
				simplifiedTerms.add(t);
			}
		}
		simplifiedTerms.add(constant.simplifier());
		return simplifiedTerms;
	}
	
}
