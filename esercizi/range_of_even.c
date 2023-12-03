#include <stdio.h>
#include <stdlib.h>
int range_of_even(int* , int , int *, int *);

void stampa(int*);

int main(){
	int *p1;
	int *p2;
	int vettore[]={1,2,3,4};
	int a = range_of_even(vettore,4,p1,p2);
	printf("%d",a);
	printf("Puntatore min: %p",p1);
	printf("Puntatore max: %p",p2);
}




int range_of_even(int* nums, int length, int *min, int *max){
	int max_array = -1111;
	int *max_pt;
	int *min_pt;
	int min_array= 10000;
	for(int i=1;i<length;i++){
		if(*(nums + i)%2==0){
			if(max_array < *(nums + i)){
			max_array = *(nums + i);
			max_pt = (nums + i);
		}
		if(min_array > *(nums + i)){
			min_array= *(nums + i);
			min_pt = (nums + i);
		}
		
		
		}
	}
	min = min_pt;
	max = max_pt;
	if(max_array==-1111){
		return 0;
	}
	return 1;
	

}

void stampa(int* res){
printf("\n");
printf("DIMENSIONE: %ld",sizeof(res));
	for(int i=0;i<sizeof(res);i++){
		printf("[%d]",res[i]);
	}

}


