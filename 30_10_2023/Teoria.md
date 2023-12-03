# Primo approccio di sincronizzazione tra processi
### Il metodo Waiting
Un processo padre può aspettare la terminazione del processo figlio attraverso un modo di sincronizzazione nominato **waiting**<br>
Per esempio il comando può essere usato in ben 2 casi:
> **gedit**: <br>
Consiste nell'eseguire gedit che è un processo figlio e aspetta fino a quando il processo gedit non termini.
<hr>

> **gedit &** : 
Consiste nell'eseguire gedit che è un processo figlio, ma in questo caso non aspetta la terminazione del processo figlio e va avanti creando altri processi.

### Aspettando la terminazione con wait in C
Se si vuole implementare questo semplice meccanismo di sincronizzazione, basta saper utilizzare il metodo 
> **pid_t wait(NULL)**
In questo caso il metodo può restituire un valore che assume 2 significati diversi:
1. Nel caso fosse uguale a -1 significa che tutti i processi figli hanno terminato l'esecuzione
2. Nel caso fosse diverso da -1 significa che il processo che ha come pid il valore restituito ha terminato la sua esecuzione.

**Nota bene**: Questa tecnica consiste nel chiamare una **system call** che blocca il processo, può sembrare una tecnica scomoda ma è molto comoda e usata quando il processo padre deve aspettare la terminazione dei processi figli\
### A cosa succede se i processi genitori terminano prima?
Non è consigliabile questa situazione e difatti si utilizza il primo metodo di sincronizzazione per evitare questa possibilità.\
Tuttavia può capitare che il **processo figlio termini prima di una wait di un processo padre**: le risorse richieste al processo figlio vengono rilasciate ma **la riga della tabella dei processi associata al figlio rimane** e quindi diventa un processo zombie\
Per risolvere questo problema basta utilizzare un'altra wait, così il processo padre si accorge della terminazione del processo figlio

### Terminazione di un processo e lo stato di uscita
Ci sono 2 motivo per cui un processo termina:
1. Interno, quindi il processo decide di terminare attraverso una **return** oppure una **system call**
2. Esterno, attraverso un segnale che porti alla terminazione il processo come il comando KILL nel terminale, si chiama esterno siccome non è il processo a decidere di terminare ma enti esterni.

Se si vuole aspettare un determinato processo figlio allora si può optare per l'istruzione
>pid_t waitpid ( pid_t pid , int * status_child , int options ).

Dopo aver chiamato la funzione, il processo aspetta fino a quando il processo desiderato **ha terminato la sua esecuzione**. Nel caso il valore restituito è -1 allora c'è stato un errore e attraverso la macro errno si può definire il tipo di errore, altrimenti restituisce il pid del processo figlio che si aspettava.


