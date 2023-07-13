public class Dicotomico {
    public static void main(String[] args) {
        int[] a = {10,20,30,40,50};
        azzera(a, 0, 5);
        stampa(a, 0, a.length);
    }
    public static void swap(int[] a){
        swap(a,0,a.length);
    }
    public static void swap(int[] a, int l, int r){
        if(l-r==2){
            int tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
        }else{
            int m = (l+r)/2;
            swap(a,l,m);
            swap(a,m,r);
        }
    }














    /**
     * 
     * Stampa[0,....5)
     *      Stampa[0,...2) <-- 0,1 stampati
     *          Stampa[0,...1) <-- 0 stampato 
     *          Stampa[1,...2) <-- 1 stampato
     *      Stampa[2,...,5) <-- 2,3,4
     *          Stampa[2,3) <-- 2 stampato 
     *          Stampa[3,...,4] <---  3 stampato
     */
    public static void stampa(int[] a, int l, int r){
        if(r-l == 1){
            // stampare a[l,...,l+1)
            System.out.println(a[l]);
        }else{
            int m = (l+r)/2;
            stampa(a,l,m); // stampa a[l,...,m)
            stampa(a,m,r); // stampa a[m,...,r)
        }
    }
    public static int minimo(int[] a, int l, int r){
        if(r-l == 1){
            return a[l];
        }else{
            int m = (l+r)/2;
            int vS = minimo(a,l,m); // stampa a[l,...,m)
            int vD = minimo(a,m,r); // stampa a[m,...,r)
            if(vS>vD){
                return vD;
            }
            return vS;
        }
    }
    public static void azzera(int[] a, int l, int r){
        if(r-l == 1){
            if(l%2==0){
                a[l]=0;
            }
        }else{
            int m = (l+r)/2;
            azzera(a,l,m);
            azzera(a,m,r);

        }
    }
    public static int minimoDiK(int[] a, int l, int r, int k){
        if(r-l == 1){
            if(a[l]<=k){
                return 1;
            }
            return 0;
        }else{
            int m = (l+r)/2;
            int cS = minimoDiK(a,l,m,k);
            int cD = minimoDiK(a,m,r,k);
            return cS+cD;
        }
    }





















    /*
     * Somma dicotomica funziona, GODO TANTISSIMO
     */
    public static int somma(int a, int b) {
        if(a == 1 && b == 0 || a== 0 && b == 1){
            return a+b;
        }else{
            int div = divPiu(a);
            int sommaA = somma(div,a-div);
            div = divPiu(b);
            int sommaB = somma(div, b-div);
            return sommaA + sommaB;

        }
    }
    /**
     * 
     * Non funziona
     */
    public static int scomposizione(int[] x, int a, int c, int d){
        if(a==1){
            x[c]=a;
            return c+1;
        }else{
            if(a%d != 0){
                d=d+1;
                scomposizione(x, a, c, d);
            }
                int b = a/2;
                int cS = scomposizione(x,b, c,d);
                int cD = scomposizione(x,b,cS,d);
                return cD;
        }
        
        
    }
    public static boolean isArrayDispari(int[] a, int l, int r){
        if(r-l == 1){
            if(a[l]%2 == 0){
                return true;
            }
            return false;
        }else{
            int m = (l+r)/2;
            boolean valoreS = isArrayDispari(a, l, m);
            boolean valoreD = isArrayDispari(a, m, r);
            return valoreD && valoreS;
        }
    }
    public static int sommaArray(int[] a){
        return sommaArray(a,0,a.length);
    }
    public static int sommaArray(int[] a, int l, int r){
        if(r-l==1){
            return a[l];
        }else{
            int m = (r+l)/2;
            int vS = sommaArray(a,l,m);
            int vD = sommaArray(a,m,r);
            return vS+vD;
        }
    }
    public static int differenza(int a, int b) {
        if(a == 0){
            return b;
        }else if(b == 0){
            return a;
        }else{
            int sommaA = differenza(a-1,b-1);
            return sommaA;
        }
    }
    
    public static int divPiu(int a){
        int div;
        if(a%2==0){
            div = a/2;
        }else{
            div = (a/2)+1;
        }
        return div;
    }
    /*
     * Ora si prova il prodotto, VAI UOMO, GODO, FUNZIONA
     */
    public static int prodotto(int a, int b){
        if(a == 1 || b == 1){
            return a*b;
        }else{
            int div = cercaDivisore(a);
            int p = prodotto(div, a/div);
            div = cercaDivisore(b);
            int p1 = prodotto(div, b/div);
            return p*p1;

        }
    }
    public static void ordinamento(int[] a){
        ordinamento(a,0,a.length-1);
    }
    public static void ordinamento(int[] x, int a, int b) {
        if(b-a==2){
            if(x[a]>x[b]){
                int tmp = x[b];
                x[b] = x[a];
                x[a] = tmp;
            }
        }else{
            int m = (a+b)/2;
            ordinamento(x,a,m);
            ordinamento(x,m,b);
        }
        
    }
    public static int cercaDivisore(int a){
        int d=1;
        int i = 2;
        while(i<=a && d == 1){
            if(a%i==0){
                d=i;
            }
            i = i+1;
        }
        return d;
    }

}
