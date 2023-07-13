package Primo;
public class RubricaDemo {
    public static void main(String[] args) {
        Rubrica R = new Rubrica(100);
        System.out.println("(1) Rubrica con contatti a,b,c: ");
        R.aggiungi("a","a@ditta");
        R.aggiungi("c","b@ditta");
        R.aggiungi("b","c@ditta");
        R.sort();
        R.scriviOutput();
    }  
}