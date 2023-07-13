package Esercitazione6;

public class Node<T> {
    private T elem;
    private Node<T> next;

    public Node(T elem, Node<T> next) {
        this.elem = elem;
        this.next = next;
    }

    public Node(T elem) {
        this.elem = elem;
        this.next = null;
    }

    public Node() {

    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public static <T> String toString(Node<T> node) {
        if (node == null) {
            return "";
        } else {
            String vi = toString(node.next);
            return vi + "[" + node.getElem() + "] ";
        }
    }

    public static <T> Node<T> reverse(Node<T> a) {
        return reverseRec(a, null);
    }

    public static <T> Node<T> reverseRec(Node<T> a, Node<T> b) {
        if (a == null) {
            return b;
        } else {
            return reverseRec(a.getNext(), new Node<T>(a.getElem(), b));
        }
    }

    public static <T> boolean inComune(Node<T> p, Node<T> q) {
        if (p == null || q == null) {
            return false;
        } else {
            return inComune(p.getNext(), q.getNext()) || p.getElem() == q.getElem();
        }
    }

    public static void main(String[] args) {
        Node<Integer> a = new Node<Integer>(0);
        a = new Node<Integer>(4, a);
        Node<Integer> b = new Node<Integer>(1);
        b = new Node<Integer>(1, b);
        b = new Node<Integer>(0, b);
        System.out.println(Node.inComune(a, b));
    }
    /**
     * abstract class A {
     * public abstract void m1();
     * }
     * abstract class B extends A {
     * public void m1()
     * {
     *      System.out.println("B.m1");
     * }
     * public abstract void m2(A obj);
     * 
     * }
     * class C extends B {
     * public void m1()
     * {
     *      System.out.println("C.m1");super.m1();
     * }
     * public void m2(A obj) {
     *      System.out.println("C.m2");
     *      obj.m1();
     * }
     * 
     * }
     * 
     * Rispondete alle seguenti domande:
     * 1. Se si eliminasse il metodo m2 dalla classe B, il codice che
     * definisce A,B,C sarebbe comunque corretto? Perché?
     * 
     * Si perchè eliminando un metodo astratto da una classe astratta implica l'eliminazione di un metodo da implementare
     * 
     * 
     * 2. Il seguente codice è corretto? Se no, spiegare perché. Se sì,
     * determinare cosa stampa.
     * A obj2 = new B();
     * obj2.m1();
     * 
     * Stamperebbe B.m1
     * 
     * 
     * 3. Il seguente codice è corretto? Se no, spiegare perché. Se sì,
     * determinare cosa stampa.
     * A obj3 = new C();
     * obj3.m1();
     * 
     * 
     * 
     * 4. Il seguente codice è corretto? Se no, spiegare perché. Se sì,
     * determinare cosa stampa.
     * A obj4 = new C();
     * obj4.m2(obj4);
     */

}