package fr.calculator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Fenêtre principale. Utilise la bibliothèque graphique swing.
 * 
 * @author Guillaume
 */
public class Fenetre extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Point d'entrée du programme.
	 */
	public static void main(String[] args) {
		// -- Utilisation de l'apparence graphique du système d'exploitation --
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// -- Affichage l'interface graphique (dans l'Event Dispatch Thread) --
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Fenetre frame = new Fenetre();
				frame.setVisible(true);
			}
		});
	}
	
	/**
	 * Contient le texte entré par l'utilisateur.
	 */
	final JTextField inputField;
	/**
	 * Contient le résultat calculé par le logiciel.
	 */
	final JLabel result;
	/**
	 * Bouton "Calculer". Lance le calcul.
	 */
	final JButton calculateButton;
	/**
	 * Bouton "Effacer". Efface le contenu de {@link #inputField}.
	 */
	final JButton eraseButton;
	
	/**
	 * Crée la fenêtre et place les composants graphiques.
	 */
	public Fenetre() {
		super("Easy Math Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// termine le programme quand on ferme la fenêtre
		this.setLocationRelativeTo(null);// centre la fenêtre
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridheight = 1;
		
		JLabel calcLabel = new JLabel("Calcul:");
		c.insets = new Insets(15, 15, 5, 5);
		panel.add(calcLabel, c);
		
		inputField = new JTextField();
		inputField.setPreferredSize(new Dimension(400, 30));
		c.insets = new Insets(15, 0, 5, 15);
		c.gridx = 1;
		c.gridwidth = 4;
		panel.add(inputField, c);
		
		eraseButton = new JButton("Effacer");
		eraseButton.addActionListener(new EraseButtonListener());
		c.insets = new Insets(5, 0, 5, 5);
		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(eraseButton, c);
		
		calculateButton = new JButton("Calculer/Résoudre");
		calculateButton.addActionListener(new CalculateButtonListener());
		c.insets = new Insets(5, 5, 5, 15);
		c.gridx = 3;
		c.gridwidth = 2;
		panel.add(calculateButton, c);
		
		JLabel resultLabel = new JLabel("Résultat:");
		c.insets = new Insets(10, 15, 15, 5);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(resultLabel, c);
		
		result = new JLabel("aucun résultat à afficher pour l'instant");
		result.setFont(result.getFont().deriveFont(Font.ITALIC));
		c.insets = new Insets(0, 0, 5, 15);
		c.gridx = 1;
		c.gridwidth = 3;
		panel.add(result, c);
		
		this.setContentPane(panel);
		this.setResizable(false);// fenêtre non redimensionnable
		this.pack();// fait correspondre la taille de la fenêtre à son contenu
	}
	
	private class EraseButtonListener implements ActionListener {
		/**
		 * Appelée quand on clique sur le bouton "Effacer".
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			inputField.setText("");// efface le texte
		}
	}
	
	private class CalculateButtonListener implements ActionListener {
		/**
		 * Appelée quand on clique sur le bouton "Calculer".
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			final JButton calculateButton = (JButton) event.getSource();
			result.setText("Calcul en cours...");
			calculateButton.setEnabled(false);
			
			Calculateur calculator = new Calculateur(Fenetre.this);
			calculator.execute();
		}
	}
	
}
