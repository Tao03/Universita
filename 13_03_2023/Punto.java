import java.lang.Math;
public class Punto{
    double x = 0.0;
    double y = 0.0;
    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void translateX (double x){
        this.x = this.x + x;
    }
    public static Punto translateX (double x){
        return new Punto(this.x+x,this.y);
    }
    public Punto translateY (double y){
        return new Punto(this.x,this.y+y);
    }
    public double distance(Punto p){
        double f = (p.getX()-this.x)*(p.getX()-this.x) + (p.getY()-this.y)*(p.getY()-this.y);
        System.out.println(f);
        return Math.sqrt(f);
    }

}