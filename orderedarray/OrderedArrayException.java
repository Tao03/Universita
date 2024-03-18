package orderedarray;

public class OrderedArrayException extends Exception {
    /**
     * @param message: the message displayed when the exception is thrown
     */
    public OrderedArrayException (String message){
        super("-- ERROR OrderedArray: "+message);
    }
}
