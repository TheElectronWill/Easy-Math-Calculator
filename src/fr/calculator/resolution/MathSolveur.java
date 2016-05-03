package fr.calculator.resolution;
import java.util.Map; // Imports.
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Variable;
import fr.calculator.analyse.Terme;
public class MathSolveur {
	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		int surfact1 = 0, surfact2 = 1, fact1 = 0, fact2 = 1; // Déclaration des variables.
		double cons1 = 0, cons2 = 1; 
		double consExpGauche1 = 0, consExpGauche2 = 1, consExpDroit1 = 0, consExpDroit2 = 1;
		double consLogGauche1 = 0, consLogGauche2 = 1, consLogDroit1 = 0, consLogDroit2 = 1;
		double consCosGauche1 = 0, consCosGauche2 = 1, consCosDroit1 = 0, consCosDroit2 = 1;
		double consSinGauche1 = 0, consSinGauche2 = 1, consSinDroit1 = 0, consSinDroit2 = 1;
		double consGauche1 = 0, consGauche2 = 1, consDroit1 = 0, consDroit2 = 1;
		int consTrin1 = 0, consTrin2 = 1;
		double consFonct1 = 0, consFonct2 = 1;
		int exp1 = 0, exp2 = 1, expGauche1 = 0, expGauche2 = 1, expDroit1 = 0, expDroit2 = 1; 
		int log1 = 0, log2 = 1, logGauche1 = 0, logGauche2 = 1, logDroit1 = 0, logDroit2 = 1; 
		int cos1 = 0, cos2 = 1, cosGauche1 = 0, cosGauche2 = 1, cosDroit1 = 0, cosDroit2 = 1; 
		int sin1 = 0, sin2 = 1, sinGauche1 = 0, sinGauche2 = 1, sinDroit1 = 0 , sinDroit2 = 1;
		double delta = 0; 
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
		Rationnel frac1 = new Rationnel(0,0), frac2 = new Rationnel(0,0), re1 = new Rationnel(0,0), re2 = new Rationnel(0,0);
		int modulo = 0, deux = 0, pi = 0;
		StringBuilder sb = new StringBuilder();
		if (gauche. equals(droit)) { // Les deux parties de l'équation sont identiques.
			return "S = ℝ";
		}
		if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) { // Initialisation des variables.
			consExpGauche1 = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel)gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		}
		else if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expGauche = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE); // Exponentielle réelle.
		}
		if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
			consExpDroit1 = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel)droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		}
		else if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expDroit = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE); // Exponentielle réelle.
		}
		if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || ((((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0))) {
				consLogGauche1 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithme néperien constant.
			}
			else {
				return "Le logarithme néperien d'un nombre négatif n'existe pas.";
			}
		}
		else if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logGauche1 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN); // Logarithme néperien réel.
		}
		if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || (((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0)) {
				consLogDroit1 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithmr néperien constant.
			}
			else {
				return "Le logarithme néperien d'un nombre négatif n'existe pas.";
			}
		}
		else if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logDroit1 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN); // Logarithme néperien réel.
		}
		if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			consCosGauche1 = gauche.facteurFonctions.get(NomFonction.COSINUS).num / gauche.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel)gauche.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		}
		else if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosGauche1 = gauche.facteurFonctions.get(NomFonction.COSINUS); // Cosinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			consCosDroit1 = droit.facteurFonctions.get(NomFonction.COSINUS).num / droit.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel)droit.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		}
		else if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosDroit1 = droit.facteurFonctions.get(NomFonction.COSINUS); // Cosinus réel.
		}
		if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			consSinGauche1 = gauche.facteurFonctions.get(NomFonction.SINUS).num / gauche.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel)gauche.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		}
		else if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sinGauche1 = gauche.facteurFonctions.get(NomFonction.SINUS); // Sinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			consSinDroit1 = droit.facteurFonctions.get(NomFonction.SINUS).num / droit.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel)droit.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		}
		else if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sinDroit1 = droit.facteurFonctions.get(NomFonction.SINUS); // Sinus réel.
		}
		if (gauche.facteurX2.denom != 0 && droit.facteurX2.denom != 0) { // Réunion des deux facteurs de x².
			surfact.num =  gauche.facteurX2.num * droit.facteurX2.denom - droit.facteurX2.num * gauche.facteurX2.denom;
			surfact.denom = gauche.facteurX2.denom * droit.facteurX2.denom;
		}
		if (gauche.facteurX.denom != 0 && droit.facteurX.denom != 0) { // Réunion des deux facteurs de x.
			fact.num = gauche.facteurX.num * droit.facteurX.denom - droit.facteurX.num * gauche.facteurX.denom;
			fact.denom = gauche.facteurX.denom * droit.facteurX.denom;
		}	
		if (gauche.constante.denom != 0 && droit.constante.denom != 0) { // Réunion des deux constantes trinôme.
			consTrin.num = gauche.constante.num * droit.constante.denom - droit.constante.num * gauche.constante.denom;
			consTrin.denom = gauche.constante.denom * droit.constante.denom;
		}
		consGauche.num = consExpGauche1*consLogGauche2*consCosGauche2*consSinGauche2 + consLogGauche1*consExpGauche2*consCosGauche2*consSinGauche2 + consCosGauche1*consLogGauche2*consExpGauche2*consSinGauche2 + consSinGauche1*consLogGauche2*consCosGauche2*consExpGauche2; // Réunion des différentes constantes fonction des deux côtés.
		consGauche.denom = consExpGauche2*consLogGauche2*consCosGauche2*consSinGauche2;
		consDroit.num = consExpDroit1*consLogDroit2*consCosDroit2*consSinDroit2 + consLogDroit1*consExpDroit2*consCosDroit2*consSinDroit2 + consCosDroit1*consLogDroit2*consExpDroit2*consSinDroit2 + consSinDroit1*consLogDroit2*consCosDroit2*consExpDroit2;
		consDroit.denom = consExpDroit2*consLogDroit2*consCosDroit2*consSinDroit2;
		consFonct.num = consGauche1*consDroit2 - consDroit1*consGauche2; // Réunion des constantes fonction de chaque côté.
		consFonct.denom = consGauche2 * consDroit2;
		cons = consTrin1*consFonct2 + consFonct1*consTrin2 / consTrin2 / consFonct2; // Réunion des constantes trinôme et fonction.
		exp.num = expGauche1 * expDroit2 - expGauche2*expDroit1; // Réunion des deux facteurs d'exponentielle réelle.
		exp.denom = expDroit1 * expDroit2;
		log.num = logGauche1*logDroit2 - logGauche2*logDroit1; // Réunion des deux facteurs de logarithme néperien réel.
		log.denom = logDroit1 * logDroit2;
		cos.num = cosGauche1*cosDroit2 - cosGauche2*cosDroit1; // Réunion des deux facteurs de cosinus réel.
		cos.denom = cosDroit1 * cosDroit2;
		sin.num = sinGauche1*sinDroit2 - sinGauche2*sinDroit1; // Réunion des deux facteurs de sinus réel. 
		sin.denom = sinDroit1 * sinDroit2;
		if ((exp.num != 0 && log.num != 0) || (exp.num != 0 && cos.num != 0) || (exp.num != 0 && sin.num != 0) || (log.num != 0 && cos.num != 0) || (log.num != 0 && sin.num != 0) || (cos.num != 0 && sin.num != 0)) { // Il y a deux fonctions réelles à traiter.
			return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
		}
		if (exp.num != 0) { // Il y a une fonction exponentielle à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if ((cons.num < 0 && cons.denom > 0) || (cons.num > 0 && cons.denom < 0)) {
					solution1 = Math.log(-cons) / exp.num * exp.denom;
				}
				else {
					return "S = ∅";
				}
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (log.num != 0) { // Il y a une fonction logarithme néperien à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				solution1 = Math.exp(-cons.num/cons.denom) / log.num * log.denom;
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (cos.num != 0) { // Il y a une fonction cosinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons == (0,0)) {
					frac1 = cos.multiplier((1,2));
					frac2 = cos.multiplier((-1,2));
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else if (cons == (-1,2) || cons == (1,-2)) {
					frac1 = cos.multiplier((1,3));
					frac2 = cos.multiplier((-1,3));
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else {
					solution1 = Math.acos(-cons.num/cons.denom) / cos.num * cos.denom;
					solution2 = -Math.acos(-cons.num/cons.denom) / cos.num * cos.denom;
					deux = 1;
					modulo = 1;
				}
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (sin.num != 0) { // Il y a une fonction sinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons == (-1,1)) || cons == (1,-1)) {
					frac1 = sin.multiplier((1,2));
					frac2 = sin.multiplier((1,2));
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else if (cons == (-1,2) || cons == (1,-2)) {
					frac1 = sin.multiplier((1,6));
					frac2 = sin.multiplier((5,6));
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else {
					solution1 = Math.asin(-cons.num/cons.denom) / sin.num * sin.denom;
					solution2 = Math.asin(Math.PI + cons.num/cons.denom) / sin.num * sin.denom;
					deux = 1;
					modulo = 1;
				}
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (surfact.num != 0) { // Résolution trinôme.
			delta = fact.multiplier(fact) - 4 * surfact.num / surfact.denom * cons;
			if (delta == 0) {
				frac1.num = -fact.num * surfact.denom;
				frac1.denom = 2 * surfact.num * fact.denom; 
			} 
			else if (delta > 0) {
				solution1 = (-fact.num / fact.denom - Math.sqrt(delta)) / 2 * surfact.num / surfact.denom;
				solution2 = (-fact.num / fact.denom + Math.sqrt(delta)) / 2 * surfact.num / surfact.denom;
				deux = 1;
			} 
			else if (delta < 0) {
				re1.num = -fact.num * surfact.denom;
				re1.denom = 2 * surfact.num * fact.denom;
				im1 = -Math.sqrt(delta) / 2 * surfact.num / surfact.denom;
				re2.num = -fact.num * surfact.num;		
				re2.denom = 2 * surfact1 * fact2;
				im2 = Math.sqrt(delta) / 2 * surfact.num / surfact.denom;
			}
		}
		else if (fact1 != 0) { // Résolution affine.
			solution1 = -cons1 / cons2 * fact2 / fact1;
		}
		else if (cons1 != 0) {
			return "Vous devez utiliser la variable x.";
		}
		else {
			return "Vous devez rentrer une équation.";
		}
		if (re1.denom != 0 && re2.denom != 0) { // Affichage solutions imaginaires.
			sb.append("S = [ ");
			Rationnel simplif1 = re1.simplifier();
			if (simplif1.denom == 1) {
				solution1 = simplif1.num;
				sb.append(solution1);
			}
			else if (simplif1.num == 0) {
				sb.append("0");
			}
			else {
				sb.append(simplif1);
			}
			sb.append(" i + (").append(im1).append(") ; ");
			Rationnel simplif2 = re2.simplifier();
			if (simplif2.denom == 1) {
				solution2 = simplif2.num;
				sb.append(solution2);
			}
			else if (simplif2.num == 0) {
				sb.append("0");
			}
			else {
				sb.append(simplif2);
			}
			sb.append(" i + (").append(im2).append(") ]");
		}
		else if (frac1.denom != 0) { // Affichage solutions fractionnaires.  
			sb.append("S = [ ");
			Rationnel simplif1 = frac1.simplifier();
			if (simplif1.denom == 1) {
				solution1 = simplif1.num;
				sb.append(solution1);
				if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			else if (simplif1.num == 0) {
				sb.append("0");
				if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			else {
				sb.append(simplif1);
				if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			if (deux != 0 && frac2.denom != 0) { // Affichage deuxième solution potentielle.
				sb.append(" ; ");
				Rationnel simplif2 = frac2.simplifier();
				if (simplif2.denom == 1) {
					solution2 = simplif2.num;
					sb.append(solution2);
					if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
				else if (simplif2.num == 0) {
					sb.append("0");
					if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
				else {
					sb.append(simplif2);
					if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
			}
			sb.append(" ]");
		}
		else { // Affichage solutions décimales.
			sb.append("S = [ ").append(solution1);
			if (deux != 0) { // Affichage deuxième solution potentielle.
				sb.append(" ; ").append(solution2);
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			sb.append(" ]");
		}
		return sb.toString();
	}
}
