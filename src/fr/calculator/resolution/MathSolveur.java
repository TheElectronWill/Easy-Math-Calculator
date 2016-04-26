package fr.calculator.resolution;
import java.util.Map;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Terme;
public class MathSolveur {
	public static String resoudre(ExpressionSimple gauche, ExpressionSimple droit) {
		int fact1 = 0, gauche.facteurX.num = 0, gauche.facteurX.denom = 0, fact2 = 0, droit.facteurX.num = 0, droit.facteurX.denom = 0; // Déclaration  et initialisation des variables générales.
		int cons1 = 0, gauche.constante.num = 0, gauche.constante.denom = 0, cons2 = 0, droit.constante.num = 0, droit.constante.denom = 0;
		int surfact1 = 0, gauche.facteurX2. = 0, gauche.facteurX2.denom = 0, surfact2 = 0, droit.facteurX.num = 0, droit.facteurX2.denom = 0;
		int u = 0, u1 = 1, v = 0, v1 = 1, w = 0, w1 = 1, delta = 0;
		double solution1 = 0, solution2 = 0, im1 = 0, im2 = 0, re3 = 0, re4 = 0;
		Rationnel frac1 = new Rationnel(0), frac2 = new Rationnel(0), re1 = new Rationnel(0), re2 = new Rationnel(0);
		int modulo = 0, deux = 0;
		StringBuilder sb = new StringBuilder();
		if (gauche. equals(droit)) { // Les deux parties de l'équation sont identiques.
			sb.append("L'égalité est toujours vraie.");
		}
		else {
			if (gauche.facteurX2.denom != 0 && droit.facteurX2.denom != 0) { // Réunion des deux facteurs de x².
				u =  gauche.facteurX2.num * droit.facteurX2.denom - droit.facteurX.num * gauche.facteurX2.denom;
				u1 = gauche.facteurX2.denom * droit.facteurX2.denom;
			}
			if (gauche.facteurX.denom != 0 && droit.facteurX.denom != 0) { // Réunion des deux facteurs de x.
				v = gauche.facteurX.num * droit.facteurX.denom - droit.facteurX.num * gauche.facteurX.denom;
				v1 = gauche.facteurX.denom * droit.facteurX.denom;
			}	
			if (gauche.constante.denom != 0 && droit.constante.denom != 0) { // Réunion des deux constantes.
				w = gauche.constante.num * droit.constante.denom - droit.constante.num * gauche.constante.denom;
				w1 = gauche.constante.denom * droit.constante.denom;
			}
			if (
		}
		if (gauche.paramFonctions.isEmpty() && droit.paramFonctions.isEmpty()) {
			if (gauche.facteurX2.denom != 0 && droit.facteurX2.denom != 0) { // Réunion des deux facteurs de x².
				u =  gauche.facteurX2.num * droit.facteurX2.denom - droit.facteurX.num * gauche.facteurX2.denom;
				u1 = gauche.facteurX2.denom * droit.facteurX2.denom;
			}
			if (gauche.facteurX.denom != 0 && droit.facteurX.denom != 0) { // Réunion des deux facteurs de x.
				v = gauche.facteurX.num * droit.facteurX.denom - droit.facteurX.num * gauche.facteurX.denom;
				v1 = gauche.facteurX.denom * droit.facteurX.denom;
			}
			if (gauche.constante.denom != 0 && droit.constante.denom != 0) { // Réunion des deux constantes.
				w = gauche.constante.num * droit.constante.denom - droit.constante.num * gauche.constante.denom;
				w1 = gauche.constante.denom * droit.constante.denom;
			}
			if (u == 0) {
				if (w != 0 && v != 0) { // Résolution affine.
					frac1.num = -w * v1;
					frac1.denom = v * w1;
				} else if (w == 0 && v != 0) {
					solution1 = 0;
				} else if (v == 0) {
					sb.append("R");
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
		else {
			for (Map.Entry<NomFonction, Terme> entry : gauche.paramFonctions.entrySet()) {
				
			}
		}
		if (deux == 0 && modulo == 0 && frac1.num == 0 && frac1.num == 0) { // Affichage solutions décimales.
			sb.append("x = ").append(solution1);

		} else if ((deux == 1 || modulo == 1) && frac1.num == 0 && frac1.num == 0) {
			sb.append("x = ").append(solution1).append(" ou x = ").append(solution2);
		} else if (frac1.num != 0 && frac1.denom != 0) { // Affichage solutions fractionnaires.
			Rationnel simplifié = frac1.simplifier();
			if (simplifié.denom == 1) {
				solution1 = simplifié.num;
				sb.append("x = ").append(solution1);
			}
			else if (simplifié.num == 0) {
				sb.append("x = 0");
			}
			else {
				sb.append("x = ").append(simplifié.num).append("/").append(simplifié.denom);
			}
		}
		else { // Affichage solutions imaginaires.
			sb.append("x = ").append(re1.num).append("/").append(re1.denom).append(" i + (").append(im1).append(") ou x = ").append(re2.num).append("/").append(re2.denom).append(" i + (").append(im2).append(")");
		}
		if (modulo == 1) { // Affichage modulo pi pour les solutions d'équations avec cosinus et sinus.
			sb.append("\n[2*pi]");
		}
		return sb.toString();
	}
}
