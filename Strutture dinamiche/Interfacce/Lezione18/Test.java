package Interfacce.Lezione18;

public class Test {
    public static void main(String[] args) {
        Tree<Integer> a = new Leaf<Integer>();
        for (int i = 0; i < 10; i++) {
            a = a.insert(i);
        }
        System.out.println(a.contain(10));
    }
}
