public class Matrice {
    public static void main(String[] args) {
        int[][] matrice = {{10,0,30,40},{10,20,30,40},{10,20,30,40},{10,20,30,40}};
        int[] a = sommaColonne(matrice);
        System.out.println(a[0]);
    }
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
}