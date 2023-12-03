#include <stdio.h>
#include <errno.h>
int main(int argc, int *argv)
{
    int n_kids = strtol("2", NULL, 10);
    int *array[n_kids];
    srand(getpid());

    int value = (rand() % 10) + 1;

    for (int i = 0; i < n_kids; i++)
    {
        array[i]= malloc(sizeof(int)*2);
        if(array[i] == NULL){
            printf("Errore nell'allocazione del vettore");
        }
        if(pipe( array[i] == -1 )){
            perror("Errore: \n");
        }
    }
    
    for (int i = 0; i < n_kids; i++)
    {
        
        

        if (fork() == 0)
        {
            printf("Processo con pid %d è stato creato\n",getpid());
            char buf[100]; /* stores bytes read from pipe */
            int num_bytes;
            int *a = array[i];

            
            int * b = array[i+1]; // processo successivo
            if (num_bytes = read(a[0], buf, sizeof(buf)) == -1)
            {
                perror("Errore: ");
            }else{
                close(a[0]);
                int val;




                val = strtol(buf,NULL,10);
                printf("Messaggio ricevuto\n");
                if(write(b[1],buf,sizeof(buf))==-1){
                    printf("Errore processo %d-esimo sulla scrittura del file\n",i);
                    perror("Error son write:");
                }
                //close(b[1]);
                
                printf("Messaggio inviato \n");
                exit(0);
            }
            

        }
    }
    int *a = array[0];
    char * buf= malloc(100);
    snprintf(buf, sizeof(buf), "%d", value);
    if (write(array[1][0], buf, sizeof(buf)) == -1)
        {
            perror("write");
        }
    while(wait(NULL)!=-1);
}