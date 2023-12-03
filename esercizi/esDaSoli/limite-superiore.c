#include <stdio.h>
#include <stdlib.h>
int *limite_superiore(int*,int);
void stampa(int*);


int main(){
	int array[] = {1,2,3,4,5,6,7,8,9,10};
	stampa(array);
	int *array_nuovo=limite_superiore(array,8);
	stampa(array_nuovo);

}

void stampa(int* array){
	printf("\n");
	for(int i=0;i<sizeof(array)-1;i++){
		printf("[%d]",array[i]);
	}
}

int *limite_superiore(int *array, int k){
	int c = 0;
	for(int i=0;i<sizeof(array)/4;i++){
		if(array[i]<k){
			c = c + 1;
		}
		
	}
	int *array_nuovo = malloc(c*4);
	for(int i=0;i<sizeof(array)-1;i++){
		if(array[i]<k){
			array_nuovo[i] = array[i];
		}
	}
	return array_nuovo;

}


