public class LibStringArray{
    public static void main(String[] args){
        String msg = "radar";
        System.out.println(palindroma(msg, 0));
    }

   public static boolean palindroma(String msg, int i){
        if(i==msg.length()/2){
            return true;
        }else{
            boolean flag = palindroma(msg, i+1);
            flag = true;
            if(!(flag && msg.charAt(i)==msg.charAt(msg.length()-i-1))){
                flag = false;
            }
            return flag;

        }
        
   }
    public static String subString(String msg, int i,int b){
        String a="";
        if(i==b){
            return a;
        }else{
            a = subString(msg, i+1, b);
            a = a+msg.charAt(i);
        }
        return a;
        
    }



















    public static String toString(int[] a){
        return toString(a,a.length);
    }
    public static String toString(int[] a, int i){
        if(i==0){
            return "";
        }else{
            String msg = toString(a,i-1);
            if(i==a.length){
                msg=msg+a[i-1];
            }else{
                msg=msg+a[i-1]+", ";
            }
            return msg;
        }
    }
    public static String toStringVar(int[] a){
        return toStringVar(a,a.length);
    }
    public static String toStringVar(int[] a, int i){
        if(i==0){
            return "";
        }else{
            String msg = toStringVar(a,i-1);
            msg = msg+a[i-1];
            if(i==a.length){
                msg = msg+".";
            }else{
                msg = msg+", ";
            }
            return msg;
        }
    }
    /*public static String toString(int[] a){
        int i=0;
        String msg="";
        while(i<a.length){
            if(i==a.length-1){
                msg=msg+a[i];
            }else{
                msg=msg+a[i]+", ";
            }
            
            i=i+1;
        }
        return msg;
    }*/
}