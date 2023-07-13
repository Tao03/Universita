public class Induzione {
    public static boolean e3(int[] a, int n) {
        int pos = -1;
        boolean esiste = false;
        int i = 0;
        while (i < a.length && !esiste) {
          esiste = a[i] == n;
          if (esiste) {
            pos = i;
          }
          i = i + 1;
        }
        return esiste;
        /*
         * (esiste == true ) se e solo se esiste almeno un indice j tale che (0 <= j <= a.length) e (a[j]==n))
         */
      }
    
}