---
title: Appunti di Sistemi operativi laboratorio
author: Taoufik
date: today
...
# Processi

Il comando " kill " invia un segnale al processo, un esempio può essere SIGKILL (aggiungendo un PID) porta
alla terminazione del processo.
Il comando kill può essere usato anche per mettere in pausa il processo oppure farlo continuare.

Siccome tutti i processi sono identificati da un PID e ha un processo Padre che gli ha generati, le system call:
- getpid() : consiste nel restituire il PID del processo corrente </li>
- gettpid() : consiste nel restituire il PID del processo padre </li>

### Comandi utili nel terminale</h3>

- **ps -Af | less**: Mostra tutte le informazioni di tutti i processi attivi 
-  **pstree -p | less** : Visualizza l'albero gerarchico dei processi attivi
### Creazione di un processo
L'istruzione fork() è una system call che **consiste al processo chiamato ora padre di creare un processo figlio**
```C 
#include < unistd.h >
pid_t fork ( void );
```
Con questa porzione di codice il sistema operativo duplica il processo padre, quindi il processo figlio appena generato avrà le stesse informazioni
del padre e verrà eseguito nuovamente\

**ATTENZIONE:** Siccome sono aree di memoria condivise, il processo padre non nota che il figlio modifichi le informazioni al momento dell'esecuzione
<p> Un altro aspetto interessante è che la funzione restituisce 2 valori distinti:\

- Nel processo Padre, il PID del figlio
- Nel processo figlio, 0

### Differenziare processo padre e figlio
L'unico modo per differenziare l'esecuzione dei 2 processi è attraverso il seguente codice:
   
```C
/* Executed only once */
if ( fork () ) {
    /* Executed by parent only */
} else {
    /* Executed by child only */
}
/* Executed twice : by both parent and child */
```


Il valore che ritorna la funzione ci dice che processo stiamo eseguendo\
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
# Segnali
I segnali possono essere generati da un utente, software oppure anche hardware\
Alcuni esempi di segnali possono essere:
1. SIGKILL: causa una terminazione del programma
2. SIGSTOP: ferma/avanza l'esecuzione di un processo

Per ottenere informazioni più approfondite basta usare il comando:
> man 7 signal

### Invio di segnali ad un qualsiasi processo
Il seguente pezzo di codice..
```C
# include < sys/types.h >
# include < signal.h >

int kill ( pid_t pid , int signum );
```
Ci dice che attraverso la procedura kill si può uccidere un processo utilizzando 2 parametri:
1. **pid**: codice univoco che identifica il processo da uccidere(se inseriamo -1 come pid allora si invia un segnale a tutti i processi esistenti)
2. **signum**:  il tipo di segnale che si vuole inviare ad un processo (se è pari a 0 allora si fa un test sull'esistenza del PID)

Invece se si vuole inviare un segnale ad un processo dalla comand line, basta farlo con il seguente comando:\
>kill -(signal) (pid)

**NB: se non si specifica il segnale, per default è SIGTERM**

### Invio di segnali a se stessi
Il codice di pezzo seguente ci permette di inviare un segnale di tipo **SIGNUM** a noi stessi
```C
# include <signal.h>
int raise ( int signum ) ;
```
Oppure possiamo chiedere al sistema operativo di inviare inserire un allarme dop sec secondi:
```C
#include < unistd .h >
unsigned int alarm (unsigned int sec) ;
```
**NB: la funzione alarm restituisce il tempo rimanente all'allarme nel caso il processo corrente dovesse essere interrotto**
### Gestione di segnali da parte del processo destinatario
Ogni segnale ha un suo handler predefinito che sono i seguenti:
1. **Term**: termina il processo
2. **Ign**: ignora il segnale
3. **Core**: il processo viene terminato ma viene salvata l'immagine del processo, quindi tutte le informazioni relative allo stato del processo.
Viene usato per fare debugging
4. **Stop**: ferma il processo
5. **Cont**: Continua il processo nel caso si fosse fermato.

Un handler predefinito a livello utente viene richiamato in modo **asincrono => ovvero che viene attivato subito**\
**indipendentemente da quello che sta succedendo**. Subito dopo, accadono 3 cose fondamentali:
1. Lo stato del processo viene salvato
2. La funzione(handler) viene eseguita
3. lo stato del processo precedente viene caricato.

**NB: lo handler dei segnali è simile a quello degli interrupt e alcuni segnali non possono essere gestiti dagli hanlder "utente"**\
E' pure possibile gestire i segnali pure in modo sincrono **ma non viene spiegato in questo corso**

### Implementazione di un handler a livello utente
Per un implementazione della gestione effettiva dei segnali si deve utilizzare una struttura del tipo:
```C
struct sigaction{
    void (* sa_handler )( int signum ) ;
    sigset_t sa_mask ; /* illustrated later */
    int sa_flags ; /* illustrated later */
    /* plus others ( for advanced users ) */
};
```
**sa_handler** è un puntatore ad una funziona definita come 
>void signal_handler ( int signum );
che viene invocata appena si riceve il segnale
**NB: Si ricorda che si deve impostare la struct a 0**
```C
# define _GNU_SOURCE /* necessary from now on */
# include < signal .h >
int sigaction ( int signum , const struct sigaction * act ,
struct sigaction * oldact );
```
Come si può notare dal codice, il metodo **sigaction** serve a impostare una qualsiasi signal handler attraverso vari parametri:
1. **signum**: indica che tipo di segnale si voglia 
2. **act**: indica il signal handler nuovo
3. **oldact**: indica il signal handler vecchio

#### Esempio di codice che gestisce un segnale
```C
void handle_signal ( int signal ); /* the handler */
struct sigaction new , old ;
bero (& new , sizeof ( new )); /* set all bytes to zero */
new . sa_handler = handle_signal ; /* set the handler */
sigaction ( signum ,& new , NULL ); /* CASE 1: set new handler */
sigaction ( signum , NULL ,& old ); /* CASE 2: get cur handler */
sigaction ( signum ,& new ,& old );
```

Una semplice implementazione di un handler sarebbe attraverso uno switch case dove si seleziona, attravero il valore del **SIGNUM** il suo handler corrispondente
```C
void handle_signal ( int signum ) {
/* signal signum triggered the handler */
switch ( signum ) {
case SIGINT :
/* handle SIGINT */
break ;
case SIGALRM :
/* handle SIGALRM */
break ;
/* other signals */ } }
```

Altre informazioni utili possono essere:
1. Gli handler di un processo padre vengono ereditati a tutti i processi figlio
2. gli hanlder vengono puliti dopo il comando **execve**
3. Le variabili globali vengono riconosciute anche all'interno del handler e offrono una sorta di comunicazione al main siccome attraverso le variabili globali il main può vedere quello che sta accadendo al'interno degli handler.
Tuttavia si deve prestare molta attenzione all'uso di variabili/strutture globali

### Differenza tra una funziona safe e no
Quando si parla di segnali e handler, le funzioni di libreria si suddividono in 2 categorie:
1. **Asynchronous Signal-Safe**: funzioni che non utilizzano variabili globali
2. **Asynchronous Signal-UnSafe**: funzioni che utilizzando variabili globali e definite non sicure, per esempio **printf(...)** non lo è.

Quind ogni volta che si vuole aggiungere una funzione di libreria in una handler, si deve prima assicurarsi che sia AS-SAFE ovvero **Asynchronous Signal-Safe**
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

# FIle descriptors

Un file descriptor è un valore intero associato a un file aperto da un processo. I file descriptor sono utilizzati per accedere e gestire i file e le risorse di I/O come le pipe.

Ogni processo ha ben 3 file descriptor:

1. **stdin**: legge l'input dalla tastiera 
2. **stdout**: è un canale di output e serve a scrivere messaggi al terminale
3. **stderr**: è un canale di output per scrivere errori(di runtime per es.) al terminale 

Se un processo apre il file usando il seguente comando
> open ("file.txt" , O_RDONLY);

Si apre un nuovo file descriptor dove l'utente può leggere dal file "file.txt" utilizzando il nuovo descriptor


### Chiudere un file descriptors
Chiudere un file descriptors con **close()** significa eliminare il collegamento tra il processo e il file creato e togliere dalla tabella dei file descriptor la entry correlata al file descriptor da eliminare.

### Il processo figlio eredita qualcosa?
Quando un processo (in questo caso supponiamo che ci sono più di 3 file descriptor) genera un processo figlio, quest'ultimo li eredita.
Nel caso il processo padre chiudesse un qualsiasi file descriptor, il figlio ha ancora la possibilità di accederli e viceversa.

# Pipes
Le pipes è un meccanismo utilizzato per comunicare tra 2 processi.
Da una parte il processo A invia informazioni e dall'altra parte il processo B riceve l'input.

```C
int pipefd [2]; /* declaring array of 2 int */
/* the call pipe sets two file descriptors */
pipe ( pipefd ) ;
```

Nel caso andasse tutto bene (quindi il valore di ritorno è pari a 0) allora si creano 2 file descriptors:
- pipefd[0] è un fd che legge dalla pipe
- pipefd[1] è un fd che scrive nella pipe

**In sostanza, qualsiasi cosa che venga scritta nel fd di input, viene letta nel fd di output.**

### Comunicazione tra 2 processi via pipe
Quando un processo genera un figlio, si genera una pipe creata tra il processo padre e figlio che **può essere gestita nel seguente modo:**

```
Nel caso non volessimo che il processo figlio parlasse con il processo padre, basta CHIUDERE il FD di output del processo padre e il FD di input del processo figlio
```

Nel caso si chiudesse soltanto uno dei due file descriptor, allora si potrebbero creare vari problemi (spiegati successivamente)

### Leggere da una pipe
Il seguente codice ci permette di leggere da una pipe:
```C
char buf [100]; /* stores bytes read from pipe */
int num_bytes ;
num_bytes = read ( pipefd [0] , buf , sizeof ( buf ));
```
Una caratteristica importante è che i dati appena letti vengono eliminati.
Dopo una lettura dalla pipe possono accadere 3 situazioni:
1. Se ci sono varie informazioni, vengono memorizzati in un vettore qualsiasi e restituisce il numero di byte letti
2. Se non ci sono informazioni e il FD di input è aperto, aspetta per qualche informazione
3. Nel caso non ci fossero informazioni e non ci fossero FD di input aperti(dall'altra parte della pipe), restituisce 0

### Scrivere da una pipe
E' simile alla lettura ma speculare:
```C
char buf [100]; /* stores bytes read from pipe */
int num_bytes ;
num_bytes = read ( pipefd [0] , buf , sizeof ( buf ));
```
1. Nel caso la pipe fosse piena, il processo aspetta che si liberi per scrivere.
2. Se c'è abbastanza spazio, scrive e restituisce il numero di bytes letti
3. Se non c'è nessuna FD di lettura aperta, viene generato il segnale **SIGPIPE**(per avvisare che le info non verrano mai lette)

### Come mai è neccessario chiudere i File descriptors di PIPE inutili?

Nel caso di un FD di input aperto **inutilizzato**:
- Il File descriptor di lettura aspetterebbe attraverso l'istruzione **read()** per sempre siccome non riceverebbe nessun dato.

Nel caso di un FD di output aperto **inutilizzato**:
- Quando un processo prova a scrivere nel FD di output, il segnale **SIGPIPE** non parte e quindi il programmatore non riuscirebbe ad accorgersi del problema che si è creato
- Invece chiudendo tutti FD di output, il segnale sarebbe partito e il programmatore si sarebbe accorto del problema 

### Informazioni ulteriori...
In generale, il comportamento corretto di un processo che non ha bisogno di una pipe:
1. Chiude il file descriptor su cui scrive
2. Appena il reader finisce di leggere(o consumare) tutti i dati inviati, riceve uno 0 che indica che il writer ha chiuso la comunicazione

Per ottenere la dimensione della PIPE (varia da macchina a macchina) lo si fa con la macro **PIPE BUF**.\

Nel caso il processo stesse aspettando di scrivere/leggere con rispettivamente **write()/read()**  e riceve un segnale:
1. lo handler viene eseguito e la chiamata bloccante **read()/write()** restituisce -1 ed ERRNO vale **EINTR**

Nel caso si volesse inviare un messaggio con le pipes
- La lunghezza dei messaggi dovrebbero avere la stessa lunghezza
- Meglio se la lunghezza del messaggio sia minore o uguale alla dimensione del buffer
- Meglio che la lunghezza del buffer di lettura sia un multiplo della lunghezza del messaggio, altrimenti il messaggio può essere spezzato e si dovrebbero fare più letture

### Copiare un file descriptor in un altro
E' possibile sovrascrivere un FD con un altro attraverso l'istruzione
```C
int dup2 ( int fd_src , int fd_dst );
```
Che consiste nel prendere il file descriptor di destinazione, farlo puntare alla pipe del file descriptor sorgente e successivamente elimare quest'ultimo.
Quindi qualsiasi cosa il processo invii al FD destinatario andrà nella pipe che era puntata dal FD sorgente.

# FIFO
Siccome le pipes sono caraterizzate da file descriptors e soltanto processi che hanno in comune un processo padre possono comunicare fra di loro, si è pensato di creare anche le **named pipes** che spezzano questo vincolo.
Consiste in una semplice pipe ma può essere utilizzata da qualsiasi processo, basta sapere il nome della pipe(che risiede nel file system)
In C, si può utilizzare il seguente comando per creare una fifo:
```C
int mkfifo ( const char * pathname , mode_t mode ) ;
```
- Il pathname indica il nome della fifo
- la modalità indica in che modo accediamo alla fifo e può essere:
    1. read
    2. write
    3. execute

# Inter-Process Communication
Gli IPC objects sono oggetti di sistema utilizzati per facilitare la comunicazione tra processi:
1. Code di messaggio: permette l'invio e la ricezione dei messaggi
2. Memoria condivisa: permette ai processi di scrivere/leggere su un area di memoria condivisa
3. Semafori: strumento per facilitare il corretto  accesso di una risorsa condivisa

Questi oggetti possono essere implementati in 2 modi:
1. System V (**quello che si usa nel corso**)
2. POSIX.

### Persistenza
Gli oggetti IPC sono residenti all'interno del kernel e non vengono eliminati nonostante tutti i processi abbiano terminato.
L'unico modo per eliminarli è **fare il riavvio della macchina**.

#### Vantaggi e svantaggi
Questo meccanismo è molto utile siccome permette ai processi di comunicare fra di loro conoscendo soltanto il "nome" (come le **FIFO**). Tuttavia, nel caso ti dimenticassi di eliminarli, potrebbero riempirti la memoria.

**CONSIGLIO:** creare una lista di IPC object per gestirli e se si vuole, eliminarli.

> ipcs : mostra lo stato dei IPC Objects aperti

> ipcs -l: mostra i limiti di sistema relativi agli oggetti (dimensione ecc.)

>ipcmk: crea un IPC object (questo è possibile soltanto su linux)

>ipcrm: elimina uno specifico IPC object

### IPC objects e files
Qualsiasi oggetto di tipo IPC ha un identificatore univoco di tipo intero.\
**NB: 2 oggetti dello stesso tipo possono avere lo stesso ID**\
Per utilizzare un oggetto IPC, il processo deve sapere l'ID, per esempio:
> msgsnd(int id, ...) si utilizza per inviare messaggi all'interno di una cosa
> write(int fd, ...) si utilizza per scrivere delle informazioni ad un file descriptor.

**RICORDA: un file descriptor può essere utilizzato soltanto dal processo che l'ha invocato, non come gli oggetti IPC dove basta soltanto conoscere l'ID**

### Come "aprire" un IPC object
L'invocazione della procedura seguente ti permette di aprire un file e restituisce il file descriptor a cui puoi accedere(ricordo che è un intero).\
Le 3 funzioni seguenti si occupano di restituire un IPC objects:
```C
int msgget (key_t key , int flags);
int shmget (key_t key , size_t size , int flags);
int semget (key_t key , int nsems , int flags);
```
- Restituisce l'ID di un oggetto di tipo IPC e come per i file ti può restituire un **oggetto esistente oppure nuovo**.
- Il primo parametro è una chiave che è stata generata e serve ai processi per accedere all'oggetto condiviso.
- Il secondo e ultimo parametro è un valore scritto in ottale che associa per ogni 3 bit i permessi(lettura/scrittura ecc) che un determinato utente/gruppo/altro può avere.

**ESEMPIO:**
- 0400 read to user
- 0020 write to group
- 0666 read/write to everybody

### Vari modi per ottenere un IPC object
1. Creare un nuovo oggetto e comunica l'ID agli altri processi, per esempio
    > id = msgget(IPC_PRIVATE, 0600)
    - Successivamente puoi comunicare l'id attraverso la command-line oppure con la fork il processo figlio si copia il valore dell'id
2. Creare un nuovo oggetto avendo già una chiave
    > id = msgget(key, IPC_CREAT | IPC_EXCL | flags);
    - se l'oggetto IPC con la chiave key esiste già, allora il metodo restituisce -1, altrimenti restituisce l'id del nuovo oggetto
3. Usare un oggetto già esistente avendo la chiave
    > id = msgget(key, 0644);
    - restituisce l'identificatore dell'oggetto esistente
    - restituisce -1 e errno= ENOENT nel caso l'oggetto non esistesse
4. Usare un oggetto già esistente avendo la chiave oppure crearne uno se non esistesse
    > id = msgget(key, IPC_CREAT | 0660);

**NB: la differenza negli ultimi 3 casi è l'uso dei flag che cambia in base a come creiamo l'oggetto**

### Problemi comuni nell'uso di IPC object
Un problema molto frequente è:\
Nel caso il programmatore creasse un oggetto, questo non viene elimato appena facciamo Ctrl+C nel terminale, risiede ancora in memoria e rilanciare il programma nuovamente porterebbe ad avere non un nuovo oggetto ma sempre lo stesso (informazioni relative all'oggetto non vengono eliminate).\

**Soluzioni:**
1. ottenere l'oggetto usando la macro IPC_PRIVATE
2. implementare un handler che gestisce il segnale Ctrl+c per elimare tutti gli oggetti creati
3. usare il comando ipcrm ogni volta che termina l'esecuzione del programma 

### Implementazione una coda di messaggio
La seguente system call:
> int msgget(key_t, int msgflag);

+ restituisce l'identificatore della coda di messaggi associata alla chiave(passata come parametro)

+ msgflag è il risultato di tante | che ha come formato:
    - permessi di accesso alla coda come read/write in base ottale
    - IPC_CREAT: nel caso la coda esista, ti restituisce l'identificatore, altrimenti viene creata
    - IPC_EXCL (usata soltanto con IPC_CREAT) nel caso la coda esistesse la chiamata fallisce

### Formato di un messaggio 
Il programmatore può decidere il formato del messaggio ma **deve avere obbligatoriamente un campo long che indica il tipo di messaggio**
- Il tipo di messaggio serve a selezionare un gruppo di messaggi e non tutti
- Deve essere positivo, altrimenti è errore

Per esempio:
```C
struct msgbuf {
long mtype ; /* type of message */
/* my personal message goes here */
};
```
Quindi il programmatore ha libertà nel decidere l'implementazione del messaggio, ma deve rispettare 2 regole:
1. I byte dedicati al campo long vengono usati **esclusivamente** per indicare il tipo del messaggio
2. La dimensione totale del messaggio non deve eccedere la lunghezza massima

**Osservazioni:** 
- Anche i messaggi lunghezza 0 sono accettabili
- Non usare i puntatori all'interno della struttura msgbuf siccome il puntatore accede ad un area di memoria del processo mittente => Segmentation fault

### Inviare un messaggio con la coda di messaggi
Attraverso la seguente system call posso inviare un messaggio
```C
int msgsnd ( int msqid , const void * msgp , size_t msgsz , int msgflg );
```
1. Il primo parametro msqid indica l'identificatore della coda dei messaggi
2. riferimento alla struttura che contiene il messaggio da inviare
3. msgsz è il valore sizeof(struct msgbuf) - sizeof(long) che è la dimensione effettiva del messaggio (senza tenere conto del campo)

Nel caso la coda dei messaggi fosse piena, l'operazione diventa bloccante a meno che si imposti come quarto parametro la macro IPC_NOWAIT che ti restituisce direttamente il valore -1.

A differenza delle pipe, non si deve chiudere il FD di input 

### Ricezione di un messaggio con la coda di messaggi
La seguente system call
```C
int msgrcv ( int msqid , void * msgp , int msgsz , long mtype , int msgflg );
```
Prende il contenuto del messaggio dalla cosa dei messaggi che ha come identificatore **msqid** e lo copia nel buffer puntato da **msgp**

1. msgsz è la dimensione del messaggio (senza tenere conto del campo type)
2. puntatore al buffer che avrà il contenuto del messaggio
3. il valore di mtype ha molteplici significati
    1. mtype == 0 si prende il primo messaggio nella coda
    2. mtype > 0 si prende il primo messaggio del tipo **mtype**
    3. mtype > 0 && MSG_EXCEPT si prende il primo messaggio di tipo **NON mtype**
    4. mtype < 0 si prende il primo messaggio che ha come valore type inferiore o uguale al valore del parametro **mtype** (in questo caso i messaggi che hanno il campo type inferiori hanno più priorità)

    Nel caso non ci fosse nessun messaggio che rispetti le 4 regole appena descritte, il processo aspetta fino a quando non trovi almeno un messaggio che soddisfi almeno 4 regole.\
    Questo rende l'operazione di tipo bloccante, ma si può evitare inserendo nel quarto parametro la macro IPC_NOWAIT(restituisce  il valore -1 ed errno = ENOMSG)

### Controllare ed eliminare un messaggio dalla coda con 
Con l'invocazione del seguente metodo
> int msgctl ( int msqid , cmd , struct msqid_ds * buf );
1. msqid è l'identificatore della coda
2. cmd 
3. buff

Per eliminare e deallocare la coda dei messaggi:
> int msgctl ( int msqid , IPC_RMID , NULL ) ;

Per ottenere lo stato della coda dei messaggi:
> int msgctl ( int msqid , IPC_STAT , struct msqid_ds * buf );

Ti restituisce lo stato della coda all'interno di una struct:
```C
struct msqid_ds {
    struct ipc_perm msg_perm ; /* Owner , permission */
    time_t msg_stime ; /* Time of last msgsnd */
    time_t msg_rtime ; /* Time of last msgrcv */
    time_t msg_ctime ; /* Time of last change */
    msgqnum_t msg_qnum ; /* Cur # msg in queue */
    msglen_t msg_qbytes ; /* Max bytes allowed in Q */
    pid_t msg_lspid ; /* PID of last msgsnd */
    pid_t msg_lrpid ; /* PID of last msgrcv */
};
```
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
# IPC memoria condivisa
Lo standard System V implementa la memoria condivisa. Quando si usa il metodo malloc, si alloca un area di memoria privata(nello heap) quindi non accessibile ad altri processi.

L'uso della memoria condivisa porta a vari vantaggi tra cui una facile implementazione e una veloce comunicazione ma i dati possono essere **inconsistenti** .

### Ciclo di vita della memoria condivisa
1. Creazione: attraverso il metodo shmget(...), dove deve essere specificata la dimensione della memoria, si alloca un area di memoria condivisa
2. Collegamento dell'area condivisa all'area di memoria dei processi
    - Dopo il collegamento dell'area di memoria condivisa, si può usare tranquillamente lo spazio di memoria appena allocato
    - qualsiasi informazioni memorizzata all'interno della nuova area di memoria, sarà visibile a tutti i processi che condividono il segmento
3. Scollegarsi dall'area di memoria dei processi: l'area non è più visibile ma continua ad esistere
4. Deallocazione: eliminazione effettiva dell'area di memoria condivisa

### Creazione di una memoria condivisa
La seguente system call ci permette di creare un area di memoria condivisa
> int shmget ( key_t key , size_t size , int shmflg )

Restituisce l'identificatore associato all'area appena creata

- il parametro **shmflag** è il risultato di tante or di campi messi assieme:
1. permessi di lettura e scrittura
2. IPC_CREAT:
    1. crea una nuova area di memoria associata alla chiave, se l'area di memoria non esiste
    2. nel caso esistesse, ti restituisce l'id del segmento associato alla chiave

**NB: l'area di memoria condivisa non viene eliminata dopo la fine dell'esecuzione del processo ma deve essere deallocata.**

### Collegamento del segmento
Per associare l'area di memoria ad un area fisica si fa con il metodo seguente:
```C
void * shmat ( int shmid , NULL /* just ignore it */ ,int shmflg )
```
- shmid è l'identificatore dell'oggetto
- shmflg è il flag
    - SHM_RDONLY consente di usare l'area di memoria in sola lettura
Il metodo restituisce il riferimento all'area di memoria
```C
struct my_data * datap ; /* shared data struct */
shm_id = shmget ( IPC_PRIVATE , sizeof ( struct my_data ) , 0600) ;
datap = shmat ( shm_id , NULL , 0) ;
/* From now on , all processes accessing to
datap - > something , read / write in shared mem */
```

### Scollegamento del segmento
Un area di memoria può essere scollegata nel seguente modo:
```C
int shmdt ( const void * shmaddr );
```
shmaddr è l'indirizzo del segmento che si vuole scollegare
Lo scollegamento occorre quando:
1. il processo termina
2. l'immagine del processo cambia(per esempio attraverso l'istruzione **exec()**)

### Controllo e deallocazione del segmento
La memoria condivisa può essere controllata dal metodo seguente:
```C
int shmctl ( int shmid , int cmd , struct shmid_ds * buf ) ;
```
- shmid è l'identificatore della memoria condivisa
- cmd è il comando che vogliamo eseguire ( può essere (IPC STAT, IPC RMID ecc. ))
- il terzo parametro può essere usato in base al valore del secondo parametro

Invece per deallocare la memoria condivisa:
```C
int shmctl ( int shmid , IPC_RMID , NULL ) ;
```
**NB: la deallocazione può essere fatta soltanto dall'ultimo processo che la sta usando, ovviamente ci sarebbero problemi nel caso venisse fatta la deallocazione mentre altri processi stanno accedendo al segmento**

