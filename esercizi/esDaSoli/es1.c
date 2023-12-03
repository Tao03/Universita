#include <stdio.h>
int length(char *);



int main(){
	char stringa[10];
	
	
	printf("Inserire una stringa grazie\n");
	fgets(stringa,10,stdin);
	
	
	int i = 0;
	int flag = 0;
	int dim = length(stringa)-1;
	
	while(i<dim/2 && flag==0){
		if(*(stringa + i)!=*(stringa + dim-i-1)){
			flag = 1;
		}
		i = i + 1;
	
	}
	if(flag==0){
		printf("La stringa inserita è un palindromo\n");
	}else{
		printf("La stringa inserita non è un palindromo\n");
	}


}

int length(char * stringa){
	int i = 0;
	while(*(stringa + i)!=0){
		i = i + 1;
	
	}
	return i;
}



