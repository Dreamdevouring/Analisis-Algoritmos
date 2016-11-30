package application;

/**
 * Análisis de Algoritmos. Programa 1.
 * Algoritmo para resolver el problema del adoquinamiento.
 * @author Gilberto Isaac López García.
 */
public class Main {
	
	//Instancia Adoquin.
    private final static Adoquin ejemplo = new Adoquin(5, 20, 30);
	
	/**
	 * Punto de entrada de la aplicación.
	 * @param args Parámetros de entrada.
	 */
	public static void main(String[] args) {
		ejemplo.adoquinar();
	}
}// Fin Main.java.
