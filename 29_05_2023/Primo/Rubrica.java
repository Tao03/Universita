package Primo;
public  class Rubrica {
    /** Invariante: 
      (i) una volta costruita, una rubrica non contiene lo stesso nome due volte, 
      (ii) (0<=numContatti <= lunghezza vettore contatti) */

    // numero dei contatti attualmente memorizzati nella rubrica
    private int numContatti;        // all'inizio parte a 0
    // array pre-allocato di contatti
    private Contatto[] contatti;    // all'inizio vale null

    private boolean isSorted;

    // Costruttore: inizializza una Rubrica con @maxContatti preallocati.
    public Rubrica(int maxContatti) {
        // costruisce una rubrica con max. num. di contatti  = maxContatti
        numContatti = 0;
        //all'inizio i contatti significativi nella rubrica sono 0
        contatti =  new Contatto[maxContatti];
        /** all'inizio tutti i contatti nella rubrica non sono significativi:
        hanno nome e email uguali a null */
        this.isSorted = true;
    }
    // La nuova rubrica costruita soddisfa l'invariante

    public  int getNumContatti() { 
        return numContatti; 
    }
    public boolean getIsSorted(){
        return this.isSorted;
    }
    public void setIsSorted(boolean flag){
        this.isSorted = flag;
    }
    
    // non viene fornito un metodo get per ottenere il vettore dei contatti:
    // conoscendolo, un'altra classe potrebbe leggere e modificare 
    // i contatti in modo errato

    public void scriviOutput() {
        int i=0;
        System.out.println("Num. contatti = " + numContatti);
        // Vengono stampati i contatti di indice da 0 fino a numContatti-1. 
        // Gli altri contatti nell'array sono privi di significato
        while(i < numContatti) {
            contatti[i].scriviOutput();
            ++i;
        }
    }

    // Il metodo cercaIndice(n) restituisce l'unico indice i di un contatto di nome n
    // restituisce numContatti se non viene trovato
    // Il metodo cercaIndice(n) e' privato per evitare che 
    // le altre classi modifichino un contatto in modo errato
    private int cercaIndice(String n) {
        if(!this.isSorted){
            this.sort();
        }
        int i=0;
        // Esaminiamo i contatti di indice da 0 a numContatti-1: il primo con nome n
        // e' il contatto cercato
        while(i < numContatti) {
            if (contatti[i].getNome().equals(n)) 
                return i; 
            ++i;
        }   

        //Se non troviamo n restituiamo un valore fittizio numContatti
        return numContatti;
    }

    // Stabilisce se il nome n e' presente nella rubrica
    public boolean presente(String n) {
        return (cercaIndice(n) < numContatti);
    }

    // Ritrorna true se la rubrica e' piena
    public boolean piena() {
        return (numContatti  == contatti.length);
    }

    // Aggiunge un nuovo contatto con nome @n e email @e.
    // Se il contatto esiste gia', il metodo non effettua l'inserimento.
    // Ritorna true se viene aggiunto false altrimenti.
    public boolean aggiungi(String n, String e) {  
        if (presente(n)) 
            return false;//la rubrica contiene gia' n: fallimento
        if (piena()) 
            return false;        //rubrica e' piena: fallimento 

        // aggiungo il nuovo contatto nella prima posizione disponibile
        contatti[numContatti] = new Contatto(n,e); 
        ++numContatti; //aggiorno il numero degli elementi

        return true; //successo
    }

    // Rimuove un conbtatto di nome @n, se esiste. 
    // Ritorna true se la rimozione e' stata effettuata.
    public boolean rimuovi(String n) {
        int i = cercaIndice(n);
        if (i == numContatti) 
            return false; // il contatto non esiste: fallimento

        //Per rimuovere sposto indietro di uno tutti i contatti che seguono il contatto rimosso
        --numContatti;
        while (i < numContatti) {
            contatti[i] = contatti[i+1]; 
            ++i;
        }
        return true; // successo
    }

    // Cambia il nome di un contatto @n ad @n2.
    // Ritorna true in caso di successo (rinominazione effettuata), false altrimenti.
    public boolean cambiaNome(String n, String n2) {
        int i = cercaIndice(n);
        if (i == numContatti) 
            return false; // contatto di nome n non trovato:fallimento 
        int i2 = cercaIndice(n2);
        if (i2 != numContatti) 
            return false; // contatto di nome n2 gia' esiste:fallimento 
        //se troviamo il contatto ne modifichiamo il nome
        contatti[i].setNome(n2);
        return true;
    }


    // Cambia l'email di un contatto di nome @n ad @e2.
    // Ritorna true in caso di successo (cambio email effettuato), false altrimenti.
    public boolean cambiaEmail(String n, String e2) {  
        int i = cercaIndice(n);
        if (i == numContatti) 
            return false; // contatto di nome n non trovato:fallimento 
        // se troviamo il contatto ne modifichiamo la email 
        contatti[i].setEmail(e2);
        return true;
    }
    public void sort(){
        for (int i = 0; i < this.numContatti; i++) {
            for (int j = i +1 ; j < this.numContatti; j++) {
                if(contatti[i].compareTo(contatti[j])>0){
                    Contatto temp = contatti[i]; // temp
                    contatti[i] = contatti[j];
                    contatti[j] = temp;
                }
            }
        }
        this.setIsSorted(true);
        
    }
}

