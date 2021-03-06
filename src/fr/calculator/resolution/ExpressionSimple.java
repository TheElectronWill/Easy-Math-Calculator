/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.calculator.resolution;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import fr.calculator.analyse.Fonction;
import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Multiplication;
import fr.calculator.analyse.Puissance;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Terme;
import fr.calculator.analyse.Variable;

/**
 * Une expression (un seul côté d'une égalité) simplifiée, dont on a extrait les informations importantes pour la
 * résolution: constante, facteur de x, facteur de x², facteurs des fonctions, paramètres des fonctions.
 *
 * @author Guillaume
 */
public class ExpressionSimple {

	public Rationnel constante, facteurX, facteurX2;
	public EnumMap<NomFonction, Rationnel> facteurFonctions;
	public EnumMap<NomFonction, Terme> paramFonctions;

	public ExpressionSimple(Rationnel constante, Rationnel facteurX, Rationnel facteurX2,
			EnumMap<NomFonction, Rationnel> facteurFonctions, EnumMap<NomFonction, Terme> paramFonctions) {
		this.constante = constante;
		this.facteurX = facteurX;
		this.facteurX2 = facteurX2;
		this.facteurFonctions = facteurFonctions;
		this.paramFonctions = paramFonctions;
	}

	public ExpressionSimple() {
		this.constante = new Rationnel(0, 1);
		this.facteurX = new Rationnel(0, 1);
		this.facteurX2 = new Rationnel(0, 1);
		this.facteurFonctions = new EnumMap(NomFonction.class);
		for (NomFonction fonction : NomFonction.values()) {
			facteurFonctions.put(fonction, new Rationnel(0, 1));
		}
		this.paramFonctions = new EnumMap(NomFonction.class);
	}

	/**
	 * Simplifie les facteurs de cette ExpressionSimple, ainsi que les paramètres des fonctions.
	 *
	 * @return cette ExpressionSimple
	 */
	public ExpressionSimple simplifier() {
		constante.simplifier();
		facteurX.simplifier();
		facteurX2.simplifier();
		for (Rationnel facteurFonction : facteurFonctions.values()) {
			facteurFonction.simplifier();
		}
		for (Map.Entry<NomFonction, Terme> e : paramFonctions.entrySet()) {
			e.setValue(e.getValue().simplifier());
		}
		return this;
	}

	/**
	 * Créer une liste de termes qui correspond à cette expression.
	 */
	public List<Terme> getTermes() {
		List<Terme> termes = new ArrayList<>();
		if (constante.num != 0) {
			termes.add(constante.simplifier());
		}
		if (facteurX.num != 0) {
			termes.add(new Multiplication(facteurX, new Variable("x")).simplifier());
		}
		if (facteurX2.num != 0) {
			termes.add(new Multiplication(facteurX2, new Puissance(new Variable("x"), new Rationnel(2))).simplifier());
		}
		for (Map.Entry<NomFonction, Rationnel> e : facteurFonctions.entrySet()) {
			NomFonction nom = e.getKey();
			Rationnel facteur = e.getValue();
			Terme param = paramFonctions.get(nom);
			if (facteur.num != 0) {
				termes.add(new Multiplication(facteur, new Fonction(nom, param)).simplifier());
			}
		}
		return termes;
	}

	/**
	 * Crée une chaîne de caractères qui contient une expression mathématique correspondant à cet objet
	 * ExpressionSimple. Il faut d'abord appeler la méthode {@link #simplifier()} au moins une fois avant d'appeler
	 * celle-ci.
	 */
	public String toMathString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<NomFonction, Rationnel> e : facteurFonctions.entrySet()) {
			NomFonction nom = e.getKey();
			Rationnel facteur = e.getValue();
			Terme param = paramFonctions.get(nom);
			if (facteur.num != 0) {//facteur non nul
				if (sb.length() > 0) {//ce n'est pas le premier terme qu'on écrit, il faut donc mettre un + ou un - devant
					if (facteur.num < 0) {
						sb.append(" - ");
						facteur.negatif();//pour ne pas ajouter un autre signe moins lors de sb.append(facteur)
					} else {
						sb.append(" + ");
					}
				}
				if (facteur.denom == 1) {
					if (facteur.num > 1) {
						sb.append(facteur.num);
					}
				} else {
					sb.append('(');
					sb.append(facteur);
					sb.append(')');
				}
				sb.append(nom);
				sb.append('(');
				sb.append(param);
				sb.append(')');
			}
		}
		if (facteurX2.num != 0) {//facteur non nul
			if (sb.length() > 0) {//ce n'est pas le premier terme qu'on écrit, il faut donc mettre un + ou un - devant
				if (facteurX2.num < 0) {
					sb.append(" - ");
					facteurX2.negatif();//pour ne pas ajouter un autre signe moins lors de sb.append(facteurX2)
				} else {
					sb.append(" + ");
				}
			}
			if (facteurX2.denom == 1) {
				if (facteurX2.num > 1) {
					sb.append(facteurX2.num);
				}
			} else {
				sb.append('(');
				sb.append(facteurX2);
				sb.append(')');
			}
			sb.append("x^2");
		}
		if (facteurX.num != 0) {//facteur non nul
			if (sb.length() > 0) {//ce n'est pas le premier terme qu'on écrit, il faut donc mettre un + ou un - devant
				if (facteurX.num < 0) {
					sb.append(" - ");
					facteurX.negatif();//pour ne pas ajouter un autre signe moins lors de sb.append(facteurX)
				} else {
					sb.append(" + ");
				}
			}
			if (facteurX.denom == 1) {
				if (facteurX.num > 1) {
					sb.append(facteurX.num);
				}
			} else {
				sb.append('(');
				sb.append(facteurX);
				sb.append(')');
			}
			sb.append("x");
		}
		if (constante.num != 0) {//facteur non nul
			if (sb.length() > 0) {//ce n'est pas le premier terme qu'on écrit, il faut donc mettre un + ou un - devant
				if (constante.num < 0) {
					sb.append(" - ");
					constante.negatif();//pour ne pas ajouter un autre signe moins lors de sb.append(constante)
				} else {
					sb.append(" + ");
				}
			}
			if (constante.denom == 1) {
				sb.append(constante.num);
			} else {
				sb.append(constante);
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "ExpressionSimple{" + "constante=" + constante + ", facteurX=" + facteurX + ", facteurX2=" + facteurX2
				+ ", facteurFonctions=" + facteurFonctions + ", paramFonctions=" + paramFonctions + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ExpressionSimple) {
			ExpressionSimple expression = (ExpressionSimple) obj;
			return expression.constante.equals(constante) && expression.facteurX.equals(facteurX)
					&& expression.facteurX2.equals(facteurX2) && expression.facteurFonctions.equals(facteurFonctions)
					&& expression.paramFonctions.equals(paramFonctions);
		}
		return false;
	}

}
