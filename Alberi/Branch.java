public class Branch extends Tree {
    private int elem;
    private Tree right;
    private Tree left;

    public Branch(int elem, Tree right, Tree left) {
        super();
        this.right = right;
        this.left = left;
    }

    @Override
    public boolean empty() {
        return true;
    }

    @Override
    public int max() {
        int a = this.right.max();
        if (a > this.elem) {
            return a;
        } else {
            return this.elem;
        }
    }

    @Override
    public boolean contains(int x) {
        if (x > this.elem) {
            return this.right.contains(x);
        } else if (this.elem < x) {
            return this.left.contains(x);
        }
        return true;
    }

    @Override
    public Tree insert(int x) {
        if (x > this.elem) {
            this.right = this.right.insert(x);
        } else if (x < this.elem) {
            this.left = this.left.insert(x);
        }
        return this;
    }

    @Override
    public int depth() {
        int a = this.right.depth();
        int b = this.left.depth();
        if (a > b) {
            return a + 1;
        }
        return b + 1;
    }

    @Override
    public int size() {
        int a = this.right.size();
        int b = this.left.size();
        return a + b + 1;
    }

    @Override
    public int sum() {
        int a = this.right.sum();
        int b = this.left.sum();
        return a + b + this.elem;
    }

    @Override
    public boolean balanced() {
        boolean a = this.right.balanced();
        boolean b = this.left.balanced();
        if (this.right.depth() - this.left.depth() <= 1) {
            return true && a && b;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(int x, int n) {
        if (n == 0) {
            return false;
        } else {
            if (this.elem == x) {
                return true;
            } else {
                boolean a = this.right.contains(x, n - 1);
                boolean b = this.left.contains(x, n - 1);
                return a || b;
            }

        }
    }

    @Override
    public Tree filter_le(int x) {
        Tree a = this.right.filter_le(x);
        Tree b = this.left.filter_le(x);
        if (this.elem < x) {
            return new Branch(this.elem, a, b);
        } else {
            return b;
        }
    }

    @Override
    public int get(int i) {
        int a = this.left.get(i);
        if(a==0){
            return this.elem;
        }
        
        return this.right.get(a-1);

    
}
    public static void main(String[] args) {
        Tree a = new Leaf();
        a = a.insert(1);
        a = a.insert(3);
        a = a.insert(5);
        a = a.insert(2);
        System.out.println(a.get(0));
    }
}
