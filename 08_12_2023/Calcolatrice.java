class Calcolatrice { //classe non eseguibile e non pubblica
    // una calcolatrice e' una pila che contiene fino a 100 interi.
    // L'ultimo intero e' il risultato delle operazioni fatte finora
    // e la prossima operazione agisce sugli ultimi due interi a,b
    // e li rimpiazza con a+b (per una addizione) oppure a*b (per una moltiplicazione)

    // stack = pila che contiene fino a 100 interi */
    private int[] stack = new int[100];

    // size = numero di interi introdotti: all'inizio 0
    // le posizioni occupate dell'array hanno indice: 0, 1, ..., size-1
    private int size = 0;
         
    // push(x): aggiunge un intero x allo stack dopo la parte utilizzata
    // e aumenta di 1 la parte di stack utilizzata (variabile size)
    private int push(int x, int[] stack, int size) {
        if(this.size<this.stack.length){
            stack[this.size] = x;
            size ++;
        }
        return size;
        
    }

    // pop(): restituisce l'ultima intero dello stack
    // e lo "cancella" riducendo di 1 la parte di stack utilizzata (variabize size)
    private int pop() {
        this.size = this.size - 1;
	    return this.stack[this.size];
    }
    public void stampa(){
        for (int i = 0; i < size; i++) {
            System.out.print("["+this.stack[i]+"]");
        }
    }
    // questo e' un metodo pubblico
    public int esegui(String istruzioni, int[] stack, int size) {
        int i = 0;
        int risultato = Integer.MAX_VALUE;
        while(i<istruzioni.length()){
            if(istruzioni.charAt(i)>='0' && istruzioni.charAt(i)<='9'){
                this.push(istruzioni.charAt(i)-'0', stack, size);
            }else if(istruzioni.charAt(i) == 's'){
                this.stampa();
            }else{
                int b = this.pop();
                int a = this.pop();
                switch (istruzioni.charAt(i)) {
                    case '%':
                        push(a%b);
                        break;
                    case '+':
                        push(a+b);
                        break;
                    case '-':
                        push(a-b);
                        break;
                    case '*':
                        push(a*b);
                        break;
                    case '/':
                        push(a/b);
                        break;
                    default:
                        break;
                }
                risultato = this.pop();
            }
            i = i + 1;
        }
        return risultato;
    }
}
