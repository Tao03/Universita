public class es5 {
    public static boolean scan(String s)
    {
	int state = 0;
	int i = 0;

	while (state >= 0 && i < s.length()) {
	    final char ch = s.charAt(i++);

	    switch (state) {
	    case 0:
           if(ch == '/'){
            state = 1;
           }else if(ch == '*' || ch == 'a'){
            state = -1;
           }
		break;

	    case 1:
           if(ch == 'a'){
            state = -1;
           }else if(ch == '*'){
            state = 2;
           }
		break;

	    case 2:
            if(ch == '*'){
                state = 3;
            }else if(ch == '/'){
                state = -1;
            }
		break;

        case 3:
            if(ch == '/'){
                state = 4;
            }else if(ch == 'a'){
                state = 2;
            }
            break;
        case 4:
        if(ch == '*'){
            state = -1;
        }else if(ch == 'a'){
            state = 2;
        }
            break;
        
	    
	    }
        System.out.println(state);
	}
	return state == 4;
    }

    public static void main(String[] args)
    {
	System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }

}