package fr.calculator.resolution;
import java.util.Map;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Terme;
public class MathSolveur {
	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		int u = 0, u1 = 1, v = 0, v1 = 1; // Déclaration des variables.
		double w = 0, w1 = 1; 
		double w21 = 0, w22 = 0, w31 = 0, w32 = 0; 
		int wa = 0, wb = 0; 
		double wc = 0, wd = 0;
		int a = 0, a1 = 0, a21 = 0, a22 = 0, a31 = 0, a32 = 0; 
		int b = 0, b1 = 0, b21 = 0, b22 = 0, b31 = 0, b32 = 0; 
		int c = 0, c1 = 0, c21 = 0, c22 = 0, c31 = 0, c32 = 0; 
		int d = 0, d1 = 0, d21 = 0, d22 = 0, d31 = 0 , d32 = 0;
		double delta = 0; 
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0, re3 = 0, re4 = 0;
		Rationnel frac1 = new Rationnel(0), frac2 = new Rationnel(0), re1 = new Rationnel(0), re2 = new Rationnel(0);
		int modulo = 0, deux = 0, pi = 0;
		StringBuilder sb = new StringBuilder();
		if (gauche. equals(droit)) { // Les deux parties de l'équation sont identiques.
			sb.append("Tous les nombres sont solutions de cette équation.");
		}
		else {
			if (gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom != 0) { // Initialisation des variables.
				a21 = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).num;
				a22 = gauche.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom;
			}
			if (gauche.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
				w21 = Math.exp(gauche.paramFonctions.get(NomFonction.EXPONENTIELLE).num / gauche.paramFonctions.get(NomFonction.EXPONENTIELLE).denom);
				w22 = 1;
			}
			if (droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom != 0) {
				a31 = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).num;
				a32 = droit.facteurFonctions.get(NomFonction.EXPONENTIELLE).denom;
			}
			if (droit.paramFonctions.get(NomFonction.EXPONENTIELLE) instanceof Rationnel) {
				w31 = Math.exp(droit.paramFonctions.get(NomFonction.EXPONENTIELLE).num / droit.paramFonctions.get(NomFonction.EXPONENTIELLE).denom);
				w32 = 1;
			}
			if (gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom != 0) {
				b21 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num;
				b22 = gauche.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom;
			}
			if (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
				if ((gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num > 0 && gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom > 0) || (gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num < 0 && gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom < 0)) {
					w21 = Math.log(gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / gauche.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom);
					w22 = 1;
				}
				else {
					sb.append("Le logarithme néperien d'un nombre négatif n'existe pas.");
				}
			}
			if (droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom != 0) {
				b31 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num;
				b32 = droit.facteurFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom;
			}
			if (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN) instanceof Rationnel) {
				if ((droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num > 0 && droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom > 0) || (droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num < 0 && droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom < 0)) {
					w31 = Math.log(droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).num / droit.paramFonctions.get(NomFonction.LOGARITHME_NEPERIEN).denom);
					w32 = 1;
				}
				else {
					sb.append("Le logarithme néperien d'un nombre négatif n'existe pas.");
				}
			}
			if (gauche.facteurFonctions.get(NomFonction.COSINUS).denom != 0) {
				c21 = gauche.facteurFonctions.get(NomFonction.COSINUS).num;
				c22 = gauche.facteurFonctions.get(NomFonction.COSINUS).denom;
			}
			if (gauche.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
				w21 = Math.cos(gauche.paramFonctions.get(NomFonction.COSINUS).num / gauche.paramFonctions.get(NomFonction.COSINUS).denom);
				w22 = 1;
			}
			if (droit.facteurFonctions.get(NomFonction.COSINUS).denom != 0) {
				c31 = droit.facteurFonctions.get(NomFonction.COSINUS).num;
				c32 = droit.facteurFonctions.get(NomFonction.COSINUS).denom;
			}
			if (droit.paramFonctions.get(NomFonction.COSINUS) instanceof Rationnel) {
				w31 = Math.cos(droit.paramFonctions.get(NomFonction.COSINUS).num / droit.paramFonctions.get(NomFonction.COSINUS).denom);
				w32 = 1;
			}
			if (gauche.facteurFonctions.get(NomFonction.SINUS).denom != 0) {
				d21 = gauche.facteurFonctions.get(NomFonction.SINUS).num;
				d22 = gauche.facteurFonctions.get(NomFonction.SINUS).denom;
			}
			if (gauche.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
				w21 = Math.sin(gauche.paramFonctions.get(NomFonction.SINUS).num / droit.paramFonctions.get(NomFonction.SINUS).denom);
				w22 = 1;
			}
			if (droit.facteurFonctions.get(NomFonction.SINUS).denom != 0) {
				d31 = droit.facteurFonctions.get(NomFonction.SINUS).num;
				d32 = droit.facteurFonctions.get(NomFonction.SINUS).denom;
			}
			if (droit.paramFonctions.get(NomFonction.SINUS) instanceof Rationnel) {
				w31 = Math.sin(droit.paramFonctions.get(NomFonction.SINUS).num / gauche.paramFonctions.get(NomFonction.SINUS).denom);
				w32 = 1;
			}
			if (gauche.facteurX2.denom != 0 && droit.facteurX2.denom != 0) { // Réunion des deux facteurs de x².
				u =  gauche.facteurX2.num * droit.facteurX2.denom - droit.facteurX.num * gauche.facteurX2.denom;
				u1 = gauche.facteurX2.denom * droit.facteurX2.denom;
			}
			if (gauche.facteurX.denom != 0 && droit.facteurX.denom != 0) { // Réunion des deux facteurs de x.
				v = gauche.facteurX.num * droit.facteurX.denom - droit.facteurX.num * gauche.facteurX.denom;
				v1 = gauche.facteurX.denom * droit.facteurX.denom;
			}	
			if (gauche.constante.denom != 0 && droit.constante.denom != 0) { // Réunion des deux constantes.
				wa = gauche.constante.num * droit.constante.denom - droit.constante.num * gauche.constante.denom;
				wb = gauche.constante.denom * droit.constante.denom;
			}
			if (w22 != 0 && w32 != 0) {
				wc = w21*w32 - w31*w22;
				wd = w22 * w32;
			}
			w = wa*wd + wc*wb;
			w1 = wb * wd;
			a = a21*a32 - a22*a31; 
			a1 = a31 * a32;
			b = b21*b32 - b22*b31;
			b1 = b31 * b32;
			c = c21*c32 - c22*c31;
			c1 = c31 * c32;
			d = d21*d32 - d22*d31;
			d1 = d31 * d32;
		if ((a != 0 && b != 0) || (a != 0 && c != 0) || (a != 0 && d != 0) || (b != 0 && c != 0) || (b != 0 && d != 0) || (c != 0 && d != 0)) { // Il y a deux fonctions réelles à traiter.
			sb.append("Cette équation ne peut être résolue à l'aide de ce logiciel.");
		}
		else if (a != 0) { // Il y a une fonction exponentielle à traiter.
			if (u == 0 && v == 0) {
				if ((w > 0 && w1 > 0) || (w < 0 && w1 < 0)) {
					solution1 = Math.log(-w/w1) / a * a1;
				}
				else {
					sb.append("Cette équation n'admet aucune solution.");
				}
			}
			else {
				sb.append("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (b != 0) { // Il y a une fonction logarithme néperien à traiter.
			if (u == 0 && v == 0) {
				solution1 = Math.exp(-w/w1) / b * b1;
			}
			else {
				sb.append("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (c != 0) { // Il y a une fonction cosinus à traiter.
			if (u == 0 && v == 0) {
				if (w == 0) {
					frac1.num = 1 * c1;
					frac1.denom = 2 * c;
					pi = 1;
					frac2.num = -1 * c1;
					frac2.denom = 2 * c;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else if ((w == -1 && w1 == 2) || (w == 1 && w1 == -2)) {
					frac1.num = 1 * c1;
					frac1.denom = 3 * c;
					frac2.num = -1 * c1;
					frac2.denom = 3 * c;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else {
					solution1 = Math.acos(-w/w1) / c * c1;
					solution2 = -Math.acos(-w/w1) / c * c1;
					deux = 1;
					modulo = 1;
				}
			}
			else {
				sb.append("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (d != 0) { // Il y a une fonction sinus à traiter.
			if (u == 0 && v == 0) {
				if ((w == -1 && w1 == 1) || (w == 1 && w1 == -1)) {
					frac1.num = 1 * b1;
					frac1.denom = 2 * b;
					frac2.num = 1 * b1;
					frac2.denom = 2 * b;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else if ((w == -1 && w1 == 2) || (w == 1 && w1 == -2)) {
					frac1.num = 1 * b1;
					frac1.denom = 6 * b;
					frac2.num = 5 * b1;
					frac2.denom = 6 * b;
					pi = 1;
					deux = 1;
					modulo = 1;
				}
				else {
					solution1 = Math.asin(-w/w1) / b * b1;
					solution2 = Math.asin(Math.PI + w/w1) / b * b1;
					deux = 1;
					modulo = 1;
				}
			}
			else {
				sb.append("Cette équation ne peut être résolue à l'aide de ce logiciel.");
			}
		}
		else if (u != 0) { // Résolution trinôme.
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
		else if (v != 0) {
			if (w != 0 && v != 0) { // Résolution affine.
				frac1.num = -w * v1;
				frac1.denom = v * w1;
			} 
			else if (w == 0 && v != 0) {
				solution1 = 0;
			} 
			else if (v == 0) {
				sb.append("Tous les nombres sont solutions de cette équation.");
			}
		}
		else if (w != 0) {
			sb.append("Vous devez utiliser la variable x.");
		}
		else {
			sb.append("Vous devez rentrer une équation.");
		}
		if (re1.denom != 0 && re2.denom != 0) { // Affichage solutions imaginaires.
			sb.append("S = [ ").append(re1.num).append("/").append(re1.denom).append(" i + (").append(im1).append(") ; ").append(re2.num).append("/").append(re2.denom).append(" i + (").append(im2).append(") ]");
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
