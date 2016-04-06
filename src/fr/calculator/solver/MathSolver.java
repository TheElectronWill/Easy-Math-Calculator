package fr.calculator.solver;
import java.util.List;
import fr.calculator.Exponentielle;
import fr.calculator.parser.Fraction;
import fr.calculator.parser.IntegerTerm;
import fr.calculator.parser.Multiplication;
import fr.calculator.parser.Term;
public class MathSolver {
	public static String solver (List<Term> gauche, List<Term> droit) {
			int fact1 = 0, fact11 = 0, fact111 = 0, fact2 = 0, fact22 = 0, fact222 = 0, cons1 = 0, cons11 = 0, cons111 = 0, cons2 = 0, cons22 = 0, cons222 = 0, valeur = 0, compt = 0, num1 = 0, denom1 = 0;
			double solution = 0;
			if (gauche == droit) {
				System.out.print("L'égalité est toujours vraie");
			}
			Term X = gauche.get(0);
			Term X2 = droit.get(0);
			Term Y = gauche.get(1);
			Term Y2 = droit.get(1);
			if ((X instanceof IntegerTerm || X instanceof Fraction) && (X2 instanceof IntegerTerm || X2 instanceof Fraction) && Y.equals("") && Y2.equals("")) {
				System.out.print("Vous devez utiliser la variable x.");
			}	
			if (X instanceof Exponentielle && X2 instanceof Exponentielle) {
				Exponentielle e = (Exponentielle) X, e2 = (Exponentielle) X2;
				Term a = e.exp, b = e2.exp;
				if (a instanceof IntegerTerm) {
					solution = ((IntegerTerm)a).value;
				}
				else {
					solution = ((IntegerTerm)b).value;
				}
			}
			else if (X instanceof Exponentielle) {
				Exponentielle e = (Exponentielle) X;
				Term a = e.exp;
				if (a instanceof IntegerTerm) {
					solution = Math.exp(((IntegerTerm)a).value);
				}
				else {
					solution = Math.log(((IntegerTerm)X2).value);
				}
			}
			else if (X2 instanceof Exponentielle) {
				Exponentielle e2 = (Exponentielle) X2;
				Term b = e2.exp;
				if (X instanceof IntegerTerm) {
					solution = Math.log(((IntegerTerm)b).value);
				}
				else {
					solution = Math.exp(((IntegerTerm)X).value);
				}
			}
			if (X instanceof Logarithme && X2 instanceof Logarithme) {
				Logarithme l = (Logarithme) X, l2 = (Logarithme) X2;
				Term a = l.log, b = l2.log;
				if (a instanceof IntegerTerm) {
					solution = ((IntegerTerm)a).value;
				}
				else {
					solution = ((IntegerTerm)b).value;
				}
			}
			else if (X instanceof Logarithme) {
				Logarithme l = (Logarithme) X;
				Term a = l.log;
				if (a instanceof IntegerTerm) {
					solution = Math.log(((IntegerTerm)a).value);
				}
				else {
					solution = Math.exp(((IntegerTerm)X2).value);
				}
			}
			else if (X2 instanceof Logarithme) {
				Logarithme l2 = (Logarithme) X2;
				Term b = l2.log;
				if (X instanceof IntegerTerm) {
					solution = Math.exp(((IntegerTerm)X).value);
				}
				else {
					solution = Math.log(((IntegerTerm)b).value);
				}
			}
			if (X instanceof Cosinus && X2 instanceof Cosinus) {
				Cosinus c = (Cosinus) X, c2 = (Cosinus) X2;
				Term a = c.cos, b = c2.cos;
				if (a instanceof IntegerTerm) {
					solution = ((IntegerTerm)a).value;
				}
				else {
					solution = ((IntegerTerm)b).value;
				}
			}
			else if (X instanceof Cosinus) {
				Cosinus c = (Cosinus) X;
				Term a = c.cos;
				if (a instanceof IntegerTerm) {
					solution = Math.cos(((IntegerTerm)a).value);
				}
				else {
					solution = Math.acos(((IntegerTerm)X2).value);
				}
			}
			else if (X2 instanceof Cosinus) {
				Cosinus c2 = (Cosinus) X2;
				Term b = c2.cos;
				if (X instanceof IntegerTerm) {
					solution = Math.acos(((IntegerTerm)X).value);
				}
				else {
					solution = Math.cos(((IntegerTerm)b).value);
				}
			}
			if (X instanceof Sinus && X2 instanceof Sinus) {
				Sinus s = (Sinus) X, s2 = (Sinus) X2;
				Term a = s.sin, b = s2.sin;
				if (a instanceof IntegerTerm) {
					solution = ((IntegerTerm)a).value;
				}
				else {
					solution = ((IntegerTerm)b).value;
				}
			}
			else if (X instanceof Sinus) {
				Sinus s = (Sinus) X;
				Term a = s.sin;
				if (a instanceof IntegerTerm) {
					solution = Math.sin(((IntegerTerm)a).value);
				}
				else {
					solution = Math.asin(((IntegerTerm)X2).value);
				}
			}
			else if (X2 instanceof Sinus) {
				Sinus s2 = (Sinus) X2;
				Term b = s2.sin;
				if (X instanceof IntegerTerm) {
					solution = Math.asin(((IntegerTerm)X).value);
				}
				else {
					solution = Math.sin(((IntegerTerm)b).value);
				}
			}		
			else {
				int num = 0, denom = 0;
				if (X instanceof Multiplication) {
					Multiplication m = (Multiplication) X;
					Term a = m.a, b = m.b;
					if (a instanceof IntegerTerm) {
						fact1 = ((IntegerTerm)a).value;				
					}
					else if (a instanceof Fraction) {
						fact11 = ((Fraction)a).numerator;
						fact111 = ((Fraction)a).denominator;
					}
					if (b instanceof IntegerTerm) {
						fact1 = ((IntegerTerm)b).value;				
					}
					else if (b instanceof Fraction) {
						fact11 = ((Fraction)b).numerator;
						fact111 = ((Fraction)b).denominator;
					}
				}
				else {
					cons1 = ((IntegerTerm)X).value;
				}
				if (X2 instanceof Multiplication) {
					Multiplication m2 = (Multiplication) X2;
					Term a = m2.a, b = m2.b;
					if (a instanceof IntegerTerm) {
						fact2 = ((IntegerTerm)a).value;				
					}
					else if (a instanceof Fraction ){
						fact22 = ((Fraction)a).numerator;
						fact222 = ((Fraction)a).denominator;
					}
					if (b instanceof IntegerTerm) {
						fact2 = ((IntegerTerm)b).value;				
					}
					else if (b instanceof Fraction ){
						fact22 = ((Fraction)b).numerator;
						fact222 = ((Fraction)b).denominator;
					}
				}
				else {
					cons2 = ((IntegerTerm)X).value;
				}
				if (Y instanceof Multiplication) {
					Multiplication m3 = (Multiplication) Y;
					Term a = m3.a, b = m3.b;
					if (a instanceof IntegerTerm) {
						fact1 = ((IntegerTerm)a).value;				
					}
					else if (a instanceof Fraction ){
						fact11 = ((Fraction)a).numerator;
						fact111 = ((Fraction)a).denominator;
					}
					if (b instanceof IntegerTerm) {
						fact1 = ((IntegerTerm)b).value;				
					}
					else if (b instanceof Fraction ){
						fact11 = ((Fraction)b).numerator;
						fact111 = ((Fraction)b).denominator;
					}
				}
				else if (Y instanceof IntegerTerm){
					cons1 = ((IntegerTerm)X).value;
				}
				else {
					cons1 = 0;
				if (Y2 instanceof Multiplication) {
					Multiplication m4 = (Multiplication) Y2;
					Term a = m4.a, b = m4.b;
					if (a instanceof IntegerTerm) {
						fact2 = ((IntegerTerm)a).value;				
					}
					else if (a instanceof Fraction ){
						fact22 = ((Fraction)a).numerator;
						fact222 = ((Fraction)a).denominator;
					}
					if (b instanceof IntegerTerm) {
						fact2 = ((IntegerTerm)b).value;				
					}
					else if (b instanceof Fraction ){
						fact22 = ((Fraction)b).numerator;
						fact222 = ((Fraction)b).denominator;
					}
				}
				else if (Y2 instanceof IntegerTerm){
					cons2 = ((IntegerTerm)X).value;
				}
				else {
					cons2 = 0;
				}
				if (fact1 != 0 && fact2 != 0) {
					denom = fact1 - fact2;
				}
				else if (fact1 != 0){
					denom = fact1*fact222 - fact22;
				}
				else {
					denom = fact2*fact111 - fact11;
				}
				if (cons1 != 0 && cons2 != 0) {
					num = cons1 - cons2;
				}
				else if (fact1 != 0){
					num = cons1*cons222 - cons22;
				}
				else {
					num = cons2*cons111 - cons11;
				}
				Fraction frac = new Fraction(num, denom);
				Term simplifié = frac.simplify();
				if (simplifié instanceof IntegerTerm) {
					solution = (((IntegerTerm)simplifié).value);
				}
				else {
					num1 = ((Fraction)simplifié).numerator;
					denom1 = ((Fraction)simplifié).denominator;
				}
			}
			System.out.print("La solution à cette équation est: ");
			if (num1 == 0 && denom1 == 0) {	
				System.out.print(solution);
			}
			else {
				System.out.print(num1);
				System.out.print("/");
				System.out.print(denom1);
			}
			System.out.print(".");
		}
	}
}
