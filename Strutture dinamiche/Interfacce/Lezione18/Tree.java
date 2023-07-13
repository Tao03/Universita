package Interfacce.Lezione18;

public abstract class Tree<T extends Comparable<T>> {
    public abstract Tree<T> insert(T element);
    public abstract Tree<T> remove(T element);
    public abstract T max();
    public abstract boolean isEmpty();
    public abstract int size();
    public abstract boolean contain(T element);
}
