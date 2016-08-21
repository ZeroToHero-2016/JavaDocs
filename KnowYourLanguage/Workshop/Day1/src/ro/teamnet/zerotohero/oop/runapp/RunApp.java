package ro.teamnet.zerotohero.oop.runapp;

import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.graphicshape.Circle;
import ro.teamnet.zerotohero.oop.graphicshape.Circles;
import ro.teamnet.zerotohero.oop.graphicshape.Shape;

public class RunApp {
    public static void main(String[] args) {
        Circles circles = new Circles();
        System.out.print("Default circle area: " + circles.getAreaPub());

        circles.getAreaDef();

        Shape circle = new Circle(10);
    }
}
