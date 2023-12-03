#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main(int argc, char * argv[]){
    char *n_iter = argv[1];
    int n = strtol(n_iter,NULL,10);
    for(int i=0;i<n;i++){
        sleep(1);
        printf("Il processo %d dice: mi sono appena svegliato da un bel ronf ronf mimimi\n",getpid());
    }
}