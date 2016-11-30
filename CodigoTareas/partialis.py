def partialis(A,n,k):
    for i in range(n):
        for j in range(i):
            if A[i] < A[j] and j < k:
                swap(A,i,j)
    return A[k-1]

def swap(A,i,j):
    r = A[j]
    A[j] = A[i]
    A[i] = r
