#include <stdio.h>
int main(int argc, char* argv[]){
    int b=0;
    int a = sprintf(*(argv+1),"%d", b);
    printf("%d",a);
}