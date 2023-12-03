#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>


#define NUM_FORKS  2  /* Hope to create NUM_FORKS child processes, but... */

int main() {
	int i;
	unsigned int my_pid, my_ppid, value;
	
	/* How many processes are really generated? */
	for (i=0; i<NUM_FORKS; i++) {
		
		value = fork();
		my_pid = getpid();
		my_ppid = getppid();
		/* How many printed lines? */
		printf("PID=%6d,  PPID=%6d,  i=%d, fork_value=%d\n",
		       my_pid, my_ppid, i, value);
	}
	exit(EXIT_SUCCESS);
}
