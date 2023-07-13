public class Leaf extends Tree{

    @Override
    public boolean empty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empty'");
    }

    @Override
    public int max() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'max'");
    }

    @Override
    public boolean contains(int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Tree insert(int x) {
        // TODO Auto-generated method stub
        return new Branch(x, this, this);
    }

    @Override
    public int depth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'depth'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public int sum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sum'");
    }

    @Override
    public boolean balanced() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'balanced'");
    }

    @Override
    public boolean contains() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Tree filter_le(int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter_le'");
    }

    @Override
    public int get(int i) {
       return i;
    }
    
}
