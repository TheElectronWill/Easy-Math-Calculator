package fr.calculator.resolution;
import java.util.Map; // Imports.
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Terme;
public class MathSolveur {
	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		int surfact = 0, surfact1 = 1, fact = 0, fact1 = 1; // Déclaration des variables.
		double cons = 0, cons1 = 1; 
		double consExpGauche1 = 0, consExpGauche2 = 1, consExpDroit1 = 0, consExpDroit2 = 1;
		double consLogGauche1 = 0, consLogGauche2 = 1, consLogDroit1 = 0, consLogDroit2 = 1;
		double consCosGauche1 = 0, consCosGauche2 = 1, consCosDroit1 = 0, consCosDroit2 = 1;
		double consSinGauche1 = 0, consSinGauche2 = 1, consSinDroit1 = 0, consSinDroit2 = 1;
		double consGauche1 = 0, consGauche2 = 1, consDroit1 = 0, consDroit2 = 1;
		int consTrin1 = 0, consTrin2 = 1;
		double consFonct1 = 0, consFonct2 = 1;
		int exp = 0, exp1 = 1, expGauche1 = 0, expGauche2 = 1, expDroit1 = 0, expdDoit2 = 1; 
		int log = 0, log1 = 1, logGauche1 = 0, logGauche2 = 1, logDroit1 = 0, logdDoit2 = 1; 
		int cos = 0, cos1 = 1, cosGauche1 = 0, cosGauche2 = 1, cosDroit1 = 0, cosdDoit2 = 1; 
		int sin = 0, sin1 = 1, sinGauche1 = 0, sinGauche2 = 1, sinDroit1 = 0 , sinDroit2 = 1;
		double delta = 0; 
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
		Rationnel frac1 = new Rationnel(0), frac2 = new Rationnel(0), re1 = new Rationnel(0), re2 = new Rationnel(0);
		int modulo = 0, deux = 0, pi = 0;
		StringBuilder sb = new StringBuilder();
		if (gauche. equals(droit)) { // Les deux parties de l'équation sont identiques.
			return ("S = ℝ");
		}
		if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) { // Initialisation des variables.
			consexpgauche1 = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel)gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		}
		else if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expgauche1 = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).num; // Exponentielle réelle.
			expgauche2 = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
			consexpdroit1 = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel)droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		}
		else if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expdroit1 = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).num; // Exponentielle réelle.
			expdroit2 = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if (((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || (((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0) {
				consloggauche1 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithme néperien constant.
			}
			else {
				return ("Le logarithme néperien d'un nombre négatif n'existe pas.");
			}
		}
		else if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			loggauche1 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num; // Logarithme néperien réel.
			loggauche2 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if (((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || (((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0) {
				conslogdroit1 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithmr néperien constant.
			}
			else {
				return ("Le logarithme néperien d'un nombre négatif n'existe pas.");
			}
		}
		else if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logdroit1 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num; // Logarithme néperien réel.
			logdroit2 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			conscosgauche1 = gauche.facteurFonctions.get(NomFonction.COSINUS).num / gauche.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel)gauche.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		}
		else if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosgauche1 = gauche.facteurFonctions.get(NomFonction.COSINUS).num; // Cosinus réel.
			cosgauche2 = gauche.facteurFonctions.get(NomFonction.COSINUS).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			conscosdroit1 = droit.facteurFonctions.get(NomFonction.COSINUS).num / droit.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel)droit.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		}
		else if (roit.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosdroit1 = droit.facteurFonctions.get(NomFonction.COSINUS).num; // Cosinus réel.
			cosdroit2 = droit.facteurFonctions.get(NomFonction.COSINUS).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			conssingauche1 = gauche.facteurFonctions.get(NomFonction.SINUS).num / gauche.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel)gauche.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.SINUS).denom)); // Sinus constant.
		}
		else if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			singauche1 = gauche.facteurFonctions.get(NomFonction.SINUS).num; // Sinus réel.
			singauche2 = gauche.facteurFonctions.get(NomFonction.SINUS).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			conssindroit1 = droit.facteurFonctions.get(NomFonction.SINUS).num / droit.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel)droit.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		}
		else if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sindroit1 = droit.facteurFonctions.get(NomFonction.SINUS).num; // Sinus réel.
			sindroit2 = droit.facteurFonctions.get(NomFonction.SINUS).denom;
		}
		else {
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (gauche.facteurX2.denom != 0 && droit.facteurX2.denom != 0) { // Réunion des deux facteurs de x².
			surfact =  gauche.facteurX2.num * droit.facteurX2.denom - droit.facteurX.num * gauche.facteurX2.denom;
			surfact1 = gauche.facteurX2.denom * droit.facteurX2.denom;
		}
		if (gauche.facteurX.denom != 0 && droit.facteurX.denom != 0) { // Réunion des deux facteurs de x.
			fact = gauche.facteurX.num * droit.facteurX.denom - droit.facteurX.num * gauche.facteurX.denom;
			fact1 = gauche.facteurX.denom * droit.facteurX.denom;
		}	
		if (gauche.constante.denom != 0 && droit.constante.denom != 0) { // Réunion des deux constantes trinôme.
			constrin1 = gauche.constante.num * droit.constante.denom - droit.constante.num * gauche.constante.denom;
			constrin2 = gauche.constante.denom * droit.constante.denom;
		}
		consgauche1 = consexpgauche1*consloggauche2*conscosgauche2*conssingauche2 + consloggauche1*consexpgauche2*conscosgauche2*conssingauche2 + conscosgauche1*consloggauche2*consexpgauche2*conssingauche2 + conssingauche1*consloggauche2*conscosgauche2*consexpgauche2; // Réunion des différentes constantes fonction des deux côtés.
		consgauche2 = consexpgauche2*consloggauche2*conscosgauche2*conssingauche2;
		consdroit1 = consexpdroit1*conslogdroit2*conscosdroit2*conssindroit2 + conslogdroit1*consexpdroit2*conscosdroit2*conssindroit2 + conscosdroit1*conslogdroit2*consexpdroit2*conssindroit2 + conssindroit1*conslogdroit2*conscosdroit2*consexpdroit2;
		consdroit2 = consexpdroit2*conslogdroit2*conscosdroit2*conssindroit2;
		consfonct1 = consgauche1*consdroit2 - consdroit1*consgauche2; // Réunion des constantes fonction de chaque côté.
		consfonct2 = consgauche2 * consdroit2;
		cons = constrin1*consfonct2 + consfonct1*constrin2; // Réunion des constantes trinôme et fonction.
		cons1 = constrin2 * consfonct2;
		exp = expgauche1*expdroit2 - expgauche2*expdroit1; // Réunion des deux facteurs d'exponentielle réelle.
		exp1 = expdroit1 * expdroit2;
		log = loggauche1*logdroit2 - loggauche2*logdroit1; // Réunion des deux facteurs de logarithme néperien réel.
		log1 = logdroit1 * logdroit2;
		cos = cosgauche1*cosdroit2 - cosgauche2*cosdroit1; // Réunion des deux facteurs de cosinus réel.
		cos1 = cosdroit1 * cosdroit2;
		sin = singauche1*sindroit2 - singauche2*sindroit1; // Réunion des deux facteurs de sinus réel. 
		sin1 = sindroit1 * sindroit2;
		if ((exp != 0 && log != 0) || (exp != 0 && cos != 0) || (exp != 0 && sin != 0) || (log != 0 && cos != 0) || (log != 0 && sin != 0) || (cos != 0 && sin != 0)) { // Il y a deux fonctions réelles à traiter.
			return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		if (exp != 0) { // Il y a une fonction exponentielle à traiter.
			if (surfact == 0 && fact == 0) {
				if ((cons > 0 && cons1 > 0) || (cons < 0 && cons1 < 0)) {
					solution1 = Math.log(-cons/cons1) / exp * exp1;
				}
				else {
					return ("S = ∅");
				}
			}
			else {
				return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (log != 0) { // Il y a une fonction logarithme néperien à traiter.
			if (surfact == 0 && fact == 0) {
				solution1 = Math.exp(-cons/cons1) / log * log1;
			}
			else {
				return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (cos != 0) { // Il y a une fonction cosinus à traiter.
			if (surfact == 0 && fact == 0) {
				if (cons == 0) {
					frac1.num = 1 * cos1;
					frac1.denom = 2 * cos;
					pi = 1;
					frac2.num = -1 * cos1;
					frac2.denom = 2 * cos;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else if ((cons == -1 && cons1 == 2) || (cons == 1 && cons1 == -2)) {
					frac1.num = 1 * cos1;
					frac1.denom = 3 * cos;
					frac2.num = -1 * cos1;
					frac2.denom = 3 * cos;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else {
					solution1 = Math.acos(-cons/cons1) / cos * cos1;
					solution2 = -Math.acos(-cons/cons1) / cos * cos1;
					deux = 1;
					modulo = 1;
				}
			}
			else {
				return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (sin != 0) { // Il y a une fonction sinus à traiter.
			if (surfact == 0 && fact == 0) {
				if ((cons == -1 && cons1 == 1) || (cons == 1 && cons1 == -1)) {
					frac1.num = 1 * sin1;
					frac1.denom = 2 * sin;
					frac2.num = 1 * sin1;
					frac2.denom = 2 * sin;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else if ((cons == -1 && cons1 == 2) || (cons == 1 && cons1 == -2)) {
					frac1.num = 1 * sin1;
					frac1.denom = 6 * sin;
					frac2.num = 5 * sin1;
					frac2.denom = 6 * sin;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else {
					solution1 = Math.asin(-cons/cons1) / sin * sin1;
					solution2 = Math.asin(Math.PI + cons/cons1) / sin * sin1;
					deux = 1;
					modulo = 1;
				}
			}
			else {
				return ("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (surfact != 0) { // Résolution trinôme.
			delta = fact * fact / fact1 / fact1 - 4 * surfact * cons / surfact1 / cons1;
			if (delta == 0) {
				if (fact != 0) {
					frac1.num = -fact * surfact1;
					frac1.denom = 2 * surfact * fact1;
				} 
				else if (fact == 0) {
					solution1 = 0;
				}
			} 
			else if (delta > 0) {
				solution1 = (-fact / fact1 - Math.sqrt(delta)) / 2 * surfact / surfact1;
				solution2 = (-fact / fact1 + Math.sqrt(delta)) / 2 * surfact / surfact1;
				deux = 1;
			} 
			else if (delta < 0) {
				if (fact != 0) {
					re1.num = -fact * surfact1;
					re1.denom = 2 * surfact * fact1;
				} 
				else if (fact == 0) {
					re3 = 0;
				}
				im1 = -Math.sqrt(delta) / 2 * surfact / surfact1;
				if (fact != 0) {
					re2.num = -fact * surfact1;		
					re2.denom = 2 * surfact * fact1;
				} 
				else if (fact == 0) {
					re4 = 0;
				}
				im2 = Math.sqrt(delta) / 2 * surfact / surfact1;
			}
		}
		else if (fact != 0) {
			if (cons != 0 && fact != 0) { // Résolution affine.
				frac1.num = -cons * fact1;
				frac1.denom = fact * cons1;
			} 
			else if (cons == 0 && fact != 0) {
				solution1 = 0;
			} 
			else if (fact == 0) {
				return ("S = ℝ");
			}
		}
		else if (cons != 0) {
			return ("Vous devez utiliser la variable x.");
		}
		else {
			return ("Vous devez rentrer une équation.");
		}
		if (re1.denom != 0 && re2.denom != 0) { // Affichage solutions imaginaires.
			sb.append("S = [ ");
			Rationnel simplifié1 = re1.simplifier();
			if (simplifié1.denom == 1) {
				solution1 = simplifié1.num;
				sb.append(solution1);
			}
			else if (simplifié.num == 0) {
				sb.append("0");
			}
			else {
				sb.append(simplifié1.num).append("/").append(silplifié1.denom)
			}
			sb.append(" i + (").append(im1).append(") ; ")sb.append(" i + (").append(im2).append(") ]");
			Rationnel simplifié2 = re2.simplifier();
			if (simplifié2.denom == 1) {
				solution2 = simplifié2.num;
				sb.append(solution2);
			}
			else if (simplifié.num == 0) {
				sb.append("0");
			}
			else {
				sb.append(simplifié2.num).append("/").append(simplifié2.denom);
			}
			sb.append(" i + (").append(im2).append(") ]");
		}
		else if (frac1.denom != 0) { // Affichage solutions fractionnaires.  
			sb.append("S = [ ");
			Rationnel simplifié = frac1.simplifier();
			if (simplifié.denom == 1) {
				solution1 = simplifié.num;
				sb.append(solution1);
				if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			else if (simplifié.num == 0) {
				sb.append("0");
				if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			else {
				sb.append(simplifié.num).append("/").append(simplifié.denom);
				if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			if (deux != 0 && frac2.denom != 0) { // Affichage deuxième solution potentielle.
				sb.append(" ; ");
				Rationnel simplifié2 = frac2.simplifier();
				if (simplifié2.denom == 1) {
					solution2 = simplifié2.num;
					sb.append(solution2);
					if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
				else if (simplifié2.num == 0) {
					sb.append("0");
					if (pi == 1) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
				else {
					sb.append(simplifié2.num).append("/").append(simplifié2.denom);
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
