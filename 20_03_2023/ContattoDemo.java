public class ContattoDemo {
    //controllo i metodi della classe Contatto
    public static void main(String[] args) {
        Contatto a = new Contatto("a", "a@ditta");
        System.out.println("Contatto a: " + a);
        System.out.println("Modifico nome a in a2");
        a.setNome("a2");
        System.out.println("Contatto a modificato: " + a);
        System.out.println("Modifico email a in a2@ditta");
        a.setEmail("a2@ditta");
        System.out.println("Contatto a modificato: " + a);
    }
}