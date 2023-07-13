package Terzo;

public class Cons extends List {
    private int elem; // Elemento memorizzato
    private List next; // Riferimento al nodo successore

    public Cons(int elem, List next) {
        this.elem = elem;
        this.next = next;
    }

    public String toString() {
        return elem + ", " + next.toString();
    }

    public List insert(int n, int x) {
        if(n==0){
            return new Cons (x, this);
        }else{
            List l = this.next.insert(n - 1, x);
            return new Cons(this.elem,l);
        }
    }
    public static void main(String[] args) {
        List l = new Nil();
        l = l.insert(0, 3);
        l = l.insert(0, 2);
        l = l.insert(0, 1);
        l = l.insert(10, 9);
        System.out.println(l.toString());
    }
}
