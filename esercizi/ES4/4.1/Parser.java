import java.io.*;

public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF)
                move();
        } else
            error("syntax error");
    }

    public void start() {
        int expr_val;
        expr_val = expr();
        match(Tag.EOF);
    }

    private int expr() {
        /*switch(look.tag){
            case '(':
                match('(');
                term();
                exprp();
                break;
            case Tag.NUM:
                match(Tag.NUM);
                term();
                exprp();
                break;

        }*/
       int term_value = term();
       return exprp(term_value);
    }

    private int exprp(int term_value) {
        int exprp_value;
        switch (look.tag) {
            case '+':
                match('+');
                term();
                exprp_value =  exprp()+term_value;
                break;
            case '-':
                match('-');
                term();
                exprp();
                exprp_value = exprp()-term_value;
            case ')':
            case -1:
                break;
            default:
                error("Errore in exprp");
        }
    }

    private int term() {
        int fact_value = fact();
        termp(fact_value);

    }

    private int termp(int fact_value1) {
        int termp_value=0;
        switch(look.tag){
            case '*':
                match('*');
                int fact_value = fact();
                termp_value = fact_value1 + fact_value; 
                termp(termp_value);
                break;
            case '/':
                match('/');
                int fact_value2 = fact();
                termp_value = fact_value1 + fact_value2; 
                termp(termp_value);
            case ')':
            case '+':
            case '-':
            case -1:
                
                break;
            default:
                error("Errore in termp");
        }
        return termp_value;
    }

    private int fact() {
        int value = 0;
        switch(look.tag){
            case '(':
                match('(');
                expr();
                match(')');
                break;
            case Tag.NUM:
            NumberTok a = (NumberTok) look;
            match(Tag.NUM);
                value= a.lexeme;
                break;
            default:
                error("Errore in fact");
        }
        return value;
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "prova.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}