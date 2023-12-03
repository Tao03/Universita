#define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>
char * stringa[2]={"Tao","San"};
int main(int argv, char*argc[]){
    int n = strtol(argc[1],NULL,10);
    int * array_kids = malloc(sizeof(int)*n);
    printf("Dimensione %d\n",n);
    for(int i=0;i<n;i++){
        int a = fork();
        printf("Processo nuovo è stato creato con pid %d \n",a);
        if(a==0){
            if(execve("kids",stringa,NULL) == -1){
                perror("execve");
                printf("valore: %d",errno);
            }
        }else{
            array_kids[i] = a;
           
        }
    }
         for (int i = 0; i < n; i++)
         {
            sleep(1);
            int status;
            kill(array_kids[i],SIGINT);
            waitpid(array_kids[i], &status, 0);
            int exit_value = WEXITSTATUS(status);
            printf("Carattere corrispondente %c\n",exit_value);
         }
         
    
}