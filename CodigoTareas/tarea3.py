#!/usr/bin/env python
# -*- coding: utf-8 -*-

ej = [2,-3,1,5,-1,3,-2,-3,3]

def MaxSubSCM(X, n):
    """Maxima subsecuencia, regresa tambien la subsecuencia."""
    GMax = 0
    SMax = 0
    mi, mf = 0, 0
    si, sf = 0, 0
    for i in range(n):
        if X[i] + SMax > GMax:
            SMax += X[i]
            GMax = SMax
            sf += 1
            mi, mf = si, sf
        elif X[i] + SMax > 0:
            SMax += X[i]
            sf += 1
        else:
            SMax = 0
            si, sf = i+1, i+1
    return (GMax, X[mi:mf])

def maxpK(X,n):
	"""Máxima subsecuencia (producto)."""
	if n == 1:
		if X[0] < 1: return (1,[])
		else: return (X[0],X)

	max_p = X[0]
	min_p = X[0]
	cand = X[0]
        
	ci, cj = 0,0
	maxi, maxj = 0,0
	mini, minj = 0,0
	
	for i in range(1,n):
		m1 = max_p*X[i]
		m2 = min_p*X[i]
		actual_max = max(m1,m2,X[i],1)
		actual_min = min(m1,m2,X[i])
		swap = maxi
		if actual_max == m1:
			maxj += 1
		elif actual_max == m2:
			maxi, maxj = mini, i+1
		elif actual_max == X[i]:
			maxi, maxj = i, i+1
		else:
			maxi = maxj = i+1

		if actual_min == m1:
			mini, minj = swap, i+1
		elif actual_min == m2:
			minj += 1
		elif actual_min == X[i]:
			mini, minj = i, i+1

		cand = max(cand,actual_max)
		if cand == actual_max:
			ci, cj = maxi, maxj

		max_p = actual_max
		min_p = actual_min
	return (cand,X[ci:cj])
	
def findCandidate(X,n):
	"""Encuentra el candidato. Algoritmo de votación de Moore."""
    maj_index = 0
    count = 1
    for i in range(1,n):
        if X[maj_index] == X[i]:
            count += 1 
        else:
            count -= 1
        if count == 0:
            maj_index = i
            count = 1
    return X[maj_index]
