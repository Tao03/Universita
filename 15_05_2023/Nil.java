public class Nil extends List {
    public boolean empty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public  boolean contains(int x){
        return false;
    }

    public  List insert(int x){
        return new Cons(x,this);
    }

    public  List append(List l){
        return l;
    }
    public String toString()
    // trasformo il primo elemento poi gli altri
    {
        return "";
    }
    public int sum(){
        return 0;
    }
    public int get(int i){
        assert false:"non esiste";
        return 0;
    }
    public List succ(){
        return this;
    }
    public int eval(int x){
        return 0;
    }
    public List filter_le(int x){
        return this;
    }
    public List filter_gt(int x){
        return this;
    }
    public List intersect(List list){
        return this;
    }

    @Override
    public int[] toArray() {
        return new int[]{};
    }


   
}