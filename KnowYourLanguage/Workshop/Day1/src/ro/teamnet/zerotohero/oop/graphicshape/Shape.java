package ro.teamnet.zerotohero.oop.graphicshape;

public class Shape extends AbstractShape implements ShapeBehaviour {

    protected int color;
    protected float saturation;
/*
    public int get_color(){
        return this.color;
    }

    public float get_saturation(){
        return this.saturation;
    }
*/
    public void set_color(int color){
        this.color = color;
    }

    public void set_saturation(int saturation){
        this.saturation = saturation;
    }

    public double area(){
        return 0.0;
    }

}
