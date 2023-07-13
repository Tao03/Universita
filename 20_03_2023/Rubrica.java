public class Rubrica {
    private Contatto[] contatti;
    private int size;
    public Rubrica(int dimensione){
        assert dimensione >0: "Inserire un valore maggiore di 0!";
        this.contatti = new Contatto[dimensione];
        this.size = 0;
    }
    public boolean piena(){
        return (this.size == this.contatti.length);
    }
    public boolean aggiungi(String nome, String email){
        assert (!presente(nome)): "IL contatto è già presente";
        if(this.size == this.contatti.length){
            radoppia();
            System.out.println("Spazio insufficiente");
        }
        this.contatti[this.size] = new Contatto(nome, email);
        this.size++;
        ordina();
        return true;
    }
    public void ordina(){
        for(int i = 0; i<this.size;i++){
            for (int j = i + 1; j < this.size; j++) {
                if(contatti[i].getNome().charAt(0)>this.contatti[j].getNome().charAt(0)){
                    Contatto c = this.contatti[i];
                    this.contatti[i] = this.contatti[j];
                    this.contatti[j] = c;
                }
            }
        }
    }
    public void radoppia(){
        Contatto[] vettore = new Contatto[this.contatti.length*2];
        for (int i = 0; i < this.contatti.length; i++) {
            
            vettore[i] = this.contatti[i];
        }
        this.contatti = vettore;
    }
    public int cercaContatto(String n){
        int index = -1;
        for (int i = 0; i < contatti.length-1 && index == -1; i++) {
            index = (n.charAt(0)>contatti[i].getNome().charAt(i) && n.charAt(0)<contatti[i + 1].getNome().charAt(i)?i:-1);
        }
        return index;
    }
    public int find(String nome){
        int indice = -1;
        boolean uscita = false;
        for (int i = 0; i < this.size && !false; i++) {
            uscita = contatti[i].getNome() == nome;
            indice = (uscita?i:-1);
        }
        return indice;
    }
    public boolean rimuovi(String nome){
        int indice = find(nome);
        assert (indice!=-1):" Mi dispiace ma il contatto non esiste";
        for (int i = indice; i < this.size; i++) {
            this.contatti[i] = this.contatti[i + 1];
        }
        this.size--;
        return true;
    }
    public boolean presente(String nome){
        boolean trovato = false;
        for (int i = 0; i < this.size && !trovato; i++) {
            trovato = contatti[i].getNome() == nome;
        }
        return trovato;
    }
    public String toString(){
        String msg = "";
        for (int i = 0; i < this.size; i++) {
            msg = msg + "Nome: "+this.contatti[i].getNome()+ "\n"+"Email: "+this.contatti[i].getEmail()+"\n";
        }
        return msg;
    }
    public static void main(String[] args) {
        Rubrica r = new Rubrica(10);
        r.aggiungi("tao", "ale@gmail.com");
        r.aggiungi("ale", "ale@gmail.com");
        System.out.println(r.toString());
    }
}
