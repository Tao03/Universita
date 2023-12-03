#include <stdio.h>

int main(){
	int valore_binario = 10;
	int size=0;
	int temp = valore_binario;
	while(valore_binario!=0){
		valore_binario = valore_binario>>1;
		size = size + 1;
	
	}
	int vettore[size];
	int i=0;
	while(temp!=0){
		vettore[i] = temp - (temp>>1)*2;
		temp = temp>>1;
		i = i + 1;
		
	}
	for(int i = size-1;i>=0;i--){
		
		printf("%d",vettore[i]);
	}
	
	


}
