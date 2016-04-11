package fr.calculator.resolution;
import java.util.List;
import fr.calculator.analyse.Fonction;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Fraction;
import fr.calculator.analyse.Multiplication;
import fr.calculator.analyse.Term;
import fr.calculator.analyse.Fraction;
import fr.calculator.analyse.Multiplication;
import fr.calculator.analyse.Term;
public class MathSolveur {
	public static String solver (List<Term> gauche, List<Term> droit) {
			int fact1 = 0, fact11 = 0, fact111 = 0, fact2 = 0, fact22 = 0, fact222 = 0;
			int cons1 = 0, cons11 = 0, cons111 = 0, cons2 = 0, cons22 = 0, cons222 = 0;
			int surfact1 = 0, surfact11 = 0, surfact111 = 0, surfact2 = 0, surfact22 = 0, surfact222 = 0;
			int valeur = 0, compt = 0, u = 0, v = 0, w = 0, num1 = 0, denom1 = 0, num2 = 0, denom2 = 0, delta = 0; 
			double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
			Fraction frac1 = new Fraction(0, 0), frac2 = new Fraction(0, 0), re1 = new Fraction(0, 0), re2 = new Fraction(0, 0);
			Number decimal = 0, decimal2 = 0;
			if (gauche == droit) {
				System.out.print("L'égalité est toujours vraie.");
			}
			Term X = gauche.get(0);
			Term X2 = droit.get(0);
			Term Y = gauche.get(1);
			Term Y2 = droit.get(1);
			Term Z = gauche.get(2);
			Term Z2 = droit.get(2);
			if (Y.equals("") && Y2.equals("") && Z.equals("") && Z2.equals("") && X instanceof Number || X instanceof Fraction && (X2 instanceof Number || X2 instanceof Fraction)) {
				System.out.print("Vous devez utiliser la variable x.");
			}		
			if (X instanceof Fonction || X2 instanceof Fonction) {		
					Fonction fonction = (Fonction) X, fonction1 = (Fonction) X2;
					if (fonction.nom == NomFonction.EXPONENTIELLE && fonction1.nom == NomFonction.LOGARITHME_NEPERIEN) {
						Term a = fonction.t, b = fonction.t;
						X = a;
						X2 = b;
					}
					else if (fonction.nom == NomFonction.EXPONENTIELLE) {
						if (fonction.t instanceof Number) {
							Number a = (Number) fonction.t; 
							if (X instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								decimal = (Math.exp(a.doubleValue()));
							}
						}
						else {
							Term a = (Term) fonction.t;
							if (X2 instanceof Number) {
								decimal2 = (Math.log(((Number)X2).doubleValue()));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2.nom == exponentielle) {
						Term b = ((Exponentielle)X2).a;
						if (X instanceof Number) {
							if (b instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.exp(((Number)X).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.log(((Number)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
					if (X.nom == logarithme && X2.nom == logarithme) {
						Term a = ((Logarithme)X).a, b = ((Logarithme)X2).a;
						X = a;
						X2 = b;
					}
					else if (X.nom == logarithme) {
						Term a = ((Logarithme)X).a;
						if (a instanceof Number) {
							if (X2 instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.log(((Number)a).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.exp(((Number)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2.nom == logarithme) {
						Term b = ((Logarithme)X2).a;
						if (X instanceof Number) {
							if (b instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.log(((Number)X).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.exp(((Number)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
					if (X.nom == cosinus && X2.nom == cosinus) {
						Term a = ((Cosinus)X).a, b = ((Cosinus)X2).a;
						X = a;
						X2 = b;
					}
					else if (X.nom == cosinus) {
						Term a = ((Cosinus)X).a;
						if (a instanceof Number) {
							if (X2 instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.cos(((Number)a).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.acos(((Number)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2.nom == cosinus) {
						Term b = ((Cosinus)X2).a;
						if (X instanceof Number) {
							if (b instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.cos(((Number)X).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.acos(((Number)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}				
						}
					}
					if (X.nom == sinus && X2.nom == sinus) {
						Term a = ((Sinus)X).a, b = ((Sinus)X2).a;
						X = a;
						X2 = b;
					}
					else if (X.nom == sinus) {
						Term a = (Sinus)X).a;
						if (a instanceof Number) {
							if (X2 instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.sin(((Number)a).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.asin(((Number)X2).value));
							}
							else {
								System.out.print("La résolution de cette équation par ce logiciel est impossible.");
							}
						}
					}
					else if (X2.nom == sinus) {
						Term b = ((Sinus)X2).a;
						if (X instanceof Number) {
							if (b instanceof Number) {
								System.out.print("Vous devez utiliser la variable x.");
							}
							else {
								X = Number(Math.sin(((Number)X).value));
							}
						}
						else {
							if (X2 instanceof Number) {
								X = a;
								X2 = Number(Math.asin(((Number)X2).value));
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
			Multiplication multi = (Multiplication) X, multi2 = (Multiplication) X2;
			if (X instanceof Multiplication) {
				Term a = ((Multiplication)X).a, b = ((Multiplication)X).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				else if (a instanceof Fraction) {
					fact11 = ((Fraction)a).numerator;
					fact111 = ((Fraction)a).denominator;					
				}
				else {
					fact1 = ((Number)a).value;									
				}
				if (b instanceof Power) {
					Term a = ((Power)b).a, b = ((Power)b).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (b instanceof Fraction) {
					fact11 = ((Fraction)b).numerator;
					fact111 = ((Fraction)b).denominator;
				}
				else {
						fact1 = ((Number)b).value;									
				}
			}
			else {
				cons1 = ((Number)X).value;
			}
			if (X2 instanceof Multiplication) {
				Term a = ((Multiplication)X2).a, b = ((Multiplication)X2).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact22 = ((Fraction)b).numerator;
						surfact222 = ((Fraction)b).denominator;
					}
					else {
						surfact2 = ((Number)b).value;
					}
				}	
				else if (a instanceof Fraction ){
					fact22 = ((Fraction)a).numerator;
					fact222 = ((Fraction)a).denominator;
				}
				else {
					fact2 = ((Number)a).value;				
				}
				if (b instanceof Power) {
					Term a = ((Power)b).a, b = ((Power)b).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact22 = ((Fraction)b).numerator;
						surfact222 = ((Fraction)b).denominator;
					}
					else {
						surfact2 = ((Number)b).value;
					}
				}
				
				else if (b instanceof Fraction ){
					fact22 = ((Fraction)b).numerator;
					fact222 = ((Fraction)b).denominator;
				}
				else {
					fact2 = ((Number)b).value;				
				}
			}
			else {
				cons2 = ((Number)X).value;
			}
			
			if (Y instanceof Multiplication) {
				Term a = ((Miultiplication)Y).a, b = ((Multiplication)Y).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact11 = ((Fraction)a).numerator;
					fact111 = ((Fraction)a).denominator;
				}
				else {
					fact1 = ((Number)a).value;				
				}
				if (b instanceof Power) {
					Term a = ((Power)b).a, b = ((Power)b).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact11 = ((Fraction)b).numerator;
					fact111 = ((Fraction)b).denominator;
				}
				else {
					fact1 = ((Number)b).value;				
				}
			}
			else if (Y instanceof Number){
				cons1 = ((Number)X).value;
			}
			else {
				cons1 = 0;				
			if (Y2 instanceof Multiplication) {
				Term a = ((Multiplication)Y2).a, b = ((Multiplication)Y2).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact22 = ((Fraction)a).numerator;
					fact222 = ((Fraction)a).denominator;
				}
				else {
					fact2 = ((Number)a).value;				
				}
				if (b instanceof Power) {
					Term a = ((Power)b).a, b = ((Power)b).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact22 = ((Fraction)b).numerator;
					fact222 = ((Fraction)b).denominator;
				}
				else {
					fact2 = ((Number)b).value;				
				}
			}
			else if (Y2 instanceof Number){
				cons2 = ((Number)X).value;
			}
			else {
				cons2 = 0;
			}
			if (Z instanceof Multiplication) {
				Term a = ((Multiplication)Z).a, b = ((Multiplication)Z).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact11 = ((Fraction)a).numerator;
					fact111 = ((Fraction)a).denominator;
				}
				else {
					fact1 = ((Number)a).value;				
				}
				if (b instanceof Power) {
					Term a = ((Power)b).a, b = ((Power)b).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact11 = ((Fraction)b).numerator;
					fact111 = ((Fraction)b).denominator;
				}
				else {
					fact1 = ((Number)b).value;				
				}
			}
			else if (Y instanceof Number){
				cons1 = ((Number)X).value;
			}
			else {
				cons1 = 0;				
			if (Z2 instanceof Multiplication) {
				Term a = ((Multiplication)Z2).a, b = ((Multiplication)Z2).b;
				if (a instanceof Power) {
					Term a = ((Power)a).a, b = ((Power)a).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (a instanceof Fraction ){
					fact22 = ((Fraction)a).numerator;
					fact222 = ((Fraction)a).denominator;
				}
				else {
					fact2 = ((Number)a).value;				
				}
				if (b instanceof Power) {
					Term a = ((Power)b).a, b = ((Power)b).b;
					if (((Number)a).value != 2 || b instanceof Multiplication) {
						Sytem.out.print("La résolution de cette équation par ce logiciel est impossible.");
					}
					else if (b instanceof Fraction){
						surfact11 = ((Fraction)b).numerator;
						surfact111 = ((Fraction)b).denominator;
					}
					else {
						surfact1 = ((Number)b).value;
					}
				}
				else if (b instanceof Fraction ){
					fact22 = ((Fraction)b).numerator;
					fact222 = ((Fraction)b).denominator;
				}
				else {
					fact2 = ((Number)b).value;				
				}
			}
			else if (Y2 instanceof Number){
				cons2 = ((Number)X).value;
			}
			else {
				cons2 = 0;
			}
			
			
			
			
				
				if (surfact1 != 0 && surfact2 != 0) {
					u = surfact1 - surfact2;
				}
				else if (surfact1 != 0){
					u = surfact1*surfact222 - surfact22;
				}
				else if (surfact2 != 0) {
					u =  surfact11 - surfact2*surfact111;
				}
				else {
					u = (surfact11*surfact222 - surfact22*surfact111) / (surfact111*surfact222);
				}
				if (fact1 != 0 && fact2 != 0) {
					v = fact1 - fact2;
				}
				else if (fact1 != 0){
					v = fact1*fact222 - fact22;
				}
				else if (fact2 != 0){
					v = fact11 - fact2*fact111;
				}
				else {
					v = (fact11*fact222 - fact22*fact111) / (fact111*fact222);
				}
				if (cons1 != 0 && cons2 != 0) {
					w = cons1 - cons2;
				}
				else if (cons1 != 0){
					w = cons1*cons222 - cons22;
				}
				else {
					w = cons11 - cons2*cons111;
				}
				else {
					w = (cons11*cons222 - cons22*cons111) / (cons111*cons222);
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
				if (simplifié instanceof Number) {
					solution1 = (((Number)simplifié).value);
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
				if (simplifié instanceof Number) {
					solution1 = (((Number)simplifié).value);
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
				if (simplifié instanceof Number) {
					solution1 = (((Number)simplifié).value);
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
	}
}