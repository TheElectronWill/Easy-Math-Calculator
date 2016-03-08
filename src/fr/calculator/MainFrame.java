package fr.calculator;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Fenêtre principale. Utilise la bibliothèque graphique swing.
 * @author raffin
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputField;
	private JLabel resultLabel;

	public MainFrame() {
		this.setTitle("Easy Math Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridheight = 1;
		
		JLabel calcLabel = new JLabel("Calcul:");
		c.insets = new Insets(15,15,5,5);
		panel.add(calcLabel, c);

		inputField = new JTextField();
		c.insets=new Insets(15,0,5,15);
		c.gridx=1;
		c.gridwidth = 3;
		panel.add(inputField, c);

		JButton eraseButton = new JButton("Effacer");
		c.insets=new Insets(5,0,5,5);
		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(eraseButton, c);

		JButton calculateButton = new JButton("Calculer/Résoudre");
		c.insets=new Insets(5,5,5,15);
		c.gridx=2;
		c.gridwidth=2;
		panel.add(calculateButton, c);

		JLabel resultInfos = new JLabel("Résultat:");
		c.insets=new Insets(15, 15, 15, 5);
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=1;
		panel.add(resultInfos,c);
		
		resultLabel = new JLabel("aucun résultat à afficher pour l'instant");
		resultLabel.setFont(resultLabel.getFont().deriveFont(Font.ITALIC));
		c.insets=new Insets(5,0,5,15);
		c.fill=GridBagConstraints.CENTER;
		c.gridx=1;
		c.gridy = 3;
		c.gridwidth=3;
		panel.add(resultLabel, c);

		this.setResizable(false);
		this.setContentPane(panel);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
	}

}
