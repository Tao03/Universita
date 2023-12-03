#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main(int argc, char*argv[]){
    int *array = malloc(sizeof(int)*2);
    if(pipe(array)==-1){
        perror("");
    }
    printf("Pipe inizializzata\n");
    int pid = fork();
    if(pid==0){
        close(array[0]);
        write(array[1],"cassa!",10);
        close(array[1]);
    }else{
        char buffer[100];
        int num_bytes;
        close(array[1]);
        if(num_bytes = read(array[0],buffer,100)==-1){
            perror("");
            exit(1);
        }
        close(array[0]);
        printf("%s\n",buffer);
    }
}