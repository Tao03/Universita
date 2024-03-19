import org.junit.runner.JunitCore;
import org.junit.runner.Result;
import org.junit.runner.notificatioon.Failure;


public class OrderedArrayJava_TestsRunner {
    /**
     * @param args: the comand line arguments
     */
    public static void main(String[] args){
        Result res = JUnitCore.runClasses(OrderedArrayTests.class);
        for(com.sun.net.httpserver.Authenticator.Failure fail: res.getFailures() ){
            System.out.println(fail.toString());
        }
        System.out.println("Esito test: "+res.wasSuccessful);
    }
}
