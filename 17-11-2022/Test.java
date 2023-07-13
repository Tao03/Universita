public class Test {
    public static void main(String[] args) {
        int[][] matriceNuova = MatriceNuovo.matriceIntersezione(3, 3);
        Matrice.stampa(matriceNuova);
        System.out.println();
        matriceNuova = MatriceNuovo.appendArray(matriceNuova, new int[]{10,20,30});
        Matrice.stampa(matriceNuova);
    }
}
