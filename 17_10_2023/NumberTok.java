public class NumberTok extends Token {
	public int lexeme;
    public NumberTok(int tag, int s) { super(tag); lexeme=s; }
    public String toString() { return "<" + tag + ", " + lexeme + ">"; }
    public static final Word
	zero = new Word(Tag.NUM, "0"),
	one = new Word(Tag.NUM, "1"),
	two = new Word(Tag.NUM, "2"),
	three = new Word(Tag.NUM, "3"),
	four = new Word(Tag.NUM, "4"),
	five = new Word(Tag.NUM, "5"),
	six = new Word(Tag.NUM, "6"),
	seven = new Word(Tag.NUM, "7"),
	eight = new Word(Tag.NUM, "8"),
	nine = new Word(Tag.NUM, "9");
	
}
