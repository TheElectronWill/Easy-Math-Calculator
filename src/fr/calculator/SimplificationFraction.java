package fr.calculator;
public class  SimplificationFraction{
	public static void SimplificationFraction(int fact5, int cons5) {
		int rest1 = 0, rest2 = 0, div = 0;
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
}