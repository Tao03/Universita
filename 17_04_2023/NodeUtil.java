public class NodeUtil<T>{
    private  T element;
    private NodeUtil<T> next;

    public NodeUtil(T element, NodeUtil<T> next) {
        this.element = element;
        this.next = next;
    }
    /*Esercizi*/
    public static <T> int size(NodeUtil<T> node){
        if(node==null){
            return 0;
        }else{
            int vi = size(node.getNext());
            return vi + 1;
        }
    }

    public static <T> int occurences(NodeUtil<T> node, T x){
        if(node==null){
            return 0;
        }else{
            int vi = occurences(node.getNext(), x);
            if(node.getElement()== x){
                vi = vi + 1;
            }
            return vi;
        }
    }

    public static <T> NodeUtil<T> reverse(NodeUtil<T> node){
        if(node==null){
            return new NodeUtil<T>(null, null);
        }else{
            NodeUtil<T> n = reverse(node.getNext());
            n = new NodeUtil<T>(node.getElement(), n);
            //NodeUtil<T> n = reverse(node.getNext());
            return n;
        }
    }
    public static <T> void print(NodeUtil<T> node){
        if(node==null){
            return;
        }else{
            print(node.getNext());
            System.out.println(node.getElement());
        }
    }
    public static <T> NodeUtil<Integer> count(NodeUtil<NodeUtil<T>> list){
        if(list.getNext()==null){
            return new NodeUtil<Integer>(countColumns(list.getElement()), null);
        }else{
            NodeUtil<Integer> vi = count(list.getNext());
            int c = countColumns(list.getElement());
            vi = new NodeUtil<Integer>(c, vi);
            return vi;
        }
    }
    
    private static <T> int countColumns(NodeUtil<T> element) {
        if(element==null){
            return 0;
        }else{
            return 1 + countColumns(element.getNext());

        }
    }

    public static <T> boolean included(NodeUtil<Integer> p, NodeUtil<Integer> q){
        if(p.getNext()==null || q.getNext()==null){
            return true;
        }else{
            boolean flag = (p.getElement()>p.getNext().getElement() && q.getElement()>q.getNext().getElement()) || (p.getElement()<=p.getNext().getElement() && q.getElement()<=q.getNext().getElement());
            return included(p.getNext(), q.getNext()) && flag;
        }
    }
    /*public static <T> NodeUtil<T> reverse(NodeUtil<T>  p){
        NodeUtil<T> newNode=null;
        while(p!=null){
            newNode = new NodeUtil<T>(p.getElement(),newNode);
            p=p.getNext();
        }
        return newNode;
    }*/
    
    public static void main(String[] args) {
        /*NodeUtil<NodeUtil<Integer>> node = new NodeUtil<NodeUtil<Integer>>(new NodeUtil<Integer>(12, null), null);
        node = new NodeUtil<NodeUtil<Integer>>(new NodeUtil<Integer>(12, null), null);
        NodeUtil<Integer> elem = count(node);*/
        NodeUtil<Integer> node = new NodeUtil<Integer>(1, null);
        node = new NodeUtil<Integer>(2, node);
        node = new NodeUtil<Integer>(3, node);
        print(node);
        System.out.println("----------------------------------------------------");
        print(reverse(node));
    }
    





























    public T getElement() {
        return element;
    }
    public void setElement(T element) {
        this.element = element;
    }
    public NodeUtil<T> getNext() {
        return next;
    }
    public void setNext(NodeUtil<T> next) {
        this.next = next;
    }
    

}