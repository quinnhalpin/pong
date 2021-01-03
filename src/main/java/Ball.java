/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Ball extends PhysicsObj {
   public final int radius;
//   public Point center;
//   public Vec v;

    public Ball(int r, Point c, int magnitude) {
        // ex: super(25, 3, 0.5, 0.05);
        super(25, 3, 0.5, 0.05);
        radius = r;
        pos = c;
        vel = Vec.getRandomVec(magnitude);
    }
    
    public Ball(int r, Point c, Vec vector) {
        super(25, 3, 0.5, 0.05);
        radius = r;
        pos = c;
        vel = vector;
    }
    
    public void setCenter(Point c) {
        pos = c;
    }
    
    public Point getCenter() {
        return pos;
    }
    
    public Point getBottomLeft() {
        Point p = new Point(pos.x - radius, pos.y - radius);
        return p;
    }
}
