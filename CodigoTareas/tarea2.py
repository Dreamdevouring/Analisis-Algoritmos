def powI(x, n):
	"""Calcula x ^ n en O(logn) iterativamente."""
	if n == 0:
		return 1
	y = 1
	while n > 1:
		if n & 1 == 1:
			y *= x
		x *= x
		n = n >> 1
	return x*y

def powR(x, n):
	"""Calcula x ^ n en O(logn) recursivamente."""
	if n == 0:
		return 1
	if n & 1 == 0:
		return powR(x*x, n >> 1) # n / 2
	else:
		return x*powR(x*x, n >> 1) # (n-1) / 2

def horner(p, x):
	"""Calcula el polinomio p en el punto x en O(n)."""
	total = 0
	i = len(p) - 1
	while i >= 0:
		total = total*x + p[i]
		i -= 1
	return total

