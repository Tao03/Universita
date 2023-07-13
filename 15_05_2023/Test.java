public class Test {
    public static void main(String[] args) {
        Nil b = new Nil();
        List a = new Cons(1,b);
        a = a.insert(1);
        a = a.insert(2);
        a = a.insert(3);
        int[] c = a.toArray();
        for (int i = 0; i < c.length; i++) {
            System.out.println("["+c[i]+"]");
        }
    }
}
