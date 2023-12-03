#define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
unsigned char c;
void handle_signal(int signum){
    printf("Evento signum!\n");
    exit(c);
}
int main(int argv, char*argc[]){

    struct sigaction sa;
    bzero (&sa , sizeof (struct sigaction));
    sa.sa_handler = handle_signal;
    //sa.sa_flags = SA_NODEFER;
    sigaction( SIGINT ,&sa ,NULL);

    c = *(argc[0]);
    while(1){
        c = c + 1;
        if(c==126){
            c = 33;
        }
    }
}