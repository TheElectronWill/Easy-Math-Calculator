package fr.calculator.resolution;
import fr.calculator.analyse.Fonction.NomFonction; // Imports.
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Variable;
public class MathSolveur {
	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		Rationnel surfact = new Rationnel(0,0), fact = new Rationnel(0,0); // Déclaration des variables.
		double cons = 0; 
		double consExpGauche = 0, consExpDroit = 0;
		double consLogGauche = 0, consLogDroit = 0;
		double consCosGauche = 0, consCosDroit = 0;
		double consSinGauche = 0, consSinDroit = 0;
		double consGauche = 0, consDroit = 0;
		double consTrin = 0;
		double consFonct = 0;
		Rationnel exp = new Rationnel(0,0), expGauche = new Rationnel(0,0), expDroit = new Rationnel(0,0); 
		Rationnel log = new Rationnel(0,0), logGauche = new Rationnel(0,0), logDroit = new Rationnel(0,0);
		Rationnel cos = new Rationnel(0,0), cosGauche = new Rationnel(0,0), cosDroit = new Rationnel(0,0);
		Rationnel sin = new Rationnel(0,0), sinGauche = new Rationnel(0,0), sinDroit = new Rationnel(0,0);
		double delta = 0; 
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0;
		Rationnel frac1 = new Rationnel(0,0), frac2 = new Rationnel(0,0), re1 = new Rationnel(0,0), re2 = new Rationnel(0,0);
		boolean modulo = false, deux = false, pi = false;
		StringBuilder sb = new StringBuilder();
		if (gauche. equals(droit)) { // Les deux parties de l'équation sont identiques.
			return "S = ℝ";
		}
		if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) { // Initialisation des variables.
			consExpGauche = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel)gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		}
		else if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expGauche = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE); // Exponentielle réelle.
		}
		if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
			consExpDroit = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).num / droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom * Math.exp(((Rationnel)droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.EXPONENTIELLE)).denom); // Exponentielle constante.
		}
		else if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Variable) {
			expDroit = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE); // Exponentielle réelle.
		}
		if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || ((((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0))) {
				consLogGauche = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithme néperien constant.
			}
			else {
				return "Le logarithme néperien d'un nombre négatif n'existe pas.";
			}
		}
		else if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logGauche = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN); // Logarithme néperien réel.
		}
		if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
			if ((((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num < 0 && ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom > 0) || (((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num > 0 && ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom < 0)) {
				consLogDroit = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom * Math.log(((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN)).denom); // Logarithmr néperien constant.
			}
			else {
				return "Le logarithme néperien d'un nombre négatif n'existe pas.";
			}
		}
		else if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Variable) {
			logDroit = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN); // Logarithme néperien réel.
		}
		if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			consCosGauche = gauche.facteurFonctions.get(NomFonction.COSINUS).num / gauche.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel)gauche.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		}
		else if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosGauche = gauche.facteurFonctions.get(NomFonction.COSINUS); // Cosinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
			consCosDroit = droit.facteurFonctions.get(NomFonction.COSINUS).num / droit.facteurFonctions.get(NomFonction.COSINUS).denom * Math.cos(((Rationnel)droit.paramFonctions.get(NomFonction.COSINUS)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.COSINUS)).denom); // Cosinus constant.
		}
		else if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Variable) {
			cosDroit = droit.facteurFonctions.get(NomFonction.COSINUS); // Cosinus réel.
		}
		if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			consSinGauche = gauche.facteurFonctions.get(NomFonction.SINUS).num / gauche.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel)gauche.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel)gauche.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		}
		else if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sinGauche = gauche.facteurFonctions.get(NomFonction.SINUS); // Sinus réel.
		}
		if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
			consSinDroit = droit.facteurFonctions.get(NomFonction.SINUS).num / droit.facteurFonctions.get(NomFonction.SINUS).denom * Math.sin(((Rationnel)droit.paramFonctions.get(NomFonction.SINUS)).num / ((Rationnel)droit.paramFonctions.get(NomFonction.SINUS)).denom); // Sinus constant.
		}
		else if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Variable) {
			sinDroit = droit.facteurFonctions.get(NomFonction.SINUS); // Sinus réel.
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
			consTrin = gauche.constante.num * droit.constante.denom - droit.constante.num * gauche.constante.denom / gauche.constante.denom / droit.constante.denom;
		}
		consGauche = consExpGauche + consLogGauche + consCosGauche + consSinGauche; // Réunion des différentes constantes fonction des deux côtés.
		consDroit = consExpDroit + consLogDroit + consCosDroit + consSinDroit;
		consFonct = consGauche + consDroit; // Réunion des constantes fonction de chaque côté.
		cons = consTrin + consFonct; // Réunion des constantes trinôme et fonction.
		exp = expGauche.ajouter(expDroit); // Réunion des deux facteurs d'exponentielle réelle.
		log = logGauche.ajouter(logDroit); // Réunion des deux facteurs de logarithme néperien réel.
		cos = cosGauche.ajouter(cosDroit); // Réunion des deux facteurs de cosinus réel.
		sin = sinGauche.ajouter(sinDroit); // Réunion des deux facteurs de sinus réel. 
		if ((exp.num != 0 && log.num != 0) || (exp.num != 0 && cos.num != 0) || (exp.num != 0 && sin.num != 0) || (log.num != 0 && cos.num != 0) || (log.num != 0 && sin.num != 0) || (cos.num != 0 && sin.num != 0)) { // Il y a deux fonctions réelles à traiter.
			return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
		}
		if (exp.num != 0) { // Il y a une fonction exponentielle à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons < 0) {
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
				solution1 = Math.exp(-cons) / log.num * log.denom;
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (cos.num != 0) { // Il y a une fonction cosinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons == 0) {
					frac1 = cos.multiplier(new Rationnel(1,2));
					frac2 = cos.multiplier(new Rationnel(-1,2));
					pi = true;
					deux = true;
					modulo = true;
				}
				else if (cons == -0.5) {
					frac1 = cos.multiplier(new Rationnel(1,3));
					frac2 = cos.multiplier(new Rationnel(-1,3));
					pi = true;
					deux = true;
					modulo = true;
				}
				else {
					solution1 = Math.acos(-cons) / cos.num * cos.denom;
					solution2 = -Math.acos(-cons) / cos.num * cos.denom;
					deux = true;
					modulo = true;
				}
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (sin.num != 0) { // Il y a une fonction sinus à traiter.
			if (surfact.num == 0 && fact.num == 0) {
				if (cons == -1) {
					frac1 = sin.multiplier(new Rationnel(1,2));
					frac2 = sin.multiplier(new Rationnel(1,2));
					pi = true;
					deux = true;
					modulo = true;
				}
				else if (cons == -0.5) {
					frac1 = sin.multiplier(new Rationnel(1,6));
					frac2 = sin.multiplier(new Rationnel(5,6));
					pi = true;
					deux = true;
					modulo = true;
				}
				else {
					solution1 = Math.asin(-cons) / sin.num * sin.denom;
					solution2 = Math.asin(Math.PI + cons) / sin.num * sin.denom;
					deux = true;
					modulo = true;
				}
			}
			else {
				return "Cette équation ne peut être résolue à l'aide de ce logiciel.";
			}
		}
		else if (surfact.num != 0) { // Résolution trinôme.
			delta = fact.multiplier(fact).num / fact.multiplier(fact).denom - 4 * surfact.num / surfact.denom * cons;
			if (delta == 0) {
				frac1.num = -fact.num * surfact.denom;
				frac1.denom = 2 * surfact.num * fact.denom; 
			} 
			else if (delta > 0) {
				solution1 = (-fact.num / fact.denom - Math.sqrt(delta)) / 2 * surfact.num / surfact.denom;
				solution2 = (-fact.num / fact.denom + Math.sqrt(delta)) / 2 * surfact.num / surfact.denom;
				deux = true;
			} 
			else if (delta < 0) {
				re1.num = -fact.num * surfact.denom;
				re1.denom = 2 * surfact.num * fact.denom;
				im1 = -Math.sqrt(delta) / 2 * surfact.num / surfact.denom;
				re2.num = -fact.num * surfact.num;		
				re2.denom = 2 * surfact.num * fact.denom;
				im2 = Math.sqrt(delta) / 2 * surfact.num / surfact.denom;
			}
		}
		else if (fact.num != 0) { // Résolution affine.
			solution1 = -cons * fact.denom / fact.num;
		}
		else if (cons != 0) {
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
				if (pi == true) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			else if (simplif1.num == 0) {
				sb.append("0");
				if (pi == true) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			else {
				sb.append(simplif1);
				if (pi == true) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
					sb.append("π");
				}
				if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			if (deux == true && frac2.denom != 0) { // Affichage deuxième solution potentielle.
				sb.append(" ; ");
				Rationnel simplif2 = frac2.simplifier();
				if (simplif2.denom == 1) {
					solution2 = simplif2.num;
					sb.append(solution2);
					if (pi == true) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
				else if (simplif2.num == 0) {
					sb.append("0");
					if (pi == true) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
				else {
					sb.append(simplif2);
					if (pi == true) { // Affichage pi pour les valeurs remarquables d'équations avec cosinus et sinus.
						sb.append("π");
					}
					if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
						sb.append("[2π]");
					}
				}
			}
			sb.append(" ]");
		}
		else { // Affichage solutions décimales.
			sb.append("S = [ ").append(solution1);
			if (deux == true) { // Affichage deuxième solution potentielle.
				sb.append(" ; ").append(solution2);
				if (modulo == true) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
					sb.append("[2π]");
				}
			}
			sb.append(" ]");
		}
		return sb.toString();
	}
}
