package fr.calculator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import fr.calculator.parser.MathAnalyseur;
import fr.calculator.parser.Term;
import fr.calculator.solver.MathSimplifieur;
import fr.calculator.solver.MathSolveur;

/**
 * Un <code>SwingWorker</code> qui effectue les calculs dans un autre Thread que l'EDT (Event Dispatch Thread, le thread dans lequel doit
 * s'effectuer toute modification de composant graphique).
 * <p>
 * Un nouveau <code>Calculator</code> est créé à chaque fois qu'on clique sur le bouton "Calculer".
 * </p>
 * 
 * @author Guillaume
 */
public class Calculateur extends SwingWorker<String, Void> {

	/**
	 * La fenêtre principale. Cette référence est nécessaire pour accéder aux composants graphiques qui sont affectés par le calcul, comme
	 * le JLabel contenant le résultat.
	 */
	private final Fenetre fenetre;

	public Calculateur(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	/**
	 * Effectue les calculs et donne le résultat sous forme de chaîne de caractères.
	 */
	@Override
	protected String doInBackground() throws Exception {
		final String calcul = fenetre.calcul.getText();// le calcul entré par l'utilisateur
		final String[] parties = calcul.split("=");

		if (parties.length > 2)
			throw new IllegalArgumentException("Equation invalide: vous ne devez pas entrer plus d'un signe égal");

		final List<List<Term>> termesParties = new ArrayList<>(parties.length);// termes simplifiés
		for (String partie : parties) {
			MathAnalyseur analyseur = new MathAnalyseur(partie);
			List<Term> termes = MathSimplifieur.simplify(analyseur.analyser());
			termesParties.add(termes);
		}

		return (parties.length == 1) ? termesParties.get(0).toString() : MathSolveur.resoudre(termesParties.get(0), termesParties.get(1));
	}

	/**
	 * Affiche le résultat calculé. Cette méthode est appelée dans l'EDT.
	 */
	@Override
	protected void done() {
		try {
			fenetre.resultat.setText(this.get());
			fenetre.boutonCalculer.setEnabled(true);
			fenetre.boutonCalculer.setText("Calculer/Résoudre");
		} catch (Exception e) {
			e.printStackTrace();// écrit les détails de l'erreur dans la console.
			JOptionPane.showMessageDialog(null, e.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);// affiche un message
																									// d'erreur dans une
																									// nouvelle fenêtre.
		}

	}

}
