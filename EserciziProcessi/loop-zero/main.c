#define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
int var;
int valore_limite;
int *processi;
int c = 0;
int flag_kids;
int dimensione = 0;
void handle_singal(int signal)
{
    printf("SEGNALE RICEVUTO\n");
    if (var > valore_limite)
    {
        int flag;
        do
        {

            flag = 0;
            srand(time(NULL));
            int processoDaEliminare = rand() % dimensione;
            //printf("Si vuole eliminare il %d\n", processoDaEliminare);
            printf("------------------------------\n");
            printf("Valore: %d\n",processi[0]);
            printf("Valore: %d\n",processi[1]);
            printf("------------------------------\n");
            if (processi[processoDaEliminare] > 0)
            {
                printf("Ora il processo padre elimina il processo figlio con pid: %d\n", processi[processoDaEliminare]);
                if (kill(processi[processoDaEliminare], SIGKILL) == -1)
                {
                    perror("Errore nell'uccisione del processo figlio\n");
                }else{
                    printf("Ucciso\n");
                    processi[processoDaEliminare] = -1;
                    flag = 1;
                    c = c + 1;
                    printf("Processi uccisi: %d\n",c);
                }
                
            }
        } while (flag == 0);
    }else{
                printf("Il valore contato dal processo è inferiore al limite\n");
        }

    printf("Fine, valore limite: %d  e var: %d \n", valore_limite, var);
}
int main(int argc, char *argv[])
{
    var = 0;
    valore_limite = strtol(argv[3], NULL, 10);
    void handler(int signal);
    struct sigaction sa;
    bzero(&sa, sizeof(struct sigaction));
    sa.sa_handler = handle_singal;
    sa.sa_flags = SA_NODEFER;
    sigaction(SIGUSR1, &sa, NULL);

    int limite = strtol(argv[2], NULL, 10);

    int n_kids = strtol(argv[1], NULL, 10);
    dimensione = n_kids;

    processi = (int*) malloc(sizeof(int)*n_kids);
    printf("DIMENSIONE: %d\n", dimensione);
    for (int i = 0; i < n_kids; i++)
    {
        int a = fork();
        if (a == 0)
        {
            printf("NATO PROCESSO FIGLIO CON PID: %d\n", getpid());
            while (1)
            {
                //printf("Il processo %d conta: %d\n",getpid(), var);
                var = var + 1;
                if (var == limite)
                {
                    var = 0;

                    kill(getppid(), SIGUSR1);
                }

                sleep(1);
            }
        }
        else
        {

            processi[i] = a;
            printf("---------------------\n");
            printf("Processo con pid %d aggiunto\n",processi[i]);
            printf("---------------------\n");
        }
    }
    while (1)
    {
        var = var + 1;
        if (var == limite)
        {
            var = 0;
        }
        if (c == dimensione)
        {
            exit(0);
        }
    }
}