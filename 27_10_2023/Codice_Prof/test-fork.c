#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>


int main() {
	pid_t my_pid, my_ppid, value;
	int var = 4;
	
	switch (value = fork()) {
	case -1:
		/* Handle error */
		fprintf(stderr,"Error #%03d: %s\n", errno, strerror(errno));
		break;
		
	case 0:
		/* Perform actions specific to child */
		my_pid = getpid();
		my_ppid = getppid();
		printf("CHILD:  PID=%d, PPID=%d, fork_value=%d\n",
		       my_pid, my_ppid, value);
		sleep(2);   /* "waiting" the parent proc to modify var */
		printf("CHILD: (after the parent sets var=8) var=%d, &var=%p\n", var, &var);
		break;
		
	default:
		/* Perform actions specific to parent */
		my_pid = getpid();
		my_ppid = getppid();
		printf("PARENT: PID=%d, PPID=%d, fork_value=%d\n",
		       my_pid, my_ppid, value);
		var = 8;
		printf("PARENT: var=%d, &var=%p\n", var, &var);
		break;
	}
	/* Both child and parent process will execute here!! */ 
	exit(EXIT_SUCCESS);
}
