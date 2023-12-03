#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>

int main() {
	/* pid_t is a integer type */
	pid_t my_pid, my_ppid;
	
	/* PID of the invoking process */
	my_pid = getpid();
	/* PID of the parent of the invoking process */
	my_ppid = getppid();
	printf("%s: My PID is %d and my parent's PID is %d\n",
	       __FILE__, my_pid, my_ppid);
	
	return 0;
}
