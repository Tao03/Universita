package Esercitazione10;

/*
 * Implementare un metodo addListOfMaxs che accetta in input
 * una lista di liste di numeri interi, e vi aggiunge in testa la lista invertita dei massimi delle liste 
 * (una lista vuota non ha alcun massimo).
 *
 * Il metodo restituisce in output la lista modificata.
 *
 * Si assuma che:
 * 1) la lista vuota sia rappresentata dal valore null;
 * 2) gli elementi dei nodi delle liste interne non siano mai null.
 *
 * Seguono alcuni esempi di risultati attesi da addListOfMaxs.
 *
 * lista  : []
 * atteso : []
 *
 * lista  : [[]]
 * atteso : [[],[]]
 *
 * lista  : [[],[]]
 * atteso : [[],[],[]]
 *
 * lista  : [[1,2],[]]
 * atteso : [[2],[1,2],[]]
 *
 * lista  : [[],[1,2]]
 * atteso : [[2],[],[1,2]]
 *
 * lista  : [[1],[2]]
 * atteso : [[2,1],[1],[2]]
 *
 * lista  : [[1,2],[],[3,4,5]]
 * atteso : [[5,2],[1,2],[],[3,4,5]]
 *
 * lista  : [[1,2],[3],[4,5,6]]
 * atteso : [[6,3,2],[1,2],[3],[4,5,6]]
 *
 * lista  : [[1,2],[2],[3,4,5,5]]
 * atteso : [[5,2,2],[1,2],[2],[3,4,5,5]]
 *
 * Non e` consentito usare break o continue all'interno di
 * cicli.
 *
 * Non e` consentito modificare in alcun modo il codice al di fuori
 * del metodo "addListOfMaxs".
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
 
 
 
 public class Main2 {
 
     private static Node<Node<Integer>> addListOfMaxs(Node<Node<Integer>> ll) {
        Node<Node<Integer>> nodeRows = ll;
        Node<Integer> nodeNuovo = null;
        while(nodeRows!=null){
            int max=0;
            
            if(nodeRows.getElem()!=null){
                Node<Integer> nodeCollumn = ll.getElem();
                max = nodeCollumn.getElem();
                nodeCollumn = nodeCollumn.getNext();
                while(nodeCollumn!=null){
                    if(max<nodeCollumn.getElem()){
                        max = nodeCollumn.getElem();
                    }
                    nodeCollumn = nodeCollumn.getNext();
                }
                

            }
            nodeNuovo = new Node<Integer>(max, nodeNuovo);
            nodeRows = nodeRows.getNext();
        }
        //ll = new Node<Node<Integer>>(nodeNuovo, ll);
        return ll;
     }
 
     //------ INIZIO CODICE PER IL TESTING -------------------------------------
 
     public static void main(String[] args) {
         test(crea(), "[]");//[]
         test(crea(crea()), "[[],[]]");//[[]]
         test(crea(crea(),crea()), "[[],[],[]]");//[[],[]]
         test(crea(crea(1,2),crea()), "[[2],[1,2],[]]");//[[1,2],[]]
         test(crea(crea(),crea(1,2)), "[[2],[],[1,2]]");//[[],[1,2]]
         test(crea(crea(1),crea(2)), "[[2,1],[1],[2]]");//[[1],[2]]
         test(crea(crea(1,2),crea(),crea(3,4,5)), "[[5,2],[1,2],[],[3,4,5]]");//[[1,2],[],[3,4,5]]
         test(crea(crea(1,2),crea(3),crea(4,5,6)), "[[6,3,2],[1,2],[3],[4,5,6]]");//[[1,2],[3],[4,5,6]]
         test(crea(crea(1,2),crea(2),crea(3,4,5,5)), "[[5,2,2],[1,2],[2],[3,4,5,5]]");//[[1,2],[2],[3,4,5,5]]
     }
 
     private static void test(Node<Node<Integer>> ll, String atteso) {
         String lista = (ll == null?"[]":ll.toString());
         System.out.println("lista    : " + lista);
         ll = addListOfMaxs(ll);
         Node<Node<Integer>> ottenuto = ll;
         System.out.println("ottenuto : " + (ottenuto==null?"[]":ll));
         System.out.println("atteso   : " + atteso);
         System.out.println(((ottenuto != null && ottenuto.toString().equals(atteso)) || 
                            (ottenuto == null && atteso.equals("[]"))) ? "=== OK" : "=== ERRORE");
         System.out.println();
     }
 
 
     @SafeVarargs
     private static <T> Node<T> crea(T...v) {
         Node<T> n = null;
         for (int i = v.length - 1; i >= 0; i--)
             n = new Node<>(v[i],n);
         return n;
     }
 //------ FINE CODICE PER IL TESTING -------------------------------------
 }
 
 
