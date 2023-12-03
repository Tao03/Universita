#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main(int argc, char *argv[])
{
    int n_kids = strtol(argv[1], NULL, 10);
    int array[n_kids][2];
    for (int i = 0; i < n_kids; i++)
    {
        if (pipe(array[i]) == -1)
        {
            perror("");
        }
    }
    printf("CHECKPOINT: Pipe inizializzata\n");

    for (int i = 0; i < n_kids; i++)
    {
        int pid = fork();
        if (pid == 0)
        {

            printf("Processo %d creato\n",getpid());
            char buffer[100];
            int num_bytes;
            if (num_bytes = read(array[i][0], buffer, 100) == -1)
            {
                printf("prova \n");
                perror("Error: ");
                exit(1);
            }
            printf("prova \n");
            printf("PID %d messaggio ricevuto: %s\n", getpid(), buffer);
            close(array[i][0]);

            write(array[i + 1][1], buffer, 100);
            close(array[i][1]);
            exit(0);
        }
    }


   
    if (write(array[0][1], "10", 2) == -1)
    {
        perror(" ");
    }
    printf("Inviato \n");
     close(array[0][0]);
    close(array[0][1]);
}