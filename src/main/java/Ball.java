/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Ball {
   public final int radius;
   public Point center;
   public Vec v;

    public Ball(int r, Point c, int magnitude) {
        radius = r;
        center = c;
        v = Vec.getRandomVec(magnitude);
    }
    
    public Ball(int r, Point c, Vec vector) {
        radius = r;
        center = c;
        v = vector;
    }
    
    public Point getBottomLeft() {
        Point p = new Point(center.x - radius, center.y - radius);
        return p;
    }
}
