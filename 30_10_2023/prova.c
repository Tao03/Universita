#include <stdio.h>
#include <stdlib.h>
int main(int argv, char argc[]){
    int a = fork();
        if(a!=0){
            printf("Processo padre con pid: %d\n",getpid());
            printf("Processo padre ha ppid: %d\n",getppid());
            printf("--------------------");
            exit(EXIT_SUCCESS);
            //while(wait(NULL)!=-1);
        }else{
                sleep(2);
                printf("Processo con PPID: %d\n",getppid());
                exit(EXIT_SUCCESS);
            
        }
    
    
    
    
}