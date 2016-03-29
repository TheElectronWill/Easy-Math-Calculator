package fr.calculator;

import java.util.Scanner;

public class RésolutionAffine {

	public static void main(String[] args) {
		
		 String fact1 = "", fact2 = "", cons1 = "", cons2 = "";
		 Scanner sc = new Scanner(System.in); 
		 String equation = sc.nextLine(); 
		 int egal = equation.indexOf("=");
		 if (egal == -1) {
			 System.out.print("Vous devez rentrer une équation.");
		 }
		 else {
			 String gauche, droit; 
			 gauche = equation.substring(0, egal); 
			 droit = equation.substring(egal + 1, equation.length());
			 if (gauche.equals(droit)) {
				 System.out.print("L'égalité est toujours vraie.");
			 }
			 else {
				 int variable1 = gauche.indexOf("x"),  
				 operateur1 = gauche.indexOf("+"), 
				 operateur2 = gauche.lastIndexOf("-");
				 if (operateur2 == 0) {
					 operateur2 = -1;
				 }
				 int variable2 = droit.indexOf("x"),
				 operateur3 = droit.indexOf("+"),
				 operateur4 = droit.lastIndexOf("-");
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
							cons1 = gauche.substring(operateur2 + 1, gauche.length());
							if (cons1.equals("")) {
								cons1 = "0";
							}
						 }
						 else if (operateur2 < variable1) {
							 
							fact1 = gauche.substring(operateur2 + 1, variable1);
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
							 cons1 = gauche.substring(operateur1 + 1, gauche.length());
							 if (cons1.equals("")) {
								 cons1 = "0";
							 }
						 }
						 else if (operateur1 < variable1) { 
							 fact1 = gauche.substring(operateur1 + 1, variable1);
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
							 cons2 = droit.substring(operateur4 + 1, droit.length());
							 if (cons2.equals("")) {
								 cons2 = "0";
							 }
						 }
						 else if (operateur4 < variable2) {
							 fact2 = droit.substring(operateur4 + 1, variable2);
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
							 cons2 = droit.substring(operateur3 + 1, droit.length());
							 if (cons2.equals("")) {
								 cons2 = "0";
							 }
						 }
						 else if (operateur3 < variable2) { 
							 fact2 = droit.substring(operateur3 + 1, variable2);
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
					 int fact3 = Integer.parseInt(fact1, 10), 
					 fact4 = Integer.parseInt(fact2, 10),
					 cons3 = Integer.parseInt(cons1, 10),
					 cons4 = Integer.parseInt(cons2, 10),
					 fact5 = fact3 - fact4,
					 cons5 = cons4 - cons3;
					 SimplificationFraction sf = new SimplificationFraction();
					 sf.SimplificationFraction(fact5, cons5);
				 }
			 }
		 }
	 }
}
