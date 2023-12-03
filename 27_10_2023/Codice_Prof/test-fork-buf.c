#include <stdio.h>
#include <unistd.h>

/*
 * Prova a eseguire con
 * 
 * ./a.out
 * 
 * e con
 *
 * ./a.out > output
 *
 * Poi prova scrivere #if 0 e/o a scommentare setvbuf
 */

int main(int argc, char * argv[])
{
#if 0
	/* Set no buffering for stdout */
	setvbuf(stdout, NULL, _IONBF, 0);
#endif
	fprintf(stdout, "(PID=%d) Prima\n", getpid());
	/* 
	 * dprintf is the non-buffered version of printf,
	 * writing directly to the file descriptor 
	 */
	dprintf(1, "(PID=%d) Dopo\n", getpid());
	fork();
	dprintf(1, "(PID=%d) Dopo fork\n", getpid());
}
