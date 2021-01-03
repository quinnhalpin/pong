
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Vec {
    private final double degrees;
    private final double magnitude;

    /**
     * @param d from 0 to 360
     * @param m from 0 to n
     */
    public Vec(double d, double m) {
        degrees = Math.floorMod((int)d, 360);
        magnitude = m;
    }

    public double getDegrees() {
        return degrees;
    }

    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public String toString() {
        return "Deg:" + degrees + " Mag:" + magnitude;
    }
    
    public Point step(Point p) {
        return new Point((int) (p.x+getXComp()), (int) (p.y+getYComp()));
    }
    
    public static Vec getRandomVec(int m) {
        Double d = new Random().nextDouble()*360;
        return new Vec(d, m);
    }
    
    public boolean isDown() {
        return degrees >= 180;
    }
    
    public boolean isRight() {
        return degrees <= 90 || degrees >= 270;
    }
    
    public Vec refractHorizontally() {
        double deg = degrees;
        boolean isDown = isDown();        
        boolean isRight = isRight();

        double change = 90;
        if (deg == 90 || deg == 270) {
            change = 180;
        }
        else if (isRight && !isDown) {
            change = -2*deg;
        }
        else if (!isRight && !isDown) {
            change = 180-deg + 90-(deg % 90);
        }
        else if (isRight && isDown) {
            change = 2*(360-deg);
        }
        else {
            change = -(180-2*(270-deg));
        }
        Double newTheta = (deg + change) % 360;
        return new Vec(newTheta, magnitude);
    }
    
    public Vec refractVertically() {
        double deg = degrees;
        boolean isDown = isDown();        
        boolean isRight = isRight();

        double change = 90;
        if (deg % 180 == 0) {
            change = 180;
        }
        else if (isRight && !isDown) {
            // Q1
            change = 2*(90-deg);
        }
        else if (!isRight && !isDown) {
            // Q2
            change = -2*(90-(180-deg));
        }
        else if (isRight && isDown) {
            // Q4
            change = -2*(90-(360-deg));
        }
        else {
            // Q3
            change = 2*(90-(deg-180));
        }
        Double newTheta = (deg + change) % 360;
        return new Vec(newTheta, magnitude);
    }
    
    
    public static Vec add(Vec first, Vec second) {
        if (first.magnitude == 0) {
            return new Vec(second.degrees, second.magnitude);
        }
        if (second.magnitude == 0) {
            return new Vec(first.degrees, first.magnitude);
        }
        
        double x = first.getXComp() + second.getXComp();
        double y = first.getYComp() + second.getYComp();
        Double theta = Math.atan(y/x);
        Double degrees = Math.toDegrees(theta);
        Double magnitude = Math.sqrt(x*x + y*y);
        return new Vec(degrees, magnitude);
    }
    
    public double getXComp() {
        Double theta = Math.toRadians(degrees);
        double x = magnitude*Math.cos(theta);
        return x;
    }
    
    public double getYComp() {
        Double theta = Math.toRadians(degrees);
        double y =magnitude*Math.sin(theta);
        return y;
    }
    
    public Vec scale(int scalar) {
        return new Vec(degrees, magnitude*scalar);
    }
    
    /**
     * Return
     * @param v
     * @param pos
     * @param x
     * @return -1 if v will not intercept x=x
     */
    public static int numTimeUnitsToXIntercept(Vec v, Point pos, int x) {
        double numerator = x-pos.x;
        double denominator = v.getXComp();
        int timeUnits = (int) (numerator/denominator);
        return (timeUnits >= 0) ? timeUnits : -1;
    }
    
    /**
     * Return
     * @param v
     * @param pos
     * @param x
     * @return -1 if y is negative
     */
    public static int yAtXIntercept(Vec v, Point pos, int x) {
        int t = Vec.numTimeUnitsToXIntercept(v, pos, x);
        int firstComp = (int) v.getYComp()*t;
        int y = firstComp + pos.y;
        return (y >= 0) ? y : -1;
    }
}
