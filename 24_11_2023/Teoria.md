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