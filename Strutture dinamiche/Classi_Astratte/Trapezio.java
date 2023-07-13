package Classi_Astratte;

public class Trapezio  extends Poligono{
    private double baseMaggiore;
    private double baseMinore;
    private double altezza;
    public Trapezio(int lati, double baseMaggiore, double baseMinore, double altezza) {
        super(lati);
        this.baseMaggiore = baseMaggiore;
        this.baseMinore = baseMinore;
        this.altezza = altezza;
    }
    public double getBaseMaggiore() {
        return baseMaggiore;
    }
    public void setBaseMaggiore(double baseMaggiore) {
        this.baseMaggiore = baseMaggiore;
    }
    public double getBaseMinore() {
        return baseMinore;
    }
    public void setBaseMinore(double baseMinore) {
        this.baseMinore = baseMinore;
    }
    public double getAltezza() {
        return altezza;
    }
    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }
    public double area(){
        return ((this.baseMaggiore + this.baseMinore) * altezza)/2;
    }
    public double perimetro(){
        return 1;
    }
}
