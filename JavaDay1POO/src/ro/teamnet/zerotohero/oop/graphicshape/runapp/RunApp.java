package ro.teamnet.zerotohero.oop.graphicshape.runapp;

import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.Immutable;
import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by user on 6/30/2016.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circle= new Circles();
        System.out.println("The default circle area is "+circle.getAreaPub());
        circle.getAreaDef();

        //Canvas canvas= new Canvas();

        Shape shape = new Circle(10);
        System.out.println(shape.area());

        ShapeBehaviour shapebehav = new Square(10);
        System.out.println(shapebehav.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        Immutable im = new Immutable();
        System.out.println(im.getX()+" "+im.getY());

    }
}
