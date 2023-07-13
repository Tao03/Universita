package Esercitazione10;

/*
 * Implementare un metodo delete(x) che, in una lista di oggetti di tipo T, 
 * cancella tutti gli elementi y tali che y.equals(x). 
 *
 * Si assuma che l'elemento di un nodo non sia mai null.
 *
 * Seguono alcuni esempi di risultati attesi da delete(8).
 *
 * lista  :
 * atteso :
 *
 * lista  : 1, 2
 * atteso : 1, 2
 *
 * lista  : 8, 8
 * atteso : 
 *
 * lista  : 2, 2, 8, 8
 * atteso : 2, 2
 *
 * lista  : 2, 8, 2, 0
 * atteso : 2, 2, 0
 *
 * Seguono alcuni esempi di risultati attesi da delete("pippo").
 *
 * lista  : pippo, pluto, pippo
 * atteso : pluto
 *
 * lista  : topolino, pippo, clarabella
 * atteso : topolino, clarabella
 * 
 *
 * Non e` consentito usare break o continue all'interno di
 * cicli.
 *
 * Non e` consentito modificare in alcun modo il codice al di fuori
 * del metodo "delete".
 *
 */

 class Node<T>{
    private T elem;
    private Node<T> next;

    public Node(T elem, Node<T> next){
        this.elem=elem;
        this.next=next;
    }

    public T getElem(){
        return elem;
    }

    public Node<T> getNext(){
        return next;
    }

    public void setElem(T elem){
        this.elem=elem;
    }
    public void setNext(Node<T> next){
        this.next=next;
    }
}

class List<T> {
    private Node<T> first;

    public List()
    { first = null; }

    public void insertFirst(T elem)
    { first = new Node<>(elem, first); }

    @Override
    public String toString() {
        String s = "";
        for (Node<T> p = first; p != null; p = p.getNext()) {
            if (p != first) s += ", ";
            s += p.getElem();
        }
        return s;
    }


    public void delete(T x) {
       deleteRic(this.first,x);
    }
    public void deleteRic(Node<T> node, T x){
        if(node.getNext()==null){
            return;
        }else{
            deleteRic(node.getNext(), x);
                if(node.getNext().getElem().equals(x)){
                    if(node.getNext().getNext()!=null){
                        node.setNext(node.getNext().getNext());
                    }else{
                        node.setNext(null);
                    }
                
            }
            /*if(node.getNext()!=null && node.getNext().getElem().equals(x)){
                if(node.getNext().getNext()!=null){
                    
                }else{
                    node.setNext(null);
                }
            }*/
        }
    }
}


public class Main0 {
    public static void main(String[] args) {
        test(crea(), "");
        test(crea(1, 2), "1, 2");
        test(crea(8, 8), "");
        test(crea(2, 2, 8, 8), "2, 2");
        test(crea(2, 8, 2, 0), "2, 2, 0");
        test2(crea("pippo", "pluto","pippo"), "pluto");
        test2(crea("topolino", "pippo","clarabella"), "topolino, clarabella");
        
    }

    private static void test(List<Integer> l, String atteso) {
        System.out.println("lista    : " + l);
        l.delete(8);
        System.out.println("ottenuto : " + l);
        System.out.println("atteso   : " + atteso);
        System.out.println(l.toString().equals(atteso) ? "=== OK" : "=== ERRORE");
        System.out.println();
    }
    
    private static void test2(List<String> l, String atteso) {
        System.out.println("lista    : " + l);
        l.delete("pippo");
        System.out.println("ottenuto : " + l);
        System.out.println("atteso   : " + atteso);
        System.out.println(l.toString().equals(atteso) ? "=== OK" : "=== ERRORE");
        System.out.println();
    }

    @SafeVarargs
    private static <T> List<T> crea(T...v) {
        List<T> l = new List<>();
        for (int i = v.length - 1; i >= 0; i--)
            l.insertFirst(v[i]);
        return l;
    }
}