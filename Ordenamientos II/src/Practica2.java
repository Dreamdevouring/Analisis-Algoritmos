public class Practica2 {

/*---implementar metodos despues de esta linea---*/
	/**
	 * Algoritmo de ordenamiento Quick Sort.
	 * @param arr El arreglo a ordenar.
	 * @return El arreglo arr ordenado en otro arreglo.
	 */
	public static int[] quickSort(int[] arr){
		//Copia a ordenar y regresar.
		int[] s = arr.clone ();
		quickSortAux (s, 0, s.length-1);
		return s;
	}

	/*
	 * Método auxiliar para Quick Sort.
	 * Ordena el arreglo s desde la posición a hasta la b
	 * (incluyendo los extremos).
	 */
	private static void quickSortAux (int[] s, int a, int b) {
		if (a < b) {
			//Partición del arreglo.
			int j = partition (s, a, b);
			//Llamadas recursivas en los subarreglos
			//resultantes de partition.
			quickSortAux (s, a, j-1);
			quickSortAux (s, j+1, b);
		}
	}

	/*
	 * Método Partition para Quick Sort.
	 * Se escoge como pivote el elementos en la primer posición.
	 */
	private static int partition (int[] s, int a, int b) {
		int i = a+1;
		int j = b;
		while (i < j)
		    if (s[i] > s[a] && s[j] < s[a])
		    	//Los elementos están en desorden.
		    	swap (s, i++, j--);
		    else if (s[i] <= s[a])
		    	i++;
		    else
		    	j--;
		if (s[i] > s[a])
			i--;
		//Ponemos al privote en su lugar.
		swap (s, a , i);
		return i;
	}

	/*
	 * Intercambia los elementos en la posición i y j
	 * del arreglo s.
	 */
	private static void swap (int[] s, int i, int j) {
		int tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}
	
	/**
	 * Algoritmo de ordenamiento Merge Sort.
	 * @param arr El arreglo a ordenar.
	 * @return El arreglo arr ordenado en otro arreglo.
	 */
	public static int[] mergeSort(int[] arr){
		int[] s = arr.clone ();
		mergeSortAux(s, 0, s.length-1);
		return s;
	}
	
	/*
	 * Método auxiliar para Merge Sort.
	 * Ordena el arreglo s desde la posición a hasta la b
	 * (incluyendo los extremos).
	 */
	private static void mergeSortAux (int[] s, int a, int b) {
		if (a < b) {
			int mitad = (a + b)/2;
			//Llamadas recursivas.
			mergeSortAux (s, a, mitad);
			mergeSortAux (s, mitad+1, b);
			//Mezcla los dos subarreglos ya ordenados recursivamente.
			mezcla (s, a, mitad, mitad+1, b);
		}
	}
	
	/*
	 * Método Mezcla para Merge Sort.
	 */
	private static void mezcla (int[] s,
			int i1, int i2, int d1, int d2) {
		int i, j, k;
		int[] t = new int[d2-i1+1];
		i = i1;
		j = d1;
		k = 0;
		while (i <= i2 && j <= d2)
			if (s[i] <= s[j])
				t[k++] = s[i++];
			else
				t[k++] = s[j++];
		while (i <= i2)
			t[k++] = s[i++];
		while (j <= d2)
			t[k++] = s[j++];
		for (int l = 0; l < k; l++) {
			s[l+i1] = t[l];
		}
	}
	
	/**
	 * Algoritmo de ordenamiento Bubble Sort.
	 * @param arr El arreglo a ordenar.
	 * @return El arrglo arr ordenado en de otro arreglo.
	 */
	public static int[] bubbleSort(int[] arr){
		int[] s = arr.clone ();
		int n = s.length;
		boolean sorted = false;
		for (int pass = 1; pass < n && !sorted; ++pass) {
			sorted = true;
			for (int i = 0; i < n-pass; i++)
				if (s[i] > s[i+1]) {
					swap (s, i, i+1);
					sorted = false;
				}
		}
		return s;
	}

	/**
	 * Genera un ejemplar que causa el peor caso para Quick Sort.
	 * El peor caso se da cuando la partición de un arreglo de n
	 * elementos regresa un subarreglo de n-1 elementos cada vez.
	 * Si la secuencia ya está ordenada descendentemente (o
	 * ascendentemente), se genera el peor caso.
	 * @param tam El tamaño del ejemplar.
	 * @return El ejemplar.
	 */
	public static int[] peorCasoQuickSort(int tam){
		int[] p = new int[tam];
		for (int i = 0; i < tam; i++)
			p[i] = tam - i;
		return p;
	}

	/**
	 * Genera un ejemplar que causa el peor caso para Merge Sort.
	 * El pero caso se da cuando Mezcla debe mezclar dos subarreglos
	 * donde los elementos está intercalados pues comparará todos los
	 * elementos de ambos subarreglos. Para generar el peor caso
	 * tomamos un arreglo inicialmente en orden inverso, creamos dos
	 * arreglos, el primero contendrá las posiciones pares y el segundo
	 * las impares, recursivamente los intercalamos y al final los
	 * copiamos en orden al arreglo de salida. Intuitivamente, lo que se
	 * está haciendo es ejecutar el proceso inverso al que realizaría
	 * Merge Sort suponiendo que Mezcla realizó todas las comparaciones
	 * posibles.
	 * @param tam El tamaño del ejemplar.
	 * @return El ejemplar.
	 */
	public static int[] peorCasoMergeSort(int tam){
		int[] p = new int[tam];
		//Arreglo en orden inverso.
		p = peorCasoQuickSort (tam);
		pcMSAux (p);
		return p;
	}
	
	/*
	 * Método auxiliar para peorCasoMergeSort.
	 */
	private static void pcMSAux (int[] s) {
		int l = s.length;
		if (l > 2) {
			int m = (l + 1)/2;
			int[] izq = new int[m];
			int[] der = new int[l - m];
			int i, j, k;
			k = 0;
			for (j = 0; j < l; j += 2)
				izq[k++] = s[j]; 
			k = 0;
			for (j = 1; j < l; j += 2)
				der[k++] = s[j];
			pcMSAux (izq);
			pcMSAux (der);
			i = 0;
			k = 0;
			while (k < m)
				s[i++] = izq[k++];
			k = 0;
			while (k < l-m)
				s[i++] = der[k++];
		}
	}

	/**
	 * Genera un ejemplar que causa el peor caso para Bubble Sort.
	 * El peor caso se da cuando el arreglo está ordenado en orden
	 * descendente pues el algoritmo intercambia el elementos por
	 * parejas, entonces cada elemento será desplazado un intercambio
	 * a la vez hasta su posición correcta, desde el principio del
	 * arreglo hasta la última posición, o penúltima, o antepenúltima,
	 * etc..., y en cada iteración se realiza un intercambio menos que
	 * en la anterior.
	 * @param tam El tamaño del ejemplar.
	 * @return El ejemplar.
	 */
	public static int[] peorCasoBubbleSort(int tam){
		return peorCasoQuickSort (tam);
	}

	/**
	 * Genera un ejemplar que causa el mejor caso para Quick Sort.
	 * El mejor caso se da cuando la posición final del pivote es
	 * en el medio del arreglo (o aproximadamente), por lo que la
	 * primera posición contiene el pivote que quedará en el medio
	 * y recursivamente se generan de igual manera los subarreglos
	 * que el método partition genera y Quick Sort ordena.
	 * @param tam El tamaño del ejemplar.
	 * @return El ejemplar.
	 */
	public static int[] mejorCasoQuickSort(int tam){
		int[] m = new int[tam];
		mcQSAux (m, 1, tam, 0, tam-1);
		return m;
	}
	
	/*
	 * Método auxiliar para mejorCasoQuickSort.
	 * s es el arreglo, a y b son los extremos del intervalo
	 * de valores para llenar el arreglo, i y j son los extremos
	 * del subarrglo que se está llenando.
	 */
	private static void mcQSAux (int[] s,
			int a, int b, int i, int j) {
		if (i <= j) {
			int medio = (a + b)/2;
			int k = (i + j)/2;
			s[i] = medio;
			mcQSAux (s, a, medio-1, i+1, k);
			mcQSAux (s, medio+1, b, k+1, j);
		}
	}

	/**
	 * Genera un ejemplar que causa el mejor caso para Merge Sort.
	 * El mejor caso se da cuando el arreglo ya está ordenado pues
	 * el método mezcla realiza una cantidad mínima de operaciones
	 * pues sólo compara los elementos del primer subarreglo contra
	 * el primero del segundo al ser este último mayor.
	 * @param tam El tamaño del ejemplar.
	 * @return El ejemplar.
	 */
	public static int[] mejorCasoMergeSort(int tam){
		int[] p = new int[tam];
		for (int i = 0; i < tam; i++)
			p[i] = i;
		return p;
	}

	/**
	 * Genera un ejemplar que causa el mejor caso para Bubble Sort.
	 * El mejor caso se da cuando el arreglo ya está ordenado pues
	 * el algoritmo no realiza intercambios y sólo recorre el arreglo
	 * una ocasión.
	 * @param tam El tamaño del ejemplar.
	 * @return El ejemplar.
	 */
	public static int[] mejorCasoBubbleSort(int tam){
		return mejorCasoMergeSort (tam);
	}

/*---implementar metodos antes de esta linea---*/

	public static void main(String[] args){
		long tiempoInicio, tiempoFin;
		int[] tamanos = {10, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};
		System.out.println("longitud del arreglo:");
		for (int i = 0; i < tamanos.length; ++i){
			System.out.print("\t"+(tamanos[i]));
		}
		System.out.println("\n\nTiempos para QuickSort:\nPeor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = peorCasoQuickSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("\nmejor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = mejorCasoQuickSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = quickSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}

		System.out.println("\n\nTiempos para MergeSort:\nPeor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = peorCasoMergeSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = mergeSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("\nmejor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = mejorCasoMergeSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = mergeSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}

		System.out.println("\n\nTiempos para BubbleSort:\nPeor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = peorCasoBubbleSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = bubbleSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}
		System.out.println("\nmejor Caso:");
		for (int i = 0; i < tamanos.length; ++i){
			int[] arr = mejorCasoBubbleSort(tamanos[i]);
			tiempoInicio = System.currentTimeMillis();
			arr = bubbleSort(arr);
			tiempoFin = System.currentTimeMillis();
			System.out.print("\t"+(tiempoFin - tiempoInicio));
		}

		System.out.print("\n");
	}
}
