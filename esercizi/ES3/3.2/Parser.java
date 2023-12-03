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
       /*  switch (look.tag) {
            case Tag.ASSIGN:
                match(Tag.ASSIGN);
                statList();
                match(-1);
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                statList();
                match(-1);
                break;
            case Tag.READ:
                match(Tag.READ);
                statList();
                match(-1);
                break;
            case Tag.FOR:
                match(Tag.FOR);
                statList();
                match(-1);
                break;
            case Tag.IF:
                match(Tag.IF);
                statList();
                match(-1);
                break;
            case '{':
                match('{');
                statList();
                match(-1);
                break;
        
            default:
                error("Errore all'inizio nello start");
                break;
        }*/
        statList();
        match(Tag.EOF);
    }
    public void statList(){
        switch (look.tag) {
            case Tag.ASSIGN:
            case Tag.PRINT:
            case Tag.READ:
            case Tag.FOR:
            case Tag.IF:
            case '{':
                stat();
                statListP();
                break;
            
            default:
                error("Errore nello statList");
                break;
        }
    }
    public void statListP(){
        switch (look.tag) {
            case ';':
                match(';');
                stat();
                statListP();
                break;
            case '}':
            case -1:
                break;
            default:
                error("Errore nello statListP");
                break;
        }
    }
    public void stat(){
        switch (look.tag) {
            case Tag.ASSIGN:
                match(Tag.ASSIGN);
                assignList();
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                match('(');
                exprList();
                match(')');
                break;
            case Tag.READ:
                match(Tag.READ);
                match('(');
                idList();
                match(')');
                break;
            case Tag.FOR:
                match(Tag.FOR);
                match('(');
                S();
                bexpr();
                match(')');
                match(Tag.DO);
                stat();
                match(')');
                break;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                bexpr();
                match(')');
                
                stat();


                Tao();
                match(Tag.END);
                match(')');
                break;
            case '{':
                statList();
                match('}');
                
                break;
            default:
                error("Errore nello stat");
        }
    }
    public void assignList(){
        switch (look.tag) {
            case '[':
                match('[');
                expr();
                match(Tag.TO);
                idList();
                match(']');
                assignListP();
                break;
        
            default:
                error("Errore nel assignList");
        }
    }
    public void assignListP(){
        switch (look.tag) {
            case '[':
                match('[');
                expr();
                match(Tag.TO);
                idList();
                match(']');
                assignListP();
                break;
            case -1:
            case ';':
            case ',':
            case '}':
            case Tag.ELSE:
            case Tag.END:
            break;
        
            default:
                error("Errore nel assignListP");
                break;
        }
    }
    public void bexpr(){
        switch (look.tag) {
            case Tag.RELOP:
                match(Tag.RELOP);
                expr();
                expr();
                break;
        
            default:
                error("Errore in bexpr");
                break;
        }
    }
    public void exprList(){
        switch (look.tag) {
            case '+':
            case '-':
            case '*':
            case '/':
            case Tag.NUM:
            case Tag.ID:
                expr();
                exprListP();
                break;
        
            default:
                error("Errore in exprList");;
        }
    }
    public void idList(){
        switch (look.tag) {
            case Tag.ID:
                match(Tag.ID);
                idListP();
                break;
            
            default:
                error("Errore in idList");
                break;
        }

    }
    public void idListP(){
        switch (look.tag) {
            case ',':
                match(',');
                match(Tag.ID);
                idListP();
                break;
            case ')':
            case ']':
                break;
            default:
                error("Errore in idListP");
                break;
        }
    }
    public void expr(){
        switch (look.tag) {
            case '+':
                match('+');
                match('(');
                exprList();
                match(')');
                break;
            case '*':
                match('*');
                match('(');
                exprList();
                match(')');
                break;
            case '-':
                match('-');
                expr();
                expr();
                break;
            case '/':
                match('/');
                expr();
                expr();
                break;
            case Tag.NUM:
                match(Tag.NUM);
                break;
            case Tag.ID:
                match(Tag.ID);
                break;
            default:
                error("Errore in expr");
                break;
        }
    }
    public void exprListP(){
        switch (look.tag) {
            case ',':
                match(',');
                expr();
                exprListP();
                break;
            case ')':
                break;
            default:
                error("Errore in exprListP");
                break;
        }
    }
    public void S(){
        switch (look.tag) {
            case Tag.ID:
                match(Tag.ID);
                match(Tag.INIT);
                expr();
                match(';');
                
                break;
            case Tag.RELOP:
                break;
            default:
                error("Errore nella stringa S");;
        }
    }
    public void Tao(){
        switch (look.tag) {
            case Tag.ELSE:
                match(Tag.ELSE);
                stat();
                break;
            case Tag.END:
                break;
            default:
                error("Errore nella stringa Tao");
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