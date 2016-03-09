package fr.calculator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Un <code>SwingWorker</code> qui effectue les calculs dans un autre Thread que l'EDT (Event Dispatch Thread, le thread
 * dans lequel doit s'effectuer toute modification de composant graphique).
 * <p>
 * Un nouveau <code>Calculator</code> est créé à chaque fois qu'on clique sur le bouton "Calculer".
 * </p>
 */
public class Calculator extends SwingWorker<String, Void> {
	
	/**
	 * Le calcul entré par l'utilisateur.
	 */
	private final String userInput;
	/**
	 * Le label à modifier avec le résultat du calcul.
	 */
	private final JLabel uiLabel;
	
	public Calculator(String userInput, JLabel uiLabel) {
		this.userInput = userInput;
		this.uiLabel = uiLabel;
	}
	
	/**
	 * Effectue les calculs et donne le résultat sous forme de chaîne de caractères.
	 */
	@Override
	protected String doInBackground() throws Exception {
		// TODO calculs ici
		return null;
	}
	
	/**
	 * Affiche le résultat calculé. Cette méthode est appelée dans l'EDT.
	 */
	@Override
	protected void done() {
		try {
			String result = this.get();
			uiLabel.setText(result);
		} catch (Exception e) {
			e.printStackTrace();// écrit les détails de l'erreur dans la console.
			JOptionPane.showMessageDialog(null, e.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);// affiche un message
																									// d'erreur dans une
																									// nouvelle fenêtre.
		}
		
	}
	
}
