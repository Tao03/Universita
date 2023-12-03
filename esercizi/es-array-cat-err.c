/* Codice che concatena le due stringhe v1 e v2 */
int main()
{
	char v1[10] = "Prima,"
	char v2[10] = " dopo"
	int len1, len2, i
	
	 /* Lunghezza delle due stringhe */
	len1 = sizeof(v1);
	len2 = sizeof(v2);

	/* Stampa delle due stringhe */
	printf("%d\n", v1);
	printf("%d\n", v2);	

	/* Copia la seconda stringa alla fine della prima */
	for (i=0; i<len2; i++)
		v1[len1+i] = v2[i];
	
	/* Stampa la stringa concatenata */
	printf("%d\n", v1);
	
}
