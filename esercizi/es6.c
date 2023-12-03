#include <stdio.h>


int binario_intero(float valore,int * vettore){
	int a = valore;
	int parte_intera[23]={0}; 
	int i = 0;
	while(a>0){
		parte_intera[i] = a%2;
		a = a/2;
		i = i + 1;
	}
	
	int j = 0;
	i = i - 1;
	int k = i;
	while(i>0){
		vettore[j] = parte_intera[i];
		i = i - 1;
		j = j + 1;
	}
	return k;
	
	
}

int binario_virgola(float valore, int * vettore){
	int a = valore;
	float parte_decimale= valore-a;
	int vettore_decimale[23]={0};
	int i = 0;
	printf("valore: %f\n",parte_decimale);
	while(parte_decimale!=0){
		parte_decimale = parte_decimale*2;
		
		if(parte_decimale>=1){
			
			vettore[i] = 1;
			printf("Binario: %d\n",vettore[i]);
			parte_decimale = parte_decimale - 1;
		}else{
			
			vettore[i] = 0;
			printf("Binario: %d\n",vettore[i]);
		}	
		i = i + 1;
	}
	return i;
	
}


void stampa(int vettore[]){
printf("\n");
	for(int i = 0;i<23;i++){
		printf("[%d]",vettore[i]);
	}
	printf("\n");
}

void unisci(int * array1, int * array2,int lim,int lim1){
	for(int i=0;i<lim1;i++){
		array1[i+lim] = array2[i];
	}
}

int ricerca(int array[],int size,int k){
	int flag = 0;
	int i,c=0;
	for(i=k;i>0;i--){
	printf("valore: [%d]",array[i]);
		if(array[i]==1){
			
			flag = 1;
			
			c = k - i;
			printf("[%d]\n",c);
		}
	}
	printf("flag: %d",flag);
	if(flag == 0){
	 c = 0;
		for(i=k;i<size && flag==0;i++){
		if(array[i]==1){
			printf("trovato!\n");
			flag = 1;
			
		}
		c = c - 1;
		printf("c: %d\n",c);
	}
	
	
	}
	
	
	return c;
	}
	

int main(){
	int parte_intera[23]={0}; 
	int limite = binario_intero(0.125,parte_intera)+1;
	
	
	int parte_decimale[23]={0};
	
	int limite_decimale = binario_virgola(0.125,parte_decimale);
	
	
	unisci(parte_intera,parte_decimale,limite,limite_decimale);
	stampa(parte_intera);
	stampa(parte_decimale);
	
	for(int i=0;i<23;i++){
		printf("[%d]",parte_intera[i]);
	}
	printf("\nla parte intera occupa %d caselle\n",limite);
	printf("la parte decimale occupa %d caselle\n",limite_decimale);
	int index = ricerca(parte_intera,limite_decimale+limite,limite);
	printf("Esponente: %d",index+127);
	
	
	

}
