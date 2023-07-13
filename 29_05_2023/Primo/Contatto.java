package Primo;
//Contatto.java
public class Contatto implements Comparable<Contatto>{
    //un contatto e' la coppia di una persona e il suo indirizzo email
    //costruttore a 2 argomenti, metodi get e set
    private String nome;
    private String email;

    public Contatto(String nome, String email) {
        this.nome = nome; 
        this.email = email;
    }

    // metodi get
    public  String getNome() {
        return nome;
    }
    public  String getEmail() {
        return email;
    }

    // metodi set
    public void setNome(String n){
        nome = n;
    }
    public void setEmail(String e){
        email = e;
    }

    // stampa le informazioni del contatto 
    public void scriviOutput() {
        System.out.println(" - " + nome + " : " + email);
    }

    @Override
    public int compareTo(Contatto a) {
        int flag = -1;
        if(nome.compareTo(a.getNome())>0){
            flag = 1;
        }else if(nome.compareTo(a.getNome())==0){
            flag = 0;
        }
        return flag;
    }
}
