#define _GNU_SOURCE    /* NECESSARY from now on */
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <signal.h> /* per usare sigaction(), sig*() */


/*
 * Handler of a signal is a function taking an integer as input and
 * producing no output
 */
void handle_signal(int signal);

/*
 * Run the program and send to the process signals from another
 * terminal
 */


int main() {
	struct sigaction sa;
	int i;
	
	/* Print pid, so that we can send signals from other shells */
	printf("My pid is: %d\n", getpid());
	
	bzero(&sa, sizeof(sa));  /* set all bytes to zero */
	/* a pointer to the function handling the signal */
	sa.sa_handler = handle_signal;
	
	/* NSIG is a macro set to the number of signals */
	printf("Setting the my handler to handle all first %d signals\n", NSIG);
	for (i=0; i<NSIG; i++) {
		if (sigaction(i, &sa, NULL) == -1) {
			fprintf(stderr,
				"Cannot set a user-defined handler for Signal #%d (%s)\n", i, strsignal(i));
		}
	}
	
	for (;;) {
		printf("Sleeping for ~3 seconds\n");
		sleep(3);
	}
}

void handle_signal(int signal) {
	
	printf("Got signal #%d: %s\n", signal, strsignal(signal));
}
