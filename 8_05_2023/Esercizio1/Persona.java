package Esercizio1;
public class Persona {
    private String nome;
    private String cognome;
    private int eta;
    public Persona(String nome, String cognome, int eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public int getEta() {
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    public boolean equals(Persona p){
        return p.nome.compareTo(this.nome) == 0 && p.cognome.compareTo(this.cognome) == 0 && p.eta == eta;
    }
    public String toString(){
        return "Nome: "+this.nome+"\nCognome: "+this.cognome+"\nEta: "+this.eta+"\n";
    }
    
}
