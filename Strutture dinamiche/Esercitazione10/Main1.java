package Esercitazione10;

/*
 * Implementare un metodo totalNumberOfElementsInLists che accetta in input
 * una lista di liste di numeri interi, e restituisce il numero totale di 
 * elementi presenti nelle liste.
 *
 * Seguono alcuni esempi di risultati attesi da totalNumberOfElementsInLists.
 *
 * lista  : []
 * atteso : 0
 *
 * lista  : [[]]
 * atteso : 0
 *
 * lista  : [[],[]]
 * atteso : 0
 *
 * lista  : [[1,2],[]]
 * atteso : 2
 *
 * lista  : [[],[1,2]]
 * atteso : 2
 *
 * lista  : [[1],[2]]
 * atteso : 2
 *
 * lista  : [[1,2],[],[3,4,5]]
 * atteso : 5
 *
 * lista  : [[1,2],[3],[4,5,6]]
 * atteso : 6
 *
 *
 * Non e` consentito usare break o continue all'interno di
 * cicli.
 *
 * Non e` consentito modificare in alcun modo il codice al di fuori
 * del metodo "totalNumberOfElementsInLists".
 *
 */

import java.util.*;

class Node<T> {
    private T elem;
    private Node<T> next;

    public Node(T elem, Node<T> next) {
        this.elem = elem;
        this.next = next;
    }

    public T getElem() {
        return elem;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String res = "[";
        res += elem == null ? "[]" : elem.toString();
        Node<T> p = next;
        while (p != null) {
            res += "," + ((p.elem == null ? "[]" : p.elem.toString()));
            p = p.next;
        }
        return res += "]";
    }
}

public class Main1 {

    private static int totalNumberOfElementsInLists(Node<Node<Integer>> ll) {
        Node<Node<Integer>> nodeRow = ll;
        int count = 0;
        while (nodeRow != null) {
            if (nodeRow.getElem() != null) {
                Node<Integer> nodeCollumn = nodeRow.getElem();
                while (nodeCollumn != null) {
                    count = count + 1;
                    nodeCollumn = nodeCollumn.getNext();
                }
            }
            nodeRow = nodeRow.getNext();
        }
        return count;
    }

    // ------ INIZIO CODICE PER IL TESTING -------------------------------------
    public static void main(String[] args) {
        test(crea(), 0);// []
        test(crea(crea()), 0);// [[]]
        test(crea(crea(), crea()), 0);// [[],[]]
        test(crea(crea(1, 2), crea()), 2);// [[1,2],[]]
        test(crea(crea(), crea(1, 2)), 2);// [[],[1,2]]
        test(crea(crea(1), crea(2)), 2);// [[1],[2]]
        test(crea(crea(1, 2), crea(), crea(3, 4, 5)), 5);// [[1,2],[],[3,4,5]]
        test(crea(crea(1, 2), crea(3), crea(4, 5, 6)), 6);// [[1,2],[3],[4,5,6]]
    }

    private static void test(Node<Node<Integer>> ll, int atteso) {
        String lista = (ll == null ? "[]" : ll.toString());
        System.out.println("lista    : " + lista);
        int ottenuto = totalNumberOfElementsInLists(ll);
        System.out.println("ottenuto : " + ottenuto);
        System.out.println("atteso   : " + atteso);
        System.out.println(ottenuto == atteso ? "=== OK" : "=== ERRORE");
        System.out.println();
    }

    @SafeVarargs
    private static <T> Node<T> crea(T... v) {
        Node<T> n = null;
        for (int i = v.length - 1; i >= 0; i--)
            n = new Node<>(v[i], n);
        return n;
    }
    // ------ FINE CODICE PER IL TESTING -------------------------------------
}
