package fr.calculator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
	
	private JTextField inputField;
	private JLabel resultLabel;
	
	public Main() {
		this.setTitle("Easy Math Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton eraseButton = new JButton("Effacer");
		JButton calculateButton = new JButton("Calculer/Résoudre");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));//alignement horizontal
		buttonPanel.add(eraseButton);
		buttonPanel.add(calculateButton);
		
		inputField = new JTextField("Calcul");
		resultLabel = new JLabel("Résultat");
		
		panel.add(inputField);
		panel.add(buttonPanel);
		panel.add(resultLabel);
		
		this.setResizable(false);
		this.setContentPane(panel);
		this.pack();
		this.setVisible(true);
		
	}

	public static void main(String[] args) {	 
		Main main = new Main();
    }

}
