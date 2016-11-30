def caracol(n): #n >= 0
	posicion = [0,0]
	pasos = 0
	while pasos < n:
		if posicion == [0,0]: #1er paso
			posicion[0] += 1
			pasos += 1
		elif posicion == [1,0]: #2o paso 
			posicion[1] += 1
			pasos += 1
		elif posicion[0] > 0 and posicion[1] > 0: #1er cuadrante
			k = posicion[0]
			posicion[0] -= k*2
			pasos += k*2
		elif posicion[0] < 0 and posicion[1] > 0: #2o cuadrante
			k = posicion[1]
			posicion[1] -= k*2
			pasos += k*2
		elif posicion[0] < 0 and posicion[1] < 0: #3er cuadrante
			k = posicion[0]
			posicion[0] -= k*2 - 1
			pasos += 1 - k*2
		elif posicion[0] > 0 and posicion[1] < 0: #4o cuadrante
			k = posicion[1]
			posicion[1] -= k*2 - 1
			pasos += 1 - k*2
	m = pasos - n
	if m > 0:
		if posicion[0] > 0 and posicion[1] > 0: #1er cuadrante
			posicion[1] -= m
		elif posicion[0] < 0 and posicion[1] > 0: #2o cuadrante
			posicion[0] += m
		elif posicion[0] < 0 and posicion[1] < 0: #3er cuadrante
			posicion[1] += m
		elif posicion[0] > 0 and posicion[1] < 0: #4o cuadrante
			posicion[0] -= m
	return posicion
	
def count(matriz):
    counter = 0
    for i in range(len(matriz) - 1):
        r = False
        for j in range(len(matriz[0])):
            if matriz[i][j] == 0:
                if matriz[i + 1][j] == 1 and not r:
                    counter += 1
                    r = True
                elif matriz[i + 1][j] == 0:
                    r = False
    return counter
