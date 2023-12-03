#include <stdio.h>
int main(){
	char str1[]="Lampa";
	
	char str2[]={'d','a','r','i','o',0};
	
	char str3[sizeof(str1)+sizeof(str2)];
	
	int i;
	
	for(i = 0;i<sizeof(str1);i++){
		str3[i] = str1[i];
	}
	
	printf("%s\n", str3);
	for(int j = 0;j<sizeof(str2);j++){
		str3[i+j] = str2[j];
	}
	printf("%s\n", str3);
	for(int j = 0;j<sizeof(str3);j++){
		printf("%c", str3[j]);
	}

}		
