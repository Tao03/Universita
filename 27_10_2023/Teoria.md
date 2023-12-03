<h1>Processi</h1>

Il comando " kill " invia un segnale al processo, un esempio può essere SIGKILL (aggiungendo un PID) porta
alla terminazione del processo.
Il comando kill può essere usato anche per mettere in pausa il processo oppure farlo continuare.

Siccome tutti i processi sono identificati da un PID e ha un processo Padre che gli ha generati, le system call:
<ul>
    <li> getpid() : consiste nel restituire il PID del processo corrente </li>
    <li> gettpid() : consiste nel restituire il PID del processo padre </li>
</ul>
<h3> Comandi utili nel terminale</h3>
<ul>
    <li> <b>ps -Af | less</b>  : Mostra tutte le informazioni di tutti i processi attivi </li>
    <li> <b>pstree -p | less</b> : Visualizza l'albero gerarchico dei processi attivi </li>
</ul>
<h3>Creazione di un processo</h3>
    <p>L'istruzione fork() è una system call che <b> consiste al processo chiamato ora padre di creare un processo figlio</b></p> 
    
    #include < unistd.h >
    pid_t fork ( void );
<hr>
<p>Con questa porzione di codice il sistema operativo duplica il processo padre, quindi il processo figlio appena generato avrà le stesse informazioni
del padre e verrà eseguito nuovamente</p>
<b> ATTENZIONE: </b> Siccome sono aree di memoria condivise, il processo padre non nota che il figlio modifichi le informazioni al momento dell'esecuzione
<p> Un altro aspetto interessante è che la funzione restituisce 2 valori distinti: </p>
<ul>
    <li> Nel processo Padre, il PID del figlio </li>
    <li> Nel processo figlio, 0 </li>
</ul>
<h3>Differenziare processo padre e figlio</h3>
<p>L'unico modo per differenziare l'esecuzione dei 2 processi è attraverso il seguente codice:
   
```C
/* Executed only once */
if ( fork () ) {
    /* Executed by parent only */
} else {
    /* Executed by child only */
}
/* Executed twice : by both parent and child */
```

<hr>
Il valore che ritorna la funzione ci dice che processo stiamo eseguendo

