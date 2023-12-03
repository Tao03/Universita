#include <stdio.h>
#include <ctype.h>   /* per islower */

/*
 * Programma che  legge in  ingresso il file  passato in  ingresso con
 * argv[1] e  scrive in uscita il  file argv[2] in cui  ogni carattere
 * minuscolo di argv[1] viene trasformato in maiuscolo
 */
int main(int argc, char * argv[])
{
	FILE *f_i, *f_o;
	int c;
	
	/* Opening the file to be read */
	if ((f_i = fopen(argv[1], "r")) == NULL) {
		fprintf(stderr, "%s:%d Errore in apertura \"%s\" in lettura.\n",
		__FILE__, __LINE__, argv[1]);
		return -1;
	}

	/* Opening the file to be written */
	if ((f_o = fopen(argv[2], "w")) == NULL) {
		fprintf(stderr, "%s:%d Errore in apertura \"%s\" in scrittura.\n",
		__FILE__, __LINE__, argv[2]);
		return -1;
	}

	/* Looping reading and writing */
	while ((c = fgetc(f_i)) != EOF) {
		/* minuscolo in maiuscolo */
		c = islower(c) ? ~0x20 & c : c;

		fputc(c, f_o);
	}
	printf("Last position of file %lu\n", ftell(f_i));
	
	fclose(f_o);
	fclose(f_i);
}
