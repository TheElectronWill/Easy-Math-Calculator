package fr.calculator.solver;
import java.util.List;
import fr.calculator.SimplificationFraction;
import fr.calculator.parser.*;
public class MathSolver {
	public static String solver (List<Term> gauche, List<Term> droit) {
			int fact = 0, fact1 = 0, cons1 = 0, fact2 = 0, cons2 = 0, valeur = 0, compt = 0, num1 = 0, denom1 = 0;
			double solution = 0;
			if (gauche == droit) {
				System.out.print("L'égalité est toujours vraie.");
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
					solution = a;
				}
				else {
					solution = b;
				}
			}
			else if (X instanceof Exponentielle) {
				Exponentielle e = (Exponentielle) X;
				Term a = e.exp;
				if (a instanceof IntegerTerm) {
					solution = Math.exp(a);
				}
				else {
					solution = Math.log(b);
				}
			}
			else if (X2 instanceof Exponentielle) {
				Exponentielle e2 = (Exponentielle) X2;
				Term b = e2.exp;
				if (a instanceof IntegerTerm) {
					solution = Math.log(a);
				}
				else {
					solution = Math.exp(b);
				}
			}
			if (X instanceof Logarithme && X2 instanceof Logarithme) {
				Logarithme l = (Logarithme) X, l2 = (Logarithme) X2;
				Term a = l.log, b = l2.log;
				if (a instanceof IntegerTerm) {
					solution = a;
				}
				else {
					solution = b;
				}
			}
			else if (X instanceof Logarithme) {
				Logarithme l = (Logarithme) X;
				Term a = l.log;
				if (a instanceof IntegerTerm) {
					solution = Math.log(a);
				}
				else {
					solution = Math.exp(b);
				}
			}
			else if (X2 instanceof Logarithme) {
				Logarithme l2 = (Logarithme) X2;
				Term b = l2.log;
				if (a instanceof IntegerTerm) {
					solution = Math.exp(a);
				}
				else {
					solution = Math.log(b);
				}
			}
			if (X instanceof Cosinus && X2 instanceof Cosinus) {
				Cosinus c = (Cosinus) X, c2 = (Cosinus) X2;
				Term a = c.cos, b = c2.cos;
				if (a instanceof IntegerTerm) {
					solution = a;
				}
				else {
					solution = b;
				}
			}
			else if (X instanceof Cosinus) {
				Cosinus c = (Cosinus) X;
				Term a = c.cos;
				if (a instanceof IntegerTerm) {
					solution = Math.cos(a);
				}
				else {
					solution = Math.arccos(b);
				}
			}
			else if (X2 instanceof Cosinus) {
				Cosinus c2 = (Cosinus) X2;
				Term b = c2.cos;
				if (a instanceof IntegerTerm) {
					solution = Math.arccos(a);
				}
				else {
					solution = Math.cos(b);
				}
			}
			if (X instanceof Sinus && X2 instanceof Sinus) {
				Sinus s = (Sinus) X, s2 = (Sinus) X2;
				Term a = s.sin, b = s2.sin;
				if (a instanceof IntegerTerm) {
					solution = a;
				}
				else {
					solution = b;
				}
			}
			else if (X instanceof Sinus) {
				Sinus s = (Sinus) X;
				Term a = s.sin;
				if (a instanceof IntegerTerm) {
					solution = Math.sin(a);
				}
				else {
					solution = Math.arcsin(b);
				}
			}
			else if (X2 instanceof Sinus) {
				Sinus s2 = (Sinus) X2;
				Term b = s2.sin;
				if (a instanceof IntegerTerm) {
					solution = Math.arcsin(a);
				}
				else {
					solution = Math.sin(b);
				}
			}		
			else {
				if (X instanceof Multiplication) {
					Multiplication m = (Multiplication) X;
					Term a = m.a, b = m.b;
					if (a instanceof IntegerTerm) {
						fact1 = ((IntegerTerm)a).value;				
					}
					else {
						fact1 = ((IntegerTerm)b).value;			 
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
					else {
						fact2 = ((IntegerTerm)b).value;			 
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
					else {
						fact1 = ((IntegerTerm)b).value;			 
					}
				}
				else if (Y instanceof IntegerTerm){
					cons1 = ((IntegerTerm)X).value;
				}
				else {
					cons1 = 0;
				}
				if (Y2 instanceof Multiplication) {
					Multiplication m4 = (Multiplication) Y2;
					Term a = m4.a, b = m4.b;
					if (a instanceof IntegerTerm) {
						fact2 = ((IntegerTerm)a).value;				
					}
					else {
						fact2 = ((IntegerTerm)b).value;			 
					}
				}
				else if (Y2 instanceof IntegerTerm){
					cons2 = ((IntegerTerm)X).value;
				}
				else {
					cons2 = 0;
				}
				int denom = fact1 - fact2, num = cons1 - cons2;
				Fraction frac = new Fraction(num, denom);
				Term simplifié = frac.simplify();
				if (simplifié instanceof IntegerTerm) {
					String solution = String.valueOf(((IntegerTerm)simplifié).value);
				}
				else {
					String num1 = String.valueOf(((IntegerTerm)simplifié.numerator).value);
					String denom1 = String.valueOf(((IntegerTerm)simplifié.denominator).value);
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