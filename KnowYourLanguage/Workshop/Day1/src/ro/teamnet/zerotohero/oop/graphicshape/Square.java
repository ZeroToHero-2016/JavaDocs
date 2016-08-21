package ro.teamnet.zerotohero.oop.graphicshape;

public class Square extends Shape {

    private int side;

    public void shape(int side){
        this.side = side;
    }

    public double area(){
        return side * side;
    }
}
