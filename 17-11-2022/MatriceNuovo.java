public class MatriceNuovo {



















    public static int[][] appendColonna(int[][] matrice,int[] colonna){
        boolean flag = (isRagged(matrice) && matrice!=null && colonna.length>0);
        int[][] matriceNuova = new int[][] {{}};
        if(flag){
            matriceNuova = new int[matrice.length][matrice[0].length+1];
            appendColonna(matrice,matriceNuova,colonna,0);
        }
    }
    public static void appendColonna(int[][] matrice, int[][] matriceNuova, int[] array, int i){
        if(i == matriceNuova[0].length){
            //da finire
        }else{
            appendColonna(matrice, matriceNuova, array, i + 1);
            appendColonna(matriceNuova, array);
        }
    }
    public static void appendColonna(int[][] matrice, int[][] matriceNuova, int[] array, int i, int j){
        if(j == matrice[i].length){

        }else{
            appendColonna(matrice,matriceNuova, array, i, j + 1);
            
        }
    }

    public static int[][] appendArray(int[][] matrice, int[] array){
        boolean flag = (matrice!=null && matrice.length>0 && isRagged(matrice));
        int[][] matriceNuova = new int[][]{{}};
        if(flag){
            matriceNuova = new int[matrice.length + 1][matrice[0].length];
            appendRiga(matrice,matriceNuova,array,0);
        }
        return matriceNuova;

    }
    public static void appendRiga(int[][] matrice, int[][] matriceNuova, int[] array, int i){
        if(i == matrice.length){
            matriceNuova[i] = array;
        }else{
            appendRiga(matrice,matriceNuova,array,i + 1);
            matriceNuova[i] = matrice[i];
        }
    }


























    public static int[] diagonali(int[][] matrice){
        boolean flag = (matrice.length>0 && isRagged(matrice));
        int[] vettore = new int[]{};
        if(flag){
            vettore = new int[matrice.length];
            diagonali(vettore,matrice,0);
        }
        return vettore;

    }
    public static void diagonali(int[] vettore, int[][] matrice, int i){
        if(i == vettore.length){

        }else{
            diagonali(vettore, matrice, i + 1);
            vettore[i] = sommaDiagonali(vettore,matrice,i,0);
        }
    }
    public static int sommaDiagonali(int[] vettore, int[][] matrice, int k, int i){
        if(i == matrice.length){
            return 0;
        }else{
            int s = sommaDiagonali(vettore, matrice, k, i + 1);
            s = s + sommaDiagonali(vettore,matrice,k,i,0);
            return s;
        }
        
    }





    public static int sommaDiagonali(int[] vettore, int[][] matrice, int k, int i, int j){
        if(j == matrice[i].length){
            return 0;
        }else{
            int s = sommaDiagonali(vettore, matrice, k, i, j+1);
            if(j-i==k){
                s = s + matrice[i][j];
            }
            return s;
            
        }
    }
    






























    public static int[][] matriceIntersezione(int m, int n){
        boolean flag = (m>0 && n>0);
        int[][] matrice = new int[m][n];
        if(flag){
            matriceIntersezione(matrice,0);
        }
        return matrice;

    }
    public static void matriceIntersezione(int[][] matrice,int i){
        if(i == matrice.length){

        }else{
            matriceIntersezione(matrice, i + 1);
            vettoreIntersezione(matrice,i,0);
        }
    }
    public static void vettoreIntersezione(int[][] matrice, int i, int j){
        if(j == matrice[i].length){

        }else{
            vettoreIntersezione(matrice, i, j + 1);
            matrice[i][j] = i + j;
        }
    }
































    public static int[][] trasposizione(int[][] matrice){
        boolean flag = (matrice != null && matrice.length>0);
        int[][] matriceTrasposta = null;
        if(flag){
            matriceTrasposta = new int[matrice[0].length][matrice.length];
            trasposizione(matriceTrasposta,matrice,0,matrice.length);
        }
        return matriceTrasposta;
    }
    public static void trasposizione(int[][] arrayNuovo,int[][] array, int l, int r){
        if(r-l == 1){
            trasposizioneVettori(arrayNuovo,array,l,0);
        }else{
            int m = (r+l)/2;
            trasposizione(arrayNuovo, array, l, m);
            trasposizione(arrayNuovo, array, m, r);
        }
        
    }
    public static void trasposizioneVettori(int[][] arrayNuovo, int[][] array, int i, int j){
        if(j == array[i].length){

        }else{
            trasposizioneVettori(arrayNuovo, array, i, j + 1);
            arrayNuovo[j][i] = array[i][j];
        }
    }























    public static int sommaMatriciale(int[][] matrice){
        boolean flag = (matrice!=null && matrice.length>0);
        int s = 0;
        if(flag){
            s = sommaMatriciale(matrice,0);
        }
        return s;
    }
    public static int sommaMatriciale(int[][] matrice, int i){
        if(i == matrice.length){
            return 0;
        }else{
            int vi = sommaMatriciale(matrice,i + 1);
            return vi  + sommaVettoriale(matrice[i],0);
        }
    }
    public static int sommaVettoriale(int[] vettore, int i){
        if(i == vettore.length){
            return 0;
        }else{
            int vi = sommaVettoriale(vettore, i + 1);
            return vi + vettore[i];
        }
    }
    public static boolean isRagged(int[][] matrice){
        return (matrice!=null && matrice.length>0) && isRagged(matrice,matrice.length,matrice[0].length);
        

    }
    public static boolean isRagged(int[][] matrice,int i, int k){
        if(i == matrice.length){
            return true;
        }else{
            boolean flag = isRagged(matrice,i + 1,k);
            return (flag && (matrice[i].length == k));

        }
    }
    
}
