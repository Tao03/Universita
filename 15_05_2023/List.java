public abstract class List
{
public abstract boolean empty();
public abstract int size();
public abstract boolean contains(int x);
public abstract List insert(int x);
public abstract List append(List l);
// NUOVI METODI
public abstract int eval(int x);
public abstract int sum();
public abstract int get(int i);
public abstract List succ();
public abstract List filter_le(int x);
public abstract List filter_gt(int x);
public abstract List intersect(List l);
public abstract int[] toArray();
}