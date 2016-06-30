package ro.teamnet.zerotohero.oop.graphicshape;

public class Circles {

    public double getAreaPub(){

        Circle circle = new Circle();
        return circle.area();
    }

    public void getAreaDef(){
        Circle circle = new Circle();
        circle.fillColor();
        circle.fillColor(10);
        circle.fillColor(10.0f);
    }
}
