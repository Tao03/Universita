#include <stdio.h>
void fibo(int*,int);
int main(){
	int n = 10;
	int a[n];
	fibo(a,n);
	for(int i=0;i<n;i++){
		printf("[%d]",a[i]);
	}
	
}



void fibo(int* array, int n){
	int a = 1;
	int b = 1;
	for(int i=0;i<n;i++){
		int c = a + b;
		array[i] = c;
		a = b;
		b = c;	
	}
	return;
}
