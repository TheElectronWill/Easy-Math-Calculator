/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.calculator.resolution;

import fr.calculator.analyse.Fonction.NomFonction;
import fr.calculator.analyse.Fraction;
import fr.calculator.analyse.Terme;
import java.util.EnumMap;

/**
 * Une expression (un seul côté d'une égalité) simplifiée, dont on a extrait les informations importantes pour
 * la résolution: constante, facteur de x, facteur de x², facteurs des fonctions, paramètres des fonctions.
 *
 * @author TheElectronWill
 */
public class ExpressionSimple {

	public Fraction constante, facteurX, facteurX2;
	public EnumMap<NomFonction, Fraction> facteurFonctions;
	public EnumMap<NomFonction, Terme> paramFonctions;

	public ExpressionSimple(Fraction constante, Fraction facteurX, Fraction facteurX2, EnumMap<NomFonction, Fraction> facteurFonctions, EnumMap<NomFonction, Terme> paramFonctions) {
		this.constante = constante;
		this.facteurX = facteurX;
		this.facteurX2 = facteurX2;
		this.facteurFonctions = facteurFonctions;
		this.paramFonctions = paramFonctions;
	}

	public ExpressionSimple() {
		this.constante = new Fraction(0, 1);
		this.facteurX = new Fraction(0, 1);
		this.facteurX2 = new Fraction(0, 1);
		this.facteurFonctions = new EnumMap(NomFonction.class);
		for (NomFonction fonction : NomFonction.values()) {
			facteurFonctions.put(fonction, new Fraction(0, 1));
		}
		this.paramFonctions = new EnumMap(NomFonction.class);
	}

	@Override
	public String toString() {
		return "ExpressionSimple{" + "constante=" + constante + ", facteurX=" + facteurX + ", facteurX2=" + facteurX2 + ", facteurFonctions=" + facteurFonctions + ", paramFonctions=" + paramFonctions + '}';
	}

}
