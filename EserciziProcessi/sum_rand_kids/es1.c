#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <errno.h>
#include <string.h>

#define NUM_KIDS 4

int main(int argc, char *argv[])
{
    pid_t child_pid;
    int status, i;

    for (i = 0; i < NUM_KIDS; i++)
    {
        switch (child_pid = fork())
        {
        case -1:
            fprintf(stderr, "Error with the fork\n");
            exit(EXIT_FAILURE);
        case 0:
            srand(getpid());
            int a = rand()%6;
            printf("Il processo %d ha generato il valore %d\n",getpid(),a);
            exit(a);
            break;
        }
    }

    int a=0;
    while ((child_pid = wait(&status)) != -1)
    {
        if(WIFEXITED(status)){
            int child_exit_status = WEXITSTATUS(status);
            printf("b: %d\n",child_exit_status);
            a = a + child_exit_status;
        }
        
        
    }
    printf("Il valore totale è: %d",a);
}
