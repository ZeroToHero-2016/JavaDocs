package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Circle extends Shape {

    private int xPos, yPos,radius;

    public Circle() {
        this.xPos=10;
        this.yPos=15;
        this.radius=20;
    }
    public Circle(int x){
        this.xPos=x;
    }
    public Circle(int x,int y){
        this.xPos=x;
        this.yPos=y;
    }
    public Circle(int x,int y, int radius){
        this.xPos=x;
        this.yPos=y;
        this.radius=radius;
    }

    @Override
    public String toString() {
        String a= "center=("+xPos+","+yPos+") and radius="+radius;
        return  a;
    }

    @Override
    public double area(){
        return Math.PI * Math.pow(radius,2) ;
    }

    public void fillColour(){
        System.out.println(super.color);
    }
    public void fillColour(int color){
        super.color=color;
        System.out.println("The circle color is now 2");
    }
    public void fillColour(float saturation){
        super.saturation=saturation;
    }

}
