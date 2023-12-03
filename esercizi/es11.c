#include <stdio.h>
int potenza(int);
int main(){
	char stringa[128];
	printf("Inserisci una stringa");
	fgets(stringa,128,stdin);
	int a = 0;
	int j=0;
	while(stringa[j]!=0){
		j = j  + 1;
	}
	j = j - 1;
	for(int i = j-1;i>=0;i--){
		a = (( (int) stringa[i])-48)*potenza(j-i-1) + a;
	}
	printf("Il valore è: %d",a);
	return 0;

}
int potenza(int i){
	if(i==0){
		return 1;
	}else{
		return potenza(i-1)*10;
	}
	
}


