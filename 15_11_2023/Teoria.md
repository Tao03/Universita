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