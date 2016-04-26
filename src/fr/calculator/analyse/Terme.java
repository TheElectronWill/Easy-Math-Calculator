package fr.calculator.analyse;

/**
 * Un terme d'une équation.
 *
 * @author Guillaume
 *
 */
public interface Terme {

	/**
	 * Inverse ce terme.
	 *
	 * @return ce même terme inversé, ou un nouvel objet équivalent à ce terme inversé.
	 */
	Terme inverser();

	/**
	 * Multiplie ce terme par -1.
	 *
	 * @return ce même terme multiplié par -1, ou un nouvel objet équivalent à ce terme multiplié par -1.
	 */
	Terme negatif();

	/**
	 * Simplifie ce terme.
	 *
	 * @return ce même terme simplifié, ou un nouvel objet équivalent à ce terme simplifié.
	 */
	Terme simplifier();

	/**
	 * Teste si ce terme est le même ou contient la même chose qu'un autre terme. Si obj n'implémente pas {@link Terme},
	 * renvoie false.
	 * <p>
	 * ATTENTION: cette méthode ne teste <i>pas</i> l'égalité mathématique, mais uniquement si les 2 objets sont
	 * construits à l'identique. Par exemple, deux fractions (2/3) sont "égales" d'après cette méthode, mais (2/3) et
	 * (4/6) ne le sont pas. Il convient donc de <i>simplifier</i> deux termes <i>avant</i> de les comparer avec
	 * equals().
	 * </p>
	 *
	 * @param obj
	 * @return true si cet objet est "égal" à obj, sinon false.
	 */
	@Override
	boolean equals(Object obj);

}
