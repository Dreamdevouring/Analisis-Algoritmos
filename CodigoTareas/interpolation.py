def interpolation(z, X, n):
    izq = 0
    der = n-1
    p = 0
    while X[der] >= z and z >= X[izq]:
        i = izq + ((z - X[izq]) * (der - izq) / (X[der]-X[izq]))
        if z > X[i]:
            izq = i + 1
        elif z < X[i]:
            der = i - 1
        else:
            return (i,p+1)
        p += 1
    if X[izq] == z:
        return (izq,p)
    else:
        return (-1,p)

s = []
for i in range(500):
    if i < 100:
        s.append(i)
    elif i < 200:
        s.append(s[i-1] + 2*i)
    elif i < 300:
        s.append(s[i-1] + 3*i)
    elif i < 400:
        s.append(s[i-1] + 4*i)
    else:
        s.append(s[i-1] + 5*i)

t = []
for i in range(500):
    if i == 499:
        t.append(2**i)
    else:
        t.append(i)
        
