def ex8(n):
	"""Ejercicio 9"""
    m = 0
    i = n
    while i > 0: # Executes floor(log(2,n)) + 1 times
        j = i
        while j <= n: # Executes i times
            j = j*2 # Theta(1)
            m += 1
        i = i / 2 # Integer division
    return m # T(n) = floor(log(2,n))^2 + 3*floor(log(2,n)) + 2
