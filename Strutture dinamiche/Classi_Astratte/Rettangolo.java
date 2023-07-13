package Classi_Astratte;

public class Rettangolo extends Figura {
    private double altezza;
    private double base;
    public Rettangolo(double h, double base){
        this.base = base;
        this.altezza = h;
    }
    public double getAltezza() {
        return altezza;
    }
    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }
    public double getBase() {
        return base;
    }
    public void setBase(double base) {
        this.base = base;
    }

    public double area(){
        return this.base * this.altezza;
    }
    public double perimetro(){
        return (this.base + this.altezza)*2;
    }
    
}
