public class Test {
    public static void main(String[] args) {
        /*int[] arrayTest = {};
        System.out.println("Esito del test 1: "+(Vettori.ricercaLineare(arrayTest, 0)==false));
        int[] arrayTest1 = {10,20,30};
        System.out.println("Esito del test 2: "+(Vettori.ricercaLineare(arrayTest1, 1)==false));
        int[] arrayTest2 = {10,20,30};
        System.out.println("Esito del test 2: "+(Vettori.ricercaLineare(arrayTest2, 10)==true));*/
		int[] arrayTest = {};
        System.out.println("Esito del test 1: "+(Vettori.ricercaLineareControVariante(arrayTest, 0)==false));
        int[] arrayTest1 = {10,20,30};
        System.out.println("Esito del test 2: "+(Vettori.ricercaLineareControVariante(arrayTest1, 1)==false));
        int[] arrayTest2 = {10,20,30};
        System.out.println("Esito del test 2: "+(Vettori.ricercaLineareControVariante(arrayTest2, 10)==true));
    }
}
