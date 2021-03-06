package fr.calculator;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import fr.calculator.analyse.MathAnalyseur;
import fr.calculator.resolution.ExpressionSimple;
import fr.calculator.resolution.MathSimplifieur;
import fr.calculator.resolution.MathSolveur;

/**
 * Un <code>SwingWorker</code> qui effectue les calculs dans un autre Thread que l'EDT (Event Dispatch Thread, le thread dans lequel doit s'effectuer toute modification de composant graphique).
 * <p>
 * Un nouveau <code>Calculator</code> est créé à chaque fois qu'on clique sur le bouton "Calculer".
 * </p>
 *
 * @author Guillaume
 */
public class Calculateur extends SwingWorker<String, Void> {

	/**
	 * La fenêtre principale. Cette référence est nécessaire pour accéder aux composants graphiques qui sont affectés par le calcul, comme le JLabel contenant le résultat.
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
		try {
			final String calcul = fenetre.calcul.getText();// le calcul entré par l'utilisateur
			final String[] parties = calcul.split("=");
			if (parties.length > 2) {
				throw new MathException("Equation invalide : vous ne devez pas entrer plus d'un signe égal");
			}
			if (parties.length == 2 && (!parties[0].contains("x") && !parties[1].contains("x"))) {
				throw new MathException("Equation invalide : vous devez utiliser la variable x");
			}

			final ExpressionSimple[] expressionsSimples = new ExpressionSimple[parties.length];
			for (int i = 0; i < parties.length; i++) {
				MathAnalyseur analyseur = new MathAnalyseur(parties[i]);
				try {
					expressionsSimples[i] = MathSimplifieur.simplifierExpression(analyseur.analyser());
				} catch (MathException ex) {
					throw ex;
				} catch (Exception ex2) {
					throw new MathException("Cette expression ne peut pas être traitée par logiciel.", ex2);
				}
			}

			return (parties.length == 1) ? "E = " + expressionsSimples[0].toMathString()
					: MathSolveur.resoudre(expressionsSimples[0], expressionsSimples[1]);
		} catch (Exception e) {
			String msg = e.getLocalizedMessage().equals("/ by zero") ? "Impossible de diviser par zéro" : e.getLocalizedMessage();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
			return "ERREUR";
		}
	}

	/**
	 * Affiche le résultat calculé. Cette méthode est appelée dans l'EDT.
	 */
	@Override
	protected void done() {
		try {
			fenetre.resultat.setText(this.get());
			fenetre.boutonCalculer.setEnabled(true);
			fenetre.pack();
		} catch (Exception e) {
			e.printStackTrace();// écrit les détails de l'erreur dans la console.
			JOptionPane.showMessageDialog(null, e.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);// affiche un message d'erreur dans une nouvelle fenêtre.
		}

	}

}
