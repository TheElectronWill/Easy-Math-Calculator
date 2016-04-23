package fr.calculator.resolution;

import fr.calculator.analyse.Fonction;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Fraction;
import fr.calculator.analyse.Multiplication;
import fr.calculator.analyse.NombreEntier;
import fr.calculator.analyse.Puissance;
import fr.calculator.analyse.Terme;
import fr.calculator.analyse.Variable;
import java.util.List;

public class MathSolveur {

	public static String resoudre(List<Terme> gauche, List<Terme> droit) {
		int fact1 = 0, fact11 = 0, fact111 = 0, fact2 = 0, fact22 = 0, fact222 = 0; // Déclaration  et initialisation des variables générales.
		int cons1 = 0, cons11 = 0, cons111 = 0, cons2 = 0, cons22 = 0, cons222 = 0;
		int surfact1 = 0, surfact11 = 0, surfact111 = 0, surfact2 = 0, surfact22 = 0, surfact222 = 0;
		int u = 0, u1 = 1, v = 0, v1 = 1, w = 0, w1 = 1, delta = 0;
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0, re3 = 0, re4 = 0;
		Fraction frac1 = new Fraction(0, 0), frac2 = new Fraction(0, 0), re1 = new Fraction(0, 0), re2 = new Fraction(0, 0);
		int modulo = 0, deux = 0;
		if (gauche == droit) { // Les deux parties de l'équation sont identiques.
			System.out.print("L'égalité est toujours vraie.");
		}
		Terme X = null; // Déclaration des termes de l'équation.
		Terme X2 = null;
		Terme Y = null;
		Terme Y2 = null;
		Terme Z = null;
		Terme Z2 = null;
		if (!gauche.isEmpty()) { // Initialisation des termes de l'équation.
			X = gauche.get(0);
		} else if (gauche.isEmpty()) {
			System.out.print("Vous devez écrire une équation.");
		}
		if (!droit.isEmpty()) {
			X2 = droit.get(0);
		} else if (droit.isEmpty()) {
			System.out.print("Vous devez écrire une équation.");
		}
		if (gauche.size() > 1) {
			Y = gauche.get(1);
		}
		if (droit.size() > 1) {
			Y2 = droit.get(1);
		}
		if (gauche.size() > 2) {
			Z = gauche.get(2);
		}
		if (droit.size() > 2) {
			Z2 = droit.get(2);
		}
		if (Y.equals("") && Y2.equals("") && Z.equals("") && Z2.equals("") && (X instanceof NombreEntier || X instanceof Fraction || X.equals("")) && (X2 instanceof NombreEntier || X2 instanceof Fraction || X2.equals(""))) { // La variable est absente.
			System.out.print("Vous devez utiliser la variable x.");
		} else if (X instanceof Fonction || X2 instanceof Fonction) { // X ou X2 sont des fonctions.
			Fonction fonction = null, fonction1 = null;
			if (X instanceof Fonction) { // X est une fonction.
				fonction = (Fonction) X;
			}
			if (X2 instanceof Fonction) { // X2 est une fonction.
				fonction1 = (Fonction) X2;
			}
			if ((fonction.nom == NomFonction.EXPONENTIELLE && fonction1.nom == NomFonction.EXPONENTIELLE) || (fonction.nom == NomFonction.LOGARITHME_NEPERIEN && fonction1.nom == NomFonction.LOGARITHME_NEPERIEN) || (fonction.nom == NomFonction.COSINUS && fonction1.nom == NomFonction.COSINUS) || (fonction.nom == NomFonction.SINUS && fonction1.nom == NomFonction.SINUS)) { // fonction et fonction2 sont deux fonctions du même genre (deux logarithmes, deux exponentielles...)
				gauche = (List<Terme>) fonction.param;
				droit = (List<Terme>) fonction.param;
				if (gauche == droit) { //
					System.out.print("L'égalité est toujours vraie.");
				}
				X = null;
				X2 = null;
				Y = null;
				Y2 = null;
				Z = null;
				Z2 = null;
				if (!gauche.isEmpty()) {
					X = gauche.get(0);
				} else if (gauche.isEmpty()) {
					System.out.print("Vous devez écrire une équation.");
				}
				if (!droit.isEmpty()) {
					X2 = droit.get(0);
				} else if (droit.isEmpty()) {
					System.out.print("Vous devez écrire une équation.");
				}
				if (gauche.size() > 1) {
					Y = gauche.get(1);
				}
				if (droit.size() > 1) {
					Y2 = droit.get(1);
				}
				if (gauche.size() > 2) {
					Z = gauche.get(2);
				}
				if (droit.size() > 2) {
					Z2 = droit.get(2);
				}
			} else if (fonction.nom == NomFonction.EXPONENTIELLE) { // fonction est une fonction exponentielle.
				if (fonction.param instanceof NombreEntier || fonction.param instanceof Fraction) { // Le paramètre de fonction est un nombre entier ou une fraction.
					if (X2 instanceof NombreEntier || X2 instanceof Fraction) { // X2 est un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction.param instanceof NombreEntier) { // Le paramètre de fonction est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.exp((a.valeur));
					} else if (fonction.param instanceof Fraction) { // Le paramètre de fonction est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.exp((frac.num / frac.denom));
					}
				} else if (X2 instanceof NombreEntier) { // X2 est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.log((a.valeur));
				} else if (X2 instanceof Fraction) { // X2 est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.log(frac.num / frac.denom);
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction1.nom == NomFonction.EXPONENTIELLE) { // fonction1 est une fonction exponentielle.
				if (fonction1.param instanceof NombreEntier || fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est un nombre entier ou une fraction.
					if (X instanceof NombreEntier || X instanceof Fraction) { // X est un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction1.param instanceof NombreEntier) { // Le paramètre de fonction1 est un nombre entier.
						NombreEntier a = (NombreEntier) X;
						solution1 = Math.exp((a.valeur));
					} else if (fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.exp(frac.num / frac.denom);
					}
				} else if (X instanceof NombreEntier) { // X est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.log(a.valeur);
				} else if (X instanceof Fraction) { // X est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.log((frac.num / frac.denom));
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction.nom == NomFonction.LOGARITHME_NEPERIEN) { // fonction est une fonction logarithme.
				if (fonction.param instanceof NombreEntier || fonction.param instanceof Fraction) { // Le paramètre de fonction est un nombre entier ou une fraction.
					if (X2 instanceof NombreEntier || X2 instanceof Fraction) { // X2 est un nombre entier ou une fonction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction.param instanceof NombreEntier) { // Le paramètre de fonction est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.log((a.valeur));
					} else if (fonction.param instanceof Fraction) { // Le paramètre de fonction est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.log((frac.num / frac.denom));
					}
				} else if (X2 instanceof NombreEntier) { // X2 est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.exp((a.valeur));
				} else if (X2 instanceof Fraction) { // X2 est un nombre entier.
					Fraction frac = (Fraction) X2;
					solution1 = Math.exp((frac.num / frac.denom));
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction1.nom == NomFonction.LOGARITHME_NEPERIEN) { // fonction1 est un fonction logarithme.
				if (fonction1.param instanceof NombreEntier || fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est un nombre entier ou une fraction.
					if (X instanceof NombreEntier || X instanceof Fraction) { // X est un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction1.param instanceof NombreEntier) { // Le paramètre de fonction1 est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.log((a.valeur));
					} else if (fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.log((frac.num / frac.denom));
					}
				} else if (X instanceof NombreEntier) { // X est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.exp((a.valeur));
				} else if (X instanceof Fraction) { // X est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.exp((frac.num / frac.denom));
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction.nom == NomFonction.COSINUS) { // fonction est une fonction cosinus.
				if (fonction.param instanceof NombreEntier || fonction.param instanceof Fraction) { // Le paramètre de fonction est un nombre entier ou une fraction.
					if (X2 instanceof NombreEntier || X instanceof Fraction) { // X2 est un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction.param instanceof NombreEntier) { // Le paramètre de fonction est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.cos((a.valeur));
					} else if (fonction.param instanceof Fraction) { // Le paramètre de fonction est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.cos((frac.num / frac.denom));
					}
				} else if (X2 instanceof NombreEntier) { // X2 est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.acos((a.valeur));
					solution2 = -solution1;
					modulo = 1;
				} else if (X2 instanceof Fraction) { // X2 est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.acos((frac.num / frac.denom));
					solution2 = -solution1;
					modulo = 1;
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction1.nom == NomFonction.COSINUS) { // fonction1 est une fonction cosinus.
				if (fonction1.param instanceof NombreEntier || fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est un nombre entier ou une fraction.
					if (X instanceof NombreEntier || X instanceof Fraction) { // X est un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction1.param instanceof NombreEntier) { // Le paramètre de fonction1 est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.cos((a.valeur));
					} else if (fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.cos((frac.num / frac.denom));
					}

				} else if (X instanceof NombreEntier) { // X est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.acos((a.valeur));
					solution2 = -solution1;
					modulo = 1;
				} else if (X instanceof Fraction) { // X est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.acos((frac.num / frac.denom));
					solution2 = -solution1;
					modulo = 1;
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction.nom == NomFonction.SINUS) { // fonction est une fonction sinus.
				if (fonction.param instanceof NombreEntier || fonction.param instanceof Fraction) { // Le paramètre de fonction est un nombre entier ou une fraction.
					if (X2 instanceof NombreEntier || X2 instanceof Fraction) { // X2 est un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction.param instanceof NombreEntier) { // Le paramètre de fonction est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.sin((a.valeur));
					} else if (fonction.param instanceof Fraction) { // Le paramètre de fonction est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.sin((frac.num / frac.denom));
					}
				} else if (X2 instanceof NombreEntier) { // X2 est un nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.asin((a.valeur));
					solution2 = Math.PI - solution1;
					modulo = 1;
				} else if (X2 instanceof Fraction) { // X2 est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.asin((frac.num / frac.denom));
					solution2 = Math.PI - solution1;
					modulo = 1;
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (fonction1.nom == NomFonction.SINUS) { // fonction1 est une fonction sinus.
				if (fonction1.param instanceof NombreEntier || fonction1.param instanceof Fraction) { // Le paramètre de fonction1 est un nombre entier ou une fraction.
					if (X instanceof NombreEntier || X instanceof Fraction) { // X es un nombre entier ou une fraction.
						System.out.print("Vous devez utiliser la variable x.");
					} else if (fonction1.param instanceof NombreEntier) { // Le paramètre de fonction1 est un nombre entier.
						NombreEntier a = (NombreEntier) fonction.param;
						solution1 = Math.sin((a.valeur));
					} else if (fonction.param instanceof Fraction) { // Le paramètre de fonction1 est une fraction.
						Fraction frac = (Fraction) fonction.param;
						solution1 = Math.sin((frac.num / frac.denom));
					}

				} else if (X instanceof NombreEntier) { // X est nombre entier.
					NombreEntier a = (NombreEntier) X2;
					solution1 = Math.asin((a.valeur));
					solution2 = Math.PI - solution1;
					modulo = 1;
				} else if (X instanceof Fraction) { // X est une fraction.
					Fraction frac = (Fraction) X2;
					solution1 = Math.asin((frac.num / frac.denom));
					solution2 = Math.PI - solution1;
					modulo = 1;
				} else {
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			}
		} else {
			if (X instanceof Multiplication) { // X est une multiplication de termes a et b.
				Terme a = ((Multiplication) X).a, b = ((Multiplication) X).b;
				if (a instanceof Puissance) { // a est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) a).exposant;
					Terme n = ((Puissance) a).n;
					if (n instanceof Variable && ex == 2) { // n est variable et ex = 2.
						if (b instanceof Fraction) { // b est une fraction.
							surfact11 = ((Fraction) b).num;
							surfact111 = ((Fraction) b).denom;
						} else if (b instanceof NombreEntier) { // b est un nombre entier.
							surfact1 = ((NombreEntier) b).valeur;
						} else { // b est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (a instanceof Fraction) { // a est une fraction.
					fact11 = ((Fraction) a).num;
					fact111 = ((Fraction) a).denom;
				} else if (a instanceof NombreEntier) { // a est un nombre entier.
					fact1 = ((NombreEntier) a).valeur;
				} else if (b instanceof Puissance) { // b est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) b).exposant;
					Terme n = ((Puissance) b).n;
					if (n instanceof Variable && ex == 2) {
						if (a instanceof Fraction) { // a est une fraction.
							surfact11 = ((Fraction) a).num;
							surfact111 = ((Fraction) a).denom;
						} else if (a instanceof NombreEntier) { // a est un nombre entier.
							surfact1 = ((NombreEntier) a).valeur;
						} else { // n est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (b instanceof Fraction) { // b est une fraction.
					fact11 = ((Fraction) b).num;
					fact111 = ((Fraction) b).num;
				} else if (b instanceof NombreEntier) { // b est un nombre entier.
					fact1 = ((NombreEntier) b).valeur;
				} else { // a ou b sont autre chose.
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (X instanceof Fraction) { // X est une fraction.
				cons11 = ((Fraction) X).num;
				cons111 = ((Fraction) X).denom;
			} else if (X instanceof NombreEntier) { // X est un nombre entier.
				cons1 = ((NombreEntier) X).valeur;
			}
			if (X2 instanceof Multiplication) { // X2 est une multiplication de termes a et b.
				Terme a = ((Multiplication) X2).a, b = ((Multiplication) X2).b;
				if (a instanceof Puissance) { // a est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) a).exposant;
					Terme n = ((Puissance) a).n;
					if (n instanceof Variable && ex == 2) { // n est variable et ex = 2.
						if (b instanceof Fraction) { // b est une fraction.
							surfact22 = ((Fraction) b).num;
							surfact222 = ((Fraction) b).denom;
						} else if (b instanceof NombreEntier) { // b est un nombre entier.
							surfact1 = ((NombreEntier) b).valeur;
						} else { // b est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (a instanceof Fraction) { // a est une fraction.
					fact22 = ((Fraction) a).num;
					fact222 = ((Fraction) a).denom;
				} else if (a instanceof NombreEntier) { // a est un nombre entier.
					fact2 = ((NombreEntier) a).valeur;
				} else if (b instanceof Puissance) { // b est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) b).exposant;
					Terme n = ((Puissance) b).n;
					if (n instanceof Variable && ex == 2) {
						if (a instanceof Fraction) { // a est une fraction.
							surfact22 = ((Fraction) a).num;
							surfact222 = ((Fraction) a).denom;
						} else if (a instanceof NombreEntier) { // a est un nombre entier.
							surfact2 = ((NombreEntier) a).valeur;
						} else { // n est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (b instanceof Fraction) { // b est une fraction.
					fact22 = ((Fraction) b).num;
					fact222 = ((Fraction) b).denom;
				} else if (b instanceof NombreEntier) { // b est un nombre entier.
					fact2 = ((NombreEntier) b).valeur;
				} else { // a ou b sont autre chose.
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (X2 instanceof Fraction) { // X2 est une fraction.
				cons22 = ((Fraction) X2).num;
				cons222 = ((Fraction) X2).denom;
			} else if (X2 instanceof NombreEntier) { // X2 est un nombre entier.
				cons2 = ((NombreEntier) X2).valeur;
			}
			if (Y instanceof Multiplication) { // Y est une multiplication de termes a et b.
				Terme a = ((Multiplication) Y).a, b = ((Multiplication) Y).b;
				if (a instanceof Puissance) { // a est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) a).exposant;
					Terme n = ((Puissance) a).n;
					if (n instanceof Variable && ex == 2) { // n est variable et ex = 2.
						if (b instanceof Fraction) { // b est une fraction.
							surfact11 = ((Fraction) b).num;
							surfact111 = ((Fraction) b).denom;
						} else if (b instanceof NombreEntier) { // b est un nombre entier.
							surfact1 = ((NombreEntier) b).valeur;
						} else { // b est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (a instanceof Fraction) { // a est une fraction.
					fact11 = ((Fraction) a).num;
					fact111 = ((Fraction) a).denom;
				} else if (a instanceof NombreEntier) { // a est un nombre entier.
					fact1 = ((NombreEntier) a).valeur;
				} else if (b instanceof Puissance) { // b est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) b).exposant;
					Terme n = ((Puissance) b).n;
					if (n instanceof Variable && ex == 2) {
						if (a instanceof Fraction) { // a est une fraction.
							surfact11 = ((Fraction) a).num;
							surfact111 = ((Fraction) a).denom;
						} else if (a instanceof NombreEntier) { // a est un nombre entier.
							surfact1 = ((NombreEntier) a).valeur;
						} else { // n est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (b instanceof Fraction) { // b est une fraction.
					fact11 = ((Fraction) b).num;
					fact111 = ((Fraction) b).denom;
				} else if (b instanceof NombreEntier) { // b est un nombre entier.
					fact1 = ((NombreEntier) b).valeur;
				} else { // a ou b sont autre chose.
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (Y instanceof Fraction) { // Y est une fraction.
				cons11 = ((Fraction) Y).num;
				cons111 = ((Fraction) Y).denom;
			} else if (Y instanceof NombreEntier) { // Y est un nombre entier.
				cons1 = ((NombreEntier) Y).valeur;
			}
			if (Y2 instanceof Multiplication) { // Y2 est une multiplication de termes a et b.
				Terme a = ((Multiplication) Y2).a, b = ((Multiplication) Y2).b;
				if (a instanceof Puissance) { // a est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) a).exposant;
					Terme n = ((Puissance) a).n;
					if (n instanceof Variable && ex == 2) { // n est variable et ex = 2.
						if (b instanceof Fraction) { // b est une fraction.
							surfact22 = ((Fraction) b).num;
							surfact222 = ((Fraction) b).denom;
						} else if (b instanceof NombreEntier) { // b est un nombre entier.
							surfact2 = ((NombreEntier) b).valeur;
						} else { // b est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (a instanceof Fraction) { // a est une fraction.
					fact22 = ((Fraction) a).num;
					fact222 = ((Fraction) a).denom;
				} else if (a instanceof NombreEntier) { // a est un nombre entier.
					fact2 = ((NombreEntier) a).valeur;
				} else if (b instanceof Puissance) { // b est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) b).exposant;
					Terme n = ((Puissance) b).n;
					if (n instanceof Variable && ex == 2) {
						if (a instanceof Fraction) { // a est une fraction.
							surfact22 = ((Fraction) a).num;
							surfact222 = ((Fraction) a).denom;
						} else if (a instanceof NombreEntier) { // a est un nombre entier.
							surfact2 = ((NombreEntier) a).valeur;
						} else { // n est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (b instanceof Fraction) { // b est une fraction.
					fact22 = ((Fraction) b).num;
					fact222 = ((Fraction) b).denom;
				} else if (b instanceof NombreEntier) { // b est un nombre entier.
					fact2 = ((NombreEntier) b).valeur;
				} else { // a ou b sont autre chose.
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (Y2 instanceof Fraction) { // Y2 est une fraction.
				cons22 = ((Fraction) Y2).num;
				cons222 = ((Fraction) Y2).denom;
			} else if (Y2 instanceof NombreEntier) { // Y2 est un nombre entier.
				cons2 = ((NombreEntier) Y2).valeur;
			}
			if (Z instanceof Multiplication) { // Z est une multiplication de termes a et b.
				Terme a = ((Multiplication) Z).a, b = ((Multiplication) Z).b;
				if (a instanceof Puissance) { // a est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) a).exposant;
					Terme n = ((Puissance) a).n;
					if (n instanceof Variable && ex == 2) { // n est variable et ex = 2.
						if (b instanceof Fraction) { // b est une fraction.
							surfact11 = ((Fraction) b).num;
							surfact111 = ((Fraction) b).denom;
						} else if (b instanceof NombreEntier) { // b est un nombre entier.
							surfact1 = ((NombreEntier) b).valeur;
						} else { // b est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (a instanceof Fraction) { // a est une fraction.
					fact11 = ((Fraction) a).num;
					fact111 = ((Fraction) a).denom;
				} else if (a instanceof NombreEntier) { // a est un nombre entier.
					fact1 = ((NombreEntier) a).valeur;
				} else if (b instanceof Puissance) { // b est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) b).exposant;
					Terme n = ((Puissance) b).n;
					if (n instanceof Variable && ex == 2) {
						if (a instanceof Fraction) { // a est une fraction.
							surfact11 = ((Fraction) a).num;
							surfact111 = ((Fraction) a).denom;
						} else if (a instanceof NombreEntier) { // a est un nombre entier.
							surfact1 = ((NombreEntier) a).valeur;
						} else { // n est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (b instanceof Fraction) { // b est une fraction.
					fact11 = ((Fraction) b).num;
					fact111 = ((Fraction) b).denom;
				} else if (b instanceof NombreEntier) { // b est un nombre entier.
					fact1 = ((NombreEntier) b).valeur;
				} else { // a ou b sont autre chose.
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (Z instanceof Fraction) { // Z est une fraction.
				cons11 = ((Fraction) Z).num;
				cons111 = ((Fraction) Z).denom;
			} else if (Z instanceof NombreEntier) { // Z est un nombre entier.
				cons1 = ((NombreEntier) Z).valeur;
			}
			if (Z2 instanceof Multiplication) { // Z2 est une multiplication de termes a et b.
				Terme a = ((Multiplication) Z2).a, b = ((Multiplication) Z2).b;
				if (a instanceof Puissance) { // a est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) a).exposant;
					Terme n = ((Puissance) a).n;
					if (n instanceof Variable && ex == 2) { // n est variable et ex = 2.
						if (b instanceof Fraction) { // b est une fraction.
							surfact22 = ((Fraction) b).num;
							surfact222 = ((Fraction) b).denom;
						} else if (b instanceof NombreEntier) { // b est un nombre entier.
							surfact2 = ((NombreEntier) b).valeur;
						} else { // b est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (a instanceof Fraction) { // a est une fraction.
					fact22 = ((Fraction) a).num;
					fact222 = ((Fraction) a).denom;
				} else if (a instanceof NombreEntier) { // a est un nombre entier.
					fact2 = ((NombreEntier) a).valeur;
				} else if (b instanceof Puissance) { // b est une puissance de nombre n et d'exposant ex.
					int ex = ((Puissance) b).exposant;
					Terme n = ((Puissance) b).n;
					if (n instanceof Variable && ex == 2) {
						if (a instanceof Fraction) { // a est une fraction.
							surfact22 = ((Fraction) a).num;
							surfact222 = ((Fraction) a).denom;
						} else if (a instanceof NombreEntier) { // a est un nombre entier.
							surfact2 = ((NombreEntier) a).valeur;
						} else { // n est autre chose.
							System.out.print("La résolution de cette équation par ce logiciel est impossible.");
						}
					}
				} else if (b instanceof Fraction) { // b est une fraction.
					fact22 = ((Fraction) b).num;
					fact222 = ((Fraction) b).denom;
				} else if (b instanceof NombreEntier) { // b est un nombre entier.
					fact2 = ((NombreEntier) b).valeur;
				} else { // a ou b sont autre chose.
					System.out.print("La résolution de cette équation par ce logiciel est impossible.");
				}
			} else if (Z2 instanceof Fraction) { // Z2 est une fraction.
				cons22 = ((Fraction) Z2).num;
				cons222 = ((Fraction) Z2).denom;
			} else if (Z2 instanceof NombreEntier) { // Z2 est un nombre entier.
				cons2 = ((NombreEntier) Z2).valeur;
			}
			if (surfact111 != 0 && surfact222 != 0) { // Réunion des deux facteurs de x².
				u = surfact11 * surfact222 - surfact22 * surfact111;
				u1 = surfact111 * surfact222;
			} else if (surfact222 != 0) {
				u = surfact1 * surfact222 - surfact22;
				u1 = surfact222;
			} else if (surfact111 != 0) {
				u = surfact11 - surfact2 * surfact111;
				u1 = surfact111;
			} else {
				u = surfact1 - surfact2;
				u1 = 1;
			}
			if (fact111 != 0 && fact222 != 0) { // Réunion des deux facteurs de x.
				v = fact11 * fact222 - fact22 * fact111;
				v1 = fact111 * fact222;
			} else if (fact222 != 0) {
				v = fact1 * fact222 - fact22;
				v1 = fact222;
			} else if (fact111 != 0) {
				v = fact11 - fact2 * fact111;
				v1 = fact111;
			} else {
				v = fact1 - fact2;
				v1 = 1;
			}
			if (cons111 != 0 && cons222 != 0) { // Réunion des deux constantes.
				w = cons11 * cons222 - cons22 * cons111;
				w1 = cons111 * cons222;
			} else if (cons222 != 0) {
				w = cons1 * cons222 - cons22;
				w1 = cons222;
			} else if (cons111 != 0) {
				w = cons11 - cons2 * cons111;
				w1 = cons111;
			} else {
				w = cons1 - cons2;
				w1 = 1;
			}
			if (u == 0) {
				if (w != 0 && v != 0) { // Résolution affine.
					frac1.num = -w * v1;
					frac1.denom = v * w1;
				} else if (w == 0 && v != 0) {
					solution1 = 0;
				} else if (v == 0) {
					System.out.print("Tous les nombres réels sont solutions de cette équation.");
				}
			} else if (u != 0) { // Résolution trinôme.
				delta = v * v / v1 / v1 - 4 * u * w / u1 / w1;
				if (delta == 0) {
					if (v != 0) {
						frac1.num = -v * u1;
						frac1.denom = 2 * u * v1;
					} else if (v == 0) {
						solution1 = 0;
					}
				} else if (delta > 0) {
					solution1 = (-v / v1 - Math.sqrt(delta)) / 2 * u / u1;
					solution2 = (-v / v1 + Math.sqrt(delta)) / 2 * u / u1;
					deux = 1;
				} else if (delta < 0) {
					if (v != 0) {
						re1.num = -v * u1;
						re1.denom = 2 * u * v1;
					} else if (v == 0) {
						re3 = 0;
					}
					im1 = -Math.sqrt(delta) / 2 * u / u1;
					if (v != 0) {
						re2.num = -v * u1;
						re2.denom = 2 * u * v1;
					} else if (v == 0) {
						re4 = 0;
					}
					im2 = Math.sqrt(delta) / 2 * u / u1;
				}
			}
		}
		if (deux == 0 && modulo == 0 && frac1.num == 0 && frac1.num == 0) { // Affichage solutions décimales.
			System.out.print("La solution à cette équation est: ");
			System.out.print(solution1);

		} else if ((deux == 1 || modulo == 1) && frac1.num == 0 && frac1.num == 0) {
			System.out.print("Les solutions à cette équation sont: ");
			System.out.print(solution1);
			System.out.print(" et ");
			System.out.print(solution2);
		} else if (frac1.num != 0 && frac1.denom != 0) { // Affichage solutions fractionnaires.
			System.out.print("La solution à cette équation est: ");
			Terme simplifié = frac1.simplifier();
			if (simplifié instanceof NombreEntier) {
				solution1 = (((NombreEntier) simplifié).valeur);
				System.out.print(solution1);
			} else {
				((Fraction) simplifié).toString();
			}
		} else { // Affichage solutions imaginaires.
			System.out.print("Les solutions à cette équation sont: ");
			re1.toString();
			System.out.print("i+(");
			System.out.print(im1);
			System.out.print(") et ");
			re2.toString();
			System.out.print("i+(");
			System.out.print(im2);
		}
		if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
			System.out.print("[2*pi]");
		}
		System.out.print(".");
	}
}
