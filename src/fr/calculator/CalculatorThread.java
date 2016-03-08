package fr.calculator;

/**
 * Thread qui effectue les calculs et les résolutions d'équations, en même temps
 * que la gestion de l'interface graphique. Un nouveau CalculatorThread est
 * lancé à chaque fois qu'on clique sur le bouton "calculer".
 */
public class CalculatorThread extends Thread {

	public CalculatorThread() {
		super("calculator/resolver thread");
	}

	@Override
	public void run() {
		// TODO Mettre ici la résolution d'équation et le calcul de valeurs
	}

}
