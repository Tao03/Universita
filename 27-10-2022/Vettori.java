public class Vettori{
    public static void main (String[] args){
        int[] array = {1,20,30,40,50,60};
        int flag = ricercaDiUnValoreCoVariante(array,60,array.length-1);
        System.out.println(flag);
    }
    public static boolean ricercaLineareControVariante(int[] x, int e){
        boolean esiste = (x!=null);
            if(esiste){
                esiste = ricercaLineareControVariante(x,e,0);
            }
        
        return esiste;
    }
	public static int ricercaDiUnValoreControVariante(int[] x, int e, int i){
		int c = -1;
		if(i==x.length){
			c=-1;
		}else{
			c = ricercaDiUnValoreControVariante(x,e,i+1);
			if(x[i]==e){
				c=i;
			}
		}
		return c;	
	}
	public static int ricercaDiUnValoreCoVariante(int[] x, int e, int i){
		int c = -1;
		if(i==0){
			if(x[i]==e){
				c=i;
			}
		}else{
			c = ricercaDiUnValoreCoVariante(x,e,i-1);
			if(x[i]==e){
				c=i;
			}
		}
		return c;	
	}



	public static boolean ricercaLineareCoVariante(int[] x, int e){
        boolean esiste = (x!=null);
            if(esiste){
                esiste = ricercaLineareCoVariante(x,e,x.length-1);
            }
        
        return esiste;
    }




	public static void azzeraValoriPariCoVariante(int[] x, int i){
		if(i==0){
			return;
		}else{
			azzeraValoriPariCoVariante(x,i-1);
			if(x[i]%2 == 0){
				x[i]=0;
			}
			
		}
		return;
	}
    public static void azzeraValoriPariControVariante(int[] x, int i){
        if(i==x.length){
            return;
        }else{
            azzeraValoriPariControVariante(x,i+1);
            if(x[i]%2 == 0){
                x[i]=0;
            }
        }
        return;
    }


	public static boolean ricercaLineareCoVariante(int[] x, int e, int i){
		boolean flag;
		if(i==0){
            flag= false;
		}else{
			flag = ricercaLineareCoVariante(x,e,i-1);
			if(x[i]==e){
				flag = false;
			}
			
		}
		return flag;
		
		
	}


    public static boolean ricercaLineareControVariante(int[] x, int e, int i){
        /**
         * L'intervallo è: [i,x,length) siccome ogni volta che richiamo la funzione, 
         * i aumenta e di conseguenza l'intervallo pure
         */
        boolean flag=false;
        if(i==x.length){
            flag=false;
        }else{
            flag = ricercaLineareCoVariante(x,e,i+1);
            /**
             * dice true o false a seconda che e appartenga ai valori di indici [i+1...x.length)
             */
            if(x[i]==e){
                flag=true;
                return flag;
            }
            /*
             * return (x[i]==e) || ricercaLineare(x,e,i+1) ==> è un'alternativa per evitare 
             * la riga 15 il costrutto della riga 19
             */
        }
        return flag;
    }
}