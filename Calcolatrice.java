public class Calcolatrice { //classe non eseguibile e non pubblica
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
    private void push(int x) {
        this.stack[this.size] = x;
        this.size = this.size + 1;
    }

    // pop(): restituisce l'ultima intero dello stack
    // e lo "cancella" riducendo di 1 la parte di stack utilizzata (variabize size)
    private int pop() {
        this.size = this.size - 1;
        return stack[size];
    }

    // questo e' un metodo pubblico
    public int esegui(String istruzioni) {
        int dx = 0;
        int len = istruzioni.length();
        while(dx<len){
            int a,b;
            char c = istruzioni.charAt(dx);
            if(c >= '0' && c <= '9' ){
                push(c - '0'); // Siccome il vettore è di tipo intero, facendo questa operazione converto il carattere in intero sottraendo lo 0 in ascii
            }else if(c == 's'){
                    int r = (int) istruzioni.charAt(dx+1);
                 push(1-(r%2));
            }else{
                b = pop();
                a = pop();
                switch(c){
                    case 's':
                        
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
                    case '%':
                        push(a%b);
                        break;
                    case  '#':
                        String msg = (this.size>0?stampa(this.size-1):" ");
                        System.out.println(msg);
                }
            }
            dx = dx + 1;
        }
        return (this.size>0?pop():-1); //alla fine restituisco l'ultimo risultato ottenuto
    }
    public static void main(String[] args){
        Calcolatrice c = new Calcolatrice();
        int value = c.esegui("s2");
        System.out.println(value);
    }
    private String stampa(int i){
        if(i == 0){
            return this.stack[i]+" ";
        }else{
            String msg = stampa(i-1);
            msg = msg + this.stack[i-1]+" ";
            return msg;
        }
    }
}
