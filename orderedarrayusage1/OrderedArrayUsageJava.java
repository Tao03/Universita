package orderedarrayusage1;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

import orderedarray.OrderedArrayException;
import orderedarray.OrderedArray;
import orderedarrayusage1.Record;
import orderedarrayusage1.RecordComparatorString;

public class OrderedArrayUsageJava{
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    /**
     * 
     * @param args: the command line arguments 
     * It should contain only one argument specyfing the file path of the data file
     */
    public static void main(String[] args) throws IOException, Exception{
        if(args.length != 1){
            throw new Exception("Errore su OrderedArrayUsageJava");
        }
        //testWithComparisonFunction(args[0], new RecordComparatorInt());
        testWithComparisonFunction(args[0], new RecordComparatorString());

    }

    private static void testWithComparisonFunction(String filepath, Comparator<Record> comparator) throws IOException,OrderedArrayException{
        OrderedArray<Record> orderedArray = new OrderedArray(comparator);
        loadArray(filepath,orderedArray);
        printArray(orderedArray);
    }
    private static void loadArray(String filepath, OrderedArray<Record> orderedArray) throws IOException, OrderedArrayException{
        System.out.println("LOADING DATA FROM FILE...");
        Path inputFilePath = Paths.get(filepath);
        try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)){
            String line = null;
            while((line = fileInputReader.readLine()) != null){
                String [] lineElements = line.split(",");
                Record recordToBeAdded = new Record(lineElements[0],Integer.parseInt(lineElements[1]));
                orderedArray.add(recordToBeAdded);
            }
        }
        System.out.println("LOADED!");
    }
    private static void printArray(OrderedArray<Record> array) throws OrderedArrayException {
        Record currRec = null;
        int sizeArr = array.size();
        for(int i = 0; i< sizeArr; i++){
            currRec = array.get(i);
            System.out.println("<"+currRec.getStringField()+","+currRec.getIntegerField()+">\n");
        }
        


    }
}
