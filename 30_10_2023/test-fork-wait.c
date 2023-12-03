#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <errno.h>
#include <string.h>

#define NUM_KIDS 8

int main (int argc, char * argv[]) {
	long num_cores, n_loops;
	pid_t child_pid;
	int status, i, j;
	
	/* Read n_loops from argv[1] */
	if (argc < 2) {
		fprintf(stderr, "Please specify argv[1] as n_loops\n");
		return -1;
	} else {
		n_loops = atol(argv[1]);
	}
	
	/* Looping to create child processes */ 
	for (i=0; i<NUM_KIDS; i++) {
		switch (child_pid = fork()) {
		case -1:
			/* Handle error */
			fprintf(stderr, "Error with the fork\n");
			exit(EXIT_FAILURE);
		case 0:
			/* Perform actions specific to child */
			printf("PID= %5d (CHILD): i=%d, running a lot\n",
			       getpid(), i);
			/* Keeping the CPU busy */
			for(j=0; j<n_loops; j++);
			exit(i);
			printf("Hi, \
my PID is %d and you should never see this message\n", getpid());
			break;
			
		default:
			/* Perform actions specific to parent */
			printf("PID= %5d (PARENT): parent of a child with PID %d\n",
			       getpid(), child_pid);
			break;
		}
	}
	
	/* Now let's wait that all kids do the stuff */
	while ((child_pid = wait(&status)) != -1) {
		printf("PID= %5d (PARENT): \
Got info of child with PID=%d, status=0x%04X\n",
		       getpid(), child_pid, status);
	}
	if (errno == ECHILD) {
		printf("In PID=%6d, no more child processes\n",
		       getpid());
		exit(EXIT_SUCCESS);
	} else {
		fprintf(stderr, "Error #%d: %s\n", errno, strerror(errno));
		exit(EXIT_FAILURE);
	}
}
