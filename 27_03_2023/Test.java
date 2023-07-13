public class Test {
    public static void main(String[] args) {
        MutablePoint a = new MutablePoint(15, 15);
        MutablePoint b = new MutablePoint(23, 30);
        MutablePoint c = new MutablePoint(52, 23);
        MutableTriangle triangolo = new MutableTriangle(a, b, c);
        System.out.println(triangolo);
        stampa(triangolo);
        triangolo.translate(10, 0);
        System.out.println(triangolo);
        stampa(triangolo);
    }
    public static void stampa(MutableTriangle triangolo){
        System.out.println("------------------------------------");
        System.out.println("Area del triangolo: "+triangolo.area());
        System.out.println("Perimetro del triangolo: "+triangolo.perimeter());
        System.out.println("------------------------------------");
    }

}
