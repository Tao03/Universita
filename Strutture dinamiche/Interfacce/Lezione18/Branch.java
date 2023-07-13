package Interfacce.Lezione18;

public class Branch<T extends Comparable<T>> extends Tree<T> {
    private T element;
    private Tree<T> right;
    private Tree<T> left;
    
    public Branch(T element, Tree<T> right, Tree<T> left) {
        this.element = element;
        this.right = right;
        this.left = left;
    }

    @Override
    public Tree<T> insert(T element) {
        if(element.compareTo(this.element)>0){
            this.right = this.right.insert(element);
            return this;
        }else if(element.compareTo(this.element)>0){
            this.left = this.left.insert(element);
            return this;
        }else{
            return this;
        }
    }

    @Override
    public Tree<T> remove(T element) {
        if(element.compareTo(this.element)>0){
            this.right = this.right.remove(element);
            return this;
        }else if(element.compareTo(this.element)<0){
            this.left = this.left.remove(element);
            return this;
        }else{
            if(right.isEmpty()){
                return this.left;
            } else if(left.isEmpty()){
                return this.right;
            }else{
                element = left.max();
                this.left.remove(element);
                return this;
            }
        }
    }

    @Override
    public T max() {
        if(right.isEmpty()){
            return this.element;
        }
        return this.right.max();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return this.right.size() + this.left.size() + 1;
    }

    @Override
    public boolean contain(T element) {
        int c = element.compareTo(this.element);
        if(c>0){
            return this.right.contain(element);
        }else if(c<0){
            return this.left.contain(element);
        }else{
            return true;
        }
    }
    
}
