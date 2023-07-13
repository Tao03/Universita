public class MutableTriangle{
    private MutablePoint a;
    private MutablePoint b;
    private MutablePoint c;

    public MutableTriangle(MutablePoint a, MutablePoint b, MutablePoint c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public MutablePoint getA(){
        return this.a;
    }
    public MutablePoint getB(){
        return this.b;
    }
    public MutablePoint getC(){
        return this.c;
    }
    public void translate(double dx, double dy){
        a.translateX(dx);
        b.translateX(dx);
        c.translateX(dx);
        a.translateY(dy);
        b.translateY(dy);
        c.translateY(dy);
    }
    public double area(){
        double area = (this.a.getX()*(this.b.getY()-this.c.getY())+this.b.getX()*(this.c.getY()-this.a.getY())+this.c.getX()*(this.a.getY()-this.b.getY()));
        area = area/2;
        return (area<0?-area:area);
    }
    public double perimeter(){
        double ab = distanza(this.a, this.b);
        double bc = distanza(this.b, this.c);
        double ca = distanza(this.c, this.a);
        return ab+bc+ca;
    }
    public void  rotate(double angle){
        this.a.rotate(angle);
        this.b.rotate(angle);
        this.c.rotate(angle);
    }
    private double distanza(MutablePoint a, MutablePoint b){
        double x = (a.getX()-b.getX())*(a.getX()-b.getX());
        double y = (a.getY()-b.getY())*(a.getY()-b.getY());
        return Math.sqrt(x+y);
    }
    public String toString(){
        String msg =  "Triangolo con:\n ";
        msg = msg + this.a.toString()+"\n";
        msg = msg + this.b.toString()+"\n";
        msg = msg + this.c.toString();
        return msg;
        
    }
    

}