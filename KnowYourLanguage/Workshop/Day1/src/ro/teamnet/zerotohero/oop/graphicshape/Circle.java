package ro.teamnet.zerotohero.oop.graphicshape;

import static java.lang.Math.PI;

public class Circle extends Shape{

    private int xPos;
    private int yPos;
    private int radius;

    private void circle(){
        this.xPos = 0;
        this.yPos = 0;
        this.radius = 0;
    }

    public Circle(int xPos){
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public double area(){
        return PI * (radius * radius);
    }

    public String toString(){
        return "center = (" + this.xPos + "," + this.yPos + ") and radius = " + this.radius;
    }

    public void fillColor(){
        System.out.println(super.color);
    }

    public void fillColor(int color){
        super.color = color;
        System.out.println("The circle color is now 2");
    }

    public void fillColor(float saturation){
        super.saturation = saturation;
    }
}
