import java.io.*;
import java.util.*;

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
            case '/':
                peek = ' ';
                return Token.div;
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
                    while (Character.isLetter(peek) == true ) {
                        s = s + peek;
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
                            return (new Token(Tag.EOF));

                    }

                } else if (Character.isDigit(peek)) {

                    switch (peek) {
                        case '0':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 0);
                        case '1':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 1);
                        case '2':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 2);
                        case '3':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 3);
                        case '4':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 4);
                        case '5':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 5);
                        case '6':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 6);
                        case '7':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 7);
                        case '8':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 8);
                        case '9':
                            peek = ' ';
                            return new NumberTok(Tag.NUM, 9);
                        default:
                            peek = ' ';
                            return new Token(Tag.EOF);
                    }

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
