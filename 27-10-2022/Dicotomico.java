import java.util.Arrays;

public class Dicotomico {
    public static void main(String[] args) {
        int[] a = {1,4,3,4};
        int[] b = {1,2,3,4};
        System.out.println(equals(a, b));
    }
    /**
     * Dicotomica:
     * 
     * conta(a,l,r) ==> [l,r)
     * 
     * if(l<r){
     *      if(r-l==1){
     *          return 1; ==> siccome si arriva a un elemento, si conta uno
     *      }else{
     *          m = (l+r)/2 ==> si cerca la media
     *          cS = conta(a,l,m)
     *          cD = conta(a,m,r)
     *      }
     *      return m
     * }else{
     *      return 0;
     * }
     * 
     */
    public static boolean equals(int[] a, int[] b){
        return equals(a,b,0,a.length);
    }
    public static void stampa(int[] a){
        stampa(a,0,a.length);
    }
    public static void complementareDiUnBooleano(boolean[] a){
        complementareDiUnBooleano(a,0,a.length);
    }
    public static boolean equals(int[] a,int[] b, int l, int r){
        if(r-l==1){
            return a[l]==b[l];
        }else{
            int m = (l+r)/2;
            boolean viS = equals(a, b,l,m);
            boolean viD = equals(a, b, m, r);
            return viS && viD;
        }
    }
    public static void stampa(int[] a, int l, int r){
        if(r-l==1){
            System.out.println(a[r-1]);
        }else{
            int m = (l+r)/2;
            stampa(a,l,m);
            stampa(a,m,r);
        }
        return ;
    }
    public static void complementareDiUnBooleano(boolean[] a, int l, int r){
        if(r-l==1){
            a[l]=!a[l];
        }else{
            int m = (l+r)/2;
            complementareDiUnBooleano(a,l,m);
            complementareDiUnBooleano(a,m,r);
        }
        return ;
    }
   
}
