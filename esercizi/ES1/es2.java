public class es2
{
    public static boolean scan(String s)
    {
	int state = 0;
	int i = 0;

	while (state >= 0 && i < s.length()) {
	    final char ch = s.charAt(i++);

	    switch (state) {
	    case 0:
		if (ch > 'A' && ch < 'Z' || ch > 'a' && ch  < 'z')
		    state = 2;
		else if (ch == '-')
		    state = 1;
		else
		    state = -1;
		break;

	    case 1:
		if (ch == '-')
		    state = 1;
		else if (ch > 'A' && ch < 'Z' || ch > 'a' && ch  < 'z' || ch > '1' && ch < '9')
		    state = 2;
		else
		    state = -1;
		break;

	    case 2:
		if (ch > 'A' && ch < 'Z' || ch > 'a' && ch  < 'z' || ch > '1' && ch < '9' && ch == '-')
		    state = 2;
		break;
            
	    
	    }
        System.out.println(state);
	}
	return state == 2;
    }

    public static void main(String[] args)
    {
	System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
}