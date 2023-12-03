import java.io.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';

    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
            if (peek == '\n')
                line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;
            case '(':
                peek = ' ';
                return Token.lpt;
            case ')':
                peek = ' ';
                return Token.rpt;
            case '[':
                peek = ' ';
                return Token.lpq;
            case ']':
                peek = ' ';
                return Token.rpq;
            case '{':
                peek = ' ';
                return Token.lpg;
            case '}':
                peek = ' ';
                return Token.rpg;
            case '+':
                peek = ' ';
                return Token.plus;
            case '-':
                peek = ' ';
                return Token.minus;
            case '*':
                peek = ' ';
                return Token.mult;
            case '/':
                
                readch(br);

                /**
                 * In questo caso stiamo cercando di capire se il prossimo carattere è 
                 *  // --> Commento
                 *  /* -> inizio commento
                 *  / --> Divisore
                 */
                if(peek == '/'){
                   while(peek!='\n'){
                    readch(br);
                   }
                }else if(peek == '*'){
                    int state = 0;
                    do{
                        switch(state){
                            case 0:
                                if(peek == '*'){
                                    state = 1;
                                }
                                break;
                            case 1:
                                if(peek == '/'){
                                    state = 2;
                                }
                                break;
                            
                        }
                        readch(br);
                    }while(peek != (char) -1 && state != 2);
                }else{
                   // peek = ' ';
                    return Token.div;
                }
                return this.lexical_scan(br);





            case ';':
                peek = ' ';
                return Token.semicolon;
            case ',':
                peek = ' ';
                return Token.comma;
            // ... gestire i casi di ( ) [ ] { } + - * / ; , ... //

            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }
                // ... gestire i casi di || < > <= >= == <> ... //
            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                }else{
                     System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }
            case '<':
                readch(br);
               // System.out.println("Carattere successivo: "+ (int) peek);
                if (peek == '>') {
                    peek = ' ';
                    return Word.ne;
                } else if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } else if (peek > '0' && peek < '9') {
                    peek = ' ';
                    return Word.lt;
                }else{
                     System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }
            case '>':
                char temp = peek;
                readch(br);

                /*BufferedReader brTemp = br;
                char temp1 = ' ';
                
                try {
                    temp1 = (char) brTemp.read();
                
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                } else if (peek > '0' && peek < '9') {
                    //peek = temp;
                    return Word.gt;
                }else{
                     System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }
            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                }else{
                     System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }
            case (char) -1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {

                    // ... gestire il caso degli identificatori e delle parole chiave //
                    String s = ""+peek;
                    readch(br);
                    int index = -1;
                    int c=0;
                    while (Character.isLetter(peek) == true || Character.isDigit(peek) == true || peek == '_') {
                        s = s + peek;
                        if(peek == '_'){
                            c = index;
                        }
                        c = c + 1;
                        readch(br);
                    }
                    if(peek == -1){
                        s = s.substring(0, s.length()-1);
                    }
                    switch (s) {
                        case "assign":
                            return Word.assign;
                        case "if":
                            return Word.iftok;
                        case "else":
                            return Word.elsetok;
                        case "do":
                            return Word.dotok;
                        case "for":
                            return Word.fortok;
                        case "begin":
                            return Word.begin;
                        case "end":
                            return Word.end;
                        case "print":
                            return Word.print;
                        case "read":
                            return Word.read;
                        case "to":
                            return Word.to;
                        default:
                            boolean flag = false;
                            if(index != -1){
                                if(s.charAt(index+1)!='_'){
                                    return null;
                                }   
                            }
                            return new Word(Tag.ID,s);

                    }


                } else if (Character.isDigit(peek)) {
                    String a = Character.toString(peek);
                    readch(br);
                    while( Character.isDigit(peek) == true ){
                        a = a + peek;
                        readch(br);
                    }
                    if(Character.isLetter(peek)==true){
                        return null;
                    }
                    return new NumberTok(Tag.NUM, Integer.parseInt(a));


                    
                            
                    

                } else {
                    System.err.println("Erroneous character: "
                            + peek);
                    return null;
                }
        }

    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "prova.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
