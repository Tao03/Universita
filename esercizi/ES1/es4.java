public class es4 {

    public static boolean scan(String s)
    {
	int state = 0;
	int i = 0;

	while (state >= 0 && i < s.length()) {
	    final char ch = s.charAt(i++);

	    switch (state) {
	    case 0:
           if(ch == '.'){
                state = 4;
           }else if(ch > '0' && ch < '9'){
                state = 1;
           }else if(ch == 'e'){
                state = -1;
           }else if(ch == '+' || ch == '-'){
                state = 2;
           }
		break;

	    case 1:
           if(ch == '.'){
            state = 4;
           }else if(ch == 'e'){
            state = 6;
           }else if(ch == '+' || ch == '-'){
            state = -1;
           }
		break;

	    case 2:
            if(ch == '+' || ch == '-' || ch == 'e'){
                state = -1;
            }else if( ch > '0' && ch < '9'){
                state = 1;
            }
		break;

        case 4:
            if(ch > '0' && ch < '9'){
                state = 5;
            }else if(ch == '+' || ch == '-'  || ch == '.'){
                state = -1;
            }
            
        case 5:
            if(ch == 'e'){
                state = 6;
            }else if(ch == '+' || ch == '-'  || ch == '.'){
                state = -1;
            }
            break;
        case 6:
            if(ch == '+' || ch == '-'){
                state = 7;
            }else if(ch > '0' && ch <'9'){
                state = 8;
            }else if(ch == '.'){
                state = 4;
            }else if(ch == 'e'){
                state = -1;
            }
            break;
        case 7:
            if(ch == '.'){
                state = 4;
            }else if(ch == '+' || ch == '-' || ch == 'e'){
                state = -1;
            }else if(ch > '0' && ch < '9'){
                state = 8;
            }
            break;
        case 8:
            if(ch == '+' || ch == '-' || ch == 'e' || ch == '.'){
                state = -1;
            }
            /*else if(ch == '.'){
                state = 7;
            }*/
	    
	    }



        System.out.println(state);
	}
	return state == 1 || state == 5 || state == 8;
    }

    public static void main(String[] args)
    {
	System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }

}
