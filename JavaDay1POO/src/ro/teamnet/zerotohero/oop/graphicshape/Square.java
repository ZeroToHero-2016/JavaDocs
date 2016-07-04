package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Square extends Shape {
    protected int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double area(){
        return side*side;
    }
}
