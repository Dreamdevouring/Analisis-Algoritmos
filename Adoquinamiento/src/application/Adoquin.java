package application;

/**
 * Análisis de Algoritmos. Programa 1.
 * Algoritmo para resolver el problema del adoquinamiento.
 * @author Gilberto Isaac López García.
 */
public class Adoquin {

	//Región a adoquinar
	private int[][] region;
	//Posición del cuadrado especial.
	private int i;
	private int j;
	
	/**
	 * Genera una región R de tamaño m x m donde m = 2 ^ k.
	 * Se marca la posición i j como el "cuadrado especial".
	 * @param k La potencia de 2. k >= 0.
	 * @param i La fila del cuadrado especial. 0 <= i < m.
	 * @param j La columna del cuadrado especial. 0 <= j < m.
	 */
	public Adoquin(int k, int i, int j){
		region = new int[1 << k][1 << k]; //1 << k = 2 ^ k
		this.i = i;
		this.j = j;
	}

	/**
	 * Adoquina la región.
	 * Imprime el resultado en la consola.
	 */
	public void adoquinar(){
		adoquinar(0, region.length - 1, 0, region.length - 1, i, j);
		System.out.println(toString());
	}
	
	/*
	 * Método recursivo para adoquinar la región.
	 * El intervalo de la región actual es [inicioI, finI] x [inicioJ, finJ].
	 * El cuadrado especial está en la posición (eI,eJ).
	 */
	private void adoquinar(int inicioI, int finI, int inicioJ, int finJ, int eI, int eJ){
		if (finI == inicioI) return; //k = 0 => No hay nada que hacer.
		if (finI == inicioI + 1) { //Caso base.
			if (eI == inicioI)
				region[finI][inicioJ] = ++region[finI][finJ];
			else
				region[inicioI][inicioJ] = ++region[inicioI][finJ];
			if (eJ == inicioJ)
				++region[eI][finJ];
			else
				++region[eI][inicioJ];
		} else {
			int midI = (inicioI + finI - 1)/2;
			int midJ = (inicioJ + finJ - 1)/2;
			int i1, i2, i3, i4;
			int j1, j2, j3, j4;
			if (eI > midI) {
				if (eJ > midJ) { //4a subregión.
					//Se coloca el adoquín.
					region[midI][midJ] = region[midI + 1][midJ] = ++region[midI][midJ + 1];
					//Posición de los cuadrados especiales para la llamada recursiva.
					i1 = midI;
					i2 = midI;
					i3 = midI + 1;
					i4 = eI;
					j1 = midJ;
					j2 = midJ + 1;
					j3 = midJ;
					j4 = eJ;
				} else { //3a subregión.
					//Se coloca el adoquín.
					region[midI][midJ] = region[midI + 1][midJ + 1] = ++region[midI][midJ + 1];
					//Posición de los cuadrados especiales para la llamada recursiva.
					i1 = midI;
					i2 = midI;
					i3 = eI;
					i4 = midI + 1;
					j1 = midJ;
					j2 = midJ + 1;
					j3 = eJ;
					j4 = midJ + 1;
				}
			} else {
				if (eJ > midJ) { //2a subregión.
					//Se coloca el adoquín.
					region[midI][midJ] = region[midI + 1][midJ] = ++region[midI + 1][midJ + 1];
					//Posición de los cuadrados especiales para la llamada recursiva.
					i1 = midI;
					i2 = eI;
					i3 = midI + 1;
					i4 = midI + 1;
					j1 = midJ;
					j2 = eJ;
					j3 = midJ;
					j4 = midJ + 1;
				} else { //1a subregión.
					//Se coloca el adoquín.
					region[midI + 1][midJ] = region[midI + 1][midJ + 1] = ++region[midI][midJ + 1];
					//Posición de los cuadrados especiales para la llamada recursiva.
					i1 = eI;
					i2 = midI;
					i3 = midI + 1;
					i4 = midI + 1;
					j1 = eJ;
					j2 = midJ + 1;
					j3 = midJ;
					j4 = midJ + 1;
				}
			}
			//Llamadas recursivas.
			adoquinar(inicioI, midI, inicioJ, midJ, i1, j1); //1a subregion.
			adoquinar(inicioI, midI, midJ + 1, finJ, i2, j2); //2a subregion.
			adoquinar(midI + 1, finI, inicioJ, midJ, i3, j3); //3a subregion.
			adoquinar(midI + 1, finI, midJ + 1, finJ, i4, j4); //4a subregion.
		}
	}
	
	/**
	 * Imprime la región como una matriz de m x m.
	 */
	@Override public String toString(){
		String regionS = "";
		for(int n = 0; n < region.length; n++){
			for(int m = 0; m < region.length; m++)
				regionS += region[n][m] + " ";
			regionS += "\n";
		}
		return regionS;
	}
}// Fin Adoquin.java.
