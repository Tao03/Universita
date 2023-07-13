public class Dicotomico{
	public static void main(String[] args){
		int i = sottoArray(new int[]{1,2,3,4,5},new int[]{1,2,3},0,5);
		System.out.println(i);
		
	}
	public static boolean uguali(int[][] a, int[][] b){
		boolean flag = (a == null || b == null);
		if(flag){
			flag = (a.length == b.length);
			if(flag){
				flag = uguali(a, b,0,a.length);
			}
		}
		return flag;
	}

	public static boolean uguali(int[][] a, int[][] b, int l, int r){
		if(r-l == 1){
			boolean flag = (a != null || b != null) && (a[l].length == b[l].length) ;
			return (flag) ? uguali(a[l],b[l],0,a[l].length) : false;
		}else{
			int m = (l+r)/2;
			return uguali(a,b,l,m) && uguali(a, b,m,r);
		}
	}
	public static boolean uguali(int[] a, int[] b, int l, int r){	
		if(r-l == 1){
			return (b[l] == a[l]) ? true: false;
		}else{
			int m = (r+l)/2;
			return uguali(a,b,l,m) && uguali(a,b,m,r);

		}
	}
























	// DA FINIRE
	public static int sottoArray(int[] a,int[] b, int l, int r){
		if(r-l == 1){
			return ricerca(b,a[l],b.length);
		}else{
			int m = (r+l)/2;
			int x = sottoArray(a,b,l,m);
			int y = sottoArray(a, b,m,r);
			if(y == 1+x){
				return x;
			}else{
				return -2;
			}
		}
	}
	public static int ricerca(int[] b,int k, int i){
		if(i==0){
			return -2;
		}else{
			int vi = ricerca(b,k,i-1);
			return (vi != -2) ? vi: (b[i-1]==k) ? i-1: -2;
		}
	}

	public static int minimo(int[][] a){
		return minimo(a,a.length-1);
	}
	public static int minimo(int[][] a, int i){
		if(i == 0){
			return 999999;
		}else{
			int min = minimo(a,i-1); // restituiscei il valore più piccolo delle colonne [a.length-1 i)
			return (min<minimo(a[i], a[i].length-1)) ? min : minimo(a[i], a[i].length-1); // restituiscei il valore più piccolo delle colonne [a.length-1 i]
		}
	}
	public static int minimo(int[] a, int i){
		if(i==0){
			return 999999;
		}else{
			int min = minimo(a, i-1); 
			return (min<a[i-1]) ? min: a[i-1];
		}
	}
	// Per agnese
	public static int sommaRicorsiva(int a, int b){
		if(b == 0){ // Caso base: il caso più semplice per la somma è quando a + 0 siccome è uguale ad a
			return a;
		}else{
			/**
			 * Caso induttivo: siccome b non è uguale a 0, si va a scomporre il problema decrementando b di uno
			 * Tuttavia la funzione sommaRicorsiva restituisce la somma di a + (b-1) == (a+b)-1 e NON a+b
			 * Quindi si aggiunge ad a + (b-1) un 1 e diventa a+(b-1)+1 == a+b
			 */
			int vi = sommaRicorsiva(a, b-1);
			vi = vi+1; 
			return vi;
		}
		
	}


















	public static boolean isQuadrata(int[][] a, int i){
		if(i == a.length){
			return true;
		}else{
			return isQuadrata(a, i+1) && isQuadrata(a[i], i,a.length);

		}
	}
	public static boolean isQuadrata(int[] a, int i, int k){
		return (a.length==k)? true : false;
	}
	public static void stampaVettore(int[] a, int l, int r){
		if(r-l == 1){
			System.out.print("["+a[l]+"] ");
		}else{
			int m = (r+l)/2;
			stampaVettore(a, l, m);
			stampaVettore(a, m, r);
		}
	}
	public static int prodottoDiagonale(int[][] a){
		if(isQuadrata(a, 0)){
			return prodottoDiagonale(a, 0, a.length);
		}else{
			return 1;
		}
	}
	public static int prodottoDiagonale(int[][] a, int l, int r){
		if(r-l == 1){
			return prodottoDiagonale(a[l], 0, a[l].length,l);
		}else{
			int m = (l+r)/2;
			return prodottoDiagonale(a, l, m) * prodottoDiagonale(a, m, r);
		}
	}
	public static int prodottoDiagonale(int[] a, int l, int r, int k){
		if(r-l == 1){
			return (l==k)? a[l]:1;
		}else{
			int m = (r+l)/2;
			return prodottoDiagonale(a,l,m,k) * prodottoDiagonale(a, m, r,k);
		}
	}
	public static void stampaMatrice(int[][] a, int l, int r){
		if(r-l == 1){
			stampaVettore(a[l], 0, a[l].length);
			System.out.println();
		}else{
			int m = (r+l)/2;
			stampaMatrice(a, l, m);
			stampaMatrice(a, m, r);
		}

	}

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

	public static boolean trovaV(int[] a,int[] b, int i, int v){
		if(i== a.length){
			return false;
		}else{
			boolean flag = trovaV(a,b,i+1,v);
			if(a[i] == v){
				if(flag){
					if(cercaV(a,i,v)== true){
						b = appendEquivalente(b, new int[]{a[i]});
					}
				}
			}
			return flag;

		}
	}
	public static boolean cercaV(int[] a, int i, int v ){
		if(i == 0){
			return false;
		}else{
			return cercaV(a, i-1, v) || a[i]==v;
		}
	}
	
	/*public static boolean cercaV(int[] a, int i,int v){
		if(i==a.length){
			return false;
		}else{
			return cercaV(a,i+1,v) || a[i]==v;
		}
	}*/












	/*						CONTROVARIANTE 				 */
	public static int[] contaFiltroElementoSuperioreControVariante(int[] a, int k){
		int[] b = new int[contaFiltroElementoSuperioreControVariante(a, k,0)];
		return b;
	}
	public static int contaFiltroElementoSuperioreControVariante(int[] a, int k,int i ){
		return (i==a.length) ? 0 : (a[i]>k) ? contaFiltroElementoSuperioreControVariante(a, k,i+1)+1 : contaFiltroElementoSuperioreControVariante(a, k,i+1); 
	}

	public static void filtroElementoSuperioreControVariante(int[] a,int[] b,int i,int k, int l, int r){
		 if(r-l == 1){
			if(a[l]>k){
				b[i]=a[l];
				i=i+1;
			}
		 }else{
			int m = (l+r)/2;
			filtroElementoSuperioreControVariante(a, b, i, k, l,m);
			filtroElementoSuperioreControVariante(a, b, i, k, m,r);
		 }
	}




























	public static int[] filtroDoppioRiferimento(int[] a, int b){
		return filtroDoppioRiferimento(a, b, 0, a.length);
	}
	public static int[] filtroMinMax(int[] a, int min, int max){
		return filtroMinMax(a,min,max,0,a.length);
	}
	public static int[] filtroIndiceDispariValorePari(int[] a){
		return filtroIndiceDispariValorePari(a, 0, a.length);
	}
	public static int[] filtroDoppioRiferimento(int[] a, int b, int l, int r){
		if(r-l == 1){
			return (a[l]/b == 2)? new int[]{a[l]}: new int[]{};
		}else{
			int m = (l+r)/2;
			int[] x = filtroDoppioRiferimento(a, b,l,m);
			int[] y = filtroDoppioRiferimento(a, b, m, r);
			return appendEquivalente(x, y);
		}
	}
	public static int[] filtroMinMax(int[] a, int min, int max, int l, int r){
		if(r-l == 1){
			//(a == 1) ? 20: 30;
			return (a[l]>= min && a[l] < max) ? new int[]{a[l]}: new int[]{};
		}else{
			int m = (l+r)/2;
			int b[] = filtroMinMax(a, min, max,l,m);
			int c[] = filtroMinMax(a,min,max,m,r);
			return appendEquivalente(b,c);
		}
	}
	public static int[] filtroIndiceDispariValorePari(int[] a, int l, int r) {
		if(r-l == 1){
			if(l%2 != 0 && a[l]%2 == 0){
				return new int[]{a[l]};
			}
			return new int[]{};
		}else{
			int m = (l+r)/2;
			int[] b = filtroIndiceDispariValorePari(a, l,m);
			int[] c = filtroIndiceDispariValorePari(a,m,r);
			return appendEquivalente(b,c);
		}
		
	}
	public static int[] filtroIndicePari(int[] a){
		return filtroIndiceDispari(a, 0, a.length);
	}
	public static int[] filtroIndicePari(int[] a, int l, int r) {
		if(r-l == 1){
			if(l%2 != 0 ){
				return new int[]{a[l]};
			}
			return new int[]{};
		}else{
			int m = (l+r)/2;
			int[] b = filtroIndicePari(a, l,m);
			int[] c = filtroIndicePari(a,m,r);
			return appendEquivalente(b,c);
		}
		
	}
	public static int[] filtroIndiceDispari(int[] a, int k){
		return filtroIndiceDispari(a, 0, a.length);
	}
	public static int[] filtroIndiceDispari(int[] a, int l, int r) {
		if(r-l == 1){
			if(l%2 != 0){
				return new int[]{a[l]};
			}
			return new int[]{};
		}else{
			int m = (l+r)/2;
			int[] b = filtroIndiceDispari(a, l,m);
			int[] c = filtroIndiceDispari(a,m,r);
			return appendEquivalente(b,c);
		}
		
	}
	public static int[] filtro (int[] a, int k){
		return filtro(a,k,0,a.length);
	}
	public static int[] filtro(int[] a, int k, int l, int r){
		if(r-l == 1){
			if(a[l]<k){
				return new int[]{a[l]};
			}
			return new int[]{};
		}else{
			int m = (l+r)/2;
			int[] b = filtro(a,k,l,m);
			int[] c = filtro(a,k,m,r);
			return appendEquivalente(b,c);
		}
	}
	
	public static int[] appendEquivalente(int[] a, int[] b) {
		int[] c = new int[a.length+b.length];
		
		int i = 0;
		while (i < a.length) { // scrive a in c
		  c[i] = a[i];
		  i = i + 1;
		}
		
		i = 0;
		while (i < b.length) { // scrive b in c
		  c[a.length+i] = b[i];
		  i = i + 1;
		}
		
		return c;
	  }  
	}
 	
	
	
	
	
// }