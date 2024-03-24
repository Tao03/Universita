package orderedarrayusage1;
import java.util.Comparator;
public class RecordComparatorString implements Comparator<Record> {
    @Override
    public int compare(Record r1, Record r2) {
        int result = (String.CASE_INSENSITIVE_ORDER).compare(r1.getStringField(), r2.getStringField());
        if (result != 0) {
            return result;
        }
        return (Integer.valueOf(r1.getIntegerField())).compareTo(r2.getIntegerField());
    }
}