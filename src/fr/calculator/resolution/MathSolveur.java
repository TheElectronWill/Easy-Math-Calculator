package fr.calculator.resolution;

import fr.calculator.MathException;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Variable;

public class MathSolveur {

	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		if (gauche.equals(droit)) { // Les deux parties de l'équation sont identiques.
			return "S = ℝ";
		}
		Rationnel surfact = new Rationnel(0), fact = new Rationnel(0); // Déclaration des variables.
		double cons = 0;
		Rationnel exp = new Rationnel(0);
		Rationnel log = new Rationnel(0);
		Rationnel cos = new Rationnel(0);
		Rationnel sin = new Rationnel(0);
		double delta;
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
		Rationnel frac1 = null, frac2 = null, re1 = null, re2 = null;
		boolean modulo = false, deux = false, pi = false;
		StringBuilder sb = new StringBuilder();
		if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) { // Initialisation des variables.
			cons += gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).decimal() * Math.exp(((Rationnel) gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).decimal()); // Exponentielle constante.
		} else if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			exp.ajouter(gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE)); // Exponentielle réelle.
		}
		if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
			cons -= droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).decimal() * Math.exp(((Rationnel) droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).decimal()); // Exponentielle constante.
		} else if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			exp.soustraire(droit.facteurFonctions.get(NomFonction.EXPONENTIELLE)); // Exponentielle réelle.
		}
		if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).decimal() > 0) {
				cons += gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).decimal() * Math.log(((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).decimal()); // Logarithme néperien constant.
			} else {
				throw new MathException("Le logarithme néperien d'un nombre négatif n'existe pas.");
			}
		} else if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			log.ajouter(gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN)); // Logarithme néperien réel.
		}
		if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).decimal() > 0) {
				cons -= droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).decimal() * Math.log(((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).decimal()); // Logarithmr néperien constant.
			} else {
				throw new MathException("Le logarithme néperien d'un nombre négatif n'existe pas.");
			}
		} else if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			log.soustraire(droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN)); // Logarithme néperien réel.
		}
		if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			cons += gauche.facteurFonctions.get(NomFonction.COSINUS).decimal() * Math.cos(((Rationnel) gauche.paramFonctions.get(NomFonction.COSINUS)).decimal()); // Cosinus constant.
		} else if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cos.ajouter(gauche.facteurFonctions.get(NomFonction.COSINUS)); // Cosinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			cons -= droit.facteurFonctions.get(NomFonction.COSINUS).decimal() * Math.cos(((Rationnel) droit.paramFonctions.get(NomFonction.COSINUS)).decimal()); // Cosinus constant.
		} else if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cos.soustraire(droit.facteurFonctions.get(NomFonction.COSINUS)); // Cosinus réel.
		}
		if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			cons += gauche.facteurFonctions.get(NomFonction.SINUS).decimal() * Math.sin(((Rationnel) gauche.paramFonctions.get(NomFonction.SINUS)).decimal()); // Sinus constant.
		} else if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sin.ajouter(gauche.facteurFonctions.get(NomFonction.SINUS)); // Sinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			cons -= droit.facteurFonctions.get(NomFonction.SINUS).decimal() * Math.sin(((Rationnel) droit.paramFonctions.get(NomFonction.SINUS)).decimal()); // Sinus constant.
		} else if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sin.soustraire(droit.facteurFonctions.get(NomFonction.SINUS)); // Sinus réel.
		}
		surfact = gauche.facteurX2.soustraire(droit.facteurX2); // Facteur de x².
		fact = gauche.facteurX.soustraire(droit.facteurX); // Facteur de x.
		cons += gauche.constante.decimal();
		cons -= droit.constante.decimal();
		if ((exp.num != 0 && log.num != 0) || (exp.num != 0 && cos.num != 0) || (exp.num != 0 && sin.num != 0) || (log.num != 0 && cos.num != 0) || (log.num != 0 && sin.num != 0) || (cos.num != 0 && sin.num != 0)) { // Il y a deux fonctions réelles à traiter.
			throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (exp.num != 0) { // Il y a une fonction exponentielle à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons < 0) {
					solution1 = Math.log(-cons * exp.denom / exp.num);
				} else {
					return "S = ∅";
				}
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (log.num != 0) { // Il y a une fonction logarithme néperien à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				solution1 = Math.exp(-cons * log.denom / log.num);
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (cos.num != 0) { // Il y a une fonction cosinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (-cons * cos.denom / cos.num == -1) {
					solution1 = 1;
					pi = true;
					modulo = true;
				} else if (-cons * cos.denom / cos.num == -0.5) {
					frac1 = new Rationnel(2,3);
					frac2 = new Rationnel(-2,3);
					pi = true;
					deux = true;
					modulo = true;
				} else if (-cons * cos.denom / cos.num == 0) {
					frac1 = new Rationnel(1,2);
					frac2 = new Rationnel(-1,2);
					pi = true;
					deux = true;
					modulo = true;
				} else if (-cons * cos.denom / cos.num == 0.5) {
					frac1 = new Rationnel(1,3);
					frac2 = new Rationnel(-1,3);
					pi = true;
					deux = true;
					modulo = true;
				} else if (-cons * cos.denom / cos.num == 1) {
					solution1 = 0;
					modulo = true;
				} else if (cons < -1 || cons > 1) {
					return "S = ∅";
				} else {
					solution1 = Math.acos(-cons * cos.denom / cos.num);
					solution2 = -solution1;
					deux = true;
					modulo = true;
				}
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (sin.num != 0) { // Il y a une fonction sinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (-cons * sin.denom / sin.num == -1) {
					frac1 = new Rationnel(-1, 2);
					pi = true;
					modulo = true;
				} else if (-cons * sin.denom / sin.num == -0.5) {
					frac1 = new Rationnel(-1, 6);
					frac2 = new Rationnel(-5, 6);
					pi = true;
					deux = true;
					modulo = true;
				} else if (-cons * sin.denom / sin.num == 0) {
					solution1 = 0;
					solution2 = 1;
					pi = true;
					deux = true;
					modulo = true;
				} else if (-cons * sin.denom / sin.num == 0.5) {
					frac1 = new Rationnel(1,6);
					frac2 = new Rationnel(5,6);
					pi = true;
					deux = true;
					modulo = true;
				} else if (-cons * sin.denom / sin.num == 1) {
					frac1 = new Rationnel(1,2);
					pi = true;
					modulo = true;
				} else if (cons < -1 || cons > 1) {
					return "S = ∅";
				} else {
					solution1 = Math.asin(-cons * sin.denom / sin.num);
					solution2 = Math.PI - solution1;
					deux = true;
					modulo = true;
				}
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (surfact.num != 0) { // Résolution trinôme.
			delta = fact.decimal() * fact.decimal() - 4 * surfact.decimal() * cons;
			System.out.print(delta);
			if (delta == 0) {
				frac1 = new Rationnel(-fact.num * surfact.denom, 2 * surfact.num * fact.denom);
			} else if (delta > 0) {
				solution1 = (-fact.decimal() - Math.sqrt(delta)) / 2 / surfact.decimal();
				solution2 = (-fact.decimal() + Math.sqrt(delta)) / 2 / surfact.decimal();
				deux = true;
			} else if (delta < 0) {
				re1 = new Rationnel(-fact.num * surfact.denom, 2 * surfact.num * fact.denom);
				im1 = -Math.sqrt(-delta) / 2 * surfact.decimal();
				re2 = new Rationnel(-fact.num * surfact.num, 2 * surfact.num * fact.denom);
				im2 = Math.sqrt(-delta) / 2 * surfact.decimal();
			}
		} else if (fact.num != 0) { // Résolution affine.
			solution1 = -cons / fact.decimal();
		} else if (cons != 0) {
			throw new MathException("Vous devez utiliser la variable x.");
		} else {
			throw new MathException("Vous devez rentrer une équation.");
		}
		if (re1 != null && re2 != null) { // Affichage solutions imaginaires.
			sb.append("S = [ ");
			Rationnel simplif1 = re1.simplifier();
			if (simplif1.denom == 1) {
				solution1 = simplif1.num;
				sb.append(solution1);
			} else if (simplif1.num == 0) {
				sb.append("0");
			} else {
				sb.append(simplif1);
			}
			sb.append(" + (").append(im1).append(" i) ; ");
			Rationnel simplif2 = re2.simplifier();
			if (simplif2.denom == 1) {
				solution2 = simplif2.num;
				sb.append(solution2);
			} else if (simplif2.num == 0) {
				sb.append("0");
			} else {
				sb.append(simplif2);
			}
			sb.append(" + (").append(im2).append(" i)]");
		} else if (frac1 != null) { // Affichage première solution fractionnaire.
			sb.append("S = [ ");
			Rationnel simplif1 = frac1.simplifier();
			if (simplif1.denom == 1) {
				sb.append(simplif1.num);
				if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append(" [2π]");
				}
			} else if (simplif1.num == 0) {
				sb.append("0");
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append(" [2π]");
				}
			} else {
				if (!pi || !(simplif1.num == 1 || simplif1.num == -1)) {
					sb.append(simplif1.num);
				}
				if (pi && simplif1.num == -1) {
					sb.append("-");
				}
				if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				sb.append("/").append(simplif1.denom);
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append(" [2π]");
				}
			}
			if (deux && frac2 != null) { // Affichage deuxième solution fractionnaire potentielle.
				sb.append(" ; ");
				Rationnel simplif2 = frac2.simplifier();
				if (simplif2.denom == 1) {
					sb.append(simplif2.num);
					if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append(" [2π]");
					}
				} else if (simplif2.num == 0) {
					sb.append("0");
					if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append(" [2π]");
					}
				} else {
					if (!pi || !(simplif2.num == 1 || simplif2.num == -1)) {
						sb.append(simplif2.num);
					}
					if (pi && simplif2.num == -1) {
						sb.append("-");
					}
					if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					sb.append("/").append(simplif2.denom);
					if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append(" [2π]");
					}
				}
			}
			sb.append(" ]");
		} else { // Affichage première solution décimale.
			sb.append("S = [ ");
			if (!pi || !(solution1 == 1 || solution1 == -1)) {
				sb.append(solution1);
			}
			if (pi && solution1 == -1) {
				sb.append("-");
			}
			if (pi && solution1 != 0) {
				sb.append("π");
			}
			if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
				sb.append(" [2π]");
			}
			if (deux) { // Affichage deuxième solution décimale potentielle.
				sb.append(" ; ");
				if (!pi || !(solution1 == 1 || solution2 == -1)) {
					sb.append(solution2);
				}
				if (pi && solution2 == -1) {
					sb.append("-");
				}
				if (pi && solution2 != 0) {
					sb.append("π");
				}
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append(" [2π]");
				}
			}
			sb.append(" ]");
		}
		return sb.toString();
	}
	
	private MathSolveur() {
	}
}
