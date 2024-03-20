package orderedarray;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Tao
 * @param <T>: type of the array elements
 */
public class OrderedArray<T>{
    private ArrayList<T> array = null;
    private Comparator<? super T> comparator;

    /**
     * it creates an empty ordered array and returns the created array
     * @param comparator: a comparator implementing the precedence relation among array elements
     */
    public OrderedArray(Comparator< ? super T> comparator){
        this.comparator = comparator;
        this.array = new ArrayList<>();
    }
    /**
     * 
     * @return true if the array is empty, otherwise false
     */
    public boolean isEmpty(){
        return (this.array).isEmpty();
    }
    public int size(){
        return (this.array).size();
    }
    public void add ( T elem) throws OrderedArrayException {
        if(elem == null){
           throw new OrderedArrayException("--add: element can't be null");
        }
        int index = getIndexInsert(elem);
        (this.array).add(index,elem);
    }
    private int getIndexInsert(T elem){
        int index = 0;
        boolean cont = true;
        T currEl = null;
        while(cont && (index < (this.array).size())){
            currEl = (this.array).get(index);
            if((this.comparator).compare(elem, currEl)<0){
                cont = false;
            }else{
                index = index + 1;
            }   
        }
        return index;
    }
    /**
     * 
     * @param i: index of the element that should be returned
     * @return: the element at position i
     * @throws OrderedArray: if and only if i is out of the array bounds 
     */
    public T get(int i ) {
        if(i<0 || i>= this.array.size()){
            //throw new OrderedArrayException("out of bound");
        }
        return (this.array).get(i);
    }
}
