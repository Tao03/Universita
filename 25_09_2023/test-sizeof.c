#include <stdio.h>

int main()
{
	int a;
	
	printf("INTEGER TYPES IN C\n");
	printf("  Bytes to represent a char: %d\n", (int)sizeof(char));
	printf("  Bytes to represent a short: %d\n", (int)sizeof(short));
	printf("  Bytes to represent a int: %d\n", (int)sizeof(a));
	printf("  Bytes to represent a long: %d\n", (int)sizeof(long));
	printf("\nFLOATING POINT TYPES IN C\n");
	printf("  Bytes to represent a float: %d\n", (int)sizeof(float));
	printf("  Bytes to represent a double: %d\n", (int)sizeof(double));
	printf("\nPOINTER TYPE IN C (it represents a memory address)\n");
	printf("  Bytes to represent a pointer to int (int *): %d\n", (int)sizeof(int *));
	printf("  Bytes to represent a pointer to double (double *): %d\n", (int)sizeof(double *));
	return 0;
}
