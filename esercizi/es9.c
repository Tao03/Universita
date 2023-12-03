#include <stdio.h>
int main(){
	int n=10;
	char *res = "";
	int i=0;
	while(n!=1){
		if(n%2==0){
			n = n/2;
			i = i + 1;
		}else{
		
			n = n*3+1;
		}
		printf("%d",n);
		
	}
	

}
