public class Esercizi{
    public static void main(String[] args) {
        boolean flag = eDREDC(new int[][]{{10,20,15},{17,21,20}});
        System.out.println(flag);
    }
    public static boolean eDREDC(int[][] a){
        int c = 0;
        int i = 0;
        while(a!= null && i<a.length && c<2){
            int f = 0;
            int j = 0;
            while(a[i] != null && j<a[i].length && f<2){
                f = (a[i][j] % 2 == 0 ? f + 1: f);
                j = j + 1;
            }
            i = i + 1;
            c = (f >= 2 ? c + 1: c);
        }
        return (c >= 2);
    }
    public static boolean eDR(int[][] a){
        int c = 0;
        int i = 0;
        while(a!=null && i<a.length ){
            boolean esisteColonnaPari = false;
            int j = 0;
            while(a[i]!= null && !esisteColonnaPari && j<a[i].length){
                esisteColonnaPari = ( a[i][j]%2 == 0 );
                j = j + 1;
            }
            c = (esisteColonnaPari ? c+1: c);
            i = i + 1;
            
        }
        return (c == 2);
    }


















    public static boolean esisteEsistePari(int[][] a){
        boolean esisteUnaRiga = false;
        boolean esisteSecondaRiga = false;
        int i = 0;
        while((!esisteUnaRiga || !esisteSecondaRiga) && i<a.length){
            boolean esisteValorePari = false;
            int j = 0;
            while(!esisteValorePari && j<a[i].length){
                if(a[i][j] %2 == 0){
                    esisteValorePari = true;
                }
                j = j + 1;
            }
            
            if(esisteUnaRiga){
                esisteSecondaRiga = esisteValorePari;
            }else{
                esisteUnaRiga = esisteValorePari;
            }
            i = i + 1;
        }
        return (esisteUnaRiga && esisteSecondaRiga);
        
    }
    public static boolean doppioDi(int[][] a, int c){
        boolean ogniRiga = true;
        int i = 0;
        while(ogniRiga && i<a.length){
            boolean esisteColonna = false;
            int j = 0;
            while(!esisteColonna && j<a[i].length){
                esisteColonna = (a[i][j]/a[i][c] == 2);
                j = j + 1;
            }
            i = i + 1;
            ogniRiga = esisteColonna;
        }
        return ogniRiga;
    }
    public static boolean doppioDiC(int[] a, int j){
        int i = 0;
        boolean esisteColonna = false;
        while(!esisteColonna && i<a.length){
            esisteColonna = (a[i]/a[j] == 2);
        }
        return esisteColonna;
    }
    public static boolean multiploDx(int[] a){
        boolean esiste = false;
        int i = 0;
        while(!false && i<a.length){
            boolean ogni = true;
            int j = i + 1;
            while(j<a.length-1 && ogni){
                    ogni = (a[i] % a[j] != 0);
                j = j + 1;
            }
            esiste = ogni;
            i = i + 1;
        }
        return esiste;

    }
    public static boolean somma(int[] a, int[] b){
        boolean ogni = true;
        int i = 0;
        while(ogni && i<a.length){
            boolean esiste = false;
            int j = 0;
            while(!esiste && j<b.length-1){
                esiste = (a[i] == (b[j]+b[j+1]));
                j = j + 1;
            }
            ogni = esiste;
            i = i + 1;
            
        }
        return ogni;

    }
    public static boolean aDD(int[][] matrice){
        int c = 0;
        int i = 0;
        while(matrice != null && i<matrice.length){
            boolean esiste = false;
            int j = 0;
            while(matrice[i]!= null &&!esiste && j<matrice[i].length){
               esiste = (matrice[i][j]%2 == 0); 
               j = j + 1;
            }
            i = i + 1;
            if(esiste){
                c = c + 1;
            }   
        }
        return c >= 2;

    }
}