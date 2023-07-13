//DynamickStack.java
public class DynamicStack {

    private Node top;

    //ultimo nodo aggiunto alla pila. "null" se non ce ne sono
    //COSTRUTTORE di una pila P = {} vuota
    public DynamicStack() {
        top = null;
    }
    public Node getTop(){
        return this.top;
    }
    public static boolean included(Node p, Node q){
        if(p.getNext()==null || q.getNext()==null){
            return true;
        }else{
            boolean flag = (p.getElem()>p.getNext().getElem() && q.getElem()>q.getNext().getElem()) || (p.getElem()<=p.getNext().getElem() && q.getElem()<=q.getNext().getElem());
            return included(p.getNext(), q.getNext()) && flag;
        }
    }
    public static DynamicStack zipSum(Node p, Node q){
        if(p==null || q==null){
            return new DynamicStack();
        }else{
            DynamicStack list = zipSum(p.getNext(), q.getNext());
            list.push(p.getElem()+q.getElem());
            return list;
        }
    }
    public static boolean isCrescente(Node a){
        if(a == null){
            return true;
        }else{
            boolean vi = isCrescente(a);
            if(a.getNext()!=null && a.getElem()>a.getNext().getElem()){
                return false;
            }
            return vi;
        }
    }





















    public static DynamicStack reverse(Node top){
        if(top==null){
            return new DynamicStack();
        }else{
            DynamicStack list = reverse(top.getNext());
            list.push(top.getElem());
            return list;
        }
    }

































    public int[] toArray(DynamicStack list){
        int[] array = new int[this.getSize(list)];
        Node nodo = this.top;
        int i = 0;
        while(nodo!=null){
            array[i] = nodo.getElem();
            nodo = nodo.getNext();
        }
        return array;
    }
    public static DynamicStack fromTo(int m, int n){
        boolean flag = (n>m);
        DynamicStack list = new DynamicStack();
        if(flag){
            fromTo( m,  n, list);
        }
        return list;
    }
    private static void fromTo(int m, int n,DynamicStack list){
        if(m==n+1){
            return;
        }else{
            fromTo(m+1, n,list);
            list.push(m);
        }
        return;
    }
    public static int occurences(Node top, int x){
        if(top==null){
            return 0;
        }else{
            int c = occurences(top.getNext(), x);
            return (top.getElem()==x?c + 1:0);
        }
    }
    public int getSize(DynamicStack list){
        Node nodo = this.top;
        int c = 0;
        while(nodo!=null){
            c = c + 1;
            nodo = nodo.getNext();
        }
        return c;
    }
    //test se la pila e' vuota
    public boolean empty() {
        return top == null;
    }

    //aggiungo un nodo in cima alla pila con un nuovo elemento x
    public void push(int x) {
        top = new Node(x, top);
    }

    //tolgo il nodo in cima alla pila e restituisco il suo contenuto
    public int pop() {
        assert !empty();
        int x = top.getElem();
        top = top.getNext(); //elimino l'ultimo nodo con contenuto x
        return x;
    }

    //restituisco il contenuto del nodo in cima alla pila senza
    //toglierlo
    public int top() {
        assert !empty();
        int x = top.getElem();
        return x;
    }

    //STAMPA. Per scorrere una pila usiamo una variabile di tipo Node
    //che parte da top e procede all'indietro lungo la pila fino
    //ad arrivare al nodo null. Usiamo di nuovo una conversione
    //Node-->String.
    public String toString() {
        Node temp = top; //partiamo dal nodo in cima alla pila
        String s = ""; //accumuliamo gli elementi in s
        while (temp != null) { //ci fermiamo quando temp arriva al nodo null
            s = s + " || " + temp.getElem() + "\n";//aggiungiamo l’elemento in cima
            temp = temp.getNext(); /* avanziamo al nodo successivo */
        }
        return s;
    }

    /* NOTA: dobbiamo salvare top in temp. Se avessimo usato top al
    posto di temp, scrivendo top=top.getNext(), avremmo cancellato
    l’indirizzo della cima della pila, e quindi perso l’accesso alla pila
    dopo l’esecuzione del metodo. */
    //COSTRUTTORE di una pila P = {1,...,n}, pila vuota se n<=0
    //Aggiunge i nodi nell’ordine da n fino a 1. 1 sta nel top.
    public DynamicStack(int n) {
        top = null;
        int i = n;
        while (i >= 1) //aggiungo il nodo che contiene i
        {
            top = new Node(i, top);
            --i;
        }
    }
    
}
