package fr.calculator;
import java.util.Scanner;
public class Exponentielle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String equation = sc.nextLine();
		int egal = equation.indexOf("=");
		if (egal == -1) {
			 System.out.print("Vous devez rentrer une équation.");
		}
		else {
			String gauche1, droit1; 
			gauche1 = equation.substring(0, egal); 
			droit1 = equation.substring(egal + 1, equation.length());
			if (gauche1.equals(droit1)) {
				System.out.print("L'égalité est toujours vraie.");
			}
			else {
				int exp1 = gauche1.indexOf("exp"),
				exp2 = droit1.indexOf("exp"),
				déb1 = gauche1.indexOf("("),
				déb2 = droit1.indexOf("("),
				fin1 = gauche1.indexOf(")"),
				fin2 = droit1.indexOf(")");
				String gauche, droit;
				if (exp1 != -1 && déb1 != -1 && fin1 != -1) {
					gauche = gauche1.substring(déb1 + 1, fin1);	
				}
				else {
					gauche = gauche1;
				}
				if (exp2 != -1 && déb2 != -1 && fin2 != -1) {
					droit = droit1.substring(déb2 + 1, fin2);
				}
				else {
					droit = droit1;
				}
				String fact1 = "", fact2 = "", cons1 = "", cons2 = "";
				int variable1 = gauche.indexOf("x"),  
						 operateur1 = gauche.indexOf("+"), 
						 operateur2 = gauche.lastIndexOf("-"),
						 variable2 = droit.indexOf("x"),
						 operateur3 = droit.indexOf("+"),
						 operateur4 = droit.lastIndexOf("-");
						 if (operateur2 == 0) {
							 operateur2 = -1;
						 }
						 if (operateur4 == 0) {
							 operateur4 = -1;
						 }
						 if (variable1 == - 1 && variable2 == -1) {
							 System.out.print("Vous devez utiliser la variable x.");
						 }
						 else if (operateur1 == -1 && operateur2 == -1 && operateur3 == -1 && operateur4 == -1 && variable1 != -1 && variable2 != -1) {
							 System.out.print("x = 0");
						 }
						 else {
							 if (operateur1 == -1 && operateur2 == -1 && variable1 != -1) {
								 fact1 = gauche.substring(0, variable1);
								 cons1 = "0";
							 }
							 else if (operateur1 == -1 && operateur2 == -1 && variable1 == -1) {
								 fact1 = "0";
								 cons1 = gauche;
							 }
							 else if (operateur1 == -1) {
								 if (operateur2 > variable1) {
									fact1 = gauche.substring(0, variable1);
									if (fact1.equals("")) {
										fact1 = "1";
									}
									cons1 = gauche.substring(operateur2, gauche.length());
									if (cons1.equals("")) {
										cons1 = "0";
									}
								 }
								 else if (operateur2 < variable1) {
									fact1 = gauche.substring(operateur2, variable1);
									if (fact1.equals("")) {
										fact1 = "1";
									}
									cons1 = gauche.substring(0, operateur2);
									if (cons1.equals("")) {
										cons1 = "0";
									}
								 }
							 }
							 else if (operateur2 == -1) {
								 if (operateur1 > variable1) { 
									 fact1 = gauche.substring(0, variable1);
									 if (fact1.equals("")) {
										 fact1 = "1";
									 }
									 cons1 = gauche.substring(operateur1, gauche.length());
									 if (cons1.equals("")) {
										 cons1 = "0";
									 }
								 }
								 else if (operateur1 < variable1) { 
									 fact1 = gauche.substring(operateur1, variable1);
									 if (fact1.equals("")) {
										 fact1 = "1";
									 }
									 cons1 = gauche.substring(0, operateur1);
									 if (cons1.equals("")) {
										 cons1 = "0";
									 }
								 }
							 }
							 if (operateur3 == -1 && operateur4 == -1 && variable2 != -1) {
								 fact2 = droit.substring(0, variable2);
								 cons2 = "0";
							 }
							 else if (operateur3 == -1 && operateur4 == -1 && variable2 == -1) {
								 fact2 = "0";
								 cons2 = droit;
							 }
							 else if (operateur3 == -1) {
								 if (operateur4 > variable2) {
									 fact2 = droit.substring(0, variable2);
									 if (fact2.equals("")) {
										 fact2 = "1";
									 }
									 cons2 = droit.substring(operateur4, droit.length());
									 if (cons2.equals("")) {
										 cons2 = "0";
									 }
								 }
								 else if (operateur4 < variable2) {
									 fact2 = droit.substring(operateur4, variable2);
									 if (fact2.equals("")) {
										 fact2 = "1";
									 }
									 cons2 = droit.substring(0, operateur4);
									 if (cons2.equals("")) {
										 cons2 = "0";
									 }
								 }
							 }
							 else if (operateur4 == -1) {
								 if (operateur3 > variable2) { 
									 fact2 = droit.substring(0, variable2);
									 if (fact2.equals("")) {
										 fact2 = "1";
									 }
									 cons2 = droit.substring(operateur3, droit.length());
									 if (cons2.equals("")) {
										 cons2 = "0";
									 }
								 }
								 else if (operateur3 < variable2) { 
									 fact2 = droit.substring(operateur3, variable2);
									 if (fact2.equals("")) {
										 fact2 = "1";
									 }
									 cons2 = droit.substring(0, operateur3);
									 if (cons2.equals("")) {
										 cons2 = "0";
									 }
								 }
							 }
							 System.out.print("x = ");
							 fact1 = "1";
							 fact2 = "1";
							 int fact3 = Integer.parseInt(fact1, 10), 
							 fact4 = Integer.parseInt(fact2, 10),
							 cons3 = Integer.parseInt(cons1, 10),
							 cons4 = Integer.parseInt(cons2, 10);
							 if (exp1 != -1 && exp2 != -1) {
								 int rest1 = 0, rest2 = 0, div = 0, cons5, fact5;
								 fact5 = fact3 - fact4;
								 cons5 = cons4 - cons3;
								 int num = cons5, denom = fact5;
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
								 if (div != 0) {
									 num = Math.abs(num) / div;
									 denom = Math.abs(denom) / div;
									 System.out.print(num);
									 if (denom > 1) {
										 System.out.print("/" + denom);
									 }
								 }
								 else {
									 System.out.print("0");
								 } 
							 }
							 else if (exp1 != -1 && exp2 == -1 && variable1 != -1 && variable2 == -1) {
								 int droit2 = Integer.parseInt(droit1, 10);
								 double solution = Math.log(droit2);
								 System.out.print(solution);
							 }
							 else if (exp1 == -1 && exp2 != -1 && variable1 == -1 && variable2 != -1) {
								 int gauche2 = Integer.parseInt(gauche1, 10);
								 double solution = Math.log(gauche2);
								 System.out.print(solution);
							 }
							 else if (exp1 != -1 && exp2 == -1 && variable1 == -1 && variable2 != -1) {
								 int gauche2 = Integer.parseInt(gauche, 10);
								 double solution = Math.exp(gauche2);
								 System.out.print(solution);
							 }
							 else if (exp1 == -1 && exp2 != -1 && variable1 != -1 && variable2 == -1) {
								 int droit2 = Integer.parseInt(droit, 10);
								 double solution = Math.exp(droit2);
								 System.out.print(solution);
							 }
					}
			}
		}
	}
}
