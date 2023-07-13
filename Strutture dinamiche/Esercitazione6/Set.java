package Esercitazione6;
public class Set<T> {
    private Node<T> first;
    private int size;


    public Set(Node<T> first) {
        this.first = first;
        this.size = 1;
    }
    public Node<T> getFirst() {
        return first;
    }
    public void setFirst(Node<T> first) {
        this.first = first;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public boolean empty(){
        return (first!=null);
    }
    public void add(T elem){
        if(!contain(elem)){
            Node<T> a = new Node<T>(elem,this.first);
            this.first = a;
        }
    }
    public void remove(T elem){
        Node<T> nodoEliminarePrev = findPrev(elem, first);
        nodoEliminarePrev.setNext(nodoEliminarePrev.getNext().getNext());
    }
    public String toString(){
        return toString(first);
    }
    public String toString(Node<T> a){
        if(a==null){
            return "";
        }else{
            String msg = toString(a.getNext());
            msg = msg + "["+a.getElem()+"] ";
            return msg;
        }
    }
    public Node<T> findPrev(T elem,Node<T> a){
        if(a.getNext()==null){
            return null;
        }else{
            Node<T> vi = findPrev(elem,a.getNext());
            if(a.getNext().getElem().equals(elem)){
                return a;
            }
            return vi;
        }
    }
    public boolean contain(T elem){
        return containRic(elem,this.first);
    }
    public boolean containRic(T elem, Node<T> a){
        if(a==null){
            return false;
        }else{
            boolean vi = containRic(elem,a.getNext());
            return (a.getElem()==elem?true:vi);
        }
    }
    public boolean subSetOf(Set<T> a){
        return subSetOf(this,a.getFirst());
    }
    /*
     * Controllare se gli elementi di b sono all'interno di a
     */
    public boolean subSetOf(Set<T> a, Node<T> b){
        if(b==null){
            return true;
        }else{
            boolean perOgni = subSetOf(a, b.getNext());
            return (a.contain(b.getElem()) && perOgni);
        }
    }
    public Set<T> union(Set<T> a){
        unionRec(this,a.getFirst());
        return this;
    }
    public void unionRec(Set<T> a, Node<T> b){
        if(b==null){

        }else{
            unionRec(a, b.getNext());
            a.add(b.getElem());
            return;
        }
    }
    public Set<T> intersection(Set<T> a){
        return (a.first==null || this.first==null?new Set<T>(new Node<T>()):intersectionRec(this,a.getFirst()));
    }
    public Set<T> intersectionRec(Set<T> a, Node<T> node){
        if(node==null){
            return new Set<T>(null);
        }else{
            Set<T> set = intersectionRec(a, node.getNext());
            if(a.contain(node.getElem())){
                set.add(node.getElem());
            }
            return set;
        }
    }
    public static void main(String[] args) {
        Node<Integer> a = new Node<Integer>(1,null);
        Set<Integer> b1 = new Set<Integer>(a);
        Set<Integer> b2 = new Set<Integer>(a);
        b1.add(2); // b1: [1] [2]
        b2.add(2); // b2 [1] [30]
        b1 = b1.intersection(b2);

        System.out.println(b1);

    }
    
}
