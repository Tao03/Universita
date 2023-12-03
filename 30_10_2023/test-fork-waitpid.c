#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <errno.h>
#include <string.h>

#define NUM_KIDS 8
/*
#define WAIT_KIDS_IN_ORDER
*/

int main (int argc, char * argv[]) {
	long n_loops;
	int j, num_alive_procs, status;
	pid_t * all_kids, child_pid;
	
	/* Read n_loops from argv[1] */
	if (argc < 2) {
		fprintf(stderr, "Please specify argv[1] as n_loops\n");
		return -1;
	} else {
		n_loops = atol(argv[1]);
	}
	
	/* vector to store PID of child processes */
	all_kids = malloc(NUM_KIDS*sizeof(*all_kids));

	/* Looping to create child processes */ 
	for (num_alive_procs=0; num_alive_procs<NUM_KIDS; num_alive_procs++) {
		switch (child_pid = fork()) {
		case -1:
			/* Handle error */
			exit(EXIT_FAILURE);
		case 0:
			/* CHILD CODE */
			/* Free the array: not needed in child */
			free(all_kids);
			printf("PID= %5d (CHILD): i=%d, running a lot\n",
			       getpid(), num_alive_procs);
			/* Keeping the CPU busy */
			for(j=0; j<n_loops; j++);
			exit(num_alive_procs);
			break;
		default:
			/* PARENT CODE */
			printf("PID= %5d (PARENT): parent of a child with PID %d\n",
			       getpid(), child_pid);
			all_kids[num_alive_procs] = child_pid;
			break;
		}
	}
	
	/* PARENT CODE: the child processes exited already */
	
	/* checking if any child proc terminated, stopped or continued */

#ifndef WAIT_KIDS_IN_ORDER
	/* wait for any child process */
	while ((child_pid = waitpid(-1, &status,0)) != -1) {
#else
	/* wait for child processes from the latest created */
	while ((child_pid = waitpid(all_kids[num_alive_procs-1], &status,0)) != -1) {
#endif
		printf("PID= %5d (PARENT): \
Got info from child with PID=%d. Status 0x%04X\n",
		       getpid(), child_pid, status);
		/* Checking the status */
		if (WIFEXITED(status)) {
			/* 
			 * the child proc exited, must decrement
			 * num_alive_procs
			 */
			printf("  child correctly exited with status %d\n",
			       WEXITSTATUS(status));
			num_alive_procs--;
		}
		if (WIFSIGNALED(status)) {
			/* 
			 * the child proc terminated by signal, must
			 * decrement num_alive_procs
			 */
			printf("  child terminated by the signal %d\n",
			       WTERMSIG(status));
#ifdef WCOREDUMP
			if (WCOREDUMP(status)) {
				printf("  child produced a core dump\n");
			}
#endif
			num_alive_procs--;
		}
		
		printf("PID= %5d (PARENT): Kids left=%d\n",
		       getpid(), num_alive_procs);
	}
	
	fprintf(stderr, "PID= %5d (PARENT): \
done with waiting because: %s (Err #%d)\n",
		getpid(), strerror(errno), errno);
	
	free(all_kids);
	exit(EXIT_SUCCESS);
}
