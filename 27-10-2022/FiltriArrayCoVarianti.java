public class FiltriArrayCoVarianti {
    public static void main(String[] args) {
        
    }

    

    public static void filtroDispari(int[] a, int i) {
        if (i == 0) {
            return;
        } else {
            filtroDispari(a, i);
            if (a[i - 1] % 2 == 0) {
                a[i - 1] = 0;
            }
        }
        return;
    }

    public static int filtroPosizioniPari(int[] a, int i, int[] b, int j) {
        if (i == 0) {
            return 0;
        } else {
            j = filtroPosizioniPari(a, i - 1, b, j);
            if (i % 2 == 0) {
                b[j] = a[i - 1];
                System.out.println("entra in posizione " + j + " " + b[j]);
                j = j + 1;
            }
        }
        return j;
    }

    public static int PosizioniDispariValoriPari(int[] a, int b[], int i) {
        if (i == a.length) {
            return 0;
        } else {
            int j = PosizioniDispariValoriPari(a, b, i + 1);
            if (a[i] % 2 == 0 || i % 2 != 0) {
                b[j] = a[i];
                j = j + 1;
            }
            return j;
        }
    }

    /**
     * 
     * Per sbaglio, forse, l'ho fatto contro-variante :D
     */
    public static int contaPosizioniDispariValoriPari(int[] a, int i) {
        if (i == a.length) {
            return 0;
        } else {
            int vi = contaPosizioniDispariValoriPari(a, i + 1);
            if (a[i] % 2 == 0 || i % 2 != 0) {
                vi = vi + 1;
            }
            return vi;
        }
    }

    public static int contaValoriLimite(int[] a, int min, int max, int i) {
        if (i == min) {
            return 1;
        } else {
            return contaValoriLimite(a, min, max, i - 1) + 1;
        }
    }

    public static int ValoriLimite(int[] a, int min, int max, int[] b, int i) {
        if (i == min) {
            return 0;
        } else {
            int vi = ValoriLimite(a, min, max, b, i - 1);
            b[vi] = a[i - 1];
            return vi + 1;

        }
    }

    public static int contaDoppio(int[] a, int i, int b) {
        if (i == 0) {
            return 0;
        } else {
            int vi = contaDoppio(a, i - 1, b);
            if (a[i - 1] / b == 2) {
                vi = vi + 1;
            }
            return vi;
        }
    }

    public static int filtroValoriDoppi(int[] a, int[] b, int i, int c) {
        if (i == 0) {
            return 0;
        } else {
            int j = filtroValoriDoppi(a, b, i - 1, c);
            if ((a[i - 1] / c) == 2) {
                b[j] = a[i - 1];
                j = j + 1;
            }
            return j;
        }
    }

}