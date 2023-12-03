#include <stdio.h>
#include <ctype.h>   /* per islower */
void readData();

int main(char* args){
	readData();	

}
void readData(){
	FILE * my_f ;
	int c;
	int contatore=1;
	int length=0;
	my_f = fopen ("prova.txt" , "r" );
	while ((c = fgetc(my_f)) != '\n') {
	
		if(c == ','){
			length = length + 1;
		}
		//fputc(c, my_f);
	}
	c=0;
	while ((c = fgetc(my_f)) != EOF) {
	
		if(c == '\n'){
			contatore = contatore + 1;
		}
		//fputc(c, my_f);
	}
	
	
	
	
	
	printf("Numero colonne: %d\n",length+1);
	printf("Numero righe: %d\n",contatore);
	
	int array[contatore * c];
	
	while ((c = fgetc(my_f)) != EOF) {
	
		if(c == '\n'){
			contatore = contatore + 1;
		}
		//fputc(c, my_f);
	}
	
	while ((c = fgetc(my_f)) != EOF) {
		int k = 0;
		while((c = fgetc(my_f) != '\n')){
			if((c > '0' && c < '9')){
				array[i*k] = c;
			
			}else if(c == '-')
		}
		//fputc(c, my_f);
	}
	
	
	
	fclose(my_f);
	/*int data[contatore];
	free(c);
	int c;
	
	
	
	my_f = fopen ("prova.txt" , "r" );
	while ((c = fgetc(my_f)) != EOF) {
	printf("Valore: %c\n", (char) c);
		

		fputc(c, my_f);
	}
	
*/	
}

