package Esercitazione10;

/*
 * Implementare un metodo removeEmptyLists che accetta in input
 * una lista di liste di numeri interi e rimuove da essa tutte le eventuali 
 * liste vuote 
 * (una lista vuota e' rappresentata dal valore null). 
 *
 * Il metodo restituisce in output la lista modificata e 
 * puo' modificare la lista di input.
 *
 *
 *
 * Seguono alcuni esempi di risultati attesi da removeEmptyLists.
 *
 * lista  : []
 * atteso : []
 *
 * lista  : [[]]
 * atteso : []
 *
 * lista  : [[],[]]
 * atteso : []
 *
 * lista  : [[1,2],[]]
 * atteso : [[1,2]]
 *
 * lista  : [[],[1,2]]
 * atteso : [[1,2]]
 *
 * lista  : [[1],[2]]
 * atteso : [[1],[2]]
 *
 * lista  : [[1,2],[],[3,4,5]]
 * atteso : [[1,2],[3,4,5]]
 *
 * lista  : [[1,2],[],[],[3,4,5],[],[6]]
 * atteso : [[1,2],[3,4,5],[6]]
 *
 *
 * Non e` consentito usare break o continue all'interno di
 * cicli.
 *
 * Non e` consentito modificare in alcun modo il codice al di fuori
 * del metodo "removeEmptyLists".
 *
 */

 import java.util.*;

 class Node<T>{
     private T elem;
     private Node<T> next;
 
     public Node(T elem, Node<T> next) {
         this.elem=elem;
         this.next=next;
     }
 
     public T getElem() {
         return elem;
     }
 
     public Node<T> getNext() {
         return next;
     }
 
     public void setElem(T elem) {
         this.elem=elem;
     }
     public void setNext(Node<T> next) {
         this.next=next;
     }
 
     @Override
     public String toString() {
         String res = "[";
         res += elem==null?"[]":elem.toString();
         Node<T> p = next;
         while (p != null) {
             res += ","+((p.elem == null?"[]":p.elem.toString()));
             p = p.next;
         }   
         return res += "]";
     }  
 }
 
 
 
 public class Main3 {
 
     private static Node<Node<Integer>> removeEmptyLists(Node<Node<Integer>> ll) {
         Node<Node<Integer>> nodeRow = ll;
         while(ll!=null){

            if(nodeRow.getElem().getElem()==null){
                nodeRow.setNext(nodeRow.getNext().getNext());
            }


            nodeRow = nodeRow.getNext();
         }
         return nodeRow;
     }
 
     //------ INIZIO CODICE PER IL TESTING ---------------------------------
 
     public static void main(String[] args) {
         test(crea(), "[]");//[]
         test(crea(crea()), "[]");//[[]]
         test(crea(crea(),crea()), "[]");//[[],[]]
         test(crea(crea(1,2),crea()), "[[1,2]]");//[[1,2],[]]
         test(crea(crea(),crea(1,2)), "[[1,2]]");//[[],[1,2]]
         test(crea(crea(1),crea(2)), "[[1],[2]]");//[[1],[2]]
         test(crea(crea(1,2),crea(),crea(3,4,5)), "[[1,2],[3,4,5]]");//[[1,2],[],[3,4,5]]
         test(crea(crea(1,2),crea(),crea(),crea(3,4,5),crea(),crea(6)), "[[1,2],[3,4,5],[6]]");//[[1,2],[],[],[3,4,5],[],[6]]
     }
 
     private static void test(Node<Node<Integer>> ll, String atteso) {
         String lista = (ll == null?"[]":ll.toString());
         System.out.println("lista    : " + lista);
         ll = removeEmptyLists(ll);
         Node<Node<Integer>> ottenuto = ll;
         System.out.println("ottenuto : " + (ottenuto==null?"[]":ll));
         System.out.println("atteso   : " + atteso);
         System.out.println(((ottenuto != null && 
                             ottenuto.toString().equals(atteso)) || 
                             (ottenuto == null && atteso.equals("[]")))?
                             "=== OK" : "=== ERRORE");
         System.out.println();
     }
 
     @SafeVarargs
     private static <T> Node<T> crea(T...v) {
         Node<T> n = null;
         for (int i = v.length - 1; i >= 0; i--)
             n = new Node<>(v[i],n);
         return n;
     }
 
 //------ FINE CODICE PER IL TESTING -----------------------------------
 }
 
 
