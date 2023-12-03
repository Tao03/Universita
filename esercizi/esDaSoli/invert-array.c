#include <stdio.h>

int input_number();
void inversione(int *);
void stampa(int*,int);
int main(){
	
	int n = input_number();
	while(n<=0){
		printf("Mi dispiace ma hai inserito un valore non corretto, ritenta");
		n = input_number();
	}
	int vettore[n];
	int i = 0;
	while(i<n){
		*(vettore+i) = input_number();
		i = i + 1;
	}
	inversione(vettore);
	stampa(vettore,n-1);
	

}

int input_number(){
	int a;
	printf("Inserisci un valore\n");
	scanf("%d",&a);
	return a;
}

void inversione(int *array){
	int array_nuovo[sizeof(array)/4];
	int i = 0;
	while(i<sizeof(array)/4){
		array_nuovo[i] = array[(sizeof(array)/4)-i-1];
		i = i + 1;
	}
	array = array_nuovo;
	//stampa(array_nuovo,(sizeof(array)/4)-1);

}
void stampa(int * array, int i){
	if(i==0){
		printf("[%d]",*(array + i));
	}else{
		stampa(array,i-1);
		printf("[%d]",*(array + i));
	}
	return;

}



