//Node.java: riutilizziamo la classe gia' vista nella Lezione 08
public class Node {
    private int elem;
    private Node next;

    public Node(int elem, Node next) {
        this.elem = elem;
        this.next = next;
    }
    
    public int getElem() {
        return elem;
    }

    public Node getNext() {
        return next;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}