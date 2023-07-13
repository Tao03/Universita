public class Contatto{
    private String nome,email;
    public Contatto(String nome, String email){
        this.nome = nome;
        this.email = email;
    }
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String toString(){
        return "Contatto, Nome: "+this.nome+" Email: "+this.email;
    }
}