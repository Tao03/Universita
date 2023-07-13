public class ImmutablePoint {
    double x = 0.0;
    double y = 0.0;
    public ImmutablePoint(double x, double y){
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
    public void translateY (double y){
        this.y = this.y+ y;
    }
    public double distance(MutablePoint p){
        double f = (p.getX()-this.x)*(p.getX()-this.x) + (p.getY()-this.y)*(p.getY()-this.y);
        System.out.println(f);
        return Math.sqrt(f);
    }
    public void rotate(double angle){
        this.x = (this.x*Math.cos(angle)-this.y*Math.sin(angle));
        this.y = (this.x*Math.sin(angle)-this.y*Math.cos(angle));
    }
    public String toString(){
        return "Punto, cordinata x: "+this.getX()+ " cordinata y: "+this.getY();
    }
}
