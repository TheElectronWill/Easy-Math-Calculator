package fr.calculator.solver;
import java.util.List;
import fr.calculator.Exponentielle;
import fr.calculator.parser.Fraction;
import fr.calculator.parser.IntegerTerm;
import fr.calculator.parser.Multiplication;
import fr.calculator.parser.Term;
import fr.calculator.Exponentielle;
import fr.calculator.parser.Fraction;
import fr.calculator.parser.IntegerTerm;
import fr.calculator.parser.Multiplication;
import fr.calculator.parser.Term;
public class MathSolver {
	public static String solver (List<Term> gauche, List<Term> droit) {
			int fact1 = 0, fact11 = 0, fact111 = 0, fact2 = 0, fact22 = 0, fact222 = 0;
			int cons1 = 0, cons11 = 0, cons111 = 0, cons2 = 0, cons22 = 0, cons222 = 0;
			int surfact1 = 0, surfact11 = 0, surfact111 = 0, surfact2 = 0, surfact22 = 0, surfact222 = 0;
			int valeur = 0, compt = 0, a = 0, b = 0, c = 0, num1 = 0, denom1 = 0, num2 = 0, denom2 = 0, delta = 0, 
			double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
			Fraction frac1 = new Fraction(0, 0), frac2 = new Fraction(0, 0), re1 = new Fraction(0, 0) re2 = new Fraction(0, 0);
			if (gauche == droit) {
				System.out.print("L'égalité est toujours vraie.");
			}
			Term X = gauche.get(0);
			Term X2 = droit.get(0);
			Term Y = gauche.get(1);
			Term Y2 = droit.get(1);
			Term Z = gauche.get(2);
			Term Z2 = droit.get(2);
			if (X instanceof Fonction || X2 instanceof Fonction) {
				if (Y.equals("") && Y2.equals("") && Z.equals("") && Z2.equals("")) {
					if ((X instanceof IntegerTerm || X instanceof Fraction) && (X2 instanceof IntegerTerm || X2 instanceof Fraction)) {
						System.out.print("Vous devez utiliser la variable x.");
					}			
					if (X instanceof Exponentielle && X2 instanceof Exponentielle) {
						Exponentielle e = (Exponentielle) X, e2 = (Exponentielle) X2;
						Term a = e.exp, b = e2.exp;
						X = a;
						X2 = b;
					}
					else if (X instanceof Exponentielle) {
						Exponentielle e = (Exponentielle) X;
						Term a = e.exp;
						if (a instanceof IntegerTerm) {
							if (X2 instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.exp(((IntegerTerm)a).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.log(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2 instanceof Exponentielle) {
						Exponentielle e2 = (Exponentielle) X2;
						Term b = e2.exp;
						if (X instanceof IntegerTerm) {
							if (b instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.exp(((IntegerTerm)X).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.log(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
					if (X instanceof Logarithme && X2 instanceof Logarithme) {
						Logarithme l = (Logarithme) X, l2 = (Logarithme) X2;
						Term a = l.log, b = l2.log;
						X = a;
						X2 = b;
					}
					else if (X instanceof Logarithme) {
						Logarithme l = (Logarithme) X;
						Term a = l.log;
						if (a instanceof IntegerTerm) {
							if (X2 instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.log(((IntegerTerm)a).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.exp(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2 instanceof Logarithme) {
						Logarithme l2 = (Logarithme) X2;
						Term b = l2.log;
						if (X instanceof IntegerTerm) {
							if (b instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.log(((IntegerTerm)X).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.exp(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
					if (X instanceof Cosinus && X2 instanceof Cosinus) {
						Cosinus c = (Cosinus) X, c2 = (Cosinus) X2;
						Term a = c.cos, b = c2.cos;
						X = a;
						X2 = b;
					}
					else if (X instanceof Cosinus) {
						Cosinus c = (Cosinus) X;
						Term a = c.cos;
						if (a instanceof IntegerTerm) {
							if (X2 instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.cos(((IntegerTerm)a).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.acos(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2 instanceof Cosinus) {
						Cosinus c2 = (Cosinus) X2;
						Term b = c2.cos;
						if (X instanceof IntegerTerm) {
							if (b instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.cos(((IntegerTerm)X).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.acos(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
					if (X instanceof Sinus && X2 instanceof Sinus) {
						Sinus s = (Sinus) X, s2 = (Sinus) X2;
						Term a = s.sin, b = s2.sin;
						X = a;
						X2 = b;
					}
					else if (X instanceof Sinus) {
						Sinus s = (Sinus) X;
						Term a = s.sin;
						if (a instanceof IntegerTerm) {
							if (X2 instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.sin(((IntegerTerm)a).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.asin(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2 instanceof Sinus) {
						Sinus s2 = (Sinus) X2;
						Term b = s2.sin;
						if (X instanceof IntegerTerm) {
							if (b instanceof IntegerTerm) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = IntegerTerm(Math.sin(((IntegerTerm)X).value));
							}
						}
						else {
							if (X2 instanceof IntegerTerm) {
								X = a;
								X2 = IntegerTerm(Math.asin(((IntegerTerm)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
				}
				else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			}
				
			if (X instanceof Multiplication) {
				Multiplication m = (Multiplication) X;
				Term a = m.a, b = m.b;
				if (a instanceof Power) {
					Power p = (Power) a;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				else if (a instanceof Fraction) {
					fact11 = ((Fraction)a).numerator;
					fact111 = ((Fraction)a).denominator;					
				}
				else {
					fact1 = ((IntegerTerm)a).value;									
				}
				if (b instanceof Power) {
					Power p = (Power) b;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (b instanceof Fraction) {
					fact11 = ((Fraction)b).numerator;
					fact111 = ((Fraction)b).denominator;
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
				if (a instanceof Power) {
					Power p = (Power) a;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact22 = ((Fraction)b).numerator;
						surfact222 = ((Fraction)b).denominator;
					}
					else {
						surfact2 = ((IntegerTerm)b).value;
					}
				}	
				else if (a instanceof Fraction ){
					fact22 = ((Fraction)a).numerator;
					fact222 = ((Fraction)a).denominator;
				}
				else {
					fact2 = ((IntegerTerm)a).value;				
				}
				if (b instanceof Power) {
					Power p = (Power) b;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact22 = ((Fraction)b).numerator;
						surfact222 = ((Fraction)b).denominator;
					}
					else {
						surfact2 = ((IntegerTerm)b).value;
					}
				}
				
				else if (b instanceof Fraction ){
					fact22 = ((Fraction)b).numerator;
					fact222 = ((Fraction)b).denominator;
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
				if (a instanceof Power) {
					Power p = (Power) a;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact11 = ((Fraction)a).numerator;
					fact111 = ((Fraction)a).denominator;
				}
				else {
					fact1 = ((IntegerTerm)a).value;				
				}
				if (b instanceof Power) {
					Power p = (Power) b;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact11 = ((Fraction)b).numerator;
					fact111 = ((Fraction)b).denominator;
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
			if (Y2 instanceof Multiplication) {
				Multiplication m4 = (Multiplication) Y2;
				Term a = m4.a, b = m4.b;
				if (a instanceof Power) {
					Power p = (Power) a;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact22 = ((Fraction)a).numerator;
					fact222 = ((Fraction)a).denominator;
				}
				else {
					fact2 = ((IntegerTerm)a).value;				
				}
				if (b instanceof Power) {
					Power p = (Power) b;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact22 = ((Fraction)b).numerator;
					fact222 = ((Fraction)b).denominator;
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
			if (Z instanceof Multiplication) {
				Multiplication m4 = (Multiplication) Y;
				Term a = m4.a, b = m5.b;
				if (a instanceof Power) {
					Power p = (Power) a;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact11 = ((Fraction)a).numerator;
					fact111 = ((Fraction)a).denominator;
				}
				else {
					fact1 = ((IntegerTerm)a).value;				
				}
				if (b instanceof Power) {
					Power p = (Power) b;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact11 = ((Fraction)b).numerator;
					fact111 = ((Fraction)b).denominator;
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
			if (Z2 instanceof Multiplication) {
				Multiplication m5 = (Multiplication) Y2;
				Term a = m5.a, b = m5.b;
				if (a instanceof Power) {
					Power p = (Power) a;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact22 = ((Fraction)a).numerator;
					fact222 = ((Fraction)a).denominator;
				}
				else {
					fact2 = ((IntegerTerm)a).value;				
				}
				if (b instanceof Power) {
					Power p = (Power) b;
					Term a = p.a, b = p.b;
					if (((IntegerTerm)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((IntegerTerm)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact22 = ((Fraction)b).numerator;
					fact222 = ((Fraction)b).denominator;
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
			
			
			
			
				
				if (surfact1 != 0 && surfact2 != 0) {
					a = surfact1 - surfact2;
				}
				else if (surfact1 != 0){
					a = surfact1*surfact222 - surfact22;
				}
				else {
					a = surfact2*surfact111 - surfact11;
				}
				if (fact1 != 0 && fact2 != 0) {
					b = fact1 - fact2;
				}
				else if (fact1 != 0){
					b = fact1*fact222 - fact22;
				}
				else {
					b = fact2*fact111 - fact11;
				}
				if (cons1 != 0 && cons2 != 0) {
					c = cons1 - cons2;
				}
				else if (cons1 != 0){
					c = cons1*cons222 - cons22;
				}
				else {
					c = cons2*cons111 - cons11;
				}
				if (a = 0) {
					frac1.set(c, b);
				}
				else {
					delta = b*b - 4*a*c;
					if (delta == 0) {
						frac1.set(-b, 2a);
					}
					else if (delta > 0) {
						solution1 = (-b - Math.sqrt(delta)) / 2a;
						solution2 = (-b + Math.sqrt(delta)) / 2a;
					}
					else {
						re1.set(-b, 2a); 
						im1 = -Math.sqrt(delta) / 2a;
						re2.set(-b, 2a);
						im2 = Math.sqrt(delta) / 2a;
					}
				}
			}
			if (solution1 != 0 && solution == 0) {
				System.out.print("La solution à cette équation est: ");
				System.out.print(solution1);
				System.out.print(".");
			}
			else if (solution1 != 0) {
				System.out.print("Les solutions à cette équation sont: ");
				System.out.print(solution1);
				System.out.print(" et ");
				System.out.print(solution2);
				System.out.print(".");
			}
			else if (frac1.a != 0 && frac1.b != 0 && frac2.a == 0 && frac2.b == 0) {
				Term simplifié = frac1.simplify();
				if (simplifié instanceof IntegerTerm) {
					solution1 = (((IntegerTerm)simplifié).value);
				}
				else {
					num1 = ((Fraction)simplifié).numerator;
					denom1 = ((Fraction)simplifié).denominator;
				}
				System.out.print("La solution à cette équation est: ");
				System.out.print(num1);
				System.out.print("/");
				System.out.print(denom1);
				System.out.print(".");
			}
			else if (frac1.a != 0 && frac1.b != 0) {
				System.out.print("Les solutions à cette équation sont: ");
				Term simplifié = frac1.simplify();
				if (simplifié instanceof IntegerTerm) {
					solution1 = (((IntegerTerm)simplifié).value);
					System.out.print(solution1);
				}
				else {
					num1 = ((Fraction)simplifié).numerator;
					denom1 = ((Fraction)simplifié).denominator;	
					System.out.print(num1);
					System.out.print("/");
					System.out.print(denom1);
				}
				System.out.print(" et ");
				Term simplifié = frac2.simplify();
				if (simplifié instanceof IntegerTerm) {
					solution1 = (((IntegerTerm)simplifié).value);
					System.out.print(solution2);
				}
				else {
					num2 = ((Fraction)simplifié).numerator;
					denom2 = ((Fraction)simplifié).denominator;
					System.out.print(num2);
					System.out.print("/");
					System.out.print(denom2);
				}
				System.out.print(".");
			}
			else {
				System.out.print("Les solutions à cette équation sont: ");
				System.out.print(((re1).a).value);
				System.out.print(".");
				System.out.print(".");
				System.out.print(".");
			}
		}
}
