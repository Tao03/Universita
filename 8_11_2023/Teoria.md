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