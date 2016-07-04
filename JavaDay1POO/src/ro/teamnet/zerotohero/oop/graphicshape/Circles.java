package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Circles {

    public double getAreaPub(){
        Circle circle = new Circle();
        return circle.area();
    }
    public void getAreaDef(){
        Circle circle= new Circle();
        circle.fillColour();
        circle.fillColour(20);
        circle.fillColour(20f);
    }
}
