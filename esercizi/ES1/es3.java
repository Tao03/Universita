public class es3
{
    public static boolean scan(String s)
    {
	int state = 0;
	int i = 0;

    while(state >= 0 && i < s.length()){
        final char ch = s.charAt(i++);

        switch(state){
            case 0:
                if(ch >= 'a' && ch <= 'z' ){
                    state = -1;
                }else if(ch%2==0 && ch>=48 && ch<=57){
                    state = 1;
                }else if(ch%2==1 && ch>=48 && ch<=57){
                    state = 2;
                }
            break;

            case 1:
                if(ch >= 'l' && ch <= 'z'){
                    state = -1;
                }else if(ch >= 'a' && ch <= 'k'){
                    state = 3;
                }else if(ch%2==1 && ch>=48 && ch<=57){
                    state = 2;
                }
            break;
            
            case 2:
                if(ch >= 'l' && ch <= 'z'){
                    state = 3;
                }else if(ch >= 'a' && ch <= 'k'){
                    state = -1;
                }else if(((int) ch)%2==0){
                    state = 1;
                }
            break;
                
            case 3:
                if(ch>='0' && ch<='9'){
                    state = -1;
                }
                break;
        
        }

        
    }
    return state == 3;
    }

    public static void main(String[] args)
    {
	System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
}