#include <stdio.h>

int main(){
	char s[80];
	fgets(s,sizeof(s),stdin);
	printf("%s",s);
	
}
