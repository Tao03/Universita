package Esercitazione8;

public class Cons extends List {
    private int element;
    private List next;
    public Cons(int element,List next){
        this.element = element;
        this.next = next;
    }
    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public int size() {
        return next.size() + 1;
    }

    @Override
    public boolean contains(int x) {
        return x== this.element || next.contains(x);
    }

    @Override
    public List insert(int x) {
        return new Cons(x,this);
    }

    @Override
    public List append(List l) {
        List lista = next.append(l);
        lista.insert(this.element);
        return l;
    }

    @Override
    public int sum() {
        return this.element + this.next.sum();
    }

    @Override
    public int get(int i) {
        if(i==0){
            return this.element;
        }else{
            return this.next.get(i-1);
        }
    }

    @Override
    public List succ() {
        List l = this.next.succ();
        l = l.insert(this.element + 1);
        return l;
    }

    @Override
    public List filter_le(int x) {
        List l = this.next.filter_le(x);
        if(this.element<=x){
            l = l.insert(this.element);
        }
        return l;
    }

    @Override
    public List filter_gt(int x) {
        List l = this.next.filter_gt(x);
        if(this.element>x){
             l = l.insert(this.element);
        }
        return l;
    }
    @Override
    public String toString(){
        return this.next.toString() + "["+this.element+"] ";
    }
    @Override
    public List intersect(List l) {
        List lista = this.next.intersect(l);
        if(l.contains(this.element)){
            lista = lista.insert(this.element);
        }
        return lista;
    }
    public static void main(String[] args) {
        List l = new Cons(1,new Nil());
        l = (List) l;
        System.out.println(l.toString());
    }
    
}
