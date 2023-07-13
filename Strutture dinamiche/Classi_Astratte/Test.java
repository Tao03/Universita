package Classi_Astratte;

public class Test {
    public static void main(String[] args) {
        Trapezio b = new Trapezio(4, 30, 20, 10);
        Figura a = new Rettangolo(10, 20);
        System.out.println(b.area());
        Figura c = (Figura) b;
        System.out.println(c.area());
    }
}
