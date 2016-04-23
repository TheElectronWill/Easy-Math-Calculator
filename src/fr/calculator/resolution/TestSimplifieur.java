package fr.calculator.resolution;

import java.util.Arrays;
import java.util.List;

import fr.calculator.analyse.*;

public class TestSimplifieur {
	
	public static void main(String[] args) throws InterruptedException {
		NombreEntier i = new NombreEntier(5);
		Multiplication m = new Multiplication(new Variable("x"), new Division(i, new Puissance(i, new NombreEntier(20))));
		
		List<Terme> terms = Arrays.asList(new NombreEntier(-20), new Parenthese(m),
				new Parenthese(new NombreEntier(2), new Fraction(25, 10)));
				
		String beforeSimplification = Arrays.deepToString(terms.toArray());
		System.out.println("tel quel  : " + beforeSimplification);
		
		long t0 = System.nanoTime();
		List<Terme> simplified = MathSimplifieur.simplifier(terms);
		double time = (System.nanoTime() - t0) / (1000_000.0);
		
		String afterSimplification = Arrays.deepToString(simplified.toArray());
		System.out.println("simplifié : " + afterSimplification);
		System.out.println("durée : " + time + " millisecondes");
	}
	
}
