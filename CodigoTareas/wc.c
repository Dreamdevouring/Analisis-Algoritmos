#include <stdio.h>
#include <stdlib.h>

/* Worst Case for Shell Sort with Shell gap sequence. */
int* wcSS (int n) {
	int* wc = (int*) malloc(n*sizeof(int));
	int i, j = n;
	for (i = 0; i < n; i += 2)
		wc[i] = j--;
	for (i = 1; i < n; i += 2)
		wc[i] = j--;
	return wc;
}

/* Worst Case for Shell Sort with Hibbard gap sequence. */
int* wcSH (int n) {
	int* wc = (int*) malloc(n*sizeof(int));
	int i, j = n;
	for (i = 0; i < n; i += 3)
		wc[i] = j--;
	for (i = 1; i < n; i += 3)
		wc[i] = j--;
	for (i = 2; i < n; i += 3)
		wc[i] = j--;
	return wc;
}

/* Worst Case for Local Insertion Sort. */
int* wcLIS (int n) {
	int* wc = (int*) malloc (n*sizeof(int));
	int i;
	for (i = 0; i < n; i++)
		if (i % 2 == 0)
			wc[i] = i/2;
		else
			wc[i] = -(i+1)/2;
	return wc;
}

/* Worst Case for Insertion Sort. */
int* wcIS (int n) {
	int* wc = (int*) malloc (n*sizeof(int));
	int i, j = n;
	for (i = 0; i < n; i++)
		wc[i] = j--;
	return wc;
}

int main () {
	int i;

	int* wcS = wcSS(10);
	for (i = 0; i < 10; i++)
		printf("%d, ", wcS[i]);
	printf("\n");

	int* wcH = wcSH(10);
	for (i = 0; i < 10; i++)
		printf("%d, ", wcH[i]);
	printf("\n");

	int* wcL = wcLIS(10);
	for (i = 0; i < 10; i++)
		printf("%d, ", wcL[i]);
	printf("\n");

	int* wcI = wcIS(10);
	for (i = 0; i < 10; i++)
		printf("%d, ", wcI[i]);
	printf("\n");

	return 0;
}