Alumno: López García Gilberto Isaac
No. Cuenta: 312202718

Instruccciones de ejecución:

1. Desde el directorio src extraído del .zip compilar
con el siguiente comando:

$ javac Practica2.java

2. Correr el programa con el siguiente comando:

$ java -Xss1g Practica2

El código fue modificado para probar tamaños de ejemplar
más grandes y dado que Merge Sort y Quick Sort se implementaron
de manera recursiva se necesita aumentar el tamaño del stack
de la JVM, por ello la bandera '-Xss1g' que incrementa el tamaño
a 1 Gb. Los tamaños de ejemplar agregados fueron:
10000, 20000, 50000, 100000, 200000, 500000