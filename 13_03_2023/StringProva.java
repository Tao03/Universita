public class StringProva{
    public String longest(String[] strings){
        assert (strings.length>0 && strings!=null): "Errore: vettore di stringhe vuoto oppure null";
        return (strings.length>0 && strings!=null)?longest(strings,0):" ";

    }
    public String concateAll(String[] strings){
        assert (strings.length>0 && strings!=null): "Errore: vettore di stringhe vuoto oppure null";
        return(strings.length>0 && strings!=null)?concateAll(strings,strings.length):"";
    }
    public String trim(String strings){
        assert (strings!=null && strings.length()>0) : "Errore: stringa null oppure vuota";
        int right = trimRight(strings);
        int left = trimLeft(strings);
        left = (left<0?strings.length():left);
        right = (right<0?0:right);
        System.out.println("Right: "+ right);
        System.out.println("Left: "+left);
        return strings.substring(left+1,right);
    }
    private int trimRight(String strings){
        boolean uscita = false;
        int index = -1;
        for(int i = strings.length()-1;i>1 && !uscita;i--){
            System.out.println(strings.charAt(i));
            if(strings.charAt(i)==' ' && strings.charAt(i - 1) != ' ' ){
                uscita = true;
                index = i;
            }
        }
        return index;
    }
    private int trimLeft(String strings){
        boolean uscita = false;
        int index = -1;
        for(int i = 0;i<strings.length()-1 && !uscita;i++){
            if(strings.charAt(i)==' ' && strings.charAt(i + 1) != ' ' ){
                uscita = true;
                index = i;
            }
        }
        return index;
    }
    private String concateAll(String[] strings,int i){
        if(i == 0){
            return "";
        }else{
            String sum = concateAll(strings,i - 1);
            return sum + strings[i - 1];
        }
    }
    private String longest(String[] strings, int i){
        if(i == strings.length){
            return "";
        }else{
            String max = longest(strings, i + 1);
            if(max.length()<strings[i].length()){
                return strings[i];
            }
            return max;
        }
        
    }
}