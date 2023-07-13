package Esercizio2;

public class Set<T> {
    private Node<T> first;
    private int size;

    public Set(Node<T> first, int size){
        this.first = first;
        this.size = size;
    }
    public int size(){
        return this.size;
    }
    public boolean empty(){
        return (first == null);
    }
    public void add(T elem){
        first = new Node<T>(elem, first);
    }
    public boolean remove(T elem){
        Node<T> top = first;
        Node<T> prev = null;
        boolean remove = false;
        while(top!=null){
            if(elem == top.getElem()){
                if(top == first){
                    this.first = this.first.getNext();
                }else if(top.getNext()==null){
                    prev.setNext(null);
                }else{
                    prev.setNext(top.getNext());
                }
                remove = true;
            }
            prev = top;
            top = top.getNext();
        } 
        return remove;
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
    public String toString(){
        return "["+first.getElem()+"]";
    }
    public boolean contains(T element){
        Node<T> top = first;
        return containsRic(top,element);
    }
    public boolean containsRic(Node<T> top, T element){
        if(top == null){
            return false;
        }else{
            boolean vi = containsRic(top.getNext(), element);
            vi = (top.getElem()== element) || vi;
            return vi;
        }
        
    }
    public Set<T> union(Set<T> lista){
        Set<T> listaNuova = this;
        unionRicorsivo(listaNuova,lista.first);
        return listaNuova;
    }
    public void unionRicorsivo(Set<T> lista,Node<T> first){
        if(first == null){
            return;
        }else{
            unionRicorsivo(lista, first.getNext());
            if(!lista.contains(first.getElem())){
                lista.add(first.getElem());
            }
            
        }
    }
    public boolean equals(Set<T> lista){
        Node<T> top = this.getFirst();
        return equalsRicorsivo(top,lista.getFirst());
        
    }
    public boolean equalsRicorsivo(Node<T> first1, Node<T> first2){
        if(first1==null || first2==null){
            if(first1 == first2){
                return true;
            }
            return false;
        }else{
            boolean vi = equalsRicorsivo(first1.getNext(), first2.getNext());
            return (first1.getElem() == first2.getElem());
        }
    }
    public boolean subSet(Set<T> list){
        Node<T> top = list.getFirst();
        return subSetRic(top);
    }
    public boolean subSetRic(Node<T> top){
        if(top == null){
            return true;
        }else{
            boolean vi = subSetRic( top.getNext());
            return vi && this.contains(top.getElem());//subSetRicElem(list.getFirst(),top);
        }
    }
    public boolean subSetRicElem(Node<T> top, Node<T> fix){
        if(top==null){
            return false;
        }else{
            boolean vi = subSetRicElem(top.getNext(), fix);
            if(top.getElem() == fix.getElem()){
                return true;
            }else{
               
                return vi || false;
                
            }
        }
    }
    public Set<T> intersezione(Set<T> lista){
        Set<T> listaNuova = new Set<>(null, 0);
        intersezioneRicorsivo(listaNuova,lista.getFirst(),this.first,lista);
        return listaNuova;
    }
    public void intersezioneRicorsivo(Set<T> lista,Node<T> a,Node<T> b,Set<T> listaB){
        if(a== null || b == null){
            return;
        }else{
            if(a==null){
                intersezioneRicorsivo(lista, a, b.getNext(),listaB);
                if(this.contains(b.getElem())== true){
                    lista.add(b.getElem());
                }
            }else{
                intersezioneRicorsivo(lista, a.getNext(), b, listaB);
                if(this.contains(a.getElem())== true){
                    lista.add(a.getElem());
                }
            }
            
        }
    }
    public static void main(String[] args) {
        Set<Integer> lista = new Set<Integer>(new Node<Integer>(10,null),5);
        Set<Integer> lista1 = new Set<Integer>(new Node<Integer>(10,null),5);
        System.out.println("Lista A");
        for (int i = 0; i < lista.size(); i++) {
            lista.add(i);
            System.out.print("["+i+"]");
        }
        System.out.println();
        System.out.println("Lista B");
        for (int i = 0; i < lista1.size+3; i++) {
            lista1.add(i);
            System.out.print("["+i+"]");
        }
        //Set<Integer> listaProva = lista.intersezione(lista1);

        //Node<Integer> node = listaProva.first;
        boolean flag = lista.subSet(lista);
        System.out.println(flag);
        /*while(node!=null){
            System.out.println(node);
            node = node.getNext();
        }*/

        /*lista.remove(10);
        lista.remove(2);
        lista.remove(3);
        lista.remove(4);
        System.out.println(lista.contains(10));
        Node<Integer> node = lista.first;
        while(node!=null){
            System.out.println(node);
            node = node.getNext();
        }*/
    }
    
}
