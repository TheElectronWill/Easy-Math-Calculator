package fr.calculator.resolution;
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
public class MathSolveur {
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
						Term a = ((Exponentielle)X).a, b = ((Exponentielle)X2).a;
						X = a;
						X2 = b;
					}
					else if (X instanceof Exponentielle) {
						Term a = ((Exponentielle)X).a;
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
						Term b = ((Exponentielle)X2).a;
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
						Term a = ((Logarithme)X).a, b = ((Logarithme)X2).a;
						X = a;
						X2 = b;
					}
					else if (X instanceof Logarithme) {
						Term a = ((Logarithme)X).a;
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
						Term b = ((Logarithme)X2).a;
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
						Term a = ((Cosinus)X).a, b = ((Cosinus)X2).a;
						X = a;
						X2 = b;
					}
					else if (X instanceof Cosinus) {
						Term a = ((Cosinus)X).a;
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
						Term b = ((Cosinus)X2).a;
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
						Term a = ((Sinus)X).a, b = ((Sinus)X2).a;
						X = a;
						X2 = b;
					}
					else if (X instanceof Sinus) {
						Term a = (Sinus)X).a;
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
						Term b = ((Sinus)X2).a;
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
				Term a = ((Multiplication)X).a, b = ((Multiplication)X).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
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
					Term a = ((Power)b).a, b = ((Power)b).b;
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
				Term a = ((Multiplication)X2).a, b = ((Multiplication)X2).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
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
					Term a = ((Power)b).a, b = ((Power)b).b;
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
				Term a = ((Miultiplication)Y).a, b = ((Multiplication)Y).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
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
					Term a = ((Power)b).a, b = ((Power)b).b;
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
				Term a = ((Multiplication)Y2).a, b = ((Multiplication)Y2).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
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
					Term a = ((Power)b).a, b = ((Power)b).b;
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
				Term a = ((Multiplication)Z).a, b = ((Multiplication)Z).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
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
					Term a = ((Power)b).a, b = ((Power)b).b;
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
				Term a = ((Multiplication)Z2).a, b = ((Multiplication)Z2).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
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
					Term a = ((Power)b).a, b = ((Power)b).b;
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
				else if (surfact2 != 0) {
					a =  surfact11 - surfact2*surfact111;
				}
				else {
					a = (surfact11*surfact222 - surfact22*surfact111) / (surfact111*surfact222);
				}
				if (fact1 != 0 && fact2 != 0) {
					b = fact1 - fact2;
				}
				else if (fact1 != 0){
					b = fact1*fact222 - fact22;
				}
				else if (fact2 != 0){
					b = fact11 - fact2*fact111;
				}
				else {
					a = (fact11*fact222 - fact22*fact111) / (fact111*fact222);
				}
				if (cons1 != 0 && cons2 != 0) {
					c = cons1 - cons2;
				}
				else if (cons1 != 0){
					c = cons1*cons222 - cons22;
				}
				else {
					c = cons11 - cons2*cons111;
				}
				else {
					a = (cons11*cons222 - cons22*cons111) / (cons111*cons222);
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
				System.out.print("La solution à cette équation est: ");
				Term simplifié = frac1.simplify();
				if (simplifié instanceof IntegerTerm) {
					solution1 = (((IntegerTerm)simplifié).value);
					System.out.print(solution1);
					System.out.print(".");
				}
				else {
					((Fraction)simplifié).toString;
				}
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
				System.out.print(((re1)a).value);
				System.out.print("i+(");
				System.out.print(((im1)a).value);
				System.out.print(") et ");
				System.out.print(((re2)a).value);
				System.out.print("i+(");
				System.out.print(((im2)a).value);
				System.out.print(").");
			}
		}
}
