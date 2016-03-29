package fr.calculator;

import java.util.Scanner;

public class Calcul {
	public static void SimplificationFraction() {
		int rest1 = 0, rest2 = 0, div = 0;
		int slash = fraction.indexOf("/");
		if (slash == -1) {
			System.out.println("Vous devez rentrer une fraction à l'aide du caractère /.");
		}
		else {
			String num1 = new String(), denom1 = new String(); 
			num1 = fraction.substring(0, slash); 
			denom1 = fraction.substring(slash + 1, fraction.length()); 
			int num = Integer.parseInt(num1, 10), denom = Integer.parseInt(denom1, 10);
			for (int compt = 1; compt <= Math.abs(num) && compt <= Math.abs(denom); compt++) {
				rest1 = Math.abs(num) % compt;
				rest2 = Math.abs(denom) % compt; 
				if (rest1 == 0 && rest2 == 0) {
					div = compt;
				}
			}
			if (num < 0 && denom > 0 || num > 0 && denom < 0) {
				System.out.print("-");
			}
			num = Math.abs(num) / div;
			denom = Math.abs(denom) / div;
			System.out.print(num);
			if (denom > 1) { 
				System.out.print("/" + denom);
			}
		}
	}
}
