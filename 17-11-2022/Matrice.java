public class Matrice {
    public static void main(String[] args) {
        int[][] a = {{2,0,1},{3,1,0},{0,1,0}};
        int[] b = sommaMatriceDiI(a); 
        stampa(b,0,b.length);
    }

    public static int[] sommaMatriceDiI(int[][] a){
        boolean flag = (a != null && isRagged(a) && a.length == a[0].length);
        int[] b = null;
        if(flag){
            b = new int[a.length];
            sommaMatriceDiI(a,b,0);
        }
        return b;
    }
    public static void sommaMatriceDiI(int[][] a, int[] b, int i){
        if(i == a.length){

        }else{
             sommaMatriceDiI(a,b,i+1);
             b[i]= sommaColonna(a,0,i);
        }
    }
    public static int sommaColonna(int[][] a, int j, int i){
        if(j == a.length){
            return 0;
        }else{
            int vi = sommaColonna(a, j+1, i);
            return vi + a[j][i] - a[i][j];
        }
    }






































    ////////////////////////////////////////////////////

    public static int[][] prodottoMatrici(int[][] a, int[][]b){
        boolean flag = /* non mettere questo che poi l'algoritmo non parte siccome il prodotto tra matrici è molto pesante dal punto di vista computazionale
        (a != null && b!= null && isRagged(a) && isRagged(b) && a.length == b[0].length);*/ true;
        int[][] c = null;
        if(flag){
            c = new int[a.length][b[0].length];
            prodottoMatrici(a,b,c,0);
        }
        return c;
    }
    public static void prodottoMatrici(int[][] a, int[][] b, int[][] c, int i){
        if(i == c.length){

        }else{
            prodottoMatrici(a, b,c,i+1);
            prodottoMatrici(a, b, c, 0, i);
        }
    }
    public static void prodottoMatrici(int[][] a, int[][] b, int[][] c, int j, int i){
        if(j == c[i].length){

        }else{
            prodottoMatrici(a, b, c, j+1, i);
            c[i][j] = prodottoVettori(a,b,c,j,i,0);
        }
    }
    public static int prodottoVettori(int[][] a, int[][] b, int[][] c, int j,int i, int f){
        int s;
        if(f == b.length){
            s = 0;
        }else{
            s = prodottoVettori(a, b, c, j,i,f+1);
            s = s + (a[i][f]*b[f][j]);
        }
        return s;
    }
    ////////////////////////////////////





















    ////////////////////////////////////////////7
    public static int[][] matriceTrasposta(int[][] a){
        boolean flag = (a != null && isRagged(a) && a.length>0);
        int[][] b = null;
        if(flag){
            b = new int[a[0].length][a.length];
            matriceTrasposta(a,b,0,a.length);
        }
        return b;
    }

    public static void matriceTrasposta(int[][] a,int[][] b, int l, int r){
        if(r-l == 1){
            colonnaTrasposta(a, b, 0,l);
        }else{
            int m = (l+r)/2;
            matriceTrasposta(a, b, l, m);
            matriceTrasposta(a, b, m, r);
        }
    } 
    public static void colonnaTrasposta(int[][] a,int[][] b, int j, int i){
        if(j == a[i].length){
            
        }else{
            colonnaTrasposta(a, b, j+1, i);
            b[j][i] = a[i][j];
        }
    }





    //////////////////////////////////////////////////// 
    public static void sommaColonne(int[][] matrice,int[] array, int i){
        if(i == matrice.length){
        }else{
            sommaColonne(matrice,array,i+1); // si suppone di aver calcolato la somma delle colonne  (matrice.length...i]
            sommaColonne(matrice,array,0,i);
        }
        
    }
    public static void  sommaColonne(int[][] matrice, int[] array, int j, int i){
        if(j == matrice[i].length){
        }else{
            sommaColonne(matrice,array,j+1,i);
            array[i] = array[i] + matrice[j][i];
        }
    }
    ////////////////////////////////////////////////////////
    public static void scansionaColonneIterativo(int[][] matrice){
        for(int i = 0; i<matrice.length;i++){
            
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print("["+matrice[j][i]+"]");
            }
            System.out.println();
        }
    }
    public static void scansionaColonneRicorsivo(int[][] matrice, int i){
        if(i == matrice.length){
            
        }else{
            scansionaColonneRicorsivo(matrice,i+1);
            scansionaColonnaRicorsivo(matrice,0,i);
           System.out.println();
        }
    }
   public static void scansionaColonnaRicorsivo(int[][] matrice, int j, int nRiga){
      if(j == matrice[nRiga].length){
      
      }else{
            scansionaColonnaRicorsivo(matrice,j+1,nRiga);
            System.out.print("["+matrice[j][nRiga]+"]");
            
      }
   }















   ///////////////////////////////////////////7
    public static void sommaVettori(int[][] matrice,int[] array, int i){
        if(i == matrice.length){

        }else{
           sommaVettori(matrice, array, i+1);
           array[i] = sommaDiagonali(matrice,i);
        }
    }
    public static int sommaDiagonali(int[][] matrice, int v){
        boolean flag = (matrice != null && isRagged(matrice));
        if(flag){
            return sommaDiagonali(matrice,0,matrice.length,v);
        }else{
            return 0;
        }
    }
    public static int  sommaDiagonali(int[][] matrice, int l, int r, int v){
        if(r-l == 1){
            return sommaDiagonali(matrice[l], l, 0, v);
        }else{
            int m = (r+l)/2;
            return sommaDiagonali(matrice, l, m,v) + sommaDiagonali(matrice, m, r,v);
            
        }
    }
    public static int sommaDiagonali(int[] colonna,int l, int i, int v){
        if(i ==  colonna.length){
            return 0;
        }else{
            int vi = sommaDiagonali(colonna,l, i+1, v);
            if(i == l+v){
                vi = vi + colonna[i];
            }
            return vi;
        }
    }

    
    
    

















///////////////////////////////////7
    public static int[] filtroLimiteSuperiore(int[][] matrice, int v){
        boolean flag = (matrice != null && isRagged(matrice));
        int[] array = null;
        if(flag){
            array = filtroLimiteSuperiore(matrice,0,matrice.length, v);
        }
        return array;
    }
    public static int[] filtroLimiteSuperiore(int[][] matrice,  int l, int r, int v){
        if(r-l == 1){
            return filtroLimiteSuperiore(matrice[l],0,v);
        }else{
            int m = (l+r)/2;
            return append(filtroLimiteSuperiore(matrice,l,m,v),filtroLimiteSuperiore(matrice,m,r,v));

        }
    }
    public static int[] filtroLimiteSuperiore(int[] colonna, int i, int v){
        if(i == colonna.length){
            return new int[]{};
        }else{
            int[] vi = filtroLimiteSuperiore(colonna,  i+1, v);
            if(colonna[i]<v){
                vi = append(vi, new int[]{colonna[i]});
            }
            return vi;
        }
    }
    ///////////////////////////////////////// Da finire
    public static void asterischi(char[][] matrice, int l, int r, int k){
        if(r-l == 1){
            if(l<= k){
                asterischi(matrice[l], 0,l+1);
            }else{
                asterischi(matrice[l], 0,l-(k-1));
            }
            
        }else{
            int m = (l+r)/2;
            asterischi(matrice, l, m,k);
            asterischi(matrice, m, r,k);
        }
    }
    
    public static void asterischi(char[] array, int i, int k){
        if(i == k){
            array[i] = '*';
        }else{
            asterischi(array, i+1, k);
            array[i] = '.';
            if(i>= k-1){
                array[i] = '*';
            }
        }
    }









    ///////////////////////////////////////////////////////////////////////////////////



















/////////////////////////////////////////////////////////
    public static void stampa(int[][] matrice){
        boolean flag = (matrice != null /*&& isRagged(matrice)*/);
        if(flag){
            stampa(matrice,0, matrice.length);
        }
    }
    public static void stampa(int[][] matrice, int l, int r){
        if(r-l == 1){
            stampa(matrice[l],0, matrice[l].length);
            System.out.println();
        }else{
            int m = (l+r)/2;
            stampa(matrice, l,m);
            stampa(matrice,m,r);
        }
    }
    public static void stampa(int[] array, int l, int r){
        if(r-l == 1){
            System.out.print(array[l]+" ");
        }else{
            int m = (l+r)/2;
            stampa(array,l,m);
            stampa(array,m,r);
        }
    }
/////////////////////////////////////////////////////////










/////////////////////////////////////////////////////////////////////7
    public static int[][] creaMatriceIntersezione(int n, int m){
        boolean flag = (n > 1 && m>1);
        int[][] matrice;
        if(flag){
            matrice = new int[n][m];
            creaMatriceIntersezione(matrice, 0, n);
        }else{
            matrice = null;
        }
        return matrice;
    }
    public static void creaMatriceIntersezione(int[][] matrice, int l, int r){
        if(r-l == 1){
            vettoreIntersezione(matrice[l],0,l);
        }else{
            int m = (l+r)/2;
            creaMatriceIntersezione(matrice, l, m);
            creaMatriceIntersezione(matrice, m, r);
        }
    }
    public static void vettoreIntersezione(int[] a, int i, int k){
        if(i == a.length){
            return;
        }else{
            vettoreIntersezione(a, i+1,k);
            a[i]= i+k;
        }
    }

///////////////////////////////////////////////////////////



















//////////////////////////////////
    public static int[] differenzaTraColonne(int[][] a){
        int[] array = new int[a.length];
        boolean flag = (a != null && isRagged(a));
        if(flag){
            differenzaTraColonne(a,array,a.length-1);
        }
        return array;
    }
    public static int  differenzaTraColonne(int[][] a, int[] array, int i){
        if(i == 0){
            array[0] = somma(a[0],0);
            return 1;
        }else{
            int vi = differenzaTraColonne(a,array,i-1);
            array[vi] = somma(a[i-1], 0)-somma(a[i],0);
            return vi + 1;
        }
    }
    public static int somma(int[] a, int i){
        if(i == a.length){
            return 0;
        }else{
            return somma(a,i+1)+a[i];
        }
    }
    public static void differenzaTraColonne(int[] a,int[] array, int l, int r){
        if(r-l == 1){
            
        }else{
            int m = (l+r)/2;
            differenzaTraColonne(a, array, l, m);
            differenzaTraColonne(a, array, m, r);
        }
    }
/////////////////////////////////

















    ////////////////////////////////////
public static int[] sommaColonne(int[][] a){
    boolean flag = (a != null  && isRagged(a));
    int[] array = new int[a.length];
    if(flag){
        sommaColonne(a,0,a.length,array);
    }
    return array;
}
public static void sommaColonne(int[][] a, int l, int r, int[] array){
    if(r-l == 1){
        sommaColonne(a[l],0,array,l);
    }else{
        int m = (l+r)/2;
        sommaColonne(a,l,m,array);
        sommaColonne(a,m,r,array);
    }
}
public static void sommaColonne(int[] a, int i, int[] k, int j){
    if(i == a.length){
        return;
    }else{
        sommaColonne(a, i+1, k,j); // ha inserito nell'array k di posizione j la somma degli elementi da [a.length....i)
        k[j] = k[j]+a[i];

    }
}
////////////////////////////////////
public static int[] append(int[] a, int x){
    int[] b = new int[a.length+1];
    append(a,b,0,x);
    return b;
}
public static void append(int[] a, int[] b, int i, int x){
    if(i==a.length){
        b[a.length]=x;
        return;
    }else{
        append(a,b,i+1, x);
        b[i]=a[i];
    }
}












//////////////////////////////////////////////////////////////
public static boolean isRagged(int[][] matrice){
    boolean flag = (matrice != null && matrice.length>1);
    if (flag){
        flag = isRagged(matrice,0,matrice.length,matrice[0].length);
    }
    return flag;
    
}
public static boolean isRagged(int[][] matrice, int l, int r, int k){
    boolean flag;
    if(r-l == 1){
        flag = (matrice[l].length == k);
    }else{
        int m = (l+r)/2;
        flag = (isRagged(matrice,l,m,k) && isRagged(matrice,m,r,k));
    }
    return flag;
}
//////////////////////////////////////////////////////////////
public static int sommaDiagonaleSecondario(int[][] a){
    boolean flag = (a != null);
    int s=0;
    if(flag){
        s = sommaRicorsiva(a, 0);
    }
    return s;
}
public static int sommaRicorsiva(int[][] a, int i){
    if(i == a.length){
        return 0;
    }else{
        int vi = sommaRicorsiva(a, i+1); // restituisce la somma della diagonale secondario a [i+1...a.length)
        vi = vi+ sommaRicorsiva(a[i], 0,a[i].length,i+1); 
        return vi;
    }
}
public static int sommaRicorsiva(int[] a, int l, int r, int k){
    int somma = 0;
    if(r-l == 1){
        if(l == k){
            somma = a[l];
        }

    }else{
        int m = (l+r)/2;
        somma = sommaRicorsiva(a, l, m, k) + sommaRicorsiva(a, m, r, k);
        
    }
    return somma;
}
//////////////////////////////////////////////////////////////
public static int[] append(int[] a, int[] b){
    int[] c = new int[a.length+b.length];
    append(a,b,c,0);
    return c;
}
public static void append(int[] a, int[] b, int[] c, int i){
    if(i==c.length){

    }else{
        append(a, b,c,i+1);
        if(i>=a.length){
            c[i] = b[i-a.length];
        }else{
            c[i] = a[i];
        }
    }
}
}