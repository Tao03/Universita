#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>


#define NUM_FORKS  2  /* Hope to create NUM_FORKS child processes, but... */

int main(int argc, char *argv[] ) {
	int i;
	unsigned int my_pid, my_ppid, value;
	char *file_name = argv[1];
    int n_kids =  strtol(argv[2],NULL,10);
    int n_writes = strtol(argv[3],NULL,10);
    for(i = 0;i<n_kids;i++){
        if(fork()==0){
            FILE * my_f ;
            my_f = fopen ("prova.txt" , "w" );
            int j = 0;
            while(j < n_writes){
                int a = getpid();
                char *parola;
                fputs(atoi(parola),my_f);
                j = j + 1;
            }
            printf("ciao\n");
        }else{
            exit(EXIT_SUCCESS);
        }
    }
    return 0;
	
}
