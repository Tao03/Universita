#include <stdio.h>
int controllo(char *,char);
int length(char *);


int main(){
	char stringa[128];
	int index = 0;
	char valori_controllati[32];
	
	
	printf("Inserire una stringa grazie\n");
	fgets(stringa,128,stdin);
	int l = length(stringa);
	int i = 0;
	while(i<l){
		if(controllo(valori_controllati,stringa[i])==0){
			int c = 0;
			for(int j = 0;j<l;j++){
				if(stringa[i] == stringa[j]){
					c = c + 1;
				}
			}
			printf("Carattere %c:  %d volte\n",stringa[i],c);
			valori_controllati[index] = stringa[i];
			index = index + 1;
		}	
		i = i + 1;
	
	}
	
	
	


}


int controllo(char valori_controllati[],char carattere){
	int flag = 0;
	int i = 0;
	while(valori_controllati[i]!=0 && flag == 0){
		if(valori_controllati[i] == carattere){
			flag = 1;
		}
		i = i + 1;
	
	}
	return flag;

}

int length(char * stringa){
	int i = 0;
	while(*(stringa + i)!=0){
		i = i + 1;
	
	}
	return i;
}



