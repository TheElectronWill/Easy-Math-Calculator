package fr.calculator.resolution;

import fr.calculator.MathException;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Variable;

public class MathSolveur {

	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		Rationnel surfact = new Rationnel(0), fact = new Rationnel(0); // Déclaration des variables.
		double cons;
		double consExpGauche = 0, consExpDroit = 0;
		double consLogGauche = 0, consLogDroit = 0;
		double consCosGauche = 0, consCosDroit = 0;
		double consSinGauche = 0, consSinDroit = 0;
		double consGauche, consDroit;
		double consTrin;
		double consFonct;
		Rationnel exp, expGauche = new Rationnel(0), expDroit = new Rationnel(0);
		Rationnel log, logGauche = new Rationnel(0), logDroit = new Rationnel(0);
		Rationnel cos, cosGauche = new Rationnel(0), cosDroit = new Rationnel(0);
		Rationnel sin, sinGauche = new Rationnel(0), sinDroit = new Rationnel(0);
		double delta;
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
		Rationnel frac1 = null, frac2 = null, re1 = null, re2 = null;
		boolean modulo = false, deux = false, pi = false;
		StringBuilder sb = new StringBuilder();
		if (gauche.equals(droit)) { // Les deux parties de l'équation sont identiques.
			return "S = ℝ";
		}
		if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) { // Initialisation des variables.
			consExpGauche = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel) gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel) gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		} else if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expGauche = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE); // Exponentielle réelle.
		}
		if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
			consExpDroit = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel) droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel) droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		} else if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expDroit = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE); // Exponentielle réelle.
		}
		if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || ((((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0))) {
				consLogGauche = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel) gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithme néperien constant.
			} else {
				throw new MathException("Le logarithme néperien d'un nombre négatif n'existe pas.");
			}
		} else if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logGauche = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN); // Logarithme néperien réel.
		}
		if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || (((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0)) {
				consLogDroit = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel) droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithmr néperien constant.
			} else {
				throw new MathException("Le logarithme néperien d'un nombre négatif n'existe pas.");
			}
		} else if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logDroit = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN); // Logarithme néperien réel.
		}
		if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			consCosGauche = gauche.facteurFonctions.get(NomFonction.COSINUS).num / gauche.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel) gauche.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel) gauche.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		} else if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosGauche = gauche.facteurFonctions.get(NomFonction.COSINUS); // Cosinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			consCosDroit = droit.facteurFonctions.get(NomFonction.COSINUS).num / droit.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel) droit.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel) droit.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		} else if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosDroit = droit.facteurFonctions.get(NomFonction.COSINUS); // Cosinus réel.
		}
		if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			consSinGauche = gauche.facteurFonctions.get(NomFonction.SINUS).num / gauche.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel) gauche.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel) gauche.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		} else if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sinGauche = gauche.facteurFonctions.get(NomFonction.SINUS); // Sinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			consSinDroit = droit.facteurFonctions.get(NomFonction.SINUS).num / droit.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel) droit.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel) droit.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		} else if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sinDroit = droit.facteurFonctions.get(NomFonction.SINUS); // Sinus réel.
		}
		surfact = gauche.facteurX2.soustraire(droit.facteurX2); // Réunion des deux facteurs de x².
		fact = gauche.facteurX.soustraire(droit.facteurX); // Réunion des deux facteurs de x.
		consTrin = gauche.constante.soustraire(droit.constante).decimal(); // Réunion des deux constantes trinôme.
		consGauche = consExpGauche + consLogGauche + consCosGauche + consSinGauche; // Réunion des différentes constantes fonction des deux côtés.
		consDroit = consExpDroit + consLogDroit + consCosDroit + consSinDroit;
		consFonct = consGauche - consDroit; // Réunion des constantes fonction de chaque côté.
		cons = consTrin + consFonct; // Réunion des constantes trinôme et fonction.
		exp = expGauche.soustraire(expDroit); // Réunion des deux facteurs d'exponentielle réelle.
		log = logGauche.soustraire(logDroit); // Réunion des deux facteurs de logarithme néperien réel.
		cos = cosGauche.soustraire(cosDroit); // Réunion des deux facteurs de cosinus réel.
		sin = sinGauche.soustraire(sinDroit); // Réunion des deux facteurs de sinus réel.
		if ((exp.num != 0 && log.num != 0) || (exp.num != 0 && cos.num != 0) || (exp.num != 0 && sin.num != 0) || (log.num != 0 && cos.num != 0) || (log.num != 0 && sin.num != 0) || (cos.num != 0 && sin.num != 0)) { // Il y a deux fonctions réelles à traiter.
			throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (exp.num != 0) { // Il y a une fonction exponentielle à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons < 0) {
					solution1 = Math.log(-cons) / exp.num * exp.denom;
				} else {
					return "S = ∅";
				}
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (log.num != 0) { // Il y a une fonction logarithme néperien à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				solution1 = Math.exp(-cons) / log.num * log.denom;
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (cos.num != 0) { // Il y a une fonction cosinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons == 0) {
					frac1 = cos.clone().multiplier(new Rationnel(1, 2));
					frac2 = cos.clone().multiplier(new Rationnel(-1, 2));
					pi = true;
					deux = true;
					modulo = true;
				} else if (cons == -0.5) {
					frac1 = cos.clone().multiplier(new Rationnel(1, 3));
					frac2 = cos.clone().multiplier(new Rationnel(-1, 3));
					pi = true;
					deux = true;
					modulo = true;
				} else if (cons < -1 || cons > 1) {
					return "S = ∅";
				} else {
					solution1 = Math.acos(-cons) / cos.num * cos.denom;
					solution2 = -solution1;
					deux = true;
					modulo = true;
				}
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (sin.num != 0) { // Il y a une fonction sinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons == -1) {
					frac1 = sin.clone().multiplier(new Rationnel(1, 2));
					frac2 = sin.clone().multiplier(new Rationnel(1, 2));
					pi = true;
					deux = true;
					modulo = true;
				} else if (cons == -0.5) {
					frac1 = sin.clone().multiplier(new Rationnel(1, 6));
					frac2 = sin.clone().multiplier(new Rationnel(5, 6));
					pi = true;
					deux = true;
					modulo = true;
				} else if (cons < -1 || cons > 1) {
					return "S = ∅";
				} else {
					solution1 = Math.asin(-cons) / sin.num * sin.denom;
					solution2 = Math.PI - solution1;
					deux = true;
					modulo = true;
				}
			} else {
				throw new MathException("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		} else if (surfact.num != 0) { // Résolution trinôme.
			Rationnel fact1 = fact.clone().multiplier(fact);
			delta = fact1.decimal() - 4 * surfact.decimal() * cons;
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
		} else if (frac1 != null) { // Affichage solutions fractionnaires.
			sb.append("S = [ ");
			Rationnel simplif1 = frac1.simplifier();
			if (simplif1.denom == 1) {
				solution1 = simplif1.num;
				sb.append(solution1);
				if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			} else if (simplif1.num == 0) {
				sb.append("0");
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			} else {
				sb.append(simplif1.num);
				if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				sb.append("/").append(simplif1.denom);
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			if (deux && frac2 != null) { // Affichage deuxième solution potentielle.
				sb.append(" ; ");
				Rationnel simplif2 = frac2.simplifier();
				if (simplif2.denom == 1) {
					solution2 = simplif2.num;
					sb.append(solution2);
					if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				} else if (simplif2.num == 0) {
					sb.append("0");
					if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				} else {
					sb.append(simplif2.num);
					if (pi) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					sb.append("/").append(simplif2.denom);
					if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
			}
			sb.append(" ]");
		} else { // Affichage solutions décimales.
			sb.append("S = [ ").append(solution1);
			if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
				sb.append("[2π]");
			}
			if (deux) { // Affichage deuxième solution potentielle.
				sb.append(" ; ").append(solution2);
				if (modulo) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			sb.append(" ]");
		}
		return sb.toString();
	}

	private MathSolveur() {
	}
}
