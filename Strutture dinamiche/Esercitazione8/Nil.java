package Esercitazione8;

public class Nil extends List{

    @Override
    public boolean empty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(int x) {
        return false;
    }
    public String toString(){
        return "";
    }
    @Override
    public List insert(int x) {
        return new Cons(x,this);
    }

    @Override
    public List append(List l) {
        return l;
    }

    @Override
    public int sum() {
        return 0;
    }

    @Override
    public int get(int i) {
        assert false;
        return 0;
    }

    @Override
    public List succ() {
        return this;
    }

    @Override
    public List filter_le(int x) {
        return this;
    }

    @Override
    public List filter_gt(int x) {
        return this;
    }

    @Override
    public List intersect(List l) {
        return this;
    }
    
}
