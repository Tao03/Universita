package Esercizio1;
public class TestDynamicQueue{
    public static void main(String[] args) {
        /*DynamicQueue<Persona> q = new DynamicQueue<Persona>();
        q.enqueue(new Persona("marco", "rossi", 17));
        q.enqueue(new Persona("marco", "rossi", 16));
        q.enqueue(new Persona("marco", "rossi", 15)); 
        q.scriviOutput();

        System.out.println( "q.empty() = " + q.empty());
        //System.out.println( "q.contains(4)  = " + q.contains(4)); //true
        //System.out.println( "q.contains(40) = " + q.contains(40));//false
        System.out.println("q.size() = " + q.size());  // stampa 3
        System.out.println("q.front()= " + q.front()); // stampa 17  
        System.out.println(q.dequeue()); //toglie e stampa 17
        System.out.println(q.dequeue()); //toglie e stampa 42
        System.out.println(q.dequeue()); //toglie e stampa 4: coda vuota

        // gli elementi vengono stampati nello stesso ordine in cui
        // sono stati inseriti, dal momento che la coda e' una
        // struttura FIFO (First-In-First-Out)
        System.out.println( "q.empty() = " + q.empty());*/
        DynamicQueue<Double> q = new DynamicQueue<Double>();
        q.enqueue(1.2);
        q.enqueue(2.3);
        q.enqueue(3.1);
        q.scriviOutput();

        System.out.println( "q.empty() = " + q.empty());
        //System.out.println( "q.contains(4)  = " + q.contains(4)); //true
        //System.out.println( "q.contains(40) = " + q.contains(40));//false
        System.out.println("q.size() = " + q.size());  // stampa 3
        System.out.println("q.front()= " + q.front()); // stampa 17  
        System.out.println(q.dequeue()); //toglie e stampa 17
        System.out.println(q.dequeue()); //toglie e stampa 42
        System.out.println(q.dequeue()); //toglie e stampa 4: coda vuota

        // gli elementi vengono stampati nello stesso ordine in cui
        // sono stati inseriti, dal momento che la coda e' una
        // struttura FIFO (First-In-First-Out)
        System.out.println( "q.empty() = " + q.empty());

    }
}

