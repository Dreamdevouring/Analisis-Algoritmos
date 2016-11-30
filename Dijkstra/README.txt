Alumno: López García Gilberto Isaac
No. de Cuenta: 312202718

Análisis de Algoritmos I.
Práctica 4: Rutas más cortas.

Para la implementación del proyecto se implementaron las clases:

	* Edge: Para representar aristas.
	* Vertex: Para representar vértices.
	* WGraph: Para representar gráficas dirigidas con pesos en las aristas.
	* BinomialTree: Para representar árboles binomiales.
	* BinomialHeap: Para representar heaps binomiales.
	* Dijkstra: Contiene el algoritmo de Dijkstra.

Estas clases se encuentran en el paquete mx.unam.ciencias.algorithms
(directorio mx/unam/ciencias/algorithms).

Se usó la biblioteca mx.unam.ciencias.edd que coniene estructuras de datos
útiles implementadas por mí en el curso de Estructuras de Datos. Las clases e
interfaces ocupadas fueron:

	* Lista: Listas biligadas.
	* ComparableIndexable: Interfaz que deben implementar los tipos que se
	  quieran ordenar con los heaps binomiales.

La clase Main del paquete 'dijkstra' es el punto de entrada del programa, en él
se crea la gráfica que se pasa como entrada.

Para compilar el programa, desde el directorio en que se encuentra el directorio
src ejecute en la terminal el siguiente comando:

$ javac dijkstra/Main.java

Para ejecutar el programa, ejecute el siguiente comando:

$ java dijkstra.Main graph.txt

donde "graph.txt" es el nombre del archivo con la información de la gráfica
sobre la cual desea correr el algoritmo de Dijkstra (se adjunta también un
ejemplo, no es necesario usar el mismo nombre de archivo, ni que esté en el
mismo directorio, pero en tal caso debe dar la ruta relativa/total del archivo).

Para construir una gráfica puede generar un archivo de texto (.txt) con la
información en el siguiente formato:

La primer línea lleva dos números enteros, separados por un tab "\t":

<n>	<a>

donde <n> es el número de vértices para la gráfica, los cuales serán etiquetados
con los números enteros 0,1,...,n-1, y <a> es la etiqueta del vértice inicial.

Las líneas subsecuentes contienen la información de las aristas (los datos
separados por tabs "\t"):

<u>	<u>	<<w>>

donde <u> y <v> son las etiquetas de los vértices iniciales y finales, es decir,
es la arista (u,v) (una arista dirigida), y <<w>> es el peso de la arista, el cual
es opcional (si no se proporciona un peso la arista será creada con un peso de 1
por defecto, el peso w debe ser un entero no negativo).

Observaciones: Si requiere gráficas muy grandes probablemente deba dar más
memoria a la JVM con el argumento -Xmx<Memoria>, ej: -Xmx1g para 1 GB.

La salida del programa son las aristas del árbol de rutas más cortas con su
respectivo peso en formato:

(u)--w-->(v)

donde la arista es (u,v) tal que weight(u,v) = w.
