/*Si legga da standard input (con fgets+strtol) un array di 7 interi. Si stampino prima tutti gli
elementi di indice dispari e poi quelli di indice pari. Se per esempio vengono letti: 11 20 37 45 51 69 75, allora viene stampato:
11 37 51 75 20 45 69*/

#include <stdio.h>
#include <stdlib.h>
int main(){
 char v[7];
 int n[7];
 
 int j = 0;

 printf("scrivi sette interi \n");
 
 while(j < 7){
 fgets(v, sizeof(v), stdin);
 n[j] = strtol(v, NULL, 10);
 j++;
 }
 
 
 
 
 for(int i = 0; i < 7; i++){
  if(i % 2 == 1){
   printf("%d ", n[i]);
  }
 }
 for(int i = 0; i < 7; i++){
  if(i % 2 == 0){
   printf(" %d", n[i]);
  }
 }
     printf("\n");
    
   
}
