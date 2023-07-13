public class FiltriArrayControVarianti {
    public static void main(String[] args) {
        int[] a = filtroElementiDispari(new int[]{15,17,21,40,50});
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        
    }
    public static int[] filtroElementiDispari(int[] a){
        int[] b = new int[0];
        b = filtroElementiDispari(a,b,0);
        return b;
    }
    public static int[] filtroElementiDispari(int[] a, int[] b, int i){
        if(i==a.length){
            return b;
        }else{
            b = filtroElementiDispari(a, b, i+1);
            if(a[i]%2!=0){
                b = append(b, a[i]);
            }
        }
        return b;

    }
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
}
