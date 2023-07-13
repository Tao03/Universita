package Interfacce.Lezione18;

public class Leaf<T extends Comparable<T>> extends Tree<T>{

    @Override
    public Tree<T> insert(T element) {
       return new Branch<T>(element, this,this);
    }

    @Override
    public Tree<T> remove(T element) {
        return this;
    }

    @Override
    public T max() {
        assert false:"Errore nell max di Leaf";
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contain(T element) {
        return false;
    } 
}
