#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>

int main() {
	/* pid_t is a integer type */
	int a = fork();
    printf("Processo %d",a);
    
}
