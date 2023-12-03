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