package orderedarrayusage1;

import java.util.Comparator;
import orderedarrayusage1.Record;;
/**
 * 
 * @author pozzato
 */
public class RecordComparatorInt implements Comparator<Record>{

    @Override
    public int compare(Record r1,Record r2){
        int result = (Integer.valueOf(r1.getIntegerField())).compareTo(r2.getIntegerField());
        if (result != 0)
           return result;
        return (String.CASE_INSENSITIVE_ORDER).compare(r1.getStringField(), r2.getStringField());
    }  // compare

} //cl
