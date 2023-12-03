#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/sysinfo.h>
#include <sys/wait.h>
#include <errno.h>
#include <time.h>
#include <string.h>
#include "my_sem_lib.h"   /* user-defined library to handle semaphores */

/*
 * Uncomment ENABLE_ROUND to enforce the ordering among the child
 * processes
 */
/* #define ENABLE_ROUND */

#define NUM_PROC      4
#define NUM_ITER      20
#define READ_BUF_SIZE 100
#define TEST_ERROR    if (errno) {fprintf(stderr,			\
					  "%s:%d: PID=%5d: Error %d (%s)\n", \
					  __FILE__,			\
					  __LINE__,			\
					  getpid(),			\
					  errno,			\
					  strerror(errno));}


int main (void) {
	pid_t child_pid;
	int my_pipe[2];
	int i, j, status;
	char * buf;
	ssize_t num_bytes;
#ifdef ENABLE_ROUND
	int s_id;
	char my_string[NUM_PROC*10];
#endif
	
	/* 
	 * Create a pipe. Data written to my_pipe[1] is read from
	 * mypipe[0]
	 */
	pipe(my_pipe);  
	TEST_ERROR;

	/* 
	 * Create an array of NUM_PROC semaphores and initializes to
	 * 1 0 0 .... 0 0 0
	 */
#ifdef ENABLE_ROUND
	s_id = semget(IPC_PRIVATE, NUM_PROC, 0600);
	TEST_ERROR;
	for (i=0; i<NUM_PROC; i++) {
		/* Only the first semaphore is available */
		sem_set_val(s_id, i, i ? 0 : 1);
		TEST_ERROR;
	}
	sem_getall(my_string, s_id);
	printf("%s:%d: PID=%5d: Sem: %s\n",
	       __FILE__, __LINE__, getpid(), my_string);
#endif
	
	for (i=0; i<NUM_PROC; i++) {
		switch (fork()) {
		case -1:
			/* Handle error */
			TEST_ERROR;
			exit(EXIT_FAILURE);
		case 0:
			/*
			 * Child process will only write, the read end
			 * must be closed
			 */
			close(my_pipe[0]);
			TEST_ERROR;
			
			for(j=0; j<NUM_ITER; j++) {
#ifdef ENABLE_ROUND
				/* try accessing semaphore i */
				sem_reserve(s_id, i);
				TEST_ERROR;
#endif
				
				dprintf(my_pipe[1],
					"CHILD PID %5d: j=%2d\n", getpid(), j);
				TEST_ERROR;
				
#ifdef ENABLE_ROUND
				/* release semaphore i+1 */
				sem_release(s_id, i== NUM_PROC-1? 0 : i+1 );
				TEST_ERROR;
#endif
			}
			/* Now the write end can be closed as well */
			close(my_pipe[1]);
			TEST_ERROR;
			exit(EXIT_SUCCESS);
			break;
			
		default:
			break;
		}
	}

	/* Parent process will only read, the write end must be closed */
	close(my_pipe[1]);
	TEST_ERROR;
	
	/* Allocated read buffer */
	buf = malloc(READ_BUF_SIZE);
	TEST_ERROR;
	
	while (num_bytes = read(my_pipe[0], buf, READ_BUF_SIZE)) {
		write(STDOUT_FILENO, buf, num_bytes);
		TEST_ERROR;
	}
	
	/* Now let's wait for the termination of all kids */
	while ((child_pid = wait(&status)) != -1) {
		printf("PARENT: PID=%d. Got info of child with PID=%d, status=0x%04X\n", getpid(), child_pid,status);
	}
	
#ifdef ENABLE_ROUND
	/* Deallocate the semaphore */
	semctl(s_id, 0, IPC_RMID);
#endif
	
	exit(EXIT_SUCCESS);
}
