#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

struct sigaction{
    void (*sa_handler)( int signum);
};

void handler(int);

int main(int argc, char * argv){
    char* timeout = "3";
    char* n_iter = "3";
    int n_kids = 3;

    struct sigaction sa;
    bzero (&sa , sizeof (sa));
    sa.sa_handler = handler;
    sigaction(SIGALRM,&sa,NULL);

    for(int i = 0;i<n_kids;i++){
         char *  const string[] = {timeout,n_iter,0};
        if(fork()==0){
            int a = alarm(1);
            execve("prova.out",string,NULL);
            exit(0);
        }else{
            while(wait(NULL)!=-1);
        }
        
    }
}
void handler(int sigalarm) {
    printf("Timer di allarme scaduto! \n");
}