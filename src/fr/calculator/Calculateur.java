package fr.calculator;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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
		final String userInput = fenetre.calcul.getText();// le calcul entré par l'utilisateur
		Thread.sleep(2000);
		// TODO calculs ici
		return "test";
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
