#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(){
	char str1[80];
	char str2[80];
	char *res=NULL;
	printf("Inserire la prima stringa");
	fgets(str1,80,stdin);
	printf("Inserire la seconda stringa");
	fgets(str2,80,stdin);
	for(int i = 0;str1[i]!=0; i++){
		if(str1[i]<32 || str1[i]>126){
			str1[i] = 0;
		}
		if(str2[i]<32 || str2[i]>126){
			str2[i] = 0;
		}
	}
	printf("Stringa 1: %ld\n",strlen(str1));
	printf("Stringa 2: %s\n",str2);
	int flag = 0;
	for(int i = 0;str1[i]!=0 && flag==0;i++){
		int flag1 = 0;
			if(str2[0]==str1[i]){
			printf("Le iniziali sono uguali");
				flag1 = 1;
				int k;
					for(k = 0; str2[k]!=0 && flag1 == 1;k++){
						if(str2[k]!=str1[k+i]){
						printf("diversi %d\n",str2[k]);
							flag1 = 0;
						}
					}
					if(flag1==1){
						res = &(str1[k+i]);
					}else{
					}
				
				
			}
		flag = flag1;
	
	
	
	
	}
	printf("Il risultato è:\n");
	if(res!=NULL){
		for(int i = 0;res[i]!=0; i++){
		printf("%c",res[i]);
	}
	}else{
	}
}

