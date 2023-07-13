public class Test {
    public static void main(String[] args) {
        Calcolatrice c = new Calcolatrice();
        int[] vettore = new int[100];
        int size = 0;
        int risultato = c.esegui("9923423s",vettore,size);
        if(risultato < Integer.MAX_VALUE){
            System.out.println("Risultato: "+risultato);
        }
    }
}
