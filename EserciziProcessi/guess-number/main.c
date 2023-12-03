# define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
void handle_signal(int sigalarm) {
    printf("ALLARME AVVIATO");
    exit(0);
}
int main(int argc, char* argv[]){
    struct sigaction sa;
    bzero (&sa , sizeof (struct sigaction));
    sa.sa_handler = handle_signal;
    //sa.sa_flags = SA_NODEFER;
    sigaction( SIGALRM ,&sa ,NULL);

    srand(time(NULL));
    int valore_terminale = strtol(argv[1],NULL,10);
    int numeroCasualeDaIndovinare = rand()%valore_terminale;
    int valore_utente = -1;
    alarm(strtol(argv[2],NULL,10));
    printf("VALORE DA INDOVINARE: %d\n",numeroCasualeDaIndovinare);
    while(valore_utente!=numeroCasualeDaIndovinare){
        printf("Inserisci un valore\n");
        scanf("%d",&valore_utente);
        if(valore_utente>numeroCasualeDaIndovinare){
            printf("Maggiore\n");
        }else if(valore_utente < numeroCasualeDaIndovinare){
            printf("Minore\n");
        }
    }
    return 0;
}