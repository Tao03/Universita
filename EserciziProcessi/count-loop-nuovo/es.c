# define _GNU_SOURCE /* necessary from now on */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

pid_t pid_child;
char *   string[3];
int state;
void handle_signal(int sigalarm) {
    state = -1;
    printf("Il processo con pid %d non ha finito di contare\n",pid_child);
    int pid = pid_child;
    kill(pid_child,SIGKILL);
    printf("Processo con pid %d è morto\n\n",pid_child);
    if(fork()==0){
        printf("Il processo nuovo sta iniziando a contare\n");
        int num = strtol(string[1],NULL,10);
        num = num/2;
        num = sprintf((string[1]), "%d",num);
        if(num==-1){
            printf("ERRORE\n");
        }
        alarm(strtol(string[0],NULL,10));
        printf("ALLARME avviato con iterazioni: %d \n",string[1]);
        int b = execve("prova.out",string,NULL);
        if(b==-1){
            printf("ERRORE con exvec\n");
        }
    }else{
        while(wait(NULL)!=-1);
    }
}




int main(int argc, char * argv[]){
    void handler (int signal);
    char* timeout = argv[0];
    char* n_iter = argv[1];
    int n_kids = 1;

    struct sigaction sa;
    bzero (&sa , sizeof (struct sigaction));
    sa.sa_handler = handle_signal;
    //sa.sa_flags = SA_NODEFER;
    sigaction( SIGALRM ,&sa ,NULL);
    
    string[0] = timeout;
    string[1] = n_iter;
    int i = 0;
    while(i<n_kids){
        char *  const string[] = {timeout,n_iter,0};
        int a = fork();
        alarm(strtol(timeout,NULL,10));
        if(a==0){
           int b = execve("prova.out",string,NULL);
        }else{
            pid_child = a;
            while(wait(NULL)!=-1);
            
        }
            i = i + 1;
        }
        
    
    printf("Tutti i processi hanno finito di contare\n");
}
