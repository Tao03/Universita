package Esercizio1;
// Nodo per realizzare liste concatenate.
public class Node<T> {
    // Elemento intero contenuto in questo nodo.
    private T elem;
    // Nodo successore, o null se e' l'ultimo nodo della lista concatenata.
    private Node<T> next;

    // Crea un nuovo nodo con valore @elem e successore @next.
    public Node(T elem, Node<T> next) {
        this.elem = elem;
        this.next = next;
    }

    // Ritorna l'elemento contenuto in questo nodo.
    public T getElem() {
        return this.elem;
    }

    // Cambia l'intero contenuto nel nodo al nuovo valore @elem.
    public void setElem(T elem) {
        this.elem = elem;
    }

    // Ritorna il riferimento al nodo successore, oppure null se non c'e' un successore.
    public Node<T> getNext() {
        return this.next;
    }

    // Cambia il riferimento al nodo successore con @next.
    public void setNext(Node<T> next) {
        this.next = next;
    }
    public String toString(){
        return elem.toString();
    }
    public boolean equalsNode(T element){
        return elem.equals(element);
    }
}
