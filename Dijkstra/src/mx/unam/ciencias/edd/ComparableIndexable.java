package mx.unam.ciencias.edd;

/**
 * Interface para objetos comparables e indexables. Los objetos instancias de
 * ComparableIndexable, además de tener el método {@link Comparable#compareTo}
 * (heredado de {@link Comparable}), tienen los métodos {@link
 * ComparableIndexable#getIndice}, {@link ComparableIndexable#setIndice},
 * {@link ComparableIndexable#getLlave} y {@link ComparableIndexable#setLlave},
 * que permiten obtener y actualizar el índice del elemento, así como la llave.
 */
public interface ComparableIndexable<T> extends Comparable<T> {

    /**
     * Regresa el índice del objeto.
     * @return el índice del objeto.
     */
    public int getIndice();

    /**
     * Actualiza el índice del objeto.
     * @param indice el nuevo índice.
     */
    public void setIndice(int indice);
	
	/**
	 * Regresa la llave del objeto.
	 * @return la llave del objeto.
	 */
	public int getLlave();
	
	/**
	 * Actualiza la llave del objeto.
	 * @param llave  la nueva llave.
	 */
	public void setLlave(int llave);
}
