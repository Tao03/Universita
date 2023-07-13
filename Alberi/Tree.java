public abstract class Tree{
    public abstract boolean empty();
    public abstract int max();
    public abstract boolean contains(int x);
    public abstract Tree insert(int x);
    public abstract int depth();

    public abstract int size();
    public abstract int sum();
    public abstract boolean balanced();
    public abstract boolean contains(int x, int n);
    public abstract Tree filter_le(int x);
    public abstract int get(int i);
}