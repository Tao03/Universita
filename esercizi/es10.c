#include <stdio.h>
#include <stdlib.h>
int isNumber(char*,int,int);
int main(){
	char stringa[20];
	printf("Inserisci una stringa");
	fgets(stringa,20,stdin);
	printf("ciao");
	
	char indice1[3];
	int n1;
	printf("Inserisci il primo indice");
	fgets(indice1,3,stdin);
	n1 = strtol(indice1,NULL,10);
	
	char indice2[3];
	int n2;
	printf("Inserisci il secondo indice");
	fgets(indice2,3,stdin);
	n2 = strtol(indice2,NULL,10);
	
	int value = isNumber(stringa,n1,n2);
	if(value==1){
		printf("E' un numero intero!");	
	}else{
		printf("Non è un numero intero!");
	}
}

int isNumber(char* array, int n1, int n2){
	char stringa[n2-n1];
	for(int i = n1;i<=n2;i++){
		char a = array[i];
		stringa[i-n1] = a;
	}
	int a = strtol(stringa,NULL,10);
	int flag = 1; // Supponiamo che sia true
	for(int i=0;i<(n2-n1) && flag == 1; i++){
		if(stringa[i]<48 || stringa[i]>57){
			flag = 0;
		}
	}
	return flag;

}
