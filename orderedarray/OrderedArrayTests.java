package orderedarray;
import java.beans.Transient;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import orderedarray.OrderedArray;
public class OrderedArrayTests {
    class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer i1, Integer i2){
            return i1.compareTo(i2);
        }
    }
    private Integer i1, i2, i3;
    private OrderedArray<Integer> orderedArray;

    @Before
    public void createOrderedArray(){
        i1 = -12;
        i2 = 0;
        i3 = 4;
        orderedArray = new OrderedArray<>(new IntegerComparator());
    }

    @Test
    public void testIsEmpty_zeroEl(){
        assertTrue(orderedArray.isEmpty());
    }
    @Test
    public void testIsEmpty_oneEl() throws Exception{
        orderedArray.add(i1);
        assertFalse(orderedArray.isEmpty());
    }

    @Test
    public void testSize_seroEl(){
        assertEquals(0,orderedArray.size());
    }

    @Test
    public void testSize_oneEl() throws Exception{
        orderedArray.add(i1);
        assertEquals(1,orderedArray.size());
    }

    @Test
    public void testSize_twoEl() throws Exception{
        orderedArray.add(i1);
        orderedArray.add(i2);
        assertEquals(2,(this.orderedArray).size());
    }
    @Test
    public void testAddGet_oneEl() throws Exception{
        orderedArray.add(i1);
        assertTrue(i1 == this.orderedArray.get(0));
    }

    @Test
    public void testArray_threeEl() throws Exception{
        orderedArray.add(i2);
        orderedArray.add(i1);
        orderedArray.add(i3);

        Integer [] arrayExpected = {i1,i2,i3};

        Integer [] arrayActual = new Integer[3];
        
        for (int i = 0; i < arrayActual.length; i++) {
            arrayActual[i] = orderedArray.get(i);
        }
        assertArrayEquals(arrayActual,arrayExpected);
    }
}
