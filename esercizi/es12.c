#include <stdio.h>
#include <stdlib.h>
int potenza(int);
int* conversione(int,int);
void stampa(int*,int);
int main(){
	char stringa[128];
	printf("Inserisci una stringa");
	fgets(stringa,128,stdin);
	int a = 0;
	int j=0;
	while(stringa[j]!=0){
		j = j  + 1;
	}
	j = j - 1;
	for(int i = j-1;i>=0;i--){
		a = (( (int) stringa[i])-48)*potenza(j-i-1) + a;
	}
	int *res = conversione(a,8);
	return 0;

}

void stampa(int* res,int n){
printf("\n");
printf("Dimensione: %d\n",n);
	for(int i=0;i<n;i++){
		printf("[%d]",res[i]);
	}

}



int potenza(int i){
	if(i==0){
		return 1;
	}else{
		return potenza(i-1)*10;
	}
	
}

int *conversione(int a, int base){
	int i=0;
	int copia = a;
	while(a!=0){
		a = a/base;
		
		i = i + 1;
	}
	int vettore[i];
	
	for(int j=0;j<i;j++){
		vettore[j] = copia%base;
		copia = copia/base;
		
	}
	int *vettore_nuovo = malloc (i*sizeof(int));
	printf("Dimensione attuale del vettore %ld\n",sizeof(*vettore_nuovo));
	printf("Dimensione del calcolo: %ld",i*sizeof(int));
	for(int j =i;j>=0;j--){
		printf("Stampa %d volte\n",j);
		vettore_nuovo[j] = vettore[i-j-1];
	}
	
	
	stampa(vettore_nuovo,i);
	
	return vettore_nuovo;
}


