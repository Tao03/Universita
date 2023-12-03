#include <stdio.h>
#include <stdlib.h>
int main(){

	char vettore[7];
	int vettore1[7];
	int vettore2[7];
	for(int k = 0;k<7;k++){
		fgets(vettore,7,stdin);
		vettore1[k] = strtol(vettore,NULL,10);
	}
	for(int i = 0; i<sizeof(vettore);i++){
		int s = 0;
		for(int j = sizeof(vettore)-1; j>=i;j--){
			s = s + vettore1[j];
		}
		vettore2[i] = s;
	
	}
	for(int i = 0;i<7;i++){
		printf("[%d]",vettore2[i]);
	
	}
}
