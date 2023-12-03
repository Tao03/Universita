#define MIN_AGE 18
#define MAX_AGE 100
#include <stdio.h>
#include <stdlib.h>
typedef struct prova{
	char * name ;
	int age ;
} record ;
struct prova * rec_rand_create ( int n ) ;
void rec_sort ( record * v , int n ) ;
void rec_print ( record * v , int n ) ;
void rec_free ( record * v , int n ) ;
char* random_string(int);
void swap(struct prova a, struct prova b);

int main(){
	struct prova *array=rec_rand_create(10);
	rec_print(array,10);
	rec_sort(array,10);
	printf("\n");
	rec_print(array,10);
}

void swap(struct prova a, struct prova b){
	struct prova temp = a;
	a = b;
	b = temp;
	
}
void rec_free(struct prova *a,int n){
	free(a);
}
void rec_sort(struct prova *array,int n){
	for(int i=0;i<sizeof(array)-1;i++){
		for(int j=i;j<sizeof(array)-1;j++){
			if(array[i].age>array[j].age){
				struct prova temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				
				//swap(array[i],array[j]);
			}
		}
	}
	
}
void rec_print(struct prova *array,int n){
	for(int i=0;i<sizeof(array)-1;i++){
		printf("Nome: %s\n",array[i].name);
		printf("Età:  %d\n",array[i].age);
		
	} 
}
struct prova* rec_rand_create(int n){
		struct prova *array = malloc (sizeof(struct prova)*n);
	for(int i=0;i<n;i++){
		int a;
		do{
			a = rand()%MAX_AGE;	
		}while(a<MIN_AGE);
			array[i].age = a;
			array[i].name = random_string(10);
	}
	return array;
} 

char* random_string(int r){
	char* string = malloc (sizeof(struct prova)*r);
	for(int i=0;i<r;i++){
		string[i] = (rand()%25) + 65;
	}
	return string;
}

