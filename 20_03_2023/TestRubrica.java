public class TestRubrica {
    public static void main(String[] args) {
        Rubrica r = new Rubrica(1);
        r.aggiungi("tao", "ouhadidt@gmail.com");
        r.aggiungi("alessandro", "marcosium@gmail.com");
        String msg = r.toString();
        System.out.println(msg);
    }
}
