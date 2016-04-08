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
	
	public static List<Term> simplifier(Term... termes) {
		return simplifier(Arrays.asList(termes));
	}
	
	public static List<Term> simplifier(List<Term> termes) {
		Fraction constante = new Fraction(0, 1);// somme de toutes les constantes
		List<Term> simplifiés = new ArrayList<>();
		for (Term t : termes) {
			t = t.simplifier();
			if (t instanceof Parenthese) {// parenthèse seule -> on développe
				for (Term pt : ((Parenthese) t).termes) {
					pt = pt.simplifier();
					if (pt instanceof NombreEntier) {
						constante.ajouter((NombreEntier) t);
					} else if (pt instanceof Fraction) {
						constante.ajouter((Fraction) t);
					} else {
						simplifiés.add(pt);
					}
				}
			} else if (t instanceof NombreEntier) {
				constante.ajouter((NombreEntier) t);
			} else if (t instanceof Fraction) {
				constante.ajouter((Fraction) t);
			} else {
				simplifiés.add(t);
			}
		}
		simplifiés.add(constante);
		return simplifiés;
	}
	
}
