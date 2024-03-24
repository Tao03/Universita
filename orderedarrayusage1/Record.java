package orderedarrayusage1;
import java.util.Objects;

/**
 * @author Maiuz
 */

public class Record {
    private String stringField = null;
    private int integerField;

    public Record (String stringField, int integerField){
        this.stringField = stringField;
        this.integerField = integerField; 
    }  // Record

    public String getStringField (){
        return this.stringField;
    }  // getStringField

    public int getIntegerField (){
        return this.integerField;
    }  // getIntegerField

}  // class