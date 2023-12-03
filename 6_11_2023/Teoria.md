## Lifecycle of signals: delivering, masking, merging
I segnali vengono generati via **hardware/software** e possono essere posticipati attraverso una **maschera**.\
Se un processo è mascherato, il segnale appena generato rimarrà nello stato di **pending**.\
Appena si toglie la maschera, il processo riceve il segnale.
Nel caso di 2 o più segnali venissero generati mentre il processo è mascherato\
viene effettuato il merge e appena il processo non è mascherato, il segnale (che è risultante di tutti i segnali precedenti) viene spedito al processo.

### Configurare una signal mask
Durante l'esecuzione, ogni processo ha una sua **signal mask**.\
Nel caso di una fork(), un processo figlio eredita la signal mask del padre, una signal mask è una collezione di tutti i segnali(si parla di 64 bit dove ogni bit è associato ad un segnale) e può essere modificata attraverso la system call:
>sigprocmask(...)

In c, esiste il tipo **sigset_t** che indica una struct di tipo maschera, le funzioni per lavorare con esse sono le seguenti:
```C
int sigemptyset ( sigset_t * set ) ;
int sigfillset ( sigset_t * set ) ;
int sigaddset ( sigset_t * set , int signum ) ;
int sigdelset ( sigset_t * set , int signum ) ;
int sigismember ( const sigset_t * set , int signum ) ;
```

Un modo per impostare una signal mask al processo corrente è quello di usare la\funzione **sigprocmask()**, nel seguente blocco di codice si vedere il numero e i tipi di parametri richiesti:
```C
# include < signal .h >
int sigprocmask ( int how , const sigset_t * set ,
sigset_t * oldset ) ;
```
1. oldset è la maschera vecchia
2. la maschera però può essere messa in vari modi:
    1. SIG_BLOCK: i segnali presenti nell'insieme dei segnali vengono aggiunti alla maschera corrente
    2. SIG_UNBLOCK: i segnali presenti nell'insieme vengono rimossi dalla maschera corrente
    3. SIG_SETMASK

Appena il segnale che viene inviato, lo handler lo maschera.
Il tutto viene fatto attraverso il campo **sa_handler** della sigaction (**VISTO LEZIONE SCORSA**): 
```C
/* How to mask a signal during SIGINT handler */
struct sigaction sa ;
sigset_t my_mask ;
bzero (& sa , sizeof (sa)) ; /* clean sa struct */
sa . sa_handler = handle_signal ; /* set handler */
sigemptyset (& my_mask ) ; /* Set an empty mask */
/* Add a signal to the sa_mask field struct sa */
sigaddset (& my_mask , signal_to_mask_in_handler ) ;
sa . sa_mask = my_mask ;
/* Set the handler */
sigaction ( SIGINT , & sa , NULL ) ;
```

### Segnali accorpati 
Nel caso un segnale venisse inviato ad un processo mascherato, il segnale va in stato di pendling e nel caso dovesse esserci un altro segnale, quest'ultimo viene "fuso" con il primo.
Questo perchè la presenza di un segnale viene registrata attraverso un valore booleano e non con un valore intero.

### Permettere l'esecuzione dei segnali durante la gestione di un altro segnale
Normalmente, quando viene inviato un segnale ad un processo che sta eseguendo un signal handler, il segnale nuovo viene bloccato fino a quando non finisce lo handler precedente.\
Tuttavia si può rendere questo sistema più flesssibile modificando il campo **sa_flags** come si vede nell'esempio:
```C
bzero (& sa , sizeof ( sa ) ) ;
sa.sa_handler = handle_signal ;
sa.sa_flags = SA_NODEFER ; /* nested signals */
sigaction ( SIGUSR1 , & sa , NULL ) ;
```
In parole povere vuole dire semplicemente che il processo gestisce il segnale anche se sta gestendo un segnale più vecchio(questo viene interotto momentaneamente)

### Generare un segnale ad un processo che è in stato di waiting
Ci sono 3 modi per portare un processo in stato di **wait**:
1. pause():
    ```C
    bzero (& sa , sizeof ( sa ) ) ;
    sa.sa_handler = handle_signal ;
    sa.sa_flags = SA_NODEFER ; /* nested signals */
    sigaction ( SIGUSR1 , & sa , NULL ) ;
    ```
    il processo rimane in stato di wait fino a quando non riceve un segnale

2. sleep(sec):
    ```C
    #include < unistd .h >
    unsigned int sleep ( unsigned int seconds ) ;
    ```
    Il processo si mette in stato di wait per un tot di secondi, se il processo riceve un segnale mentre è in "sleeping", si sveglia e la funzione restituisce il tempo rimanente per cui il processo deve ancora dormire.\
    **NOTA BENE: NON USARE SLEEP NEL PROGETTO, E' UNA PESSIMA IDEA IN GENERALE**

3. nanosleep():
    ```C
    #include < time .h >
    struct timespec my_time ;
    my_time . tv_sec = 1;
    my_time . tv_nsec = 234567000;
    nanosleep (& my_time , NULL ) ;
    ```
    E' la miglior alternativa allo sleep()

### Pessimo modo di sincronizzare i processi
Un modo **SBAGLIATO** di sincronizzare l'esecizione dei processi è quello di usare sleep() o nanosleep() e lo si può notare guardando il seguente blocco di codice:
```C
if ( fork () ) {
/* PARENT */
sleep (10) ;
/* the parent thinks the child has finished */
} else {
/* CHILD */
/* do my work before the parent checks */
}
```
E' sbagliato per 2 semplici motivi:
Innanzitutto noi non sappiamo se il tempo di esecuzione del processo figlio sia minore o uguale a 10 secondi.
Il secondo motivo è che il processo figlio potrebbe non essere schedulato per più di 10 secondi, in questo caso non ha neanche iniziato.\
Quindi possiamo dire che:
- sleep() garantisce che il processo dorma per un tot di secondi
- sleep()/nanosleep() vengono usati per operazioni di outptu
- **MAI USARE** sleep() o nanosleep() per aspettare un altro processo(come nell'esempio precedente)

Cosa succede quando un processo viene messo in pausa(attraverso una system call) con **wait() pause() sleep()**?
- Lo stato del processo viene salvato
- la funzione di handler viene eseguita
- dopo che la funzione di handler è stata restituita, possono esserci 2 casi:
    1. restarting: quando viene restituito un valore dal handler, la systema call viene rifatta 
    2. abort: la system call viene eliminata
Il comportamento predefinito è il **secondo, ovvero aborting** ma si può cambiare il comportantemento attraverso il campo **SA_RESTART** che si trova nella struct **sigaction**, sfortunatamente diverse system call hanno comportamenti diversi e conviene (nel caso si volesse operare con le system call) approfondire l'argomento attraverso il terminale inserendo il comando:
> man 7 signal