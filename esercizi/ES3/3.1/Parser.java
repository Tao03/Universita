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
        expr();
        match(Tag.EOF);
    }

    private void expr() {
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
       term();
       exprp();
    }

    private void exprp() {
        switch (look.tag) {
            case '+':
                match('+');
                term();
                exprp();
                break;
            case '-':
                match('-');
                term();
                exprp();
            case ')':
            case -1:
                break;
            default:
                error("Errore in exprp");
        }
    }

    private void term() {
        fact();
        termp();

    }

    private void termp() {
        switch(look.tag){
            case '*':
                match('*');
                fact();
                termp();
                break;
            case '/':
                match('/');
                fact();
                termp();
            case ')':
            case '+':
            case '-':
            case -1:
                break;
            default:
                error("Errore in termp");
        }
    }

    private void fact() {
        switch(look.tag){
            case '(':
                match('(');
                expr();
                match(')');
                break;
            case Tag.NUM:
                match(Tag.NUM);
                break;
            default:
                error("Errore in fact");
        }
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