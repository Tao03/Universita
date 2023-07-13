package Esercizio1;
public class Test {
    public static void main(String[] args) {
        Persona pe = new Persona("marco", "rossi", 19);
        Node<Persona> p = new Node<Persona>(pe, null);
        boolean a = p.equalsNode(pe);
        System.out.println(a);
    }
}
