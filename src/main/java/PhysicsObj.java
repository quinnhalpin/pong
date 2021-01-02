/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class PhysicsObj {
    protected Point pos;
    protected Vec vel;
    protected Vec acc;
    private final double maxAbsVel;
    private final double maxAbsAcc;

    public PhysicsObj(double v, double a) {
        maxAbsVel = v;
        maxAbsAcc = a;
	pos = new Point(0, 0);
	vel = new Vec(0, 0);
	acc = new Vec(0, 0);
    }

    public Point getPos() {
	return pos;
    }

    public void setPos(int x, int y) {
	pos = new Point(x, y);
    }

    public Vec getVel() {
	return vel;
    }

    private void setVel(Vec v) {
        vel = new Vec(v.getDegrees(), Math.min(maxAbsVel, v.getMagnitude()));
    }

    public Vec getAcc() {
	return acc;
    }

    public void setAcc(Vec a) {
	acc = new Vec(a.getDegrees(), Math.min(maxAbsAcc, a.getMagnitude()));
    }
    
    public void step() {
        Vec newVel = Vec.add(vel, acc);
        setVel(newVel);
        pos = vel.step(pos);
    }
}
