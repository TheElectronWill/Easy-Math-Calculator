package fr.calculator.parser;

/**
 * Un terme d'une équation. En fait c'est juste un truc qui peut est ajouté d'un coté de l'équation (ie un terme
 * d'addition).
 * 
 * @author TheElectronWill
 * 		
 */
public interface Term {
	
	/**
	 * Inverse ce terme.
	 * 
	 * @return ce même terme inversé, ou un nouvel objet équivalent à ce terme inversé.
	 */
	Term reverse();
	
	/**
	 * Multiplie ce terme par -1.
	 * 
	 * @return ce même terme multiplié par -1, ou un nouvel objet équivalent à ce terme multiplié par -1.
	 */
	Term negate();
	
	/**
	 * Simplifie ce terme.
	 * 
	 * @return ce même terme simplifié, ou un nouvel objet équivalent à ce terme simplifié.
	 */
	Term simplify();
	
}
