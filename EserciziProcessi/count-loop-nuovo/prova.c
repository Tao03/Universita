# define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>


int main(int argc, char * argv[]){
     printf("------------------------------\n");
    printf("Il processo con PID %d e ppid: %d è appena partito\n",getpid(),getppid());
    char *n_iter = argv[1];
    int n = strtol(n_iter,NULL,10);
    for(int i=0;i<n;i++){
        printf("Processo %d: sto contando\n",getpid());
    }

    printf("Processo pid: %d con ppid: %d ha appena terminato contando %d volte \n",getpid(),getppid(),n);
    exit(0);
}