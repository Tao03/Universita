#define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>
char *stringa[2] = {"Tao", "San"};
int *array_kids;
int dimensione;
int s=0;
void handler(int signum)
{
    srand(getpid());
    int flag = 0;
    int indice_vittima;
    int pid_figlio_casuale;
    do{
        indice_vittima = rand() % dimensione;
        pid_figlio_casuale = array_kids[indice_vittima];
        flag = (pid_figlio_casuale>0?1:0);
    }while(flag==0);
    
    
        if (kill(array_kids[indice_vittima], SIGINT) == 0)
        {
            printf("Processo ucciso\n");
             array_kids[indice_vittima] = -1;
            int status;
            waitpid(pid_figlio_casuale, &status, 0);
            int exit_value = WEXITSTATUS(status);
            s = s + exit_value;
            if (s % 256 == 0)
            {
                for(int i = 0;i<dimensione;i++){
                    int pid_figlio_casuale = array_kids[i];
                    if(pid_figlio_casuale>0){
                        if(kill(pid_figlio_casuale,SIGINT)==0){
                            int status;
                            waitpid(pid_figlio_casuale, &status, 0);
                            int exit_value = WEXITSTATUS(status);
                            printf("Processo con pid %d eliminato!",pid_figlio_casuale);
                            array_kids[i] = -1;
                        }else{
                            perror("Errore");
                        }
                    }
                }
            }
            else
            {
                int a = fork();
                printf("Processo nuovo è stato creato con pid %d \n", a);
                array_kids[indice_vittima] = a;
                if (a == 0)
                {
                    if (execve("char-loop", stringa, NULL) == -1)
                    {
                        perror("execve");
                        printf("valore: %d", errno);
                    }
                }else{
                    alarm(1);
                    while(wait(NULL)!=-1);
                }
                
            }
            printf("Carattere corrispondente %c\n", exit_value);
        }
        else
        {
            perror("Errore\n");
        }
    
}
int main(int argv, char *argc[])
{

    int n = strtol("1", NULL, 10);
    array_kids = malloc(sizeof(int) * n);
    dimensione = n;

    struct sigaction sa;
    bzero(&sa, sizeof(struct sigaction));
    sa.sa_handler = handler;
    sa.sa_flags = SA_NODEFER;
    sigaction(SIGALRM, &sa, NULL);

    printf("Dimensione %d\n", n);
    for (int i = 0; i < n; i++)
    {
        int a = fork();
        printf("Processo nuovo è stato creato con pid %d \n", a);
        if (a == 0)
        {
            if (execve("char-loop", stringa, NULL) == -1)
            {
                perror("execve");
                printf("valore: %d", errno);
            }
        }
        else
        {
            array_kids[i] = a;
        }
    }
    alarm(1);
    while(wait(NULL)!=-1);
}