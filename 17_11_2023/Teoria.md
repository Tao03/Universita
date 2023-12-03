# Semafori
Nella programmazione concorrente, se non viene gestito, il valore di uscita del programma varia da esecuzione a esecuzione.\
Questo perchè l'ordine con cui i processi vengono eseguiti varia e l'uso dei **semafori è utile per la sincronizzazione dei processi**

Per ogni semaforo all'interno del SO, viene registrato una struttura dati che memorizza il valore di una variabile s (sempre positiva).

Il valore del semaforo indica il numero di accessi possibili ad una risorsa.

Un qualsiasi processo, davanti ad una risorsa condivisa si comporta nel seguente modo:
1. si salva il valore del semaforo in una variabile a
2. il processo cercherà di acquisire la risorsa nel seguente modo:
    1. se il valore del semaforo è pari a 0, fai attendere l'acquisizione della risorsa fino a quando il valore del semaforo non incrementi
    2. altrimenti: decrementa il valore del semaforo e permetti l'acquisizione della risorsa al processo
    3. dopo l'uso della risorsa, il processo la rilascia, questo comporta a incrementare il valore del semaforo

### Creazione ed uso di un semaforo (Standard V)
Lo standard System V implementa un **array di semafori**, qualsiasi operazione effettuata sui semafori di questo tipo sono **atomici**.\
La funzione seguente crea un array di **nsems** semafori e restituisce l'identificatore di quest'ultimo:
> int semget ( key_t key , int nsems , int semflag );
- Il parametro semflag è un risultato di **OR logici** parametri:
    1. Permessi di lettura e scrittura (i 9 bit meno significativi)
    2. IPC_CREAT
        1. Crea un nuovo semaforo associato alla chiave se non esiste
        2. altrimenti restituisce il semaforo esistente associato alla chiave
    3. IPC_EXCL ( usato soltanto con IPC_CREAT ): in caso di errore controllare il campo errno

Dopo la creazione, i semafori non sono inizializzati e dobbiamo farlo noi esplicitamente attraverso la funzione **semctl1()**.\
Anche loro, devono essere eliminati esplicitamente altrimenti rimangono in memoria.

### Operazione su un singolo semaforo
L'uso e il rilascio di un semafoto è nominato come **semaphore operation** e sono memorizzati all'interno di una struttura dedicata:
```C
struct sembuf {
    unsigned short sem_num ; /* sem number */
    short sem_op ; /* sem operation */
    short sem_flg ; /* flags */
}
```
E' possibile passare da una singola operazione a più operazioni attraverso la seguente funzione:
> int semop ( int s_id , struct sembuf * ops , size_t nops );
- ops è l'array delle operazioni
- nops è il numero di operazioni da fare (vengono fatte in modo atomiche)
- La chiamata viene bloccata se una delle operazioni non può essere eseguita

### Operazioni di accesso/rilascio su una risorsa
Per accedere ad una risorsa, il valore del semaforo deve essere **decrementato**: 
> my_op.sem_op =  - <num - res>.

**NB:** il processo si blocca se le <num - res> risorse non sono disponibili.\

Invece per rilasciare una risorsa protetta da un semaforo, si deve
incrementare il valore del semaforo:
> my_op.sem_op =   <num - res>.

**NB:** il processo **NON** si blocca quando incrementa il valore del semaforo

Il valore sem_num indica l'indice che indica la posizione del semaforo all'interno dell'array.

### Protezione di una sezione critica
In questo esempio, vari processi vogliono accedere ad una risorsa condivisa ma per eviare problemi di **inconsistenza** si utilizzano i semafori:
```C
struct sembuf my_op ;
int sem_id ;
...
sem_id = semget ( IPC_PRIVATE /* key */ , 1 /* nsems */ , 0600 /* flags */);
/* Initialize the semaphore allowing one access only */
semctl ( sem_id , 0, SETVAL , 1) ; /* later details of semctl call */
/* sharing sem_id with all processes accessing the resource */
...
/* now trying to access critical section */
my_op . sem_num = 0; /* only one semaphore in array of semaphores */
my_op . sem_flg = 0; /* no flag : default behavior */
my_op . sem_op = -1; /* accessing the resource */
semop ( sem_id , & my_op , 1) ; /* blocking if others hold resource */
/* NOW IN CRITICAL SECTION */
...
my_op . sem_op = 1; /* releasing the resource */
semop ( sem_id , & my_op , 1) ; /* may un - block others */
```

### Situazione dove N processi aspettano che si liberi la risorsa
Se N processi devono aspettare che il processo B termini allora:
1. il semaforo è inizializzato a 1
2. tutti i processi aspettano che il valore del semaforo sia a 0 con un operazione indicata nel campo my_op.semp_op 
    > my_op.semp_op = 0
3. Il processo B decrementa il valore del semaforo di uno
4. Appena il valore del semaforo passa a 0, gli altri processi acquisiscono la risorsa

### Modi per evitare di aspettare per sempre
Nel caso una risorsa protetta da un semaforo non è disponibile, il processo:
1. potrebbe aspettare fino a quando la risorsa non sia nuovamente disponibile
2. oppure decide di fare qualcosa

Il tutto può essere deciso se modifichiamo il campo della struct:
```C
struct sembuf sop ;
sop . sem_flg = IPC_NOWAIT ;
semop (.. , & sop , 1) ; /* dont wait */
```
Appena si esegue il metodo **semop**:
1. Se la risorsa è disponibile, allora viene ceduta e continua con l'esecuzione
2. Nel caso non fosse disponibile, allora restituisce -1 e il campo **errno** viene impostato ad **EAGAIN**

Si può anche aggiungere l'opzione che il processo aspetti la risorsa per un intervallo di tempo:
```C
#include <time.h>
struct timespec {
time_t tv_sec ; /* seconds */
long tv_nsec ; /* nanoseconds */ }
semtimedop (/* same as semop */ , struct timespec * timeout );
```

Un pessimo modo di aspettare l'acquisizione della risorsa:
intervallo di tempo:
```C
sop.sem_flg = IPC_NOWAIT ;
do {
semop (.. , & sop , 1) ;
}while ( errno == EAGAIN );
```
### Operazioni di controllo sui semafori
Per ottenere varie informazioni su un determinato semaforo, si può utilizzare il metodo seguente:
```C
int semctl ( int s_id , int i , int cmd );
int semctl ( int s_id , int i , int cmd , /* arg */);
```

1. s_id è l'identificatore dell'oggetto 
2. i è l'indice associato all'oggetto all'interno del vettore
3. cmd descrive indica le azioni eseguite sul semaforo
4. c'è un quarto parametro che è opzionale e dipende dal tipo del comando

Per inizializzare o ottenere il valore i-esimo del semaforo:
```C
int semctl ( int s_id , int i , SETVAL , int val );
int semctl ( int s_id , int i , GETVAL ); // in questo caso viene restituito il valore del semaforo i-esimo
```

Per sapere il numero dei processi che sono bloccati dal semaforo i-esimo:
```C
int semctl ( int s_id , int i , GETNCNT ) ;
```

Per sapere l'ultimo processo che ha acceduto alla risorsa:
```C
int semctl ( int s_id , int i , GETPID ); //restituisce il pid dell'ultimo processo che ha eseguito il metodo semop(s_id,...) sul i-esimo semaforo
```

Per deallocare il semaforo:
```C
int semctl ( int s_id , /* ignored */ , IPC_RMID ); //restituisce il pid dell'ultimo processo che ha eseguito il metodo semop(s_id,...) sul i-esimo semaforo
```
**NB: quando un processo è bloccato su una semop() e il semaforo viene deallocato/rimosso il processo bloccato viene sbloccato e restituisce il valore -1 con campo erno impostato a EIDRM**

Quando un processo è bloccato su una semop() e riceve un segnale non mascherato:
1. Lo handler viene eseguito
2. la semop che è stata richiamata restituisce -1 e il campo errno viene impostato a EINTR

Anche se il flag SA_RESTART fosse impostato nell'handler del segnale tramite la funzione sigaction(...), una semop(...) interrotta fallirà sempre con errno impostato su EINTR.