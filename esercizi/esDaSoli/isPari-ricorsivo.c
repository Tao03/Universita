#include <stdio.h>

int isPari(int);

int main(){
	
	int n;
	printf("Inserire un valore\n");
	scanf("%d",&n);
	int value = isPari(n);
	if(value==0){
		printf("Il valore %d è pari",n);
	}else{
		printf("Il valore %d è dispari",n);
	}
	

}


int isPari(int n){
	if(n<=1){
		return n;
	}else{
		n = isPari(n-2);
		return n;
	}

}
